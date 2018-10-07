/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jpmorgan.chase.trade.reporting.system.constants.TradeReportConstant;
import com.jpmorgan.chase.trade.reporting.system.exception.TradeReportException;
import com.jpmorgan.chase.trade.reporting.system.model.TradeInstruction;
import com.jpmorgan.chase.trade.reporting.system.utils.TradeDataFormatter;

/**
 * @author prasad
 *
 */
public class TradeTxtFileInstructionParser implements InstructionParser {

	private static final Logger log = Logger.getLogger(TradeTxtFileInstructionParser.class);

	List<TradeInstruction> trades = new ArrayList<TradeInstruction>();

	/**
	 * @param File
	 * @return Trades
	 */
	public List<TradeInstruction> parseTradeInstructions(File input) {
		String readTradeInstruction = "";
		try {
			log.debug("method parseTradeInstructions Start : loading file");
			BufferedReader b = getFileData(input);
			log.debug("method parseTradeInstructions : loading file complete");
			log.debug("method parseTradeInstructions : Build List of instructions");
			while((readTradeInstruction = b.readLine())!=null){
				if(!readTradeInstruction.isEmpty()) {
					TradeInstruction trade = buildTradeInstruction(readTradeInstruction);
					if(trade != null) {
						trades.add(trade);
					}else {
						log.error(TradeReportConstant.INVALID_TRADE_INSTRUCTION_EMPTY);
					}
				}else {
					log.error(TradeReportConstant.INVALID_TRADE_INSTRUCTION_EMPTY);
				}
				readTradeInstruction = "";
			}
			log.debug("method parseTradeInstructions finish");
		} catch (IOException e) {
			log.error(TradeReportConstant.ERROR_LOADING_FILE_DATA);
		} catch (TradeReportException e) {
			log.error("Error in method parseTradeInstructions : " +e.getMessage());
		}
		return trades;
	}
	
	/**
	 * Trade instruction is built using builder pattern
	 * @param readTradeInstruction
	 * @return
	 */
	private TradeInstruction buildTradeInstruction(String readTradeInstruction) {
		String [] inputTrade = readTradeInstruction.split(TradeReportConstant.PIPE_SEPERATOR);
		if(inputTrade.length == TradeReportConstant.INCOMING_COLUMN_MAX_LENGTH) {
			return new TradeInstruction.TradeInstructionBuilder().setEntity(inputTrade[0])
					.setTradeinstructionType(TradeDataFormatter.getTradeInstructionType(inputTrade[1]))
					.setAgreedFx(new BigDecimal(inputTrade[2]))
					.setCurrency(inputTrade[3])
					.setInstructionDate(TradeDataFormatter.getLocaleDate(inputTrade[4]))
					.setSettlementDate(TradeDataFormatter.getLocaleDate(inputTrade[5]))
					.setUnits(Integer.parseInt(inputTrade[6]))
					.setPricePerUnit(new BigDecimal(inputTrade[7]))
					.build();
		}else {
			log.error(TradeReportConstant.INVALID_TRADE_INSTRUCTION_MISSING);
		}
		return null;
	}

	/**
	 * This methods load incoming text file with trade instructions
	 * @param file
	 * @return
	 * @throws TradeReportException
	 */
	private static BufferedReader getFileData(File file) throws TradeReportException{
		try{
			return new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			log.error(TradeReportConstant.ERROR_LOADING_FILE);
			throw new TradeReportException(TradeReportConstant.ERROR_LOADING_FILE);
		}
	}
}
