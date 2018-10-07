/**
 * 
 */
package trade.reporting.system;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.chase.trade.reporting.system.constants.TradeInstructionType;
import com.jpmorgan.chase.trade.reporting.system.model.TradeInstruction;
import com.jpmorgan.chase.trade.reporting.system.model.TradeRank;
import com.jpmorgan.chase.trade.reporting.system.processor.TradeSettlementCalculator;
import com.jpmorgan.chase.trade.reporting.system.utils.TradeDataFormatter;

/**
 * @author prasad
 *
 */
public class TradeSettlementCalculatorTest {

	
	List<TradeInstruction> trades = null;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		trades = new LinkedList<>();
		trades.add(new TradeInstruction.TradeInstructionBuilder().setEntity("one")
					.setTradeinstructionType(TradeDataFormatter.getTradeInstructionType("B"))
					.setAgreedFx(new BigDecimal("0.50"))
					.setCurrency("SGD")
					.setInstructionDate(TradeDataFormatter.getLocaleDate("01-Jun-2018"))
					.setSettlementDate(TradeDataFormatter.getLocaleDate("01-Jul-2019"))
					.setUnits(Integer.parseInt("100"))
					.setPricePerUnit(new BigDecimal("100.50"))
					.build());
		trades.add(new TradeInstruction.TradeInstructionBuilder().setEntity("two")
				.setTradeinstructionType(TradeDataFormatter.getTradeInstructionType("S"))
				.setAgreedFx(new BigDecimal("0.65"))
				.setCurrency("AED")
				.setInstructionDate(TradeDataFormatter.getLocaleDate("01-Feb-2018"))
				.setSettlementDate(TradeDataFormatter.getLocaleDate("01-Nov-2019"))
				.setUnits(Integer.parseInt("125"))
				.setPricePerUnit(new BigDecimal("200.75"))
				.build());
	}

	/**
	 * Test method for {@link com.jpmorgan.chase.trade.reporting.system.processor.TradeSettlementCalculator#calculateSettlementAmountByDate(java.util.List, java.lang.String)}.
	 */
	@Test
	public void testCalculateSettlementAmountByDate_sell_instruction () {
		Map<LocalDate, BigDecimal> result = TradeSettlementCalculator.calculateSettlementAmountByDate(trades,
				TradeInstructionType.SELL.getInstructionType());
		assertEquals(1, result.size());
	}

	/**
	 * Test method for {@link com.jpmorgan.chase.trade.reporting.system.processor.TradeSettlementCalculator#calculateSettlementAmountByDate(java.util.List, java.lang.String)}.
	 */
	@Test
	public void testCalculateSettlementAmountByDate_buy_instruction() {
		Map<LocalDate, BigDecimal> result = TradeSettlementCalculator.calculateSettlementAmountByDate(trades,
				TradeInstructionType.BUY.getInstructionType());
		assertEquals(1, result.size());
	}
	
	/**
	 * Test method for {@link com.jpmorgan.chase.trade.reporting.system.processor.TradeSettlementCalculator#getSettlementDatesByTrades(java.util.List, java.lang.String)}.
	 */
	@Test
	public void testGetSettlementDatesByTrades_sell_instruction() {
		Map<LocalDate, LinkedList<TradeInstruction>> result = TradeSettlementCalculator.getSettlementDatesByTrades(trades,
				TradeInstructionType.SELL.getInstructionType());
		assertEquals(1, result.size());
	}

	/**
	 * Test method for {@link com.jpmorgan.chase.trade.reporting.system.processor.TradeSettlementCalculator#getSettlementDatesByTrades(java.util.List, java.lang.String)}.
	 */
	@Test
	public void testGetSettlementDatesByTrades_buy_instruction() {
		Map<LocalDate, LinkedList<TradeInstruction>> result = TradeSettlementCalculator.getSettlementDatesByTrades(trades,
				TradeInstructionType.BUY.getInstructionType());
		assertEquals(1, result.size());
	}
	
	/**
	 * Test method for {@link com.jpmorgan.chase.trade.reporting.system.processor.TradeSettlementCalculator#calculateSettlementRanking(java.util.Map)}.
	 */
	@Test
	public void testCalculateSettlementRanking_buy_instruction() {
		Map<LocalDate, LinkedList<TradeInstruction>> result = TradeSettlementCalculator.getSettlementDatesByTrades(trades,
				TradeInstructionType.BUY.getInstructionType());
		LinkedList<TradeRank> rankings = TradeSettlementCalculator.calculateSettlementRanking(result);
		assertEquals(1, rankings.size());
	}

	/**
	 * Test method for {@link com.jpmorgan.chase.trade.reporting.system.processor.TradeSettlementCalculator#calculateSettlementRanking(java.util.Map)}.
	 */
	@Test
	public void testCalculateSettlementRanking_sell_instruction() {
		Map<LocalDate, LinkedList<TradeInstruction>> result = TradeSettlementCalculator.getSettlementDatesByTrades(trades,
				TradeInstructionType.SELL.getInstructionType());
		LinkedList<TradeRank> rankings = TradeSettlementCalculator.calculateSettlementRanking(result);
		assertEquals(1, rankings.size());
	}
}
