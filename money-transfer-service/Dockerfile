FROM openjdk:8u171-jre-alpine
RUN apk --no-cache add curl
CMD java ${JAVA_OPTS} -jar money-transfer-service.jar
EXPOSE 8083
HEALTHCHECK --start-period=30s --interval=5s CMD curl -f http://localhost:8083/actuator/health || exit 1
COPY build/libs/money-transfer-service.jar .
