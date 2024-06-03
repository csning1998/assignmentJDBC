# Java Web 專案環境設定指引

This guide will help you set up a Java Web project in IntelliJ IDEA Ultimate and use Docker Compose to create a PostgreSQL database and Tomcat server.

## Prerequisite

* 已安裝 Git
* 已安裝 Docker Desktop
* 已安裝 IntelliJ IDEA Ultimate

## 步驟

### 1. Git Clone 專案

```bash
git clone <您的專案 Git 儲存庫網址>
```

### 2. IntelliJ IDEA Ultimate 設定

1. **開啟專案：** 在 IntelliJ IDEA Ultimate 中，選擇 "Open"，然後選取您剛剛 clone 下來的專案資料夾。

2. **設定自動構建 Artifact：**
    - 打開 "Project Structure" (File -> Project Structure 或 Ctrl+Alt+Shift+S)。
    - 在 "Project Settings" 下選擇 "Artifacts"。
    - 點擊 "+" 按鈕，選擇 "Web Application: Archive" -> "For 'assignmentJDBC:war exploded'"。
    - 在 "Output Layout" 標籤頁中，確認您的 WAR 檔案輸出路徑正確。
    - 在 "Build on make" 選項打勾，這樣每次編譯專案時都會自動構建 WAR 檔案。

3. **啟用 Docker 整合：**
    - 打開 "Settings/Preferences" (File -> Settings... 或 Ctrl+Alt+S)。
    - 在 "Plugins" 中搜尋 "Docker"，確認 Docker 外掛程式已啟用。
    - 在 "Build, Execution, Deployment" -> "Docker" 中設定 Docker 連線。

### 3. 執行 Docker Compose

1. **開啟 Services 工具視窗：** 在 IntelliJ IDEA Ultimate 中，選擇 "View" -> "Tool Windows" -> "Services"。

2. **啟動服務：**
    - 點擊 "+" 按鈕，選擇 "Docker Compose"。
    - 將終端機指令路徑移至 `docker-compose.yml` 檔案處。
    - 使用 `docker compose up` 以建立 Docker 環境
    - 每次要啟動網頁伺服器時，僅需點擊 "Play" 按鈕啟動服務。

### 4. 執行 PostgreSQL SQL Query Console

1. **開啟 PostgreSQL Console：**
    - 在 "Services" 工具視窗中，展開 "Containers"。
    - 在 PostgreSQL 容器上右鍵點擊，選擇 "Open PostgreSQL Console"。

2. **建立資料庫：**
    - 輸入以下 SQL命令建立 `javadb` 資料庫：
      ```sql
      CREATE DATABASE javadb;
      ```

3. **建立資料表：** 命令建立 `users` 資料表：
     ```sql
     CREATE TABLE users (
         _ID SERIAL PRIMARY KEY,
         employee_email VARCHAR(50) NOT NULL,
         employee_name VARCHAR(50) NOT NULL,
         employee_id VARCHAR(10) UNIQUE NOT NULL,
         hashedPWD VARCHAR(255) NOT NULL,
         signature VARCHAR(255) NOT NULL
     ); 
     ```
   
4. 命令插入範例資料：
    ```sql
      INSERT INTO users (employee_email, employee_name, employee_id, hashedPWD, signature)
      VALUES ('[已移除電子郵件地址]', 'John Doe', '1234567890', 'hashed_password_here', 'signature_here');
      ```

## 驗證

- **訪問 Web 應用：** 在瀏覽器中輸入 `http://localhost:8081` (或您在 `docker-compose.yml` 中設定的埠號)，確認是否能看到您的 Web 應用程式。
- **訪問 PostgreSQL：** 使用 `psql` 或其他 PostgreSQL 客戶端工具，連接到 `localhost:5433` (或您設定的埠號)，確認是否能訪問 `javadb` 資料庫和 `users` 資料表。

## 其他說明

- **修改埠號：** 如果您需要修改埠號，請在 `docker-compose.yml` 中相應地修改。
- **Dockerfile：** 如果您的專案需要自定義 Docker 映像檔，請在專案根目錄下建立一個 `Dockerfile`。
- **Nginx 配置：** 如果您使用 Nginx 作為反向代理，請在 `./nginx` 目錄下建立一個 `dev.conf` 檔案來配置 Nginx。

希望這份指引能幫助您順利設定 Java Web 專案的開發環境！
