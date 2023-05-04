# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory to /app
WORKDIR /src

# Copy the project jar file into the container at /app
COPY target/OfferUp-PaymentService.jar /src

# Expose port 8080 to the outside world
EXPOSE 8084


# Run the jar file when the container starts
CMD ["java", "-jar", "OfferUp-PaymentService.jar"]
