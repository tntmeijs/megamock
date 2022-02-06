# This Dockerfile has been created using this guide:
# https://codefresh.io/docs/docs/learn-by-example/java/gradle/

# Step 1: use Gradle to compile the code and run unit tests
FROM gradle:7.3.3-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Step 2: use a fresh JDK image and only copy the JAR file created in step 1
FROM openjdk:17.0.2
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar", "--spring.data.mongodb.host=mongodb"]
