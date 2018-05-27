package com.ca;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * <p>This class is used to configure basic authentication for the embedded undertow application server</p>
 * <p>To enable the OOBT undertow security we can add the following dependency to the pom (to disable the security remove this dependency):</p>
 * <pre>
 * 	&ltdependency>
 *		&ltgroupId>org.springframework.boot&lt/groupId>
 *		&ltartifactId>spring-boot-starter-security&lt/artifactId>
 *	&lt/dependency>
 * </pre> 
 * <p>The default user is 'user', password is generated on startup and displayed to the log as INFO message</p>
 * <p>You can set a different password using <b>security.user.password=new_password</b> in the application.xml file or <b>--security.user.password=new_password</b> as a program argument</p>
 * <p>You can also implement your own <b>SecurityConfig</b> class and set the user/password dynamically using the user input.</p>
 * @see https://docs.spring.io/spring-boot/docs/1.4.0.RELEASE/reference/htmlsingle/#boot-features-security
 * @author barna10
 */

// Note:
// @EnableWebSecurity will switch off all auto-configuration of web security thereby allowing you to take complete control.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger log = LogManager.getLogger();
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Note: 
		// Use this to enable the undertow basic authentication (undertow popup rather than spring login page)
		// Note that the CSRf token is disabled for all requests
		//log.info("Disabling CSRF, enabling basic authentication...");
		//http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
		log.info("Disabling basic authentication...");
		http.csrf().disable().authorizeRequests().anyRequest().permitAll();
	}
	
	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		log.info("Setting in-memory security using the user input...");

		String username = null;
		String password = null;
		
		System.out.println("\nPlease set the admin credentials for this web application (will be required when browsing to the web application)");
		Console console = System.console();
    	
		// Read the credentials from the user console: 
		// Note: 
		// Console supports password masking, but is not supported in IDEs such as eclipse; 
		// thus if in IDE (where console == null) use scanner instead:
		if (console == null) {
			// Use scanner:
			Scanner scanner = new Scanner(System.in);
			while (true) {
	        	System.out.print("Username: ");
	        	username = scanner.nextLine();
	            System.out.print("Password: ");
	            password = scanner.nextLine();
	            System.out.print("Confirm Password: ");
	            String inputPasswordConfirm = scanner.nextLine();
	          
	            if (username.isEmpty()) {
	            	System.out.println("Error: user must be set - please try again");
	            } else if (password.isEmpty()) {
	            	System.out.println("Error: password must be set - please try again");
	            } else if (!password.equals(inputPasswordConfirm)) {
	            	System.out.println("Error: password and password confirm do not match - please try again");
	            } else {
	            	log.info("Setting the in-memory security using the provided credentials...");
	            	break;
	            }
	            System.out.println("");
	        }
	        scanner.close();
		} else {
			// Use Console
			while (true) {
	        	username = console.readLine("Username: ");
	        	char[] passwordChars = console.readPassword("Password: ");
	        	password = String.valueOf(passwordChars);
	            char[] passwordConfirmChars = console.readPassword("Confirm Password: ");
	            String passwordConfirm = String.valueOf(passwordConfirmChars);
	            
	            if (username.isEmpty()) {
	            	System.out.println("Error: Username must be set - please try again");
	            } else if (password.isEmpty()) {
	            	System.out.println("Error: Password must be set - please try again");
	            } else if (!password.equals(passwordConfirm)) {
	            	System.out.println("Error: Password and Password Confirm do not match - please try again");
	            } else {
	            	log.info("Setting the in-memory security using the provided credentials...");
	            	break;
	            }
	            System.out.println("");
	        }
		}
		
		// Set the inMemoryAuthentication object with the given credentials:
        if (username != null && password != null) {
        	auth.inMemoryAuthentication()
        	.withUser(username)
        	.password(password)
        	.roles("USER");
        }
	}*/
}