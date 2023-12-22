FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR .

COPY pom.xml .

COPY src ./src

RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/storimak.jar"]



