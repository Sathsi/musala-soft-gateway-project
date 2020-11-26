# musala-soft-gateway-project
This Repository contains Gateway API
This Repository contains Gateway API

Installation Guide

Method 1:

1) Copy the SQL scrip (.sql)  from the shared location to your local machine
Note: This script has both Schema structure and the test data

2) Import script file into the MYSQL DB tool which you use.
eg: MYSQL Workbench

3) Import the data file into the MYSQL DB tool
OR
Run the sql script (as a query) in your DB tool

4) Checkout the project from git
Git repo:

5) Navigate to the project directory which you checkout the gateway project

6) Build the project
command: mvn clean install

7) After build success, navigate to the target directory

8) Launch the executable JAR file (com.musala.gateways.api-0.0.1.jar) from the command line
command: java -jar com.musala.gateways.api-0.0.1.jar

9) Once application is stared you will be able to use the gateway APIs
swagger url: http://localhost:8080/swagger-ui.html#

OR
Method 2:
1) Follow the step 3 to step 5 in Method 1
2) Copy the jar file(com.musala.gateways.api-0.0.1.jar) from the shared location to your local machine
3) Follow the step 8 to step 9 in Method 1
