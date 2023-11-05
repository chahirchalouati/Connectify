FROM openjdk:17-slim
COPY  /target/*.jar /usr/local/lib/app.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]

