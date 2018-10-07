/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.processor;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import com.jpmorgan.chase.trade.reporting.system.constants.TradeReportConstant;
import com.jpmorgan.chase.trade.reporting.system.exception.TradeReportException;
import com.jpmorgan.chase.trade.reporting.system.factory.WorkingDaysFactory;
import com.jpmorgan.chase.trade.reporting.system.model.TradeInstruction;
import com.jpmorgan.chase.trade.reporting.system.model.WorkingDays;
import com.jpmorgan.chase.trade.reporting.system.parser.TradeTxtFileInstructionParser;
import com.jpmorgan.chase.trade.reporting.system.reports.IncomingTradeRankedReporter;
import com.jpmorgan.chase.trade.reporting.system.reports.IncomingTradeReporter;
import com.jpmorgan.chase.trade.reporting.system.reports.OutgoingTradeRankedReporter;
import com.jpmorgan.chase.trade.reporting.system.reports.OutgoingTradeReporter;
import com.jpmorgan.chase.trade.reporting.system.reports.ReportGenerator;

/**
 * @author prasad
 *
 */
public class DailyTradeReportProcessor {
	
	private static final Logger log = Logger.getLogger(DailyTradeReportProcessor.class);

	private TradeTxtFileInstructionParser parser = new TradeTxtFileInstructionParser();
	private List<TradeInstruction> tradeInstructionsList = null;
	private ReportGenerator dailyReport = null;
	
	public void processDailtTradeReports(File file) throws TradeReportException {
		if(file != null && file.exists()) {
			log.debug("input fie recieved for processing daily trade reports");
			//get the input instructions parsed to list
			tradeInstructionsList = parser.parseTradeInstructions(file);
			//calculate settlement date using factory and strategy of working days
			for (TradeInstruction tradeInstruction : tradeInstructionsList) {
				WorkingDays wd = WorkingDaysFactory.getWorkingDaysStrategy(tradeInstruction.getCurrency());
				LocalDate settlementDate = wd.findSettlementWorkingDay(tradeInstruction.getSettlementDate());
				tradeInstruction.setSettlementDate(settlementDate);
			}
			
			//incoming report
			log.debug("incoming trade reports processig starts");
			dailyReport = new IncomingTradeReporter();
			dailyReport.generateReport(tradeInstructionsList);
			log.debug("incoming trade reports processig ends");
			
			//outgoing report
			log.debug("outgoing trade reports processig starts");
			dailyReport = new OutgoingTradeReporter();
			dailyReport.generateReport(tradeInstructionsList);
			log.debug("outgoing trade reports processig ends");
			
			//incoming report with ranking
			log.debug("incoming entity ranking trade reports processig starts");
			dailyReport = new IncomingTradeRankedReporter();
			dailyReport.generateReport(tradeInstructionsList);
			log.debug("incoming entity ranking trade reports processig ends");
			
			//outgoing report with ranking
			log.debug("outgoing entity ranking trade reports processig starts");
			dailyReport = new OutgoingTradeRankedReporter();
			dailyReport.generateReport(tradeInstructionsList);
			log.debug("incoming entity ranking trade reports processig ends");
			
			log.debug("Daily trade reports end here");
		}else {
			log.debug(TradeReportConstant.INVALID_FILE);
			throw new TradeReportException(TradeReportConstant.INVALID_FILE);
		}
	}
}
