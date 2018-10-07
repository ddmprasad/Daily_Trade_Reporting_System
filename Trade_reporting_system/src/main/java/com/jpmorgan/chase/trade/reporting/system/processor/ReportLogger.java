/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.processor;

import org.apache.log4j.Logger;

import com.jpmorgan.chase.trade.reporting.system.parser.TradeTxtFileInstructionParser;

/**
 * @author prasad
 *
 */
public class ReportLogger {

	private static final Logger log = Logger.getLogger(TradeTxtFileInstructionParser.class);
	
	public static void logReportOnConsole(StringBuilder report) {
		log.info(report);
	}
}
