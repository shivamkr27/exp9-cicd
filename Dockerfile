FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B clean package

FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=build /workspace/target/cicd-experiment9-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
