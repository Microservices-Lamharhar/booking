FROM openjdk:17
WORKDIR /app
ADD target/booking-0.0.1-SNAPSHOT.jar booking.jar 
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "booking.jar"]
