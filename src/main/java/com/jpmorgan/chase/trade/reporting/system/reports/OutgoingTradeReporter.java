/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.reports;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.jpmorgan.chase.trade.reporting.system.constants.TradeInstructionType;
import com.jpmorgan.chase.trade.reporting.system.model.TradeInstruction;
import com.jpmorgan.chase.trade.reporting.system.processor.ReportLogger;
import com.jpmorgan.chase.trade.reporting.system.processor.TradeSettlementCalculator;

/**
 * @author prasad
 *
 */
public class OutgoingTradeReporter implements ReportGenerator {
	
	private Map<LocalDate, BigDecimal> outGoingReport = new TreeMap<>();

	/* (non-Javadoc)
	 * @see com.jpmorgan.chase.trade.reporting.system.processor.ReportGenerator#generateReport(java.util.List)
	 */
	@Override
	public void generateReport(List<TradeInstruction> trades) {
		
		outGoingReport = TradeSettlementCalculator.calculateSettlementAmountByDate(trades, TradeInstructionType.BUY.getInstructionType());
        StringBuilder report = new StringBuilder();
        report.append("\n==================================================\n")
        .append("           Outgoing Daily Trade Report              \n")
        .append("----------------------------------------------------\n")
        .append("      Settlement Date      |     Traded Amount      \n")
        .append("====================================================\n");

        for (LocalDate date : outGoingReport.keySet()) {
        	report.append(date).append("      |     ").append(outGoingReport.get(date)).append("\n");
		}
        
        ReportLogger.logReportOnConsole(report);
	}

}
