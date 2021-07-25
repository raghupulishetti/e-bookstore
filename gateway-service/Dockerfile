FROM openjdk:11

VOLUME /tmp

ARG JAR_FILE

COPY target/${JAR_FILE} gateway-service.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway-service.jar"]