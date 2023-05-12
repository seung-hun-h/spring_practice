FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]