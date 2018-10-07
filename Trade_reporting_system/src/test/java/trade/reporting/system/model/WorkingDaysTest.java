/**
 * 
 */
package trade.reporting.system.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.chase.trade.reporting.system.factory.WorkingDaysFactory;
import com.jpmorgan.chase.trade.reporting.system.model.WorkingDays;

/**
 * @author prasad
 *
 */
public class WorkingDaysTest {

	WorkingDays arabicWorkingDay = null;
	WorkingDays defaultWorkingDay = null;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		arabicWorkingDay = WorkingDaysFactory.getWorkingDaysStrategy("SAR");
		defaultWorkingDay = WorkingDaysFactory.getWorkingDaysStrategy("USD");
	}

	//Test for Arabic working days
	/**
	 * Test method for {@link trade.reporting.system.model.WorkingDays#findSettlementWorkingDay(java.time.LocalDate)}.
	 */
	@Test
	public void testFindSettlementWorkingDay_arabic_sunday() {
		LocalDate date = LocalDate.of(2018, 10, 07);
		LocalDate outputDate = arabicWorkingDay.findSettlementWorkingDay(date);
		assertEquals(LocalDate.of(2018, 10, 07), outputDate);
	}

	@Test
	public void testFindSettlementWorkingDay_arabic_friday() {
		LocalDate date = LocalDate.of(2018, 10, 05);
		LocalDate outputDate = arabicWorkingDay.findSettlementWorkingDay(date);
		assertEquals(LocalDate.of(2018, 10, 07), outputDate);
	}
	@Test
	public void testFindSettlementWorkingDay_arabic_saturday() {
		LocalDate date = LocalDate.of(2018, 10, 06);
		LocalDate outputDate = arabicWorkingDay.findSettlementWorkingDay(date);
		assertEquals(LocalDate.of(2018, 10, 07), outputDate);
	}
	
	//Test for default working days
	@Test
	public void testFindSettlementWorkingDay_default_sunday() {
		LocalDate date = LocalDate.of(2018, 10, 07);
		LocalDate outputDate = defaultWorkingDay.findSettlementWorkingDay(date);
		assertEquals(LocalDate.of(2018, 10, 8), outputDate);
	}
	@Test
	public void testFindSettlementWorkingDay_default_saturday() {
		LocalDate date = LocalDate.of(2018, 10, 06);
		LocalDate outputDate = defaultWorkingDay.findSettlementWorkingDay(date);
		assertEquals(LocalDate.of(2018, 10, 8), outputDate);
	}
	@Test
	public void testFindSettlementWorkingDay_default_friday() {
		LocalDate date = LocalDate.of(2018, 10, 05);
		LocalDate outputDate = defaultWorkingDay.findSettlementWorkingDay(date);
		assertEquals(LocalDate.of(2018, 10, 05), outputDate);
	}

}
