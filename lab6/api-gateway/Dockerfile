# Step 1: Build Stage
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
# Copy Maven configuration and source code
COPY pom.xml ./
COPY src ./src
# Build the application and create the JAR file
RUN mvn clean package -DskipTests

# Step 2: Runtime Stage
FROM eclipse-temurin:17-jdk AS runtime
WORKDIR /app
# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar
# Expose the application port
EXPOSE 8090
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
