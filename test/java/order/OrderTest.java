package order;

import com.order.dao.OrderDAO;
import com.order.model.Order;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private static OrderDAO dao;

    @BeforeAll
    static void setup() {
        dao = new OrderDAO();
    }

    @Test
    void testInsertOrderPositive() throws SQLException {
        boolean result = dao.insertOrder(1, 1500.0);
        assertTrue(result);

        List<Order> orders = dao.getOrdersByCustomer(1);
        assertTrue(orders.stream().anyMatch(o -> o.getAmount() == 1500.0));
    }

    @Test
    void testGetOrdersByCustomerPositive() throws SQLException {
        List<Order> orders = dao.getOrdersByCustomer(1);
        assertFalse(orders.isEmpty());
    }

    @Test
    void testInsertOrderNegative_InvalidCustomer() {
        assertThrows(SQLException.class, () -> dao.insertOrder(9999, 2000.0));
    }

    @Test
    void testGetOrdersByCustomerNegative_NoOrders() throws SQLException {
        List<Order> orders = dao.getOrdersByCustomer(8888);
        assertTrue(orders.isEmpty());
    }
}
