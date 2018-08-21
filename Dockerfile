FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} springBootExamples-0.0.1-SNAPSHOT.jar
#EXPOSE 8000
#ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springBootExamples-0.0.1-SNAPSHOT.jar"]
