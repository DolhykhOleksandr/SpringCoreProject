# SpringApplication

S
This project springCoreDemo as an educational endeavor focusing on the practice of various technologies. The main objectives include user registration, email confirmation via token, and displaying all users post sign-in.

JPA Repository is primarily utilized for database interaction, with specific interfaces extending it. Some methods involve HQL queries. The services handle messaging, user registration, and token confirmation (with a time limit for validity). Logging is incorporated for debugging, and log information is stored in a designated file. Services work with entities, and controllers handle models, necessitating entity-to-DTO conversion before sending information to controllers.

Web controllers facilitate navigation between pages, perform checks, and forward information to services for processing. Access to these controllers occurs via URL using REST-style, enhancing readability. REST controllers provide app functionality to other developers.

HTML documents display information to users, with Thymeleaf used for dynamic pages. Spring Security, along with BCrypt encryption, ensures secure access, and Liquibase is employed for DDL and DML queries.

The application features comprehensive unit and integration test coverage. Profiles differentiate between H2DB (used in development and integration tests) and PostgreSQL (used in production). Exception handling for user information is profile-dependent, based on the selected environment.

Project setup details, such as MailDev, Hibernate, and DBMS, are specified in application*.yml files in the "resources" directory, contingent on the chosen profile.

## Workflow

1. Create database in PostgresSQL DBMS
2. Add and run the MailDev in Docker ($ docker run -p 1080:1080 -p 1025:1025 maildev/maildev)
```shell script
docker run -p 1080:1080 -p 1025:1025 maildev/maildev
```
3. Open the project in IDE
4. Change username and password in application-prod.yaml(PostgresSQL) or use application-dev.yaml(h2-console)
5. Run project
6. After the registration go to localhost:1080 and confirm the email


## Technologies

* Java SE 17
* Maven
* Spring Boot
    - Data JPA | JDBC
    - Web
    - Security
    - Mail
    - Test
* Hibernate
* PostgresSQL
* Liquibase
* H2DB
* Lombok
* Log4j
* Jackson
* Guava
* GIT
* HTML
* REST
* Thymeleaf
* JUnit, Mockito
* MailDev
