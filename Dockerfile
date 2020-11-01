FROM openjdk:11.0.8-jdk-slim AS BUILD_IMAGE
COPY . /tmp/
WORKDIR /tmp/
RUN ./mvnw package

FROM openjdk:11.0.8-jre-slim
ARG JAR_FILE
COPY --from=BUILD_IMAGE /tmp/target/${JAR_FILE} app.jar
RUN chmod +x app.jar
EXPOSE 8080
CMD java -jar app.jar
