package com.jpmorgan.chase.trade.reporting.system.model;

import java.time.DayOfWeek;
import java.util.HashSet;

public class ArabicWorkingDays extends WorkingDays {

	private static ArabicWorkingDays object = null;
	
	private ArabicWorkingDays() {
		availableBusinessDays = new HashSet<>();
		availableBusinessDays.add(DayOfWeek.SUNDAY);
		availableBusinessDays.add(DayOfWeek.MONDAY);
		availableBusinessDays.add(DayOfWeek.TUESDAY);
		availableBusinessDays.add(DayOfWeek.WEDNESDAY);
		availableBusinessDays.add(DayOfWeek.THURSDAY);
	}

	public static ArabicWorkingDays getInstance() {
		if(object == null) {
			object = new ArabicWorkingDays();
		}
		return object;
	}
}
