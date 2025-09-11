# Stage 1: Build the application
# Using a Maven image with Java 17, which is a standard for modern Spring Boot apps.
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set the working directory for the build stage
WORKDIR /app

# Copy the pom.xml to download dependencies first, leveraging Docker's cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of your application's source code
COPY src ./src

# Package the application into a .jar file. We skip tests for a faster build.
RUN mvn package -DskipTests


# Stage 2: Create the final, smaller runtime image
# Using a slim Java 17 JRE (Java Runtime Environment) for a smaller image size
FROM eclipse-temurin:17-jre-jammy

# Set the working directory for the final image
WORKDIR /app

# --- IMPORTANT ---
# Expose port 6996. This must match the port you configure in your Render service settings.
# If your application.properties specifies a different server.port, update this value.
EXPOSE 7070

# Copy the compiled .jar from the 'build' stage into the final image
COPY --from=build /app/target/*.jar app.jar

# The command that will run when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]


