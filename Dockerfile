FROM gradle:8.4-jdk21-alpine AS build
COPY --chown=gradle:gradle . /appBuild
WORKDIR /appBuild
RUN gradle buildFatJar --no-daemon

FROM openjdk:21
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /appBuild/server/build/libs/*.jar /app/exchange-api.jar
ENTRYPOINT ["java","-jar","/app/exchange-api.jar"]