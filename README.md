# BasSid API Demo

## 1. 需求說明文件

[需求說明文件](docs/spec.md)

## 2. 開發環境與工具

- OpenJDK 17+

- Maven 3+

- PostgreSQL 14.4

- PGAdmin 4+

- Spring Boot 3.0

## 3. 功能

- Restful API

- Exception Handling

- Spring Security

- JSON Web Tokens (JWT) Authorization

- BCrypt Password Encryption

- Swagger OpenAPI format

## 4. 使用 `docker-compose` 運行服務

1. 解壓縮後，進到資料夾裡面。

    ```bash
    tar -xvzf bassid_demo.tar.gz
    cd bassid_demo
    ```

2. 進行 build image 和 run container

    ```bash
    docker-compose up -d --build
    ```

3. 關閉服務

    ```bash
    docker-compose down -v
    ```

## 5. 使用 `maven` 運行服務 (使用這個方式記得先把 DB 服務透過 `docker-compose` 運行)

P.S 使用 `maven` 運行服務，請先自行安裝 `openJDK` 和 `maven` 等開發工具，版本需求同上面 [提及](#2-開發環境與工具)。

P.S 需要先到 `application.yaml` 把 `spring.datasource.url` 的值改成 `jdbc:postgresql://localhost:5432/bassid_demo_database` ，因為預設環境是使用 `docker-compose` 運行，若想切換回 `docker-compose` 運行，再將 `spring.datasource.url` 的值改為 `jdbc:postgresql://postgres:5432/bassid_demo_database` 即可。

1. 解壓縮後，進到資料夾裡面。

    ```bash
    tar -xvzf bassid_demo.tar.gz
    cd bassid_demo
    ```

2. 清理舊的編譯檔和重新編譯

    ```bash
    mvn clean install
    ```

3. 運行 Spring Boot 服務

    ```bash
    mvn spring-boot:run
    ```

## 6. 檢視 Swagger 文件

1. 打開瀏覽器，直接在網址輸入 `http://{server_ip}:9090/swagger-ui/index.html`

    P.S. server_ip 就是您啟用服務的網址，假設是在本地端運行，則把 `server_ip` 改為 `localhost` 即可。

2. 其中 `Good` 相關 API 執行需要先有 `JWT Token` 的驗證，可以使用 `Authentication` 相關的 `login` API 取得 `JWT Token`，在 Response Header 中的 `Authrization` 標頭，右上角有個鑰匙鎖點開後，把剛剛複製的值貼上即可。

3. 預設建立的使用者登入帳號密碼為

    帳號：`bassid`

    密碼：`password`

## 7. 資料庫 `create-schema` script 與 `insert-data` script

- [create-schema script](src/main/resources/script/sql/ddl/schema.sql)
- [insert-data script](src/main/resources/script/sql/dml/data.sql)
