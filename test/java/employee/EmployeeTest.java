package employee;

import com.employee.dao.EmployeeDAO;
import com.employee.model.Employee;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    private static EmployeeDAO dao;

    @BeforeAll
    static void setup() {
        dao = new EmployeeDAO();
    }

    @Test
    void testInsertEmployeePositive() throws SQLException {
        dao.deleteEmployee(201); // clean previous test data
        Employee e = new Employee(201, "Alice", "Developer", 50000, 1);
        assertTrue(dao.insertEmployee(e));
        List<Employee> employees = dao.getEmployeesByDept(1);
        assertTrue(employees.stream().anyMatch(emp -> emp.empId == 201));
    }

    @Test
    void testInsertEmployeeNegative_DuplicateId() throws SQLException {
        dao.deleteEmployee(202); // clean previous test data
        Employee e1 = new Employee(202, "Bob", "Tester", 40000, 2);
        assertTrue(dao.insertEmployee(e1));

        Employee e2 = new Employee(202, "DuplicateBob", "Tester", 45000, 2);
        assertThrows(SQLException.class, () -> dao.insertEmployee(e2));
    }

    @Test
    void testUpdateSalaryPositive() throws SQLException {
        dao.deleteEmployee(203);
        Employee e = new Employee(203, "Charlie", "Manager", 60000, 3);
        dao.insertEmployee(e);

        assertTrue(dao.updateSalary(203, 70000));

        List<Employee> employees = dao.getEmployeesByDept(3);
        Employee updated = employees.stream().filter(emp -> emp.empId == 203).findFirst().orElse(null);
        assertNotNull(updated);
        assertEquals(70000, updated.salary);
    }

    @Test
    void testUpdateSalaryNegative_InvalidId() throws SQLException {
        assertFalse(dao.updateSalary(9999, 80000));
    }

    @Test
    void testDeleteEmployeeNegative_InvalidId() throws SQLException {
        assertFalse(dao.deleteEmployee(9999));
    }
}
