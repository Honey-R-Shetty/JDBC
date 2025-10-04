package com.order.main;

import com.order.dao.OrderDAO;
import com.order.model.Order;
import java.util.List;
import java.util.Scanner;

public class OnlineOrderApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OrderDAO dao = new OrderDAO();
		while (true) {
			System.out.println("***Restaurant***\n1.Place Order \n2.Order History \n3.Exit");
			System.out.print("Choice: ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.print("Enter CustomerID : ");
				int id = sc.nextInt();
				System.out.print("Enter Amount : ");
				double amount = sc.nextDouble();
				dao.insertOrder(id, amount);
				break;
			case 2:
				System.out.print("CustomerID: ");
				int cid = sc.nextInt();
				List<Order> list = dao.getOrdersByCustomer(cid);
				list.forEach(System.out::println);
				break;
			case 3:
				System.exit(0);
			}
		}
	}
}
