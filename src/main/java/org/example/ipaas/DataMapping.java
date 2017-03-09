package org.example.ipaas;

import twitter4j.Status;

public class DataMapping {
	
	/**
	 * Expected Tweet format:
	 *    [mention] [action] [quantity] [ticker]
	 *    e.g. \@RHDemoAccount BUY 300 RHT  
	 */

	public Trade map(Status status) {
		Trade trade = new Trade();
		trade.setAccountId(status.getUser().getScreenName());
		String[] text = status.getText().split(" ");
		if (text.length != 5) {
			throw new IllegalArgumentException("Content of tweet invalid for trade order");
		}
		trade.setAction(text[2]);
		trade.setAmount(Integer.parseInt(text[3]));
		trade.setTicker(text[4]);
		
		return trade;
	}
}
