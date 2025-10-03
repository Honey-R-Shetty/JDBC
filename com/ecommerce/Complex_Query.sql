SELECT c.CustomerID, c.FullName, SUM(od.Quantity * od.Price) AS TotalSpending
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
JOIN OrderDetails od ON o.OrderID = od.OrderID
JOIN Products p ON od.ProductID = p.ProductID
JOIN Categories cat ON p.CategoryID = cat.CategoryID
WHERE o.OrderDate >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH)
GROUP BY c.CustomerID, c.FullName
HAVING COUNT(DISTINCT cat.CategoryID) >= 3
ORDER BY TotalSpending DESC
LIMIT 3;
