/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.exception;

/**
 * @author prasad
 *
 */
public class TradeReportException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1453438135977968288L;

	public TradeReportException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public TradeReportException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TradeReportException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public TradeReportException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TradeReportException(Throwable cause) {
		super(cause);
	}
	

}
