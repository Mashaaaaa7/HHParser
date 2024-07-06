FROM gradle:8.4-jdk21 as packager
COPY . /home/src
WORKDIR /home/src
RUN gradle -v
RUN gradle buildFatJar --no-daemon

FROM openjdk:21
COPY --from=packager /home/src/build/libs/*.jar /home/app/app.jar
WORKDIR /home/app/run
EXPOSE 80
ENTRYPOINT ["java", "-jar", "../app.jar"]
