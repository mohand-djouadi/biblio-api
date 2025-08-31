FROM maven:3.9-eclipse-temurin-17 AS build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTest

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /sunlib
COPY --from=build /target/*.jar sunlib.jar
ENTRYPOINT ["java", "-jar", "sunlib.jar"]