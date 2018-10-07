/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.constants;

/**
 * @author prasad
 *
 */
public class TradeReportConstant {

	public static final String PIPE_SEPERATOR = ":";
	public static final int INCOMING_COLUMN_MAX_LENGTH = 8;
	
	public static final String CURRENCY_AED = "AED";
	public static final String CURRENCY_SAR = "SAR";
	
	public static final String ERROR_LOADING_FILE = "Error in loading input File";
	public static final String ERROR_LOADING_FILE_DATA = "Error in loading file data";
	public static final String INVALID_FILE = "Invalid input : file not valid";
	public static final String INVALID_TRADE_INSTRUCTION_MISSING = "Invalid trade instruction : Missing fileds in Record";
	public static final String INVALID_TRADE_INSTRUCTION_EMPTY = "Invalid trade instruction : Empty Record";
	
	public static final String TRADE_DATE_FORMAT = "dd-MMM-yyyy";
}
