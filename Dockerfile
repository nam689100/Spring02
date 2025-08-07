FROM openjdk:17
COPY target/Spring02-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

#FROM openjdk:17
#worKDIR /app
#COPY target/Ex01-0.0.1-SNAPSHOT.jar app.jar
#CMD ["java", "-jar", "app.jar"]