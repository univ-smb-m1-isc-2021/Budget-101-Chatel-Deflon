FROM openjdk:11-jre-slim

COPY ./target/my-spring-1.0-SNAPSHOT.jar .

EXPOSE 8081

CMD ["sh","-c","java -XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=70  -XshowSettings $JAVA_OPTS -jar my-spring-1.0-SNAPSHOT.jar"]