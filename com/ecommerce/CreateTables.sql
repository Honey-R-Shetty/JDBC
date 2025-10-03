-- Customers
CREATE TABLE Customers ( 
    CustomerID INT PRIMARY KEY AUTO_INCREMENT, 
    FullName VARCHAR(100) NOT NULL, 
    Email VARCHAR(100) UNIQUE NOT NULL, 
    Phone VARCHAR(20), 
    CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP 
); 

-- Categories
CREATE TABLE Categories ( 
    CategoryID INT PRIMARY KEY AUTO_INCREMENT, 
    CategoryName VARCHAR(50) UNIQUE NOT NULL 
); 

-- Products
CREATE TABLE Products ( 
    ProductID INT PRIMARY KEY AUTO_INCREMENT, 
    ProductName VARCHAR(100) NOT NULL, 
    Price DECIMAL(10,2) NOT NULL, 
    Stock INT NOT NULL, 
    CategoryID INT, 
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID) 
); 

-- Orders
CREATE TABLE Orders ( 
    OrderID INT PRIMARY KEY AUTO_INCREMENT, 
    CustomerID INT, 
    OrderDate DATETIME DEFAULT CURRENT_TIMESTAMP, 
    PaymentMethod VARCHAR(50), 
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) 
); 

-- OrderDetails
CREATE TABLE OrderDetails ( 
    OrderDetailID INT PRIMARY KEY AUTO_INCREMENT, 
    OrderID INT, 
    ProductID INT, 
    Quantity INT NOT NULL, 
    Price DECIMAL(10,2) NOT NULL, 
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID), 
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) 
); 

-- OrderAudit
CREATE TABLE OrderAudit ( 
    AuditID INT PRIMARY KEY AUTO_INCREMENT, 
    OrderID INT, 
    CustomerID INT, 
    OrderDate DATETIME, 
    ActionType VARCHAR(20), 
    LoggedAt DATETIME DEFAULT CURRENT_TIMESTAMP 
); 

-- HighValueOrders
CREATE TABLE HighValueOrders ( 
    HVOrderID INT PRIMARY KEY AUTO_INCREMENT, 
    OrderID INT, 
    CustomerID INT, 
    TotalAmount DECIMAL(12,2), 
    CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP 
);

