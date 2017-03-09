package org.example.ipaas;

public class Trade {

	private String accountId;
	private String ticker;
	private int amount;
	private String action;
	
	public Trade() {
		
	}
	
	public Trade(String accountId, String ticker, int amount, String action) {
		this.accountId = accountId;
		this.ticker = ticker;
		this.amount = amount;
		this.action = action;
	}
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public String toString() {
		return "\naccountId = " + accountId
			 + "\nticker    = " + ticker
			 + "\namount    = " + amount
			 + "\naction    = " + action;
	}
}
