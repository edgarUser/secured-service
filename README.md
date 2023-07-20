# secured-service
Adding security layer to basic service

## Table of contents
* [General information](#general-information)
* [Architecture design](#architecture-design)
* [Technologies](#technologies)
* [Setup](#setup)
* [Execution](#execution)
* [Test](#test)
* [References](#reference)

## General information
This service it is being used to set up Spring Security from default to custom, for this version consider the next:
* Default Spring Security configuration (Convention over Configuration)
* Default Basic Authentication flow
* Default Authentication provider
* Default User details services
* Default Password encoder


## Architecture design
The next diagram is the architecture current representation(work in progress)

![](docs/img/secured-service.png)

## Technologies
Project is created with:
* Java (JDK 17)
* Spring Boot (3.1.1)
* Spring Security (6.1.1)

## Setup
Create self-signed Certificate
```shell
openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365
openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "certificate"
```

To download dependencies and build the service use the next:
```shell
./gradlew build
```

## Execution
To start the service use the next:
```shell
./gradlew bootRun
```

## Test
To test the service you can use the next:
### Option 1)
```shell
curl -k -v -u user:$user-pwd-generated https://localhost:8080/greeting
```
### Option 2)
```shell
echo -n user:$user-pwd-generated | base64
curl -k -v -H "Authorization: Basic output-previous-command" https://localhost:8080/greeting
```

## Reference
List of reference used to address this project:
* link