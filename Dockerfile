FROM java:8-jdk-alpine
ARG JAR_FILE=target/tng-crs-authentication.jar
COPY ${JAR_FILE} /usr/crs/apps/tng-crs-authentication.jar
WORKDIR /usr/crs/apps
ENTRYPOINT ["java", "-jar" , "tng-crs-authentication.jar"]