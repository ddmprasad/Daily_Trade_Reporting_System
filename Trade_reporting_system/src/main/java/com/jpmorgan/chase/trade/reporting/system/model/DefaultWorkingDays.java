/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.model;

import java.time.DayOfWeek;
import java.util.HashSet;

/**
 * @author prasad
 *
 */
public class DefaultWorkingDays extends WorkingDays {

	private static DefaultWorkingDays object = null;
	
	private DefaultWorkingDays() {
		availableBusinessDays = new HashSet<>();
		availableBusinessDays.add(DayOfWeek.MONDAY);
		availableBusinessDays.add(DayOfWeek.TUESDAY);
		availableBusinessDays.add(DayOfWeek.WEDNESDAY);
		availableBusinessDays.add(DayOfWeek.THURSDAY);
		availableBusinessDays.add(DayOfWeek.FRIDAY);
	}

	public static DefaultWorkingDays getInstance() {
		if(object == null) {
			object = new DefaultWorkingDays();
		}
		return object;
	}
}
