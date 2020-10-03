FROM mcr.microsoft.com/java/jdk:11u8-zulu-alpine as builder

WORKDIR /app

RUN apk update && \
    apk add --no-cache git

COPY . .

WORKDIR /app/src/uploader

RUN chmod +x gradlew
RUN ./gradlew build --info

FROM mcr.microsoft.com/java/jre-headless:11u8-zulu-alpine

WORKDIR /app
COPY --from=builder /app/src/uploader/build/libs/uploader-*.jar ./app.jar
COPY /src/uploader/AI-Agent.xml ./AI-Agent.xml
ADD https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.0.0-PREVIEW.7/applicationinsights-agent-3.0.0-PREVIEW.7.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128M", "-XX:+PrintFlagsFinal", "-javaagent:/app/applicationinsights-agent-3.0.0-PREVIEW.7.jar", "-jar", "app.jar"]
