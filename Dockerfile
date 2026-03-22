FROM eclipse-temurin:21-jre
WORKDIR /app
EXPOSE 8080
COPY target/taller-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
