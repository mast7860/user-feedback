FROM eclipse-temurin:17-jre-alpine
COPY user-feedback/build/libs/*-all.jar user-feedback.jar
EXPOSE 8188
CMD java -XshowSettings -XX:+HeapDumpOnOutOfMemoryError -XX:MaxRAMPercentage=60.0 -Dmicronaut.environments=${ENVIRONMENT} ${JAVA_OPTS} -jar user-feedback.jar