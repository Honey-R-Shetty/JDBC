package com.ecommerce;

import java.sql.*;

public class PlaceOrderApp {
	public static void main(String[] args) {
		int customerId = 1;
		int productId = 2;
		int quantity = 3;
		String paymentMethod = "Credit Card";

		try (Connection conn = DBConnection.getConnection();
				CallableStatement stmt = conn.prepareCall("{CALL PlaceOrder(?, ?, ?, ?)}")) {

			stmt.setInt(1, customerId);
			stmt.setInt(2, productId);
			stmt.setInt(3, quantity);
			stmt.setString(4, paymentMethod);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("Order Placed! OrderID: " + rs.getInt("OrderID"));
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
