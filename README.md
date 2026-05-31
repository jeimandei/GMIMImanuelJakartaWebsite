# Church Platform

## Architecture Overview

This repository contains a Java 21, Spring Boot 3 multi-module church platform with microservices-oriented module boundaries:

- church-gateway-service (public MVC frontend)
- church-auth-service (authentication and registration)
- church-user-service (user CRUD, roles, status)
- church-cms-service (CMS pages CRUD)
- church-event-service (events CRUD and upcoming endpoint)
- church-media-service (sermons, livestreams, gallery)
- church-interaction-service (prayer requests, contact messages, announcements)
- church-admin-service (admin MVC dashboard)
- church-common (shared entities, enums, exception handling)

## Prerequisites

- Java 21
- Maven 3.9+
- Oracle Database 19c+

## Database Setup

1. Create Oracle user/schema (for example `CHURCH_APP`).
2. Apply schema:
   - `db/schema.sql`
3. Apply seed data:
   - `db/seed.sql`

## Service Ports

- gateway: 8080
- auth: 8081
- user: 8082
- cms: 8083
- event: 8084
- media: 8085
- interaction: 8086
- admin: 8087

## Build and Test

```bash
mvn clean verify
```

## Run Example Service

```bash
mvn -pl church-user-service spring-boot:run
```

## Notes

- Package naming follows `com.jeimandei.imanuelbytes.*`.
- Passwords use BCrypt.
- YouTube livestream embed validation enforces `https://www.youtube.com/embed/...` format.
