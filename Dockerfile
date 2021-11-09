FROM adoptopenjdk/openjdk11:alpine
ARG JAR_FILE=target/*.jar
WORKDIR /app
#COPY ${JAR_FILE} app.jar
COPY target/book-wishlist-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8081
ENTRYPOINT [ "java", "-Dspring.profiles.active=prod" , "-jar", "app.jar" ]