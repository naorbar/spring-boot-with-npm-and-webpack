# Spring Boot entry point

## Authors
- barna10

## Date
August 2017

## Description
The StartAppServer class is the entry point for spring boot web server.
It is used to run the Migration Tools web applications such as Attributes Mapper tool, IM Analyzer tool etc.

## Infrastructure
This is a spring-mvc web app built with an embedded undertow server; i.e. spring boot
The application is compiled to a jar file and can be executed with java -jar command, 
It can also be compiled to a war file and deployed as usual to any application server.  
For more details about spring boot browse to: [spring-boot project home page](https://projects.spring.io/spring-boot/)

## Usage (Production Mode)
### To run the web application on the embedded undertow server use (StartAppServer class): 
	java -jar <this_jar_file> [--server.port=<port> default port:8443]
e.g.

	java -jar vapp-migration-tools-14.2.0.jar

Browse to: 

	https://localhost:8443/ 

to view the main page

## Usage (Development Mode)
### To run this project on an embedded undertow server: 
Right click on *StartAppServer* class > *Run as* > *Java Application*

Browse to: 

	https://localhost:8443/ 

to view the main page

### To debug this project on a remote server: 
Run this application in the console with the following debug flags:

	java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8888,suspend=n -jar migration-wizard-14.2.0.jar

Run your eclipse project on remote debug:

Right click on *StartAppServer* class > *Debug as* > *Debug Configuration* > *Remote java application* > specify the host and port (e.g. localhost, 8888)
