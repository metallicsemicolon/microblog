#FROM gradle:7.5.1-jdk17 AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle bootImage --no-daemon

FROM eclipse-temurin:17
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME

#COPY --from=build /home/gradle/src/build/libs/*.jar /usr/app/app.jar

COPY /build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
