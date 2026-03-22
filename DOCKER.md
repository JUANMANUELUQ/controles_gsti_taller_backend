# Docker — Instrucciones rápidas

1. Construir el JAR con Maven (usa el wrapper incluido):

```bash
./mvnw -B -DskipTests package
```

2. Construir la imagen Docker (alternativa multi-stage en `Dockerfile`):

```bash
docker build -t taller-app:latest .
```

3. Ejecutar con Docker:

```bash
docker run -p 8080:8080 --rm taller-app:latest
```

4. O usar docker-compose:

```bash
docker-compose up --build
```

Notas:
- El proyecto usa Java 21 (según `pom.xml`). El `Dockerfile` usa Temurin 21.
- Si necesitas montar volúmenes, variables de entorno o conectarlo a MongoDB, indícalo y lo agrego.
