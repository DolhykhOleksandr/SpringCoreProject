# SpringApplication

This SpringCoreProject is an educational project. The main
objectives include user registration, sending a token to the mail to confirm the email, display all users after sign in.

JPA Repository is primarily utilized for database interaction. Some methods
involve JPQL queries. The services provide opportunities for sending messages to users, to register and search the
users, to confirm the tokens (at the same time, it's worth noting, if user didn't pass the confirmation in within a
certain number of minutes - the token becomes invalid).

In order to debug the code was added the logging and all log's
information will be saved in a file. The services are working with entities, the controllers - with models,
that's why before sending information to the controllers, entities are converted to DTO.

Web-controllers navigate between pages, perform checks, and pass information to the services for processing. The access
to these controllers are passing via url using REST.

HTML documents display information to users: Thymeleaf is used for dynamic pages, JS and CSS with HTML are used for
displaying the table of our entities. Spring Security, along with BCrypt
encryption, ensures secure access, and Liquibase is employed for DDL and DML queries.

The application features comprehensive unit and integration test coverage. Profiles differentiate between H2DB (used in
development) and PostgreSQL (used in production). Exception handling for user information is
profile-dependent, based on the selected environment.

Project setup details, such as MailDev, Hibernate,Liquibase and DBMS, are specified in application*.yml files in the "
resources" directory, depending on the selected profile.

## Workflow

1. Create database in PostgreSQL DBMS
2. Change username and password in application-prod.yaml(PostgresSQL) or use application-dev.yaml(h2-console)
3. Add and run the MailDev in Docker ($ docker run -p 1080:1080 -p 1025:1025 maildev/maildev) ou use my shell script.
4. Run project
5. After the registration go to localhost:1080 and confirm the email.
6. Login in.
7. After authentication in Postman  you get a permit for CRUD requests.

```shell script
docker run -p 1080:1080 -p 1025:1025 maildev/maildev
```

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
* Liquibase
* PostgreSQL
* H2DB
* Thymeleaf
* HTML
* JavaScript
* CSS
* Lombok
* SLF4J
* Jackson
* GIT
* REST
* JUnit, Mockito
* AOP
* MailDev
* Swagger

