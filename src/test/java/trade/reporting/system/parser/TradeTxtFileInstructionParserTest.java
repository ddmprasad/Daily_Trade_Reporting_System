package trade.reporting.system.parser;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.jpmorgan.chase.trade.reporting.system.model.TradeInstruction;
import com.jpmorgan.chase.trade.reporting.system.parser.TradeTxtFileInstructionParser;

public class TradeTxtFileInstructionParserTest {
	
	TradeTxtFileInstructionParser parser = new TradeTxtFileInstructionParser();

	@Test
	public void testParseTradeInstructions() {
		File file = new File("src/test/resources/InputText_parser_test.txt");
		List<TradeInstruction> trades = parser.parseTradeInstructions(file);
		assertEquals(2, trades.size());
	}

}
