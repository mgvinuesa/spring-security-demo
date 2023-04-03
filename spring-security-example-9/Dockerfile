FROM maven:3.8.5-openjdk-11 as builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:17.0.2-jdk

COPY --from=builder /usr/src/app/target/spring-security-example-9-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT java -jar app.jar 