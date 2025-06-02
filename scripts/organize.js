const fs = require('fs');
const path = require('path');

const categories = JSON.parse(fs.readFileSync('categories.json'));
const baseDir = '.';

for (const file of fs.readdirSync(baseDir)) {
  // 只处理 .java/.py/.js 等代码文件，跳过文件夹和无扩展名
  if (!file.match(/\.(java|py|js)$/)) continue;

  const nameWithoutExt = file.replace(/\.[^.]+$/, '');
  const category = categories[nameWithoutExt];

  if (!category) continue; // 没有分类则跳过

  const categoryDir = path.join(baseDir, category);
  if (!fs.existsSync(categoryDir)) {
    fs.mkdirSync(categoryDir);
  }

  const oldPath = path.join(baseDir, file);
  const newPath = path.join(categoryDir, file);

  // 如果文件已在对应目录则跳过
  if (oldPath !== newPath) {
    fs.renameSync(oldPath, newPath);
    console.log(`Moved ${file} -> ${category}/`);
  }
}
