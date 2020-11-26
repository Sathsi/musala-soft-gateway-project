This Repository contains Gateway API

Installation Guide

Method 1:

1) Copy the SQL scrip (Gateway.sql)  from the shared location to your local machine
Note: This script has both Schema structure and the test data

2) Import script file into the MYSQL DB tool which you use.
eg: MYSQL Workbench

3) Import the data file into the MYSQL DB tool
OR
Run the sql script (as a query) in your DB tool

4) Clone the project from git (checkout the develop branch)
Note: This also has the sql script ((Gateway.sql)) in main branch
Git repo: https://github.com/Sathsi/musala-soft-gateway-project/

5) Navigate to the project directory which you checkout the gateway project

6)Edit the application.properties file with your DB details.

7) Build the project
command: mvn clean install

8) After build success, navigate to the target directory

9) Launch the executable JAR file (com.musala.gateways.api-0.0.1.jar) from the command line
command: java -jar com.musala.gateways.api-0.0.1.jar

10) Once application is stared you will be able to use the gateway APIs
swagger url: http://localhost:8080/swagger-ui.html#

OR
Method 2:
Note: For this method you need to configure database as it is in application.properties file
1) Follow the step 3 to step 5 in Method 1
2) Copy the jar file(com.musala.gateways.api-0.0.1.jar) from the shared location to your local machine
3) Follow the step 9 to step 10 in Method 1
