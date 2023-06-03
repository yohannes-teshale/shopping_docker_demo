FROM maven AS builder

WORKDIR  /usr/src/shoppingApp
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY . .
RUN mvn package

FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY --from=builder /usr/src/shoppingApp/target/ShoppingService-0.0.1-SNAPSHOT.jar .

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.9.0/wait /wait
RUN chmod +x /wait

ENTRYPOINT ["java", "-jar", "/app/ShoppingService-0.0.1-SNAPSHOT.jar"]