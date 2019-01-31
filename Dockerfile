FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/nba-scheduler.jar
ARG DEPENDENCY=target/dependency
ARG LIB=target/lib/*
COPY ${JAR_FILE} /app/app.jar
COPY ${LIB} /app/lib/
COPY ${DEPENDENCY}/META-INF /app/META-INF
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar", "--spring.profiles.active=test", "--spring.datasource.username=root", "--spring.datasource.password=raledon"]