package trade.reporting.system.factory;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jpmorgan.chase.trade.reporting.system.factory.WorkingDaysFactory;
import com.jpmorgan.chase.trade.reporting.system.model.ArabicWorkingDays;
import com.jpmorgan.chase.trade.reporting.system.model.DefaultWorkingDays;
import com.jpmorgan.chase.trade.reporting.system.model.WorkingDays;

public class WorkingDaysFactoryTest {

	@Test
	public void testGetWorkingDaysStrategy_Arabic() {
		WorkingDays wd = WorkingDaysFactory.getWorkingDaysStrategy("AED");
		assertTrue(wd instanceof ArabicWorkingDays);
	}

	@Test
	public void testGetWorkingDaysStrategy_Default() {
		WorkingDays wd = WorkingDaysFactory.getWorkingDaysStrategy("SGD");
		assertTrue(wd instanceof DefaultWorkingDays);
	}
}
