FROM gcr.io/distroless/java:11

COPY /build/libs /app
WORKDIR /app

CMD ["integration-0.0.1-SNAPSHOT-plain.jar"]