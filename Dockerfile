FROM maven:3.6-jdk-11 AS build
WORKDIR /myApp
COPY src src
COPY pom.xml .
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
VOLUME /tmp
COPY --from=build /myApp/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]