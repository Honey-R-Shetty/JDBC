package com.order.model;

import java.sql.Date;

public class Order {
	public int orderId, custId;
	public Date orderDate;
	public double amount;

	public Order(int orderId, int custId, Date orderDate, double amount) {
		this.orderId = orderId;
		this.custId = custId;
		this.orderDate = orderDate;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return orderId + " , CustID:" + custId + " , " + orderDate + " , " + amount;
	}

	public double getAmount() {
		return amount;
	}
}
