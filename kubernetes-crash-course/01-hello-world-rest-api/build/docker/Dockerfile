FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD build/libs/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]