FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml ./pom.xml
COPY empldata ./empldata
COPY sbemplms ./sbemplms
COPY emplms ./emplms
RUN mvn -f pom.xml -q -DskipTests clean install

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/sbemplms/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]