FROM openjdk:8-jre
ADD ./target/zuulgateway-0.0.1-SNAPSHOT.jar /usr/src/zuulgateway-0.0.1-SNAPSHOT.jar
EXPOSE 8081
HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8081/actuator/health || exit 1
WORKDIR usr/src
ENTRYPOINT ["java","-jar","zuulgateway-0.0.1-SNAPSHOT.jar"]
