/**
 * 
 */
package trade.reporting.system;

import java.io.File;

import org.junit.Test;

import com.jpmorgan.chase.trade.reporting.system.processor.DailyTradeReportProcessor;

/**
 * @author prasad
 *
 */
public class DailyTradeReportProcessorTest {

	/**
	 * Test method for {@link com.jpmorgan.chase.trade.reporting.system.processor.DailyTradeReportProcessor#processDailtTradeReports(java.io.File)}.
	 */
	@Test
	public void testProcessDailtTradeReports() {
		DailyTradeReportProcessor processor = new DailyTradeReportProcessor();
		File file = new File("src/test/resources/InputText.txt");
		try{
			processor.processDailtTradeReports(file);
		}catch (Exception e) {
			
		}
	}

}
