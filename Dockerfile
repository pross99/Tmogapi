# Build stage
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Final stage
FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates
ARG JAR_FILE=target/*.jar
COPY --from=build /app/target/T-tracker2-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
