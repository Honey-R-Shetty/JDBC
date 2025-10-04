package com.employee.dao;

import com.employee.model.Employee;
import com.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public boolean insertEmployee(Employee e) throws SQLException {
        String sql = "INSERT INTO Employee(emp_id,name,designation,salary,dept_id) VALUES(?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.empId);
            ps.setString(2, e.name);
            ps.setString(3, e.designation);
            ps.setDouble(4, e.salary);
            ps.setInt(5, e.deptId);
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    public boolean updateSalary(int empId, double newSalary) throws SQLException {
        String sql = "UPDATE Employee SET salary=? WHERE emp_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, newSalary);
            ps.setInt(2, empId);
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    public boolean deleteEmployee(int empId) throws SQLException {
        String sql = "DELETE FROM Employee WHERE emp_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, empId);
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    public List<Employee> getEmployeesByDept(int deptId) throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE dept_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, deptId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Employee(
                    rs.getInt("emp_id"),
                    rs.getString("name"),
                    rs.getString("designation"),
                    rs.getDouble("salary"),
                    rs.getInt("dept_id")
                ));
            }
        }
        return list;
    }
}
