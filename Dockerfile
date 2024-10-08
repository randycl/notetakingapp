# Use an official Maven image to build the project
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY note-taking-app/note-taking-app/pom.xml .
#RUN mvn dependency:go-offline

COPY note-taking-app/note-taking-app/src ./src
RUN mvn clean

# Copy the application.properties into the test directory.
COPY note-taking-app/note-taking-app/src/main/resources/application.properties ./src/test/resources/application.properties

# Package the application
RUN ls -lta /app
RUN ls -lta /app/src/test/java
RUN ls -lta /app/src/test/resources
RUN mvn --version
RUN mvn compile
RUN ls -lta target
RUN uname -a
# RUN mvn test

# Use an OpenJDK image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

RUN echo openjdk-17
RUN java --version

# Copy the packaged jar file from the build stage
COPY --from=build /app/target/notetakingapp-*.jar /app/notetakingapp.jar
RUN ls -lta /app

# Expose the port your application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/notetakingapp.jar"]
#ENTRYPOINT ["tail", "-f", "/dev/null"]
