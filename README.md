# HMCTS Dev Test Backend

The backend portion of the HMCTS case management system. The frontend can be found at: https://github.com/anonraccooon/hmcts-dev-test-frontend

The technology stack includes Java 21, Spring Boot, and PostgreSQL.

## Build

```bash
./gradlew build
```

## Database

This application requires PostgreSQL. To set up the Database using the psql shell, run the following:
```bash
psql -h localhost -U postgres -p 5432
(Input your superuser password)

postgres=# CREATE USER app_user WITH PASSWORD 'password';
postgres=# CREATE DATABASE hmcts_dev_db OWNER app_user;

```

## Running

Simply run the application file located at src/main/java/uk/gov/hmcts/reform/dev/Application.java
The application runs on **https://localhost:4000**

The frontend must also be running to access the full application

## Endpoints

1. /  -> Landing page
2. GET /tasks -> Returns a list of all tasks currently in the database
3. POST /tasks (RequestBody: Task) -> Validates and creates a new task, saving it in the database
4. GET /tasks/{id} -> Returns the details for one specific task based on the id

## Testing

Unit tests for the application can be found and run at src/test/java/uk/gov/hmcts/reform/dev