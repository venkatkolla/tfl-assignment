# Spring Boot "TFLAssignment"  Project

This is a sample Java / Maven / Sprig Boot application that can 
connects to TFL Open API   to get the given road traffic status
## How to Run

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Unzip the application
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar target/TFLAssignment-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
eApiApplication   : Started UnifiedRoadServiceApiApplication in 8.34 seconds (JVM running for 9.529)
2023-11-23 17:05:59.040  INFO 6536 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2023-11-23 17:05:59.040  INFO 6536 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2023-11-23 17:05:59.043  INFO 6536 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms

```

## How to test the Service
Run the following curl commands 
curl http://localhost:8080/Road/A2

% Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
Dload  Upload   Total   Spent    Left  Speed
100    69    0    69    0     0    135      0 --:--:-- --:--:-- --:--:--   135{"response":"The status of the A2 is Good and No Exceptional Delays"}


curl http://localhost:8080/Road/A233
% Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
Dload  Upload   Total   Spent    Left  Speed
100    40    0    40    0     0    192      0 --:--:-- --:--:-- --:--:--   192{"response":"A233 is not a valid road."}

