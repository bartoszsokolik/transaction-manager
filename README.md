### Description
Resource for filtering transactions by given account type and customer id

### Usage
a) Clone repository

b) In root project directory run command `./gradlew clean build`

c) After successful build run `docker-compose up --build`

d) Run in browser: http://localhost:8080/transaction-manager/swagger-ui/
or use postman or curl
```
curl -u user:userpass http://localhost:8080/transaction-manager/api/transactions?account_type=1&customer_id=1
```
There are two users which can be configured via application.yml
- username: admin, password: adminpass
- username: user, password: userpass

e) Tests are run during project build. Tests can be run with command from root project directory `./gradlew clean test`
