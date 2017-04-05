/**
 * 
 */
package com.jpmorgan.message.processor.manager;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jpmorgan.message.processor.util.SalesMessageUtil;

/**
 * @author Nidhi Shetty
 * 
 */
public class SalesManagerTest {

	SalesManager sm;

	@BeforeTest
	void setup() {
		sm = new SalesManager();
	}

	/**
	 * @testcase test case to verify message with negative product value
	 * @throws Exception
	 * */
	@Test(priority = 1, expectedExceptions = Exception.class)
	public void testNegativeProductValue() throws Exception {
		// message with negative product value
		sm.getSalesFromMessage("plate at -20p");
	}

	/**
	 * @testcase test case to verify message with negative product count
	 * @throws Exception
	 * */
	@Test(priority = 2, expectedExceptions = Exception.class)
	public void testNegativeProductCount() throws Exception {
		// message with negative product count
		sm.getSalesFromMessage("-20 sales of plates at 10p each");
	}

	/**
	 * @testcase test case to verify negative adjustment value
	 * @throws Exception
	 * */
	@Test(priority = 3, expectedExceptions = Exception.class)
	public void testNegativeAdustmentValue() throws Exception {
		// message with negative adjustment value
		sm.getSalesFromMessage("Add -30p plates");
	}

	/**
	 * @testcase test case to verify invalid message
	 * @throws Exception
	 * */
	@Test(priority = 4, expectedExceptions = Exception.class)
	public void testInvalidMessage() throws Exception {
		// invalid message - empty string
		sm.getSalesFromMessage(" ");
	}

	/**
	 * @testcase test case to verify the message processing of 50 messages
	 *           (including message types - 1,2,3)
	 * @throws Exception
	 * */
	@Test(priority = 5)
	public void testMessageProcessor() throws Exception {
		List<String> sampleMessages = new ArrayList<String>();
		// get the list of sample messages
		sampleMessages = SalesMessageUtil.sampleMessageList();
		for (String message : sampleMessages)
			sm.getSalesFromMessage(message);// message processing
	}
}
