# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY build/libs/career-connect-web-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Set environment variables
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"] 