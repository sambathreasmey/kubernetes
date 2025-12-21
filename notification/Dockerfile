# Use OpenJDK 23 as the base image
FROM openjdk:26-ea-21-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the target directory to the container
COPY target/*.jar /app/application.jar

# Expose the port your application will run on
EXPOSE 8080

# Run the application when the container starts
ENTRYPOINT ["java", "-jar", "application.jar"]