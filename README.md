# user-feedback

## Introduction

This project is used to collect feedback from customers and store in a database and also expose the insights of the feedback.

## Technology Stack

* [Coretto](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/what-is-corretto-17.html) - Amazon OpenJDK 17
* [Gradle](https://gradle.org/) - Build tool
* [Micronaut](https://micronaut.io/) - Microservices framework

## AWS Services

- AWS SQS
- AWS SES
- AWS DYNAMODB
- AWS PARAMETER STORE

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing
purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

This service requires Java version 17 or newer in order to run, and also requires Java annotation processing to be
enabled if you are using an IDE.

Enabling annotation processing in **IntelliJ IDEA** is done by opening
**Preferences->Build, Execution, Deployment -> Compiler > Annotation Processors** and then
selecting **Enable annotation processing**.

### Installing

The service can be built using the following command:

```
./gradlew build
```

Running the service locally is done using the following command:

```
goto doker-user-feedback folder and run "docker compose up" to start local stack

then go  to terraform-user-feedback folder and run terrform

terraform init ( first time)

terraform apply ( create resources)
```

```
./gradlew run -Dmicronaut.environments=local
```
or
```
set environment variable in your IDE run config MICRONAUT_ENVIRONMENTS=local
gradlew run
```
## Running the tests

Unit tests can be run using the following command:

```
./gradlew test
```

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, run the following command:

```
git tag
``` 

## Tips

Upgrade gradle version

```
./gradlew wrapper --gradle-version={versionNumber}
```

## TODO:
- User mapstruct to remove emailId from response to list
- Add filters in list operation with date , score
- Improve the html template in email
- convert email endpoint to scheduler to send email weekly
- Unit tests
## Micronaut 3.2.7 Documentation

- [User Guide](https://docs.micronaut.io/3.2.7/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.2.7/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.2.7/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature assertj documentation

- [https://assertj.github.io/doc/](https://assertj.github.io/doc/)

## Feature management documentation

- [Micronaut Management documentation](https://docs.micronaut.io/latest/guide/index.html#management)

## Feature lombok documentation

- [Micronaut Project Lombok documentation](https://docs.micronaut.io/latest/guide/index.html#lombok)

- [https://projectlombok.org/features/all](https://projectlombok.org/features/all)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature openapi documentation

- [Micronaut OpenAPI Support documentation](https://micronaut-projects.github.io/micronaut-openapi/latest/guide/index.html)

- [https://www.openapis.org](https://www.openapis.org)

## Feature reactor documentation

- [Micronaut Reactor documentation](https://micronaut-projects.github.io/micronaut-reactor/snapshot/guide/index.html)

## Feature mockito documentation

- [https://site.mockito.org](https://site.mockito.org)

## Feature jms-sqs documentation

- [Micronaut AWS SQS JMS Messaging documentation](https://micronaut-projects.github.io/micronaut-jms/snapshot/guide/index.html)

## Feature aws-v2-sdk documentation

- [Micronaut AWS SDK 2.x documentation](https://micronaut-projects.github.io/micronaut-aws/latest/guide/)

- [https://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/welcome.html](https://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/welcome.html)

