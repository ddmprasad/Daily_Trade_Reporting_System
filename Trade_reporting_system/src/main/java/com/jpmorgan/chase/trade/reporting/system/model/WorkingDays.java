/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * @author prasad
 *
 */
public abstract class WorkingDays {
	
	private static final Logger log = Logger.getLogger(WorkingDays.class);
	
	protected Set<DayOfWeek> availableBusinessDays = null;

	/**
	 * 
	 * @param inpuDate
	 * @return
	 */
	public LocalDate findSettlementWorkingDay(LocalDate inpuDate) {
		final DayOfWeek inputDay = inpuDate.getDayOfWeek();
		log.debug("input day of the settlement date is : "+inputDay);
        // in case the given date is working date, just return this
        if (availableBusinessDays.contains(inputDay)) {
            return inpuDate;
        }
        // otherwise look for the next working date (Recursively)
        return findSettlementWorkingDay(inpuDate.plusDays(1));
	}
}
