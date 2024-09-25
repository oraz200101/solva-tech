FROM gradle:8.8-jdk17 AS build
WORKDIR /app

COPY . .
RUN gradle build --no-daemon

FROM openjdk:17
VOLUME /tmp

RUN mkdir -p /app/files
COPY --from=build /app/build/libs/solva-tech-oraz-0.0.1-SNAPSHOT.jar /solva-tech-oraz-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/solva-tech-oraz-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081