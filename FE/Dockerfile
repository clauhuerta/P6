FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/spring-skeleton-1.0-SNAPSHOT.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/app.jar"]