/**
 * 
 */
package com.jpmorgan.message.processor.model;

/**
 * @author Nidhi Shetty
 * 
 */
public enum AdjustmentOperation {

	ADD("Add"), SUBTRACT("Subtract"), MULTIPLY("Multiply");

	private final String value;

	/**
	 * @param value
	 */
	private AdjustmentOperation(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public static AdjustmentOperation fromValue(final String v) {
		for (final AdjustmentOperation c : AdjustmentOperation.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v + "");
	}

}
