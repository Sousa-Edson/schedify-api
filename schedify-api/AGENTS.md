# Schedify API - Project Context for AI Assistants

## Stack
- Java 21, Spring Boot 4.0.6, Spring Framework 7.x
- Maven wrapper (./mvnw)
- PostgreSQL (prod/dev), H2 (test)
- Flyway (migrações), Hibernate (JPA)
- Lombok, MapStruct 1.5.5.Final
- Springdoc OpenAPI 3.0.3 (Swagger)

## Java Setup
- SDK: `/home/edson/.sdkman/candidates/java/21.0.8-tem`
- Ativar antes de Maven: `source /home/edson/.sdkman/bin/sdkman-init.sh && sdk use java 21.0.8-tem`

## Build & Test
```bash
./mvnw clean test           # testa com H2 (profile test)
./mvnw clean compile        # compila sem testar
./mvnw clean install -DskipTests
```

## Run
```bash
./mvnw spring-boot:run                              # profile default (ddl-auto=update)
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod  # profile prod (Flyway + validate)
```

## Profiles
| Profile | Banco   | ddl-auto | Flyway |
|---------|---------|----------|--------|
| default | Postgres | update   | disabled |
| prod    | Postgres | validate | enabled |
| dev     | Postgres | update   | disabled |
| test    | H2      | create-drop | disabled |

## Project Structure (Clean Architecture)
```
domain/          → Entidades, serviços de domínio, ports
application/     → Casos de uso (UseCases)
infrastructure/  → JPA entities, repositórios, config, mappers
interfaces/      → REST controllers, DTOs
```

## Conventions
- Tudo em português (classes, variáveis, endpoints, migrations)
- MapStruct para conversão DTO ↔ Domain ↔ Entity
- DayOfWeek armazenado como Integer no banco (via DisponibilidadeEntityMapper)
- Step de slots: 15 minutos (configurável em application.yml)

## Flyway
- Migrations em `src/main/resources/db/migration/`
- Nomear: `V<numero>__<descricao>.sql`
- Usar `CREATE TABLE IF NOT EXISTS` para evitar conflito com Hibernate
- Rollback de migration falha é automático (transação por arquivo)
- Reparar histórico: `DELETE FROM flyway_schema_history WHERE success = false;`
- Ou via Maven: `./mvnw flyway:repair`

## Swagger
- http://localhost:8080/swagger-ui.html
- OpenAPI: http://localhost:8080/api-docs

## Troubleshooting
- "Attempt to recreate a file" (MapStruct): Build → Rebuild Project no IntelliJ
- Flyway não roda: verificar se `spring-boot-flyway` está no pom.xml (obrigatório no SB 4.x)
- "missing table" no profile prod: dropar `flyway_schema_history` e reaplicar
