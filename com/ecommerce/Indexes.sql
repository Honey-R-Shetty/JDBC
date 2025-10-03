-- Orders in last 30 days
CREATE INDEX idx_orders_orderdate ON Orders(OrderDate);

-- Customers by Email
CREATE UNIQUE INDEX idx_customers_email ON Customers(Email);

-- Products by Category
CREATE INDEX idx_products_category ON Products(CategoryID);

-- Joins between Orders and OrderDetails
CREATE INDEX idx_orderdetails_orderid ON OrderDetails(OrderID);
CREATE INDEX idx_orderdetails_productid ON OrderDetails(ProductID);
