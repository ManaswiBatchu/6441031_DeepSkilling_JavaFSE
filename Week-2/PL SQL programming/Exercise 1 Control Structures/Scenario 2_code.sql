CREATE TABLE Customers (
    CustomerID   NUMBER PRIMARY KEY,
    Name         VARCHAR2(100),
    Age          NUMBER,
    Balance      NUMBER,
    InterestRate NUMBER,
    IsVIP        VARCHAR2(5)
);

INSERT INTO Customers VALUES (1, 'Manaswi', 65, 12000, 8.5, 'FALSE');
INSERT INTO Customers VALUES (2, 'Akshaya', 45, 5000, 9.0, 'FALSE');
INSERT INTO Customers VALUES (3, 'Sathvika', 70, 15000, 7.5, 'FALSE');
COMMIT;

CREATE TABLE Loans (
    LoanID     NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    DueDate    DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Loans VALUES (101, 1, SYSDATE + 15);
INSERT INTO Loans VALUES (102, 2, SYSDATE + 40);
INSERT INTO Loans VALUES (103, 3, SYSDATE + 10);
COMMIT;

BEGIN
    FOR vip_rec IN (SELECT CustomerID, Name, Balance FROM Customers WHERE Balance > 10000) LOOP
        UPDATE Customers
        SET IsVIP = 'TRUE'
        WHERE CustomerID = vip_rec.CustomerID;

        DBMS_OUTPUT.PUT_LINE('Customer promoted to VIP: ' || vip_rec.Name);
    END LOOP;
END;
/
