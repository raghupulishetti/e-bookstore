FROM openjdk:11

VOLUME /tmp

ARG JAR_FILE

COPY target/${JAR_FILE} cloud-config-service.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8888", "/cloud-config-service.jar"]