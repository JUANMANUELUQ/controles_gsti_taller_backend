# Docker — Instrucciones rápidas

1. Opción recomendada: Docker multi-stage (build dentro de la imagen)

	- El `Dockerfile` incluido ya realiza el build dentro de una etapa `maven` y copia el JAR al stage final basado en `eclipse-temurin:21-jre`.

	- Para construir localmente:
	 - Para construir localmente (Opción A — recomendado para desarrollo rápido):

	```bash
	# 1) Genera el JAR localmente
	./mvnw -B -DskipTests=true package

	# 2) Construye la imagen usando el JAR local (usa Dockerfile.local)
	docker build -f Dockerfile.local -t taller-app:local .

	# 3) Ejecuta el contenedor (con Mongo local)
	docker run -p 8080:8080 --rm -e MONGODB_URI="mongodb://host.docker.internal:27017/tallerdb" taller-app:local
	```

	 - Para construir localmente (Opción B — build dentro de la imagen):

	```bash
	docker build -t taller-app:latest .
	docker run -p 8080:8080 --rm -e MONGODB_URI="mongodb://host.docker.internal:27017/tallerdb" taller-app:latest
	```

	Uso con `docker-compose` (local):

	 - `docker-compose.yml` construye usando `Dockerfile` por defecto (multi-stage). Si quieres que `docker-compose` use el JAR local en lugar de compilar dentro de la imagen, hay un archivo `docker-compose.override.yml` que fuerza el uso de `Dockerfile.local`.

	```bash
	# Genera el JAR
	./mvnw -B -DskipTests=true package

	# Levanta servicios usando el JAR local (override aplica automáticamente)
	docker-compose up --build
	```

2. Opción alternativa (Render — build nativo)

	- En Render puedes usar el build nativo configurando:

```bash
# Build command
./mvnw -B -DskipTests=true package

# Start command
java -jar target/taller-0.0.1-SNAPSHOT.jar
```

	- Y establecer la variable `MONGODB_URI` en la UI de Render.

Notas:
- `application.properties` ahora respeta la variable `PORT` con `server.port=${PORT:8080}`.
- El `Dockerfile` crea un usuario no-root para ejecutar la app.
- Si prefieres que el contenedor use credenciales de Mongo, indícame las variables (usuario/clave) y las añado al `docker-compose.yml` o como instrucciones para Render.
