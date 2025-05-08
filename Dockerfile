# Usa un'immagine base con Java 17
FROM eclipse-temurin:17-jdk

# Crea una cartella app all'interno del container
WORKDIR /app

# Copia il file .jar generato da Maven
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Comando per avviare l'app Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
