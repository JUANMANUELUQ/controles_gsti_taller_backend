FROM maven:3.10.1-jdk-21 AS build
WORKDIR /workspace
COPY pom.xml ./
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre
RUN groupadd -r appgroup && useradd -r -g appgroup appuser || true
WORKDIR /app
EXPOSE 8080
COPY --from=build /workspace/target/*.jar app.jar
USER appuser
ENTRYPOINT ["java","-jar","/app/app.jar"]
