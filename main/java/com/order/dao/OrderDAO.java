package com.order.dao;

import com.order.model.Order;
import com.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

	public void insertOrder(int custId, double amount) {
		String sql = "INSERT INTO Orders(cust_id,order_date,amount) VALUES(?,NOW(),?)";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, custId);
			ps.setDouble(2, amount);
			ps.executeUpdate();
			System.out.println("Order placed.");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<Order> getOrdersByCustomer(int custId) {
		List<Order> list = new ArrayList<>();
		String sql = "SELECT * FROM Orders WHERE cust_id=?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, custId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Order(rs.getInt("order_id"), rs.getInt("cust_id"), rs.getDate("order_date"),
						rs.getDouble("amount")));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
