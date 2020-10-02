# アップローダーサンプルアプリ

## 環境変数

- STORAGE_CONNECTION_STRING
  - ストレージ アカウントへの接続文字列
  - 例:
    - `DefaultEndpointsProtocol=https;AccountName=storageaccount;AccountKey=XXXXXXXXX==;EndpointSuffix=core.windows.net`
- STORAGE_CONTAINER_NAME
  - ファイル保存先のコンテナ名
  - 例: 
    - `container`
- DATABASE_URL
  - データベースの接続文字列
  - 例: 
    - `jdbc:sqlserver://db.database.windows.net:1433;database=uploader;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;`
- DATABASE_USERNAME
  - データベースのユーザー名
  - 例:
    - `dbuser`
- DATABASE_PASSWORD
  - データベースのパスワード
  - 例:
    - `password`
- SERVER_PORT
  - アプリケーションを起動するサーバーのポート番号
  - 例:
    - `80`
- APPLICATIONINSIGHTS_CONNECTION_STRING
  - Application Insights の接続文字列
  - 例:
    - `InstrumentationKey=XXXX-XXXX-XXXX;IngestionEndpoint=https://japaneast-0.in.applicationinsights.azure.com/`

## API

- OpenAPI
  - `GET http://<server>.<port>/api/v3/api-docs`
- Swagger UI
  - `GET http://<server>.<port>/api/swagger-ui.html`
  - ブラウザで開くこと。API Management 経由だと開けないので注意
- ヘルスエンドポイント
  - `GET http://<server>.<port>/api/actuator/health`
  - 備考: アクチュエーターを使用してます
    - https://spring.pleiades.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-health
- ファイルのアップロード
  - `POST http://<server>.<port>/api/upload`
  - curl の読み出し例
    - `curl.exe -X POST "http://10.240.0.14/api/upload" -H  "accept: */*" -H  "Content-Type: multipart/form-data" -F "file=@187233.jpg;type=image/jpeg"`
- ファイルのダウンロード
  - `GET http://<server>.<port>/api/download/<blobName>`

