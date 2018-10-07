/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.parser;

import java.io.File;
import java.util.List;

import com.jpmorgan.chase.trade.reporting.system.model.TradeInstruction;

/**
 * @author prasad
 *
 */
public interface InstructionParser {

	public List<TradeInstruction> parseTradeInstructions(File input);
}
