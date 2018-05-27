package com.ca;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This is the main entry of the spring-boot web application (used to start the application server)
 * @HowTos
 * <ol>
 * 	<li>Changing the server port:<BR> 
 * 		The server start on port 8080; to run the application on different port use this JVM argument: server.port<BR>
 * 		e.g. <BR>
 * 		-Dserver.port=8888
 * 	</li>
 * 	<li>Changing the console/log banner:<BR> 
 * 		To change the banner put a banner.txt or banner.png/jpg in the source folder or on the classpath e.g. \src\main\java\banner.JPG
 * 	</li>
 *  <li>SSL Support:<BR>
 *  	To enable the SSL support for the embedded undertow application server, generate a keystore file and set these properties in application.properties file: <BR>
 * 		To remove the SSL support, just remove these settings
 *		<pre>
 *		server.port = 8443
 * 		server.ssl.key-store = classpath:.keystore
 * 		server.ssl.key-store-password = ****
 * 		server.ssl.key-password = ****
 * 		</pre>
 * 		On production, ssl is enabled by default - to disable it use this program argument:<BR> 
 * 		<code>--spring.profiles.active=dev</code><BR>
 * 		It will load the dev 'profile' which sets the http port, rather than the https properties
 * </li>
 * 	<li>Basic Authentication:<BR>
 * 		To enable the OOBT undertow security, add the following dependency to the pom (to disable the security remove this dependency):</p>
 * 		<pre>
 * 		&ltdependency>
 *			&ltgroupId>org.springframework.boot&lt/groupId>
 *			&ltartifactId>spring-boot-starter-security&lt/artifactId>
 *		&lt/dependency>
 * 		</pre> 
 * 		<p>The default user is 'user', password is generated on startup and displayed to the log as INFO message</p>
 * 		<p>You can set a different password using <b>security.user.password=new_password</b> in the application.xml file or <b>--security.user.password=new_password</b> as a program argument</p>
 * 		<p>You can also implement your own <b>SecurityConfig</b> class and set the user/password dynamically using the user input.</p>
	
 * </ol>
 * @author barna10
 */

@Configuration
@EnableAutoConfiguration
@EnableCaching
@EnableScheduling
@ComponentScan(basePackages = "com")
public class StartAppServer {

	private static final Logger log = LogManager.getLogger();

	public static void main(String[] args) throws Exception {
		// This line is used to load the 'prod' profile, i.e. the "application-prod.properties" which is used to enable the SSL support for the embedded undertow application server
		//log.info("Setting the default profile: [prod]");
		//System.setProperty("spring.profiles.default", "prod");
		
		log.info("Setting the default profile: [dev]");
		System.setProperty("spring.profiles.default", "dev");
		
		SpringApplication.run(StartAppServer.class, args);
	}

	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {
			@Override
			public void contextInitialized(ServletContextEvent sce) {
				log.info("ServletContext initialized");
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				log.info("ServletContext destroyed");
			}
		};
	}

}
