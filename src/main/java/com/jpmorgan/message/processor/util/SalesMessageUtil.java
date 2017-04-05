/**
 * 
 */
package com.jpmorgan.message.processor.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nidhi Shetty
 * 
 */
public class SalesMessageUtil {

	/**
	 * This is the sampleMessageList method creates and returns a list of sample
	 * messages for processing.
	 * 
	 * @return list of sample messages
	 * @see List<String>
	 */
	public static List<String> sampleMessageList() {
		List<String> messageList = new ArrayList<String>();
		// (1-10)
		messageList.add("apple at 20p");
		messageList.add("chair at 40p");
		messageList.add("book at 30p");
		messageList.add("20 sales of pens at 10p each");
		messageList.add("Multiply 20p apples");
		messageList.add("apple at 10p");
		messageList.add("chair at 20p");
		messageList.add("Add 5p pens");
		messageList.add("apple at 20p");
		messageList.add("pen at 10p");
		// (11-20)
		messageList.add("book at 20p");
		messageList.add("apple at 20p");
		messageList.add("10 sales of apples at 10p each");
		messageList.add("apple at 15p");
		messageList.add("apple at 15p");
		messageList.add("chair at 35p");
		messageList.add("book at 20p");
		messageList.add("book at 10p");
		messageList.add("Subtract 5p books");
		messageList.add("apple at 20p");
		// (21-30)
		messageList.add("25 sales of chairs at 30p each");
		messageList.add("Add 2p chairs");
		messageList.add("pen at 12p");
		messageList.add("apple at 5p");
		messageList.add("chair at 35p");
		messageList.add("pen at 15p");
		messageList.add("chair at 30p");
		messageList.add("book at 20p");
		messageList.add("book at 30p");
		messageList.add("pen at 10p");
		// (31-40)
		messageList.add("pen at 5p");
		messageList.add("15 sales of books at 20p each");
		messageList.add("Add 5p books");
		messageList.add("apple at 20p");
		messageList.add("book at 10p");
		messageList.add("Multiply 2p pens");
		messageList.add("chair at 25p");
		messageList.add("apple at 15p");
		messageList.add("pen at 5p");
		messageList.add("book at 10p");
		// (41-50)
		messageList.add("30 sales of bottles at 15p each");
		messageList.add("book at 15p");
		messageList.add("Add 2p bottles");
		messageList.add("book at 35p");
		messageList.add("bottle at 20p");
		messageList.add("5 sales of books at 20p each");
		messageList.add("chair at 45p");
		messageList.add("Subtract 5p chairs");
		messageList.add("apple at 10p");
		messageList.add("pen at 5p");
		return messageList;
	}
}
