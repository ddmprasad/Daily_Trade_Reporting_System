/**
 * 
 */
package com.jpmorgan.chase.trade.reporting.system.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author prasad
 *
 */
public class TradeRank {
   
    private final String tradeEntity;
    private final LocalDate settlementDate;
    private BigDecimal totalSettlementAmount;
    private int rank;
	/**
	 * @param rank
	 * @param tradeEntity
	 * @param settlementDate
	 * @param totalSettlementAmount
	 */
	public TradeRank(String tradeEntity, LocalDate settlementDate, BigDecimal totalSettlementAmount) {
		this.tradeEntity = tradeEntity;
		this.settlementDate = settlementDate;
		this.totalSettlementAmount = totalSettlementAmount;
	}
	/**
	 * @return the totalSettlementAmount
	 */
	public BigDecimal getTotalSettlementAmount() {
		return totalSettlementAmount;
	}
	/**
	 * @param totalSettlementAmount the totalSettlementAmount to set
	 */
	public void setTotalSettlementAmount(BigDecimal totalSettlementAmount) {
		this.totalSettlementAmount = totalSettlementAmount;
	}
	/**
	 * @return the tradeEntity
	 */
	public String getTradeEntity() {
		return tradeEntity;
	}
	/**
	 * @return the settlementDate
	 */
	public LocalDate getSettlementDate() {
		return settlementDate;
	}
	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((settlementDate == null) ? 0 : settlementDate.hashCode());
		result = prime * result + ((totalSettlementAmount == null) ? 0 : totalSettlementAmount.hashCode());
		result = prime * result + ((tradeEntity == null) ? 0 : tradeEntity.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeRank other = (TradeRank) obj;
		if (settlementDate == null) {
			if (other.settlementDate != null)
				return false;
		} else if (!settlementDate.equals(other.settlementDate))
			return false;
		if (totalSettlementAmount == null) {
			if (other.totalSettlementAmount != null)
				return false;
		} else if (!totalSettlementAmount.equals(other.totalSettlementAmount))
			return false;
		if (tradeEntity == null) {
			if (other.tradeEntity != null)
				return false;
		} else if (!tradeEntity.equals(other.tradeEntity))
			return false;
		return true;
	}
	
}
