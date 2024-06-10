# 壹、系統實作

### 第一節、目的說明

---

- 根據確認的設計規格進行系統實作，包括分析系統、設計規格及程式碼撰寫，建立軟
  體元件並建立實作規格。

### 第二節、系統實作說明

---

1. **程式語言：**
   - 前端網頁：HTML5、CSS3、JavaScript
   - 後端框架：Java Web Servlet
2. **實作版本：**
   - 診間病患系統 Ver. 0.1
3. **相關軟體：**
   - 軟體編輯：IntelliJ IDEA Ultimate（以下簡稱 IDE）
   - 環境容器：Docker → 包含 **伺服器 Tomcat 9** 與 **資料庫 PostgreSQL**。詳見 `docker-compose.yaml` 檔案說明。
   - 版本控管：Git

### 第三節、系統建置方式

---

1. Git Clone 專案：

    ```powershell
    git clone https://github.com/csning1998/assignmentJDBC.git
    ```

2. **IDE 設定**
   1. **開啟專案：** 在 IDE 中，選擇 "Open"，然後選取稍早 clone 下來的專案資料夾。
   2. **設定自動構建 Artifact：**
      1. 打開 Project Structure （File -> Project Structure 或 Ctrl + Alt + Shift + S）
      2. 在 Project Settings 下選擇 Artifacts。
      3. 點擊 "+" 按鈕，選擇 Web Application: Archive -> For `assignmentJDBC:war exploded`。
      4. 在 Output Layout 標籤頁中，確認專案中的 WAR 檔案輸出路徑正確。
      5. 在 Build on make 選項打勾，這樣每次編譯專案時，都會自動構建 WAR 檔案。如下圖所示：

   3. **啟用 Docker 整合：**
      1. 打開 Settings （File -> Settings -> Plugins 或 Ctrl + Alt + S）。
      2. 在 Plugins 中搜尋 Docker，確認 Docker 外掛程式已啟用。

      3. 在 Build, Execution, Deployment -> "Docker 中設定 Docker 連線，確認下方顯示＂Connection Successful＂。

3. **執行 `Docker-Compose.yaml` 檔案**
   1. 開啟 Services 工具視窗： 在 IDE 中，選擇 View -> Tool Windows -> Services 或使用 Alt + F8。
   2. 啟動服務：
      1. 確認 Docker 已經成功連接上；若未連接上，需要在此設定連接到 [Localhost](http://Localhost) 的 Docker 內。

      2. 將終端機指令路徑移至 `docker-compose.yml` 檔案處，在本例中是專案資料夾的根目錄。
         - 我們使用以下指令以建立 Docker 環境，這樣每次要啟動網頁伺服器時，僅需點擊 Play 按鈕即可啟動服務。

             ```docker
             docker compose up
             ```

         - 初次建立系統時，大約需要 5 到 10 分鐘建立環境。在本案例中，我們將資料庫與伺服器包裝在同一個 Container 裡面，這樣就可以避免受到潛在的本機系統環境的干擾。
         - 在完成系統建立後，我們亦可使用以下指令查看系統運作狀態：

             ```docker
             docker ps
             ```

           我們可以看到底下已經有三個 Image，分別是 資料庫伺服器（Port: 5433:5432）、Debug 阜口（Port: 5005:5005 / 8888:8080）、以及 Tomcat 網頁伺服器（Port: 8081:80）

         - 其他相關於 Docker 的操作，敬請 [**點此**](https://docs.docker.com/?utm_source=docker&utm_medium=inproductad&utm_campaign=20-11nurturecli_docs) 參閱 Docker 官方說明，或使用 `docker --help` 查看相關指令。
4.  **執行 PostgreSQL Query Console**
   1. **開啟 PostgreSQL Console：**
      1. 在 View -> Tool Windows 清單中，點選 Database。
      2. 在 PostgreSQL 容器上選到 postgres，選擇 "Open PostgreSQL Console"。

        
   2. **建立資料庫：**

      我們複製在專案根目錄中的 `buildTablespace.sql` 的內容，複製貼上到 Console 內，完成後點選執行：

       ```sql
       grant all privileges on database postgres to postgres;
       
       create table users
       (
           _id            serial       primary key,
           employee_email varchar(50)  not null,
           employee_name  varchar(50)  not null,
           employee_id    varchar(50)  not null unique,
           hashedpwd      varchar(255) not null,
           signature      text         not null
       );
       
       create table medical_record
       (
           _mid               serial        primary key,
           patient_name       varchar(255)  not null,
           patient_identifier varchar(15)   not null,
           patient_birthdate  date          not null,
           patient_gender     varchar(10)   not null,
           phone_number       varchar(20),
           patient_address    text,
           patient_height     numeric(5, 2) not null,
           patient_weight     numeric(5, 2) not null,
           first_visit_date   date          not null,
           family_history     text          not null,
           personal_history   text          not null,
           medical_record     text          not null
       );
       
       alter table users owner to postgres;
       alter table medical_record owner to postgres;
       ```

   3. 完成後即可在右側看到被建立好的空 Tables：
      - `medical_record` ：存放病患之病歷資料。
      - `users` ：存放使用者資訊。

