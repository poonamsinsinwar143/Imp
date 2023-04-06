# Imp
Insurance Management Plateform


This is a REST based Insurance Management Platform developed in Spring Boot and Java.

Springboot version: 2.7.10
JDK version: jdk-11

Default db: H2(In Memory)

Steps to run:
mvn clean install
mvn spring-boot:run

Swagger url:
http://localhost:8080/swagger-ui/index.html#/

API Security: Basic Auth
Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ==


Features(Implemented through RESTful APIs):


1.Create client:-
This api is used to create a new Client.


curl --location 'http://localhost:8080/api/clients' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ==' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Mohan Kumar",
    "dob":"1993-08-16",
    "address":"G104, Shree Vardhman Mantra",
    "city":"Gurgaon",
    "state":"Haryana",
    "country":"India",
    "zipcode":"122102",
    "email":"bharat@gmail.com",
    "phone":"6756764567"
}'


2.Get client:-
This api is used to get a client.


curl --location 'http://localhost:8080/api/clients/1' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ=='


3.Get all client:-
This api is used to get all clients.


curl --location 'http://localhost:8080/api/clients' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ=='


4.Delete client:-
This api is used to delete a client.


curl --location --request DELETE 'http://localhost:8080/api/clients/6' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ=='


5.Update client:-
This api is used to update a client.


curl --location --request PUT 'http://localhost:8080/api/clients/6' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ==' \
--header 'Content-Type: application/json' \
--data '{
    "id":6,
    "city":"shri nagar",
    "state":"kashmir",
    "zipcode":"875629"
}'


6.Get policy types:-
This api is used to get all policy type.


curl --location 'http://localhost:8080/api/policytypes' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ=='


7.Create insurance policy:-
This api is used to create a new insurance policy.


curl --location 'http://localhost:8080/api/policies' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ==' \
--header 'Content-Type: application/json' \
--data '{
    "coverageAmount":90000.0,
    "premium":6000.0,
    "policyTypeId":4,
    "clientId":4,
    "startDate":"2020-03-03",
    "endDate":"2021-03-03"
}'


8.Get insurance policy:-
This api is used to get insurance policy.


curl --location 'http://localhost:8080/api/policies/2' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ==' \
--data ''


9.Get all insurance policy:
This api is used to get all insurance policy.


curl --location 'http://localhost:8080/api/policies' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ=='


10.Update insurance policy:-
This api is used to update a insurance policy.


curl --location --request PUT 'http://localhost:8080/api/policies/3' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ==' \
--header 'Content-Type: application/json' \
--data '{
    "coverageAmount":90000.0,
    "premium":6000.0,
    "policyTypeId":4,
    "clientId":4,
    "startDate":"2020-03-03",
    "endDate":"2021-03-03"
}'


11.Delete insurance policy:-
This api is used to delete a insurance policy.


curl --location --request DELETE 'http://localhost:8080/api/policies/6' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ=='


12.Get all claim status:-
This api is used to get all claim status.


curl --location 'http://localhost:8080/api/claimstatus' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ=='


13.Create claim:-
This api is used to create a new claim.


curl --location 'http://localhost:8080/api/claims' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ==' \
--header 'Content-Type: application/json' \
--data '{
    "claimDate":"2023-04-04",
    "description":"Claim is created.",
    "claimAmount":100000,
    "policyNumber":3
}'


14.Get claim:-
This api is used to get a claim.


curl --location 'http://localhost:8080/api/claims/3' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ=='


15.Get all claim:-
This api is used to get all claims.


curl --location 'http://localhost:8080/api/claims' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ==' \
--header 'Content-Type: application/json' \
--data '{
    "claimDate":"2023-04-04",
    "description":"Claim is created.",
    "claimAmount":100000,
    "policyNumber":3
}'


16.Update claim:-
This api is used to update a claim.


curl --location --request PUT 'http://localhost:8080/api/claims/3' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ==' \
--header 'Content-Type: application/json' \
--data '{
    "claimAmount":50000,
    "claimDate":"2023-04-01",
    "claimStatusId":2,
    "description":"Claim for Health Insurance",
    "policyNumber":4

}'


17.Delete claim :-
This api is used to delete a claim.


curl --location --request DELETE 'http://localhost:8080/api/claims/3' \
--header 'Authorization: Basic dGVzdHVzZXI6RGZ4QDQzIyEqMQ=='
