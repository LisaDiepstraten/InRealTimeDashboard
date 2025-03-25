# Use the official Gradle image for building the application
FROM gradle:7.5.0-jdk17 AS builder

# Set the working directory
WORKDIR /opt/app

# Copy the Gradle wrapper files
COPY gradlew gradlew
COPY gradle gradle/
COPY build.gradle .
COPY settings.gradle .

# Copy the source code into the container
COPY src src

# Build the application
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon

# Use a smaller base image for the final image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /opt/app

# Copy the JAR file from the builder stage
COPY --from=builder /opt/app/build/libs/*.jar app.jar

# Expose the application's port (adjust as necessary)
EXPOSE 8899

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
