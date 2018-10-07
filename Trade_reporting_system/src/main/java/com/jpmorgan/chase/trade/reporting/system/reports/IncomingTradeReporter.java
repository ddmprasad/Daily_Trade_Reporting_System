/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.reports;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.jpmorgan.chase.trade.reporting.system.constants.TradeInstructionType;
import com.jpmorgan.chase.trade.reporting.system.model.TradeInstruction;
import com.jpmorgan.chase.trade.reporting.system.processor.ReportLogger;
import com.jpmorgan.chase.trade.reporting.system.processor.TradeSettlementCalculator;

/**
 * @author prasad
 *
 */
public class IncomingTradeReporter implements ReportGenerator {
	
	private Map<LocalDate, BigDecimal> incomingGoingReport = null;

	/* (non-Javadoc)
	 * @see com.jpmorgan.chase.trade.reporting.system.processor.ReportGenerator#generateReport(java.util.List)
	 */
	@Override
	public void generateReport(List<TradeInstruction> trades) {
		
		incomingGoingReport = TradeSettlementCalculator.calculateSettlementAmountByDate(trades, TradeInstructionType.SELL.getInstructionType());
        StringBuilder report = new StringBuilder();
        report
        .append("\n===================================================\n")
        .append("           Incoming Daily Trade Report              \n")
        .append("----------------------------------------------------\n")
        .append("      Settlement Date      |     Traded Amount      \n")
        .append("====================================================\n");
        //build report
        for (LocalDate date : incomingGoingReport.keySet()) {
        	report.append(date).append("      |     ").append(incomingGoingReport.get(date)).append("\n");
		}
        //send report to print
        ReportLogger.logReportOnConsole(report);
	}

}
