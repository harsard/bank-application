
#### First time execute
```
sdk use java 21.0.6-tem && mvn spring-boot:run
```

####
```
mvn spring-boot:run
```
#### Run Services (Order Matters)

1️⃣	Config Server-8888
2️⃣	Eureka Serve-8761
3️⃣	API Gateway-8080
4️⃣	Account Service-8081
5️⃣	Customer Service-8082

cd config-server
mvn clean spring-boot:run

cd ../eureka-server
mvn clean spring-boot:run

cd ../api-gateway
mvn clean spring-boot:run

cd ../account-service
mvn clean spring-boot:run

cd ../customer-service
mvn clean spring-boot:run


Service	Endpoint
✅ Account Service	Get All Accounts	curl -X GET http://localhost:8080/account/accounts
✅ Account Service	Get Account by ID	curl -X GET http://localhost:8080/account/accounts/1
✅ Customer Service	Get All Customers	curl -X GET http://localhost:8080/customer/customers
✅ Customer Service	Get Customer by ID	curl -X GET http://localhost:8080/customer/customers/1
✅ Eureka	Check Discovery	Open http://localhost:8761 in browser
✅ Config Server	Check Config	curl -X GET http://localhost:8888/account-service/dev

All Accounts	curl -X GET http://localhost:8080/account/accounts
All Customers	curl -X GET http://localhost:8080/customer/customers
Single Account	curl -X GET http://localhost:8080/account/accounts/1

### Cloud config refresh -customer service
curl -X POST http://localhost:8082/api/v1/customers/actuator/refresh