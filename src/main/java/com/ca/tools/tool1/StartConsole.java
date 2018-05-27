package com.ca.tools.tool1;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ca.tools.tool1.service.Tool1Service;

/**
 * This is the main entry of tool1 console application used for testing
 * @author barna10
 */
public class StartConsole {

	private static final Logger log = LogManager.getLogger();
	
	/**
	 * Start this console to get two IM directories, compare two imsManagedObject and generate the compare outputs accordingly<BR>
	 * This method doens't replace the web UI and is used for testing purposes only.
	 * @author barna10
	 */
	public static void main(String[] args) {
		
		try {
			Tool1Service service = new Tool1Service();
			
			System.out.print("Use this console get IM directories, compare two imsManagedObject and generate the compare outputs accordingly;\n" +
					"Note: Use this console for testing purposes only; for production, use the Web interface!");
			
			Scanner scanner = new Scanner(System.in);

			loop: while (true) {
					
				System.out.print("\n\nPlease choose one of the following options:" +
						"\n\t(1) Get available IM directories map [online]" + 
						"\n\t(2) more options here...." + 
						"\n\t(q) quit - quit");
				System.out.print("\n\nEnter your input here:");
				String input = scanner.nextLine();

				String host = null;
				
				switch (input) {
				case "q": case "quit":
					System.out.println("Aborting...");
					break loop;
				case "1": case "Get available IM directories map [online]":
					try {
						System.out.print("Please specify IM host:");
						host  = scanner.nextLine();
						// TODO
						// TODO
						
					} catch (Exception e) {
						System.out.println("Error: Could not complete the specified operation - " + e.getMessage());
						break;
					}
					break;
				default:
					System.out.println("Illegal input...");
					break;
				}
			}
			scanner.close();
			return;
		} catch (Exception e) {
			log.error("Failed - " + e.getCause() + " - " + e.getMessage());
		}
	}
	
}
