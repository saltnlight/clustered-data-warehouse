FROM openjdk:17-jdk-slim
LABEL maintainer=""
ARG JAR_FILE=target/*.jar
COPY ./target/ClusteredDataWarehouse-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 7070
ENTRYPOINT ["java", "-jar", "/app.jar"]