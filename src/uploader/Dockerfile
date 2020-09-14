FROM mcr.microsoft.com/java/jdk:11u8-zulu-alpine as builder

WORKDIR /app

COPY . .
RUN chmod +x gradlew
RUN ./gradlew build

FROM mcr.microsoft.com/java/jre-headless:11u8-zulu-alpine

WORKDIR /app
COPY --from=builder /app/build/libs/uploader-0.0.1-SNAPSHOT.jar ./app.jar
ADD https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.0.0-PREVIEW.5/applicationinsights-agent-3.0.0-PREVIEW.5.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "-javaagent:/app/applicationinsights-agent-3.0.0-PREVIEW.5.jar"]
