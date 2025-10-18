# Etapa 1: build do projeto com Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copia o arquivo pom.xml e baixa dependências (melhor cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código fonte do projeto
COPY src ./src

# Compila o projeto e gera o .jar
RUN mvn clean package -DskipTests

# Etapa 2: imagem final leve com o JDK
FROM openjdk:17-jdk-slim

WORKDIR /app
EXPOSE 8080

# Copia o .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Define o ponto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
