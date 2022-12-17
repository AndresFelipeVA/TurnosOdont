FROM openjdk:11-jre-slim
VOLUME /tmp
#El .jar debe estar al mismo nivel que el Dockerfile
COPY *.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]