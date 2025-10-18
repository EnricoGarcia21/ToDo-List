# Etapa 1: Build do projeto com Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copia o arquivo pom.xml e baixa dependências antes (para cache eficiente)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código-fonte e constrói o .jar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final, leve
FROM openjdk:17-jdk-slim

WORKDIR /app
EXPOSE 8080

# Copia o .jar gerado da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Define o ponto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
