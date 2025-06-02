import os
import shutil
from category_map import category_map

source_dir = "/Users/xinning/Desktop/LC"
target_dir = "/Users/xinning/Desktop/LC_Organized"

for filename in os.listdir(source_dir):
    if not filename.endswith(".java") and not filename.endswith(".py"):
        continue

    problem_name = filename.split('.')[0] + '.' + '.'.join(filename.split('.')[1:-1])
    extension = filename.split('.')[-1]

    category = category_map.get(problem_name, "Uncategorized")
    problem_folder = os.path.join(target_dir, category, problem_name)

    os.makedirs(problem_folder, exist_ok=True)

    src_path = os.path.join(source_dir, filename)
    dst_path = os.path.join(problem_folder, filename)
    shutil.copy2(src_path, dst_path)

    # 创建 notes.md 如果还没有
    notes_path = os.path.join(problem_folder, "notes.md")
    if not os.path.exists(notes_path):
        with open(notes_path, "w") as f:
            f.write(f"# Notes for {problem_name}\n\n- ")

print("completed organisation of LeetCode problems.")
