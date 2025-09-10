# --- Etapa 1: Construcción (Build Stage) ---
# Usamos una imagen oficial de Maven con Java 17 para construir el proyecto.
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Establecemos el directorio de trabajo dentro del contenedor.
WORKDIR /app

# Copiamos solo el pom.xml para descargar las dependencias.
# Esto aprovecha el caché de Docker y acelera las futuras construcciones.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código fuente.
COPY src ./src

# Construimos la aplicación y nos saltamos los tests.
RUN mvn package -DskipTests


# --- Etapa 2: Ejecución (Run Stage) ---
# Usamos una imagen de Java 17 mucho más ligera, solo con lo necesario para ejecutar.
FROM openjdk:17-jre-slim

# Establecemos el directorio de trabajo.
WORKDIR /app

# Exponemos el puerto 8080, que es el que Spring Boot usa por defecto.
EXPOSE 8080

# Copiamos el archivo .jar construido en la etapa anterior a nuestra imagen final.
COPY --from=build /app/target/*.jar app.jar

# El comando que se ejecutará cuando el contenedor se inicie.
ENTRYPOINT ["java", "-jar", "app.jar"]