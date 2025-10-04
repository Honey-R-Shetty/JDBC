package com.order.model;

public class Customer {
	public int custId;
	public String name, email;

	public Customer(int custId, String name, String email) {
		this.custId = custId;
		this.name = name;
		this.email = email;
	}

	@Override
	public String toString() {
		return custId + " , " + name + " , " + email;
	}
}
