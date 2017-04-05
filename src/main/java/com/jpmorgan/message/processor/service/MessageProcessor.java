/**
 * 
 */
package com.jpmorgan.message.processor.service;

import java.util.Scanner;

import com.jpmorgan.message.processor.manager.SalesManager;

/**
 * @author Nidhi Shetty
 * 
 */
public class MessageProcessor {

	/**
	 * This is the main method which takes input messages from the user and uses
	 * the SalesManager.getSalesFromManager() for processing and recording each
	 * message.
	 * 
	 * @param args
	 * @exception - for invalid input error.
	 * @see Exception
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); // Reading from System.in
		boolean hasNext = true;
		while (hasNext) {
			System.out.println("Enter the message for processing:");
			String message = scan.nextLine(); // Reads user input from console
			System.out.println(message);
			SalesManager sm = new SalesManager();
			try {
				// Message Processing
				sm.getSalesFromMessage(message);
			} catch (Exception e) {
				System.out.println("Error:" + e.getMessage());
				scan.close();
				return;
			}
		}
		scan.close();
	}

}
