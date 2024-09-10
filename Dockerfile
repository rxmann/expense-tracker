FROM openjdk:22-jdk

ADD target/expense.jar expense.jar

ENTRYPOINT ["java", "-jar", "/expense.jar"]