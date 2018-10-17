FROM openjdk:8u171-jre-alpine
RUN apk --no-cache add curl
CMD java ${JAVA_OPTS} -jar account-group-service.jar
EXPOSE 8085
HEALTHCHECK --start-period=30s --interval=5s CMD curl -f http://localhost:8085/actuator/health || exit 1
COPY build/libs/account-group-service.jar .
