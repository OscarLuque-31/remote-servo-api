# --- Etapa 1: Construcción (Build Stage) ---
# Cambiado para ser más explícito
FROM docker.io/library/maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests


# --- Etapa 2: Ejecución (Run Stage) ---
# Cambiado para ser más explícito
FROM docker.io/library/openjdk:17-jre-slim

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]