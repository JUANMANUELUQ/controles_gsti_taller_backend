FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace
# Use the Maven Wrapper to build (avoids depending on a specific 'maven:' image tag)
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./
COPY src ./src
RUN chmod +x mvnw && ./mvnw -B -DskipTests package

FROM eclipse-temurin:21-jre
RUN groupadd -r appgroup && useradd -r -g appgroup appuser || true
WORKDIR /app
EXPOSE 8080
COPY --from=build /workspace/target/*.jar app.jar
USER appuser
# Ensure the JVM binds to the runtime $PORT provided by Render (fallback 8080).
# Use a shell exec form so the java process receives signals correctly.
ENTRYPOINT ["sh","-c","exec java -Dserver.port=$PORT -jar /app/app.jar"]
