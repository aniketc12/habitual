FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests


FROM openjdk:23-jdk-slim
WORKDIR /app
COPY --from=build /app/target/habitual-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "app.jar"]
