/**
 * 
 */
package com.jpmorgan.message.processor.manager;

import java.util.ArrayList;
import java.util.List;

import com.jpmorgan.message.processor.model.AdjustmentOperation;
import com.jpmorgan.message.processor.model.Sale;

/**
 * @author Nidhi Shetty
 * 
 */
public class SalesManager {

	// list of all the sales recorded
	public static List<Sale> salesList = new ArrayList<Sale>();
	// list of adjustments made to the sales
	public static List<String> adjustmentsLog = new ArrayList<String>();
	// list of all the messages processed
	public static List<String> messageLog = new ArrayList<String>();

	/**
	 * This is the getSalesFromMessage method which processes and records every
	 * message.
	 * <p>
	 * Each message is verified to identify the message type and then processed
	 * accordingly.
	 * 
	 * @param message
	 *            - message to be processed
	 * @exception - for invalid input error.
	 * @see Exception
	 */
	public void getSalesFromMessage(String message) throws Exception {
		String[] splitMessage = message.split("\\s+"); // split the message with
														// space character
		boolean isMessageType3 = false;
		boolean isOldProduct = true;
		Sale productDetails = new Sale();

		for (AdjustmentOperation enumVal : AdjustmentOperation.values()) {
			if (enumVal.value().equalsIgnoreCase(splitMessage[0])) {
				isMessageType3 = true;
				break;
			}
		}
		if (isMessageType3 && splitMessage.length == 3) {
			// message type 3 - E.g. Add 20p apples
			String productName = (splitMessage[2].substring(0,
					splitMessage[2].length() - 1));
			int adjustment = Integer.parseInt(splitMessage[1].substring(0,
					splitMessage[1].length() - 1));
			if (adjustment < 1)
				throw new Exception("Please enter a valid adjustment value!!");
			salesAdjustments(splitMessage[0], productName, adjustment);
			adjustmentsLog.add(productName + ":" + splitMessage[0] + " "
					+ adjustment);
		} else if (splitMessage[0].matches("\\d+") && splitMessage.length == 7) {
			// message type 2 - E.g. 20 sales of apples at 10p each
			Sale existingProduct = searchProduct(splitMessage[3].substring(0,
					splitMessage[3].length() - 1));
			if (existingProduct == null) {
				productDetails.setProductType(splitMessage[3].substring(0,
						splitMessage[3].length() - 1));
				isOldProduct = false;
			} else {
				productDetails = existingProduct;
			}
			int count = Integer.parseInt(splitMessage[0]);
			int value = Integer.parseInt(splitMessage[5].substring(0,
					splitMessage[5].length() - 1)) * count;
			// check for negative product count or value
			if (count < 1 || value < 1)
				throw new Exception(
						"Please enter a valid product count and value!!");
			productDetails.setCount(productDetails.getCount() + count);
			productDetails.setValue(productDetails.getValue() + value);
		} else if (splitMessage.length == 3) {
			// message type 1 - E.g. apple at 20p
			Sale existingProduct = searchProduct(splitMessage[0]);
			if (existingProduct == null) {
				productDetails.setProductType(splitMessage[0]);
				isOldProduct = false;
			} else {
				productDetails = existingProduct;
			}
			double value = Integer.parseInt(splitMessage[2].substring(0,
					splitMessage[2].length() - 1));
			productDetails.setProductType(splitMessage[0]);
			// check for negative product value
			if (value < 1)
				throw new Exception("Please enter a valid product value!!");
			productDetails.setCount(productDetails.getCount() + 1);
			productDetails.setValue(productDetails.getValue() + value);
		} else {
			throw new Exception("Invalid message entered!!");
		}
		// every new product type should be added to the sales list
		if (productDetails != null && !isOldProduct)
			salesList.add(productDetails);
		messageLog.add(message);
		checkSales();
	}

	/**
	 * This is the checkSales method which checks for the count of total
	 * messages processed.
	 * <p>
	 * After every 10 messages generateSalesReport method is invoked to generate
	 * sales report. After 50 messages generateAdjustmentsReport method is
	 * invoked to generate adjustments report.
	 */
	public void checkSales() {
		if (messageLog.size() % 10 == 0) {
			System.out.println("--------------------------------------");
			System.out.println("10 new messages logged successfully !!");
			System.out.println("--------------------------------------");
			generateSalesReport();
			System.out.println("--------------------------------------");
		}
		if (messageLog.size() % 50 == 0) {
			System.out.println("--------------------------------------");
			System.out
					.println("50 messages completed!! Application is not accepting new messages!");
			System.out.println("--------------------------------------");
			generateAdjustmentsReport();
			System.out.println("--------------------------------------");
			System.exit(0);
		}
	}

	/**
	 * This is the salesAdjustments method which makes adjustments to specific
	 * sale type based on the adjustment operation specified.
	 * 
	 * @param adjOperation
	 *            - type of adjustment operator.
	 * @param product
	 *            - name of the product for which adjustment is to be made.
	 * @param adjValue
	 *            - adjustment value to be carried out on the specified product.
	 */
	public void salesAdjustments(String adjOperation, String product,
			int adjValue) {
		if (adjOperation.equalsIgnoreCase(AdjustmentOperation.ADD.value())) {
			for (Sale sale : salesList) {
				if (sale.getProductType().equalsIgnoreCase(product))
					sale.setValue(sale.getValue()
							+ (sale.getCount() * adjValue));
			}
		} else if (adjOperation.equalsIgnoreCase(AdjustmentOperation.SUBTRACT
				.value())) {
			for (Sale sale : salesList) {
				if (sale.getProductType().equalsIgnoreCase(product))
					sale.setValue(sale.getValue()
							- (sale.getCount() * adjValue));
			}
		} else if (adjOperation.equalsIgnoreCase(AdjustmentOperation.MULTIPLY
				.value())) {
			for (Sale sale : salesList) {
				if (sale.getProductType().equalsIgnoreCase(product))
					sale.setValue(sale.getValue() * adjValue);
			}
		}
	}

	/**
	 * This is the generateSalesReport method which generates a report of number
	 * of sale of each product and their total value (after every 10 messages).
	 */
	public void generateSalesReport() {
		for (Sale soldProduct : salesList) {
			System.out.println("Name of the Product :"
					+ soldProduct.getProductType());
			System.out
					.println("Total Product Count :" + soldProduct.getCount());
			System.out.println("Total Product Value :" + soldProduct.getValue()
					+ "\n");
		}
	}

	/**
	 * This is the generateAdjustmentsReport method which generates a report of
	 * the adjustments made to each sale type (after 50 messages).
	 */
	public void generateAdjustmentsReport() {
		for (String adjustment : adjustmentsLog) {
			System.out.println(adjustment);
		}
	}

	/**
	 * This is the searchProduct method which searches for a specific Sale entry
	 * based on the product name passed.
	 * 
	 * @param productName
	 *            - name of the product to be searched.
	 * @return - the sale entry matching the specified product name.
	 * @see Sale
	 */
	public Sale searchProduct(String productName) {
		for (Sale sale : salesList) {
			if (sale.getProductType().equalsIgnoreCase(productName))
				return sale;
		}
		return null;
	}

}
