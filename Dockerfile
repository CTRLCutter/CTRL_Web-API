# Build stage
FROM maven:3.8.4-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Package stage
FROM openjdk:11-jdk-slim
COPY --from=build /home/app/target/*.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","app.jar"]