FROM openjdk:17
WORKDIR /ziplanerx-external-proxy
COPY ./target/*.jar /ziplanerx-external-proxy
EXPOSE 8080
CMD ["java", "-jar", "ExternalProxy-0.0.1-SNAPSHOT.jar"]