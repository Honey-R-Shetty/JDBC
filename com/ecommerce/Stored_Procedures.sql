DELIMITER $$

CREATE PROCEDURE PlaceOrder(
    IN p_CustomerID INT,
    IN p_ProductID INT,
    IN p_Quantity INT,
    IN p_PaymentMethod VARCHAR(50)
)
BEGIN
    DECLARE v_Stock INT;
    DECLARE v_OrderID INT;

    SELECT Stock INTO v_Stock FROM Products WHERE ProductID = p_ProductID;
    IF v_Stock < p_Quantity THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient stock';
    END IF;

    START TRANSACTION;

    INSERT INTO Orders(CustomerID, PaymentMethod) VALUES (p_CustomerID, p_PaymentMethod);
    SET v_OrderID = LAST_INSERT_ID();

    INSERT INTO OrderDetails(OrderID, ProductID, Quantity, Price)
    SELECT v_OrderID, ProductID, p_Quantity, Price FROM Products WHERE ProductID = p_ProductID;

    UPDATE Products SET Stock = Stock - p_Quantity WHERE ProductID = p_ProductID;

    COMMIT;

    SELECT v_OrderID AS OrderID;
END$$

DELIMITER ;
