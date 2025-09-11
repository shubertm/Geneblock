FROM gradle:jdk21 AS build

COPY --chown=gradle:gradle server /home/gradle/src/server
COPY --chown=gradle:gradle shared /home/gradle/src/shared
COPY ./gradle /home/gradle/src/gradle
COPY ./gradle.properties /home/gradle/src/gradle.properties
COPY ./settings.gradle.kts /home/gradle/src/settings.gradle.kts
COPY ./gradlew /home/gradle/src/gradlew
COPY ./build.gradle.kts /home/gradle/src/build.gradle.kts
COPY .editorconfig /home/gradle/src/.editorconfig

RUN mkdir /home/gradle/src/app

WORKDIR /home/gradle/src/server
RUN gradle buildFatJar --no-daemon

FROM openjdk:21
EXPOSE 8080:8080
COPY --from=build /home/gradle/src/server/build/libs/*.jar /home/gradle/src/app/geneblock.jar
ENTRYPOINT ["java","-jar","/home/gradle/src/app/geneblock.jar"]
