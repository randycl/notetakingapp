# Use an official Maven image to build the project
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY note-taking-app/note-taking-app/pom.xml .
RUN mvn dependency:go-offline

COPY note-taking-app/note-taking-app/src ./src

# Package the application
RUN mvn test

# Use an OpenJDK image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the packaged jar file from the build stage
COPY --from=build /app/target/notetakingapp-*.jar /app/notetakingapp.jar

# Expose the port your application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/notetakingapp.jar"]
