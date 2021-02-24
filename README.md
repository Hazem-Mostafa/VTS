# Vaccination Tracking System (VTS)

This is a tutorial project, which demonstrates Microservice using Spring Boot and Spring Cloud

## Functional services

VTS was decomposed into three core microservices. All of them are independently deployable applications, organized around certain business domains.

![Vaccination Tracking System](https://github.com/Hazem-Mostafa/VTS/blob/main/docs/03-Architecture/architecture.png)

#### Clients service
Contains general info about the client such as name, mobile number and email address.

Method	| Path	| Description
------------- | ------------------------- | ------------- 
GET	| /clients	| Get all clients data
POST| /clients	| Create a new client
GET	| /clients/{clientId}	| Get specified client data using its ID


#### Vaccines service
Contains general info about vaccine such as name, dose and repetition count.

Method	| Path	| Description
------------- | ------------------------- | ------------- 
GET	| /vaccines	| Get all vaccines data
POST| /vaccines	| Create a new vaccine
GET	| /vaccines/{vaccineId}	| Get specified vaccine data using its ID


#### Branches service
Contains all the branches related data and the branch related info such as branch store data and branch visits data.

Method	| Path	| Description
------------- | ------------------------- | ------------- 
GET	| /branches	| Get all branches data
POST	| /branches	| Create a new branch
POST	| /branches/store	| Create a new branch store
POST	| /branches/visit	| Create a new branch visit
GET	| /branches/{branchId}	| Get specified branch data using its ID
GET	| /branches/{branchId}/visit/time-slots/{timeSlot}	| Get a branch visit by branch Id and visit time
GET	| /branches/{branchId}/visit/status/{visitStatus}	| Get all visits by branch Id, visit status and visit date range
GET	| /branches/{branchId}/visit/date/{fromDate}	| Get all visits by branch Id, visit status and visit date for one day
GET	| /branches/{branchId}/visit/date/{fromDate}/{toDate}	| Get all visits by branch Id, visit status and visit date range
GET	| /branches/{branchId}/visit/status/{visitStatus}/date/{fromDate}	| Get all visits by branch Id, visit status and visit date for one day
GET	| /branches/{branchId}/visit/status/{visitStatus}/date/{fromDate}/{toDate}	| Get all visits by branch Id, visit status and visit date range
GET	| /branches/{branchId}/time-slots	| Get all available time slots by branch id
GET	| /branches/visit/status/{visitStatus}/date/{fromDate}	| Get all visits by branch Id, visit status and visit date for one day
GET	| /branches/visit/status/{visitStatus}/date/{fromDate}/{toDate}	| Get all visits by branch Id, visit status and visit date range
GET	| /branches/vaccine/{vaccineId}	| Get all branches by vaccine id


#### Notes
- Each microservice has its own database, so there is no way to bypass API and access persistance data directly.
- In this project, I use H2 Database as a primary database for each service.
- Service-to-service communication is quite simplified: microservices talking using only synchronous REST API.


### Config service
In this project, I use `native profile`, which simply loads config files from the local classpath.


### API Gateway
In this project, I use API Gateway architecture pattern. It is a single entry point into the system, used to handle requests by routing them to the appropriate backend service. I have used Spring Cloud Gateway

### Service discovery
I use Netflix Eureka

### Http client
#### Feign
I use Feign Http client for service to service communication


## How to run all the things?
- Make sure to build the project: from the root folder you can run this command `mvn clean package [-DskipTests]`
- Under each service/target folder you will find the generated jar you can run this service using this command `java -jar discovery-server.jar`
- After successfully running all services you can access any of them using the api gateway url followed by the desired functionality as described previously, for example you can list all branch data using `http://localhost:8080/branches/{branchId}`


#### Important endpoints
- http://localhost:8080 - Gateway
- http://localhost:8761 - Eureka Dashboard
- http://localhost:8100/swagger-ui.html - swagger ui for clients service
- http://localhost:8100/h2-console - db console for clients service
- http://localhost:8200/swagger-ui.html - swagger ui for vaccines service
- http://localhost:8200/h2-console - db console for vaccines service
- http://localhost:8300/swagger-ui.html - swagger ui for branches service
- http://localhost:8300/h2-console - db console for branches service