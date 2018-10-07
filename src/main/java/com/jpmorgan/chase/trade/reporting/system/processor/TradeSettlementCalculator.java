package com.jpmorgan.chase.trade.reporting.system.processor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.jpmorgan.chase.trade.reporting.system.model.TradeInstruction;
import com.jpmorgan.chase.trade.reporting.system.model.TradeRank;

/**
 * @author prasad
 *
 */
public class TradeSettlementCalculator {

	private static final Logger log = Logger.getLogger(TradeSettlementCalculator.class);
	/**
	 * This method calculate Settlement amount for incoming / outgoing trades
	 * @param trades
	 * @param instrType
	 * @return
	 */
	public static Map<LocalDate, BigDecimal> calculateSettlementAmountByDate(List<TradeInstruction> trades, String instrType) {
		log.debug("calculateSettlementAmountByDate starts here");
		Map<LocalDate, BigDecimal> tradeReport = new TreeMap<>(Collections.reverseOrder());
		log.debug("calculateSettlementAmountByDate for instructions type "+ instrType);
		for(TradeInstruction trade : trades) {
			//process only records with requested incoming / outgoing calls
			if(instrType.equalsIgnoreCase(trade.getTradeinstructionType().getInstructionType())) {
				BigDecimal amt = tradeReport.get(trade.getSettlementDate());
				if(amt == null) {
					//add new entry for new settlement date
					tradeReport.put(trade.getSettlementDate(), trade.getTotalAmountTraded());
				}else {
					//update the entry
					tradeReport.put(trade.getSettlementDate(), amt.add(trade.getTotalAmountTraded()));
				}
			}
		}
		log.debug("calculateSettlementAmountByDate ends here");
		return tradeReport;
	}
	
	/**
	 * This method determines the trade list for settlement date  
	 * @param trades
	 * @param instrType
	 * @return
	 */
	public static Map<LocalDate, LinkedList<TradeInstruction>> getSettlementDatesByTrades(List<TradeInstruction> trades, String instrType) {
		log.debug("getSettlementDatesByTrades starts here");
		Map<LocalDate, LinkedList<TradeInstruction>> tradeReport = new TreeMap<>();
		log.debug("getSettlementDatesByTrades for instructions type "+ instrType);
		for(TradeInstruction trade : trades) {
			if(instrType.equalsIgnoreCase(trade.getTradeinstructionType().getInstructionType())) {
				LinkedList<TradeInstruction> tradeList = tradeReport.get(trade.getSettlementDate());
				if(tradeList == null) {
					//new entry of settlement date create new list for trades
					tradeList = new LinkedList<TradeInstruction>();
				}
				tradeList.add(trade);
				tradeReport.put(trade.getSettlementDate(), tradeList);
			}
		}
		log.debug("getSettlementDatesByTrades ends here");
		return tradeReport;
	}

	/**
	 * This method calculate all the rankings for entities
	 * @param reportBydate
	 * @return
	 */
	public static LinkedList<TradeRank> calculateSettlementRanking(Map<LocalDate, LinkedList<TradeInstruction>> reportBydate) {
		log.debug("calculateSettlementRanking starts here");
		LinkedList<TradeInstruction> reportTradesBydate = null;
		LinkedList<TradeRank> finalRanks = new LinkedList<>();
		for (LocalDate date : reportBydate.keySet()) {
			reportTradesBydate = reportBydate.get(date);
			finalRanks.addAll(dataProcessingForRanking(reportTradesBydate));
		}
		Collections.sort(finalRanks, (new Comparator<TradeRank>() {
			@Override
			public int compare(TradeRank rank1, TradeRank rank2) {
				// compare and sort list
				return rank1.getTotalSettlementAmount().compareTo(rank2.getTotalSettlementAmount());
			}
		}).reversed());
		
		//Assigning ranking for all the entities
		finalRanks = assignRankings(finalRanks);
		log.debug("calculateSettlementRanking ends here");
		return finalRanks;
	}

	/**
	 * This method pre-process and built data for determining the rankings
	 * @param reportTradesBydate
	 * @return
	 */
	private static List<TradeRank> dataProcessingForRanking(LinkedList<TradeInstruction> reportTradesBydate) {
		LinkedList<TradeRank> tradeRanks = new LinkedList<>();;
		Map<String, TradeRank> entityMap = new HashMap<>();
		for(TradeInstruction trade : reportTradesBydate) {
			TradeRank rank = entityMap.get(trade.getEntity());
			if(rank == null) {
				rank = new TradeRank(trade.getEntity(), trade.getSettlementDate(), trade.getTotalAmountTraded());
				entityMap.put(trade.getEntity(), rank);
			}else {
				rank.setTotalSettlementAmount(rank.getTotalSettlementAmount().add(trade.getTotalAmountTraded()));
				entityMap.put(trade.getEntity(), rank);
			}
		}
		for (String entity : entityMap.keySet()) {
        	tradeRanks.add(entityMap.get(entity));
		}
		return tradeRanks;
	}

	/**
	 * This method determines and assign the ranks at trade level
	 * @param finalRanks
	 * @return
	 */
	private static LinkedList<TradeRank> assignRankings(LinkedList<TradeRank> finalRanks) {
		int rank = 0;
    	BigDecimal prevAmt = new BigDecimal(0);
    	for (TradeRank tradeRank : finalRanks) {
    		rank = prevAmt.equals(tradeRank.getTotalSettlementAmount()) ? rank : rank+1;
            tradeRank.setRank(rank);
            prevAmt = tradeRank.getTotalSettlementAmount();
        }
		return finalRanks;
	}
}
