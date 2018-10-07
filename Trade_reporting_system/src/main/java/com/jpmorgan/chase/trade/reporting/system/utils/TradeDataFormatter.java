/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.jpmorgan.chase.trade.reporting.system.constants.TradeInstructionType;
import com.jpmorgan.chase.trade.reporting.system.constants.TradeReportConstant;

/**
 * @author prasad
 *
 */
public class TradeDataFormatter {
	
	public static LocalDate getLocaleDate(String input) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern(TradeReportConstant.TRADE_DATE_FORMAT);
		return LocalDate.parse(input,format);
	}

	public static TradeInstructionType getTradeInstructionType(String input) {
		return input.equalsIgnoreCase("B") ? TradeInstructionType.BUY : TradeInstructionType.SELL;
	}
}
