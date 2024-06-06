FROM openjdk:17
COPY "./target/PortalEscolar-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8130
ENTRYPOINT [ "java","-jar","app. jar"]