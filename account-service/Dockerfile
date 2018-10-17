FROM openjdk:8u171-jre-alpine
RUN apk --no-cache add curl
CMD java ${JAVA_OPTS} -jar account-service.jar
EXPOSE 8082
HEALTHCHECK --start-period=30s --interval=5s CMD curl -f http://localhost:8082/actuator/health || exit 1
COPY build/libs/account-service.jar .
