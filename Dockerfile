#Specifies the base image for the Docker image.
FROM openjdk:17-oracle
#Sets the working directory inside the container
WORKDIR /app
#Copies the Spring Boot JAR file into the container.
COPY target/spring-keycloak-0.0.1-SNAPSHOT.jar /app/app.jar
#Sets the default command to run the Spring Boot application.
CMD ["java","-jar","app.jar"]
EXPOSE 8081