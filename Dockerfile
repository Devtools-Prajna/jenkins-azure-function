# Use the official Azure Functions Java 11 base image
FROM mcr.microsoft.com/azure-functions/java:4-java11

# Set working directory inside container
WORKDIR /home/site/wwwroot

# Copy the packaged Azure Function app from target folder into the image
COPY target/azure-functions/javaapp/ .

# Expose the port Azure Functions runtime listens on
EXPOSE 80

# Default command to run the Azure Functions host
CMD [ "sh", "-c", "func host start --java" ]
