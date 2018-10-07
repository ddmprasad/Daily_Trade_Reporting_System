/**
 * 
 */
package trade.reporting.system.utils;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.jpmorgan.chase.trade.reporting.system.constants.TradeInstructionType;
import com.jpmorgan.chase.trade.reporting.system.utils.TradeDataFormatter;

/**
 * @author prasad
 *
 */
public class TradeDataFormatterTest {

	/**
	 * Test method for {@link trade.reporting.system.utils.TradeDataFormatter#getLocaleDate(java.lang.String)}.
	 */
	@Test
	public void testGetLocaleDate() {
		String inputDate = "10-Mar-2017";
		LocalDate date = TradeDataFormatter.getLocaleDate(inputDate);
		assertEquals(date, LocalDate.of(2017, 3, 10));;
	}

	/**
	 * Test method for {@link trade.reporting.system.utils.TradeDataFormatter#getTradeInstructionType(java.lang.String)}.
	 */
	@Test
	public void testGetTradeInstructionType() {
		String instrType = "S";
		TradeInstructionType tradeInstr = TradeDataFormatter.getTradeInstructionType(instrType);
		assertEquals(TradeInstructionType.SELL.getInstructionType(), tradeInstr.getInstructionType());
	}

}
