/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.constants;

/**
 * @author prasad
 *
 */
public enum TradeInstructionType {

	BUY("B"),
    SELL("S");

	private String instructionType; // Buy / Sell

	private TradeInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}

	/**
	 * @return the instructionType
	 */
	public String getInstructionType() {
		return instructionType;
	}
}
