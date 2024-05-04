FROM eclipse-temurin:17-jre-alpine

COPY target/tiktok-0.0.1-SNAPSHOT.jar tiktok-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java","-jar","/tiktok-0.0.1-SNAPSHOT.jar" ]