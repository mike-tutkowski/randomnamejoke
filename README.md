To run the REST service from a command prompt:
    * Navigate to the folder that contains this README file.
    * Execute the following command: ./mvnw spring-boot:run
    * Execute CTRL-C to terminate the REST service.

Alternatively, you can run the REST service from a command prompt as follows:
    * Navigate to the folder that contains this README file.
    * Execute the following command to build a JAR file: ./mvnw clean package
    * java -jar target/random-name-joke-service-1.0.0.jar
    * Execute CTRL-C to terminate the REST service.

To run the unit tests for the REST service from a command prompt:
    * Navigate to the folder that contains this README file.
    * ./mvnw clean test

A log file, called randomnamejoke.log, is created (if not already present) in the logs folder
when running the REST service. You can control the characteristics of logging by editing the
log4j.properties file located in src/main/resources (requires a restart of the REST service
for the changes to take effect).

