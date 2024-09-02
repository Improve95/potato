FROM openjdk:23-ea-17-jdk-bullseye
FROM gradle:8.10.0-jdk21
# FROM postgres:13.16-bullseye

WORKDIR /potatoApplication

COPY ./build/libs/potato-0.0.1-plain.jar ./build/libs
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

RUN gradle wrapper

EXPOSE 8080

CMD [ "./gradlew", "bootRun" ]