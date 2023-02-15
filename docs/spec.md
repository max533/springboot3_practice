## 需求說明

用戶於登入系統請求成功後，Server 產生 JWT token 回傳給客戶端，且之後的CRUD Request 都需帶入 Token 取得 Response Data

## 開發規格

- Language：Java / Javascript / Python / Go 擇一
- OpenAPI：Swagger
- Database：PostgreSQL

## API List

### POST `/auth/login`

Login 用戶登入系統並在Response Header Authorization取得token

- **Request**

  Body

  ```json
  {
      "account":"{{account}}",
      "password":"{{password}}"
  }
  ```

- **Success Response**

  Headers

  | Field         | Type   | Description |
  | ------------- | ------ | ----------- |
  | Authorization | string | JWT token   |

  Status Code

  - 200 `成功`

  Content

  ```json
  {
      "account":"使用者帳號",
      "name":"使用者名稱"
  }
  ```

- **Error Response**

  Status Code

  - 401 `帳號密碼錯誤`

  - 500 `系統錯誤`

### POST `/goods/add`

新增商品

- **Request**

  Header

  | Field         | Type   | Description |
  | ------------- | ------ | ----------- |
  | Authorization | string | JWT token   |

  Body

  ```Json
  {
      "goods_name":"{{goods_name}}"
  }
  ```

- **Success Response**

  Status Code

  - 200 `成功`

  Content

  ```json
  {
      "_id":"商品代號",
      "goods_name":"商品名稱"
  }
  ```

- **Error Response**

  Status Code

  - 403 `權限不足`

  - 500 `系統錯誤`

### GET `/goods`

取得所有商品

- **Request**

  Header

  | Field         | Type   | Description |
  | ------------- | ------ | ----------- |
  | Authorization | string | JWT token   |

- **Success Response**

  Status Code

  - 200 `成功`

  Content

  ```json
  [
      {
          "_id":"商品代號1",
          "goods_name":"商品名稱1"
      },
      {
          "_id":"商品代號2",
          "goods_name":"商品名稱2"
      }
  ]
  ```



- **Error Response**

  Status Code

  - 403 `權限不足`

  - 500 `系統錯誤`

### GET `/goods/{id}`

取得指定商品

- **Request**

  Header

  | Field         | Type   | Description |
  | ------------- | ------ | ----------- |
  | Authorization | string | JWT token   |

  Variable

  | Field | Type   | Description |
  | ----- | ------ | ----------- |
  | id    | string | 商品代號    |

- **Success Response**

  Status Code

  - 200 `成功`

  Content

  ```json
  {
      "_id":"商品代號",
      "goods_name":"商品名稱"
  }
  ```

- **Error Response**

  Status Code

  - 403 `權限不足`

  - 500 `系統錯誤`

### PUT `/goods/{id}`

更新商品

- **Request**

  Header

  | Field         | Type   | Description |
  | ------------- | ------ | ----------- |
  | Authorization | string | JWT token   |

  Variable

  | Field | Type   | Description |
  | ----- | ------ | ----------- |
  | id    | string | 商品代號    |

  Body

  ```json
  {
  	"goods_name":"商品名稱"
  }
  ```

- **Success Response**

  Status Code

  - 200 `成功`

  Content

  ```json
  {
      "_id":"商品代號",
      "goods_name":"商品名稱"
  }
  ```

- **Error Response**

  Status Code

  - 403 `權限不足`

  - 500 `系統錯誤`

### DELETE `/goods/{id}`

刪除商品

- **Request**

  Header

  | Field         | Type   | Description |
  | ------------- | ------ | ----------- |
  | Authorization | string | JWT token   |

  Variable

  | Field | Type   | Description |
  | ----- | ------ | ----------- |
  | id    | string | 商品代號    |

- **Success Response**

  Status Code

  - 200 `成功`

- **Error Response**

  Status Code

  - 403 `權限不足`

  - 500 `系統錯誤`

## 檔案規格

systemUser

| 英文名稱 | 中文名稱 | 資料型態 | 長度 | 小數位數 | 允許空值 | P-Key | 欄位說明 |
| -------- | :------: | -------: | ---: | -------: | :------: | :---: | :------- |
| _id      |          |     uuid |      |          |    N     |   P   | UUID     |
| account  |          |  varchar |  128 |          |          |   U   | 帳號     |
| password |          |  varchar |  128 |          |          |       | 密碼     |

goods

| 英文名稱 | 中文名稱 | 資料型態 | 長度 | 小數位數 | 允許空值 | P-Key | 欄位說明 |
| -------- | :------: | -------: | ---: | -------: | :------: | :---: | :------- |
| _id      |          |     uuid |      |          |    N     |   P   | UUID     |
| name     |          |  varchar |  128 |          |    N     |       | 商品名稱 |

## 繳交文件

- 程式碼
- 專案啟動方式
- 資料庫table的create script和data的insert script
