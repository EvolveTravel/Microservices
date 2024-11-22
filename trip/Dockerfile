# Usar una imagen base de OpenJDK
FROM openjdk:22-jdk

# Crear y usar un directorio temporal para la aplicación
VOLUME /tmp

# Exponer el puerto en el que la aplicación escucha (por defecto 8080)
EXPOSE 8095

# Copiar el archivo .jar generado en Gradle al contenedor
COPY build/libs/trip-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
