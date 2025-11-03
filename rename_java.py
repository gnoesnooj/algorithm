import os
import re

# 현재 디렉토리 기준으로 탐색
base_dir = '.'

for root, _, files in os.walk(base_dir):
    for file in files:
        if file.endswith('.java'):
            file_path = os.path.join(root, file)

            # --- 1️⃣ 파일명 규칙 적용 ---
            filename_only = file[:-5]            # .java 제거 전
            # 1. 빈칸 → _
            filename_only = filename_only.replace(' ', '_')
            # 2. 맨앞이 숫자면 p 붙이기
            if filename_only[0].isdigit():
                filename_only = 'p' + filename_only

            class_name = filename_only

            # --- 파일 내용 읽기 ---
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()

            # --- 클래스명 수정 ---
            content = re.sub(r'public\s+class\s+\w+', f'public class {class_name}', content)

            # --- 수정된 내용 다시 쓰기 ---
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(content)

            # --- 2️⃣ 파일명 변경 (.java 붙이기 확인) ---
            new_file_name = class_name
            if not new_file_name.endswith('.java'):
                new_file_name += '.java'
            new_file_path = os.path.join(root, new_file_name)

            if new_file_path != file_path:
                os.rename(file_path, new_file_path)
                file_path = new_file_path  # 출력용 업데이트

            print(f"✅ {file_path} → class={class_name}")
