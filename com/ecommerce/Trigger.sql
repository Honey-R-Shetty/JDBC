DELIMITER $$

CREATE TRIGGER trg_after_order_insert
AFTER INSERT ON Orders
FOR EACH ROW
BEGIN
    DECLARE v_TotalAmount DECIMAL(12,2);

    -- Log in OrderAudit
    INSERT INTO OrderAudit(OrderID, CustomerID, OrderDate, ActionType)
    VALUES (NEW.OrderID, NEW.CustomerID, NEW.OrderDate, 'INSERT');

    -- Calculate total order amount
    SELECT SUM(Quantity * Price) INTO v_TotalAmount
    FROM OrderDetails
    WHERE OrderID = NEW.OrderID;

    -- Insert into HighValueOrders if > 50,000
    IF v_TotalAmount > 50000 THEN
        INSERT INTO HighValueOrders(OrderID, CustomerID, TotalAmount)
        VALUES (NEW.OrderID, NEW.CustomerID, v_TotalAmount);
    END IF;
END$$

DELIMITER ;
