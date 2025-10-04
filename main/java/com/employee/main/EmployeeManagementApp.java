package com.employee.main;

import com.employee.dao.EmployeeDAO;
import com.employee.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementApp {
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		EmployeeDAO dao = new EmployeeDAO();

		while (true) {
			System.out.println("***Employee***\n1.Display by Dept \n2.Insert \n3.Update Salary \n4.Delete \n5.Exit");
			System.out.print("Choice: ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.print("Enter DeptID: ");
				int deptno = sc.nextInt();
				List<Employee> list = dao.getEmployeesByDept(deptno);
				list.forEach(System.out::println);
				break;
				
			case 2:
				System.out.print("Enter EmpID : ");
				int empid = sc.nextInt();
				System.out.println("Enter name : ");
				String name = sc.next();
				System.out.println("Enter designation : ");
				String designation = sc.next();
				System.out.println("Enter salary : ");
				Double salary = sc.nextDouble();
				System.out.println("Enter deptno : ");
				deptno = sc.nextInt();
				Employee employee = new Employee(empid,name,designation,salary,deptno);
				dao.insertEmployee(employee);
				break;
				
			case 3:
				System.out.print("Enter EmpID : ");
				empid = sc.nextInt();
				System.out.println("Enter salary : ");
				salary = sc.nextDouble();
				dao.updateSalary(empid,salary);
				break;
				
			case 4:
				System.out.print("EmpID to Delete: ");
				dao.deleteEmployee(sc.nextInt());
				break;
				
			case 5:
				System.exit(0);
			}
		}
	}
}
