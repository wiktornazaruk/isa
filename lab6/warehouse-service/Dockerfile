# Use Maven image to build the JAR
FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /app
# Copy Maven configuration and project files
COPY pom.xml .
COPY src ./src
# Build the JAR file
RUN mvn clean package -DskipTests

# Use a lightweight JDK image to run the application
FROM eclipse-temurin:23-jdk-alpine
WORKDIR /app
# Copy the built JAR file from the Maven image
COPY --from=build /app/target/*.jar warehouse-service.jar
# Expose port
EXPOSE 8091
# Define the default command to run the JAR file
ENTRYPOINT ["java", "-jar", "warehouse-service.jar"]
