#  Tool1

## Authors
 - barna10

## Description
This tool is ...

It gets as input ...

## Usage

To run this program as a web application run the following command:

	java -jar <this_jar_file> [--server.port=<port> default port:8443]
e.g.

	java -jar my-spring-boot-template-nodb-0.0.1-SNAPSHOT.jar

and browse to:

	https://<host>:<port>
	
**For testing purposes only:**

To run this program as a console application use (StartConsole class): 
	
	java -cp <this_jar_file> -Dloader.main=<another_main_class> org.springframework.boot.loader.PropertiesLauncher
e.g.

	java -cp my-spring-boot-template-nodb-0.0.1-SNAPSHOT.jar -Dloader.main=com.ca.tools.tool1.StartConsole org.springframework.boot.loader.PropertiesLauncher
