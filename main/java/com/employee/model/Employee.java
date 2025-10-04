package com.employee.model;

public class Employee {
	public int empId;
	public String name;
	public String designation;
	public double salary;
	public int deptId;

	public Employee(int empId, String name, String designation, double salary, int deptId) {
		this.empId = empId;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return empId + " , " + name + " , " + designation + " , " + salary + " , DeptID: " + deptId;
	}
}
