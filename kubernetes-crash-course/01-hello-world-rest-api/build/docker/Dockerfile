FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD ${JAR_FILE} app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]