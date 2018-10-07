/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.reports;

import java.util.List;

import com.jpmorgan.chase.trade.reporting.system.model.TradeInstruction;

/**
 * @author prasad
 *
 */
public interface ReportGenerator {

	public void generateReport(List<TradeInstruction> trades);
}
