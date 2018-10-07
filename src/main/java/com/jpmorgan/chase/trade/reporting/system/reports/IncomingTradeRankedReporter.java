/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.reports;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jpmorgan.chase.trade.reporting.system.constants.TradeInstructionType;
import com.jpmorgan.chase.trade.reporting.system.model.TradeInstruction;
import com.jpmorgan.chase.trade.reporting.system.model.TradeRank;
import com.jpmorgan.chase.trade.reporting.system.processor.ReportLogger;
import com.jpmorgan.chase.trade.reporting.system.processor.TradeSettlementCalculator;

/**
 * @author prasad
 *
 */
public class IncomingTradeRankedReporter implements ReportGenerator {
	
	private Map<LocalDate, LinkedList<TradeInstruction>> inComingReportBydate = null;
	LinkedList<TradeRank> rankings = new LinkedList<>();

	/* (non-Javadoc)
	 * @see com.jpmorgan.chase.trade.reporting.system.processor.ReportGenerator#generateReport(java.util.List)
	 */
	@Override
	public void generateReport(List<TradeInstruction> trades) {
		//fetch grouped trades based on Settlement Date
		inComingReportBydate = TradeSettlementCalculator.getSettlementDatesByTrades(trades, TradeInstructionType.SELL.getInstructionType());
		
		rankings = TradeSettlementCalculator.calculateSettlementRanking(inComingReportBydate);
		
		StringBuilder report = new StringBuilder();
        report
        .append("\n============================================================================\n")
        .append("                  Incoming Daily Trade Entity Ranking Report                 \n")
        .append("-----------------------------------------------------------------------------\n")
        .append("       Date      |     Rank    |     Entity     |      Settlement Amount     |\n")
        .append("=============================================================================\n");
        
        
        
    	for (TradeRank tradeRank : rankings) {
            report
                    .append(tradeRank.getSettlementDate()).append("      |     ")
                    .append(tradeRank.getRank()).append("    |     ")
                    .append(tradeRank.getTradeEntity()).append("     |      ")
                    .append(tradeRank.getTotalSettlementAmount()).append("\n");
        }
        
        //send report to print
        ReportLogger.logReportOnConsole(report);
		
	}

}
