FROM openjdk:8u171-jre-alpine
RUN apk --no-cache add curl
HEALTHCHECK --start-period=30s --interval=5s CMD curl -f http://localhost:8084/actuator/health || exit 1
CMD java ${JAVA_OPTS} -jar customer-view-service.jar
EXPOSE 8084
COPY build/libs/customer-view-service.jar .
