FROM openjdk:24-jdk-oracle

WORKDIR /potatoApp

COPY potato-0.0.1.jar .

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://database:5432/walletDB
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

EXPOSE 8080

CMD java -jar potato-0.0.1.jar
