#
# build stage
#
FROM maven:3.8.5-openjdk-17 as build
COPY . /home/app
WORKDIR /home/app
RUN mvn clean install -am -DskipTests=true
#
# package stage
#
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar 
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]


