# Etapa 1: Compilación (Build)
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compila el proyecto saltándose las pruebas para que sea más rápido
RUN mvn clean package -DskipTests

# Etapa 2: Producción (Run)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copia el archivo .jar generado en la Etapa 1
COPY --from=build /app/target/*.jar app.jar
# Expone el puerto 8080 (donde corre Spring Boot)
EXPOSE 8080
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
