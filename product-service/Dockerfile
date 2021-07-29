FROM openjdk:11

VOLUME /tmp

ARG JAR_FILE

COPY target/${JAR_FILE} product-service.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000", "/product-service.jar"]