package org.example.ipaas;

public class OrderDeskBean implements OrderDesk {

	@Override
	public void placeTrade(Trade trade) {
		System.out.println("\n[Order Desk] placing trade order ...\n " + trade);
	}

	@Override
	public Trade getTrade() {
		return new Trade("babo", "RHT", 1000, "BUY");
	}

}
