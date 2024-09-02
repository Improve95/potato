FROM openjdk:23-ea-17-jdk-bullseye
# FROM postgres:13.16-bullseye

WORKDIR /potatoApplication

COPY ./build/libs/potato-0.0.1.jar .

EXPOSE 8080

CMD [ "java", "-jar", "potato-0.0.1.jar" ]
