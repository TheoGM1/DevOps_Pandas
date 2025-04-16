FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the jar and source files
COPY target/*.jar /app/pandas-java.jar
COPY src/main/java/DevOps/product.csv /app/product.csv
COPY src/main/java/DevOps/DemoApp.java /app/DevOps/DemoApp.java

# Compile the Java file with correct package structure
RUN mkdir -p /app/DevOps && javac -cp pandas-java.jar DevOps/DemoApp.java

# Run using the fully qualified class name
CMD ["java", "-cp", ".:pandas-java.jar", "DevOps.DemoApp"]
