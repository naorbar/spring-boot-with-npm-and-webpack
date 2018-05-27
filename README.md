## This is a spring-boot template with undertow, npm and webpack:sparkles:

## Author
Naor Bar

## Date
May, 2018

## Description
This is a spring-boot template with undertow, npm and webpack

### How to add npm to an existing spring-boot project:

Add 'com.github.eirslett' plugin to the pom; it will install node and npm, use run 'npm install' to add all dependencies, and finally run 'webpack build' to convert all nodejs sources to client side code.


    <plugin>
	<!-- Install node, run npm install and webpack compilation for frontend files to be server ready in /src/main/resources/public -->
	<!-- If you wish to compile the project without frontend, run maven build with these parameters: skip.npm=true skip.webpack=true -->
	<groupId>com.github.eirslett</groupId>
	<artifactId>frontend-maven-plugin</artifactId>
	<version>1.6</version>
	<configuration>
		<nodeVersion>v8.9.1</nodeVersion>
		<workingDirectory>app</workingDirectory>
	</configuration>
	<executions>
		<execution>
			<id>install node and npm</id>
			<goals>
				<goal>install-node-and-npm</goal>
			</goals>
		</execution>
		<execution>
			<id>npm install</id>
			<goals>
				<goal>npm</goal>
			</goals>
		</execution>
		<execution>
			<id>webpack build</id>
			<goals>
				<goal>webpack</goal>
			</goals>
		</execution>
	</executions>
    </plugin>

Now, running 'mvn package' will create 'app' folder and install node, npm and the npm modules into that folder

But it will fail when tryin to run webpack, with this error:

    Cannot find module 'C:\Users\barna10\eclipse-workspace\spring-boot-with-npm-and-webpack\app\node_modules\webpack\bin\webpack.js'
  
Add package.json to the app folder - it will include webpack dependecy:

    {
        "name": "spring-boot-with-npm-and-webpack",
        "version": "1.0.0",
        "description": "@Description Use NPM in my spring-boot project\r @Author barna10\r @Date barna10",
        "main": "index.js",
        "scripts": {
	  "test": "echo \"Error: no test specified\" && exit 1",
	  "build": "webpack"
        },
        "author": "barna10",
        "license": "ISC",
        "dependencies": {
          "arithmetic": "^1.0.1",
	  "repeat-string": "^1.6.1"
        },
        "devDependencies": {
          "webpack": "^4.9.1",
          "webpack-cli": "^2.1.4"
        }
    }

Running 'mvn package' will now succeed, but the web app (index.html) will not recognize all the required modules:

    Uncaught ReferenceError: require is not defined
        at index.js:1

To fix that, add webpack.config.js:
    
    const path = require('path');

    module.exports = {
      entry: './client/index.js',
      output: {
	        path: path.resolve(__dirname, '../src/main/resources/', 'public'),
	        filename: '[name].bundle.js'
      }
    };

This will order webpack to take the original nodejs code from 'app' folder: ./client/index.js and convert it to client side code; 
it will put the generated code in src/main/resources/public folder.

Now make sure the index.html file refers to the converted code, 
i.e. something like this:

    <h1>Simple Web Page</h1>
    <script src="main.bundle.js"></script>
