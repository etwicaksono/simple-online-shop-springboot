# Use an official Maven image to build the project
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml .
COPY src ./src
COPY src/main/resources/application.properties.docker ./src/main/resources/application.properties

# Build the application
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime as a parent image
FROM openjdk:23-ea-17-jdk-bullseye

# Install necessary packages including Microsoft core fonts
RUN apt-get update \
    && apt-get install -y --no-install-recommends fontconfig \
    && apt-get install -y --no-install-recommends wget cabextract \
    && wget https://downloads.sourceforge.net/corefonts/arial32.exe \
    && cabextract -q arial32.exe -d /usr/share/fonts/truetype/msttcorefonts \
    && fc-cache -f -v \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* arial32.exe


# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the previous stage
COPY --from=build /app/target/simple-online-shop-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]