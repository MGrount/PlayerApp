# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar

# Copy the CSV file into the container
COPY src/main/resources/player.csv /app/player.csv

# Expose port 8080 to the outside world (if your Spring Boot application listens on port 8080)
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]