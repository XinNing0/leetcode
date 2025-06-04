import os
import requests
import re
import shutil
from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
import time

SOURCE_DIR = "/Users/xinning/Desktop/LC/Problems"
TARGET_DIR = "/Users/xinning/Desktop/LC/Organized"
API_URL = "https://leetcode.com/graphql"

HEADERS = {
    "Content-Type": "application/json",
    "Referer": "https://leetcode.com",
    "User-Agent": "Mozilla/5.0"
}

def get_problem_data(slug):
    query = {
        "operationName": "questionData",
        "variables": {"titleSlug": slug},
        "query": """query questionData($titleSlug: String!) {
            question(titleSlug: $titleSlug) {
                title
                difficulty
                topicTags {
                    name
                }
            }
        }"""
    }
    try:
        res = requests.post(API_URL, json=query, headers=HEADERS)
        if res.status_code == 200:
            return res.json()["data"]["question"]
        else:
            print(f"⚠️ Failed API response for {slug} (status {res.status_code})")
    except Exception as e:
        print(f"❌ Exception for {slug}: {e}")
    return None

def slugify_title(filename):
    name, ext = os.path.splitext(filename)
    if '.' not in name:
        return None, None, ext
    number, slug = name.split('.', 1)
    return number, slug, ext

def sanitize_folder_name(name):
    return re.sub(r'[^\w\- ]', '', name)

def process_file(file_path):
    file = os.path.basename(file_path)
    if not (file.endswith(".java") or file.endswith(".py")):
        return

    number, slug, ext = slugify_title(file)
    if not slug or not number:
        print(f"Skipping: {file} (filename format invalid)")
        return

    print(f"📡 Fetching data for slug: {slug}")
    problem_data = get_problem_data(slug)
    if not problem_data:
        print(f"❌ No data found for {slug}")
        return

    title = problem_data["title"]
    tags = problem_data["topicTags"]
    category = tags[0]["name"] if tags else "Uncategorized"
    category = sanitize_folder_name(category)
    difficulty = problem_data["difficulty"]

    folder_name = f"{number}.{slug}"
    dest_folder = os.path.join(TARGET_DIR, category, folder_name)
    os.makedirs(dest_folder, exist_ok=True)

    # 复制代码文件到 Organized 目录
    dest_code_path = os.path.join(dest_folder, file)
    if not os.path.exists(dest_code_path):
        shutil.copy2(file_path, dest_code_path)
        print(f"📂 Copied code file to: {dest_code_path}")
    else:
        print(f"⏭️ Code file already exists: {dest_code_path}")

    # 生成笔记文件
    base_filename = os.path.splitext(file)[0]
    note_ext = ext.replace('.', '') + ".txt"
    notes_path = os.path.join(dest_folder, f"{base_filename}.{note_ext}")
    if not os.path.exists(notes_path):
        with open(notes_path, "w") as f:
            f.write(f"{title} ({difficulty})\n")
            f.write(f"Tags: {', '.join(tag['name'] for tag in tags)}\n\n")
            f.write("My Notes:\n")
            f.write("- ")
        print(f"📝 Created note: {notes_path}")
    else:
        print(f"⏭️ Note already exists: {notes_path}")

class LCFileHandler(FileSystemEventHandler):
    def on_created(self, event):
        if not event.is_directory:
            print(f"🆕 Detected new file: {event.src_path}")
            process_file(event.src_path)

    def on_modified(self, event):
        if not event.is_directory:
            print(f"✏️ Detected modified file: {event.src_path}")
            process_file(event.src_path)

def main():
    if not os.path.exists(SOURCE_DIR):
        print(f"❌ Source directory {SOURCE_DIR} does not exist!")
        return

    event_handler = LCFileHandler()
    observer = Observer()
    observer.schedule(event_handler, SOURCE_DIR, recursive=False)
    observer.start()
    print(f"🕵️ Watching directory: {SOURCE_DIR} for new/modified .java/.py files...")

    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        observer.stop()
    observer.join()

if __name__ == "__main__":
    main()
