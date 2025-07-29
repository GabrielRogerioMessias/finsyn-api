# Etapa 1: Build do projeto com Maven + Java 17
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copia o POM e baixa dependências para cache eficiente
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código
COPY src ./src

# Compila o projeto e gera o .jar
RUN mvn clean package -DskipTests

# Etapa 2: Executar o .jar com imagem menor
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia o .jar gerado da imagem de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Corrigido: tem que ter espaço depois de ENTRYPOINT
ENTRYPOINT ["java", "-jar", "app.jar"]
