# Use OpenJDK 17
FROM openjdk:17-jdk-alpine

# Copy jar file
COPY target/backend-1.0.0.jar app.jar

# Expose port
EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
