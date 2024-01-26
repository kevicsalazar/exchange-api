FROM gradle:8.4-jdk21-alpine

# Set the working directory
WORKDIR /app

# Copy the entire project
COPY . /app

# Build the project
RUN ./gradlew build

# Expose the port used by your Ktor application
EXPOSE 8080

# Command to run the application
CMD ["./gradlew", "server:run"]