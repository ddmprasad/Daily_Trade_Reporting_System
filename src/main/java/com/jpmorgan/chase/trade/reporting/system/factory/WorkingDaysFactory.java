/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.factory;

import org.apache.log4j.Logger;

import com.jpmorgan.chase.trade.reporting.system.constants.TradeReportConstant;
import com.jpmorgan.chase.trade.reporting.system.model.ArabicWorkingDays;
import com.jpmorgan.chase.trade.reporting.system.model.DefaultWorkingDays;
import com.jpmorgan.chase.trade.reporting.system.model.WorkingDays;

/**
 * @author prasad
 *
 */
public class WorkingDaysFactory {

	private static final Logger log = Logger.getLogger(WorkingDaysFactory.class);
	/**
	 * This method gives the strategy for object for Settlement Date Calculations
	 * @param currency
	 * @return
	 */
	public static WorkingDays getWorkingDaysStrategy(String currency) {
		WorkingDays wd = null;
		if(TradeReportConstant.CURRENCY_AED.equalsIgnoreCase(currency) ||
				TradeReportConstant.CURRENCY_SAR.equalsIgnoreCase(currency)) {
			log.debug("requested for arabic working days strategy");
			wd = ArabicWorkingDays.getInstance();
		}else {
			log.debug("requested for default working days strategy");
			//default strategy for all other currencies
			wd = DefaultWorkingDays.getInstance();
		}
		return wd;
	}
}
