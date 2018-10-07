/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jpmorgan.chase.trade.reporting.system.constants.TradeInstructionType;

/**
 * @author prasad
 *
 */
public class TradeInstruction {

	private String entity;
	private TradeInstructionType tradeinstructionType;
	private BigDecimal agreedFx;
	private String currency;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private int units;
	private BigDecimal pricePerUnit;
	private BigDecimal totalAmountTraded;
	
	/**
	 * @param entity
	 * @param tradeinstructionType
	 * @param agreedFx
	 * @param currency
	 * @param instructionDate
	 * @param settlementDate
	 * @param units
	 * @param pricePerUnit
	 * @param totalAmountTraded
	 */
	private TradeInstruction(String entity, TradeInstructionType tradeinstructionType, BigDecimal agreedFx,
			String currency, LocalDate instructionDate, LocalDate settlementDate, int units, BigDecimal pricePerUnit) {
		this.entity = entity;
		this.tradeinstructionType = tradeinstructionType;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
		this.totalAmountTraded = calclateTotalAmountTraded();
	}
	
	/**
	 * 
	 * @return
	 */
	private BigDecimal calclateTotalAmountTraded() {
		return this.pricePerUnit.multiply(BigDecimal.valueOf(this.units))
				.multiply(this.agreedFx);
	}
	/**
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}
	/**
	 * @param entity the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}
	/**
	 * @return the tradeinstructionType
	 */
	public TradeInstructionType getTradeinstructionType() {
		return tradeinstructionType;
	}
	/**
	 * @param tradeinstructionType the tradeinstructionType to set
	 */
	public void setTradeinstructionType(TradeInstructionType tradeinstructionType) {
		this.tradeinstructionType = tradeinstructionType;
	}
	/**
	 * @return the agreedFx
	 */
	public BigDecimal getAgreedFx() {
		return agreedFx;
	}
	/**
	 * @param agreedFx the agreedFx to set
	 */
	public void setAgreedFx(BigDecimal agreedFx) {
		this.agreedFx = agreedFx;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the instructionDate
	 */
	public LocalDate getInstructionDate() {
		return instructionDate;
	}
	/**
	 * @param instructionDate the instructionDate to set
	 */
	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}
	/**
	 * @return the settlementDate
	 */
	public LocalDate getSettlementDate() {
		return settlementDate;
	}
	/**
	 * @param settlementDate the settlementDate to set
	 */
	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}
	/**
	 * @return the units
	 */
	public int getUnits() {
		return units;
	}
	/**
	 * @param units the units to set
	 */
	public void setUnits(int units) {
		this.units = units;
	}
	/**
	 * @return the pricePerUnit
	 */
	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}
	/**
	 * @param pricePerUnit the pricePerUnit to set
	 */
	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	/**
	 * @return the totalAmountTraded
	 */
	public BigDecimal getTotalAmountTraded() {
		return totalAmountTraded;
	}
	/**
	 * @param totalAmountTraded the totalAmountTraded to set
	 */
	public void setTotalAmountTraded(BigDecimal totalAmountTraded) {
		this.totalAmountTraded = totalAmountTraded;
	}
	
	public static class TradeInstructionBuilder {

		private String entity;
		private TradeInstructionType tradeinstructionType;
		private BigDecimal agreedFx;
		private String currency;
		private LocalDate instructionDate;
		private LocalDate settlementDate;
		private int units;
		private BigDecimal pricePerUnit;
		/**
		 * 
		 */
		public TradeInstructionBuilder() {
		}
		
		/**
		 * @param entity the entity to set
		 */
		public TradeInstructionBuilder setEntity(String entity) {
			this.entity = entity;
			return this;
		}
		/**
		 * @param tradeinstructionType the tradeinstructionType to set
		 */
		public TradeInstructionBuilder setTradeinstructionType(TradeInstructionType tradeinstructionType) {
			this.tradeinstructionType = tradeinstructionType;
			return this;
		}
		/**
		 * @param agreedFx the agreedFx to set
		 */
		public TradeInstructionBuilder setAgreedFx(BigDecimal agreedFx) {
			this.agreedFx = agreedFx;
			return this;
		}
		/**
		 * @param currency the currency to set
		 */
		public TradeInstructionBuilder setCurrency(String currency) {
			this.currency = currency;
			return this;
		}
		/**
		 * @param instructionDate the instructionDate to set
		 */
		public TradeInstructionBuilder setInstructionDate(LocalDate instructionDate) {
			this.instructionDate = instructionDate;
			return this;
		}
		/**
		 * @param settlementDate the settlementDate to set
		 */
		public TradeInstructionBuilder setSettlementDate(LocalDate settlementDate) {
			this.settlementDate = settlementDate;
			return this;
		}
		/**
		 * @param units the units to set
		 */
		public TradeInstructionBuilder setUnits(int units) {
			this.units = units;
			return this;
		}
		/**
		 * @param pricePerUnit the pricePerUnit to set
		 */
		public TradeInstructionBuilder setPricePerUnit(BigDecimal pricePerUnit) {
			this.pricePerUnit = pricePerUnit;
			return this;
		}
		
		public TradeInstruction build() {
			return new TradeInstruction(entity, tradeinstructionType, agreedFx, currency,
					instructionDate, settlementDate, units, pricePerUnit);
		}
	}
}
