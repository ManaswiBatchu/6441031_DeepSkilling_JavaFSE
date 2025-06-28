CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    CustomerName VARCHAR2(100),
    Email VARCHAR2(100),
    LastModified DATE
);

INSERT INTO Customers VALUES (1, 'Manaswi', 'manaswi@example.com', SYSDATE);
INSERT INTO Customers VALUES (2, 'Sathvika', 'sathvika@example.com', SYSDATE);
INSERT INTO Customers VALUES (3, 'Rekha', 'rekha@example.com', SYSDATE);
INSERT INTO Customers VALUES (4, 'Keerthi', 'keerthi@example.com', SYSDATE);
COMMIT;

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

SET LINESIZE 100
SET PAGESIZE 100
COLUMN CustomerID FORMAT 999 HEADING "ID"
COLUMN CustomerName FORMAT A15 HEADING "Name"
COLUMN LastModified FORMAT A12 HEADING "Modified Date"

BEGIN
    UPDATE Customers SET Email = 'manaswi.new@example.com' WHERE CustomerID = 1;
    UPDATE Customers SET CustomerName = 'Sathvika K' WHERE CustomerID = 2;
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Updated Customer Records:');
    DBMS_OUTPUT.PUT_LINE('-----------------------');
    FOR c IN (SELECT CustomerID, CustomerName, TO_CHAR(LastModified, 'DD-MON-YYYY') AS LastModified 
              FROM Customers ORDER BY CustomerID) LOOP
        DBMS_OUTPUT.PUT_LINE(
            RPAD(c.CustomerID, 5) || 
            RPAD(c.CustomerName, 15) || 
            c.LastModified
        );
    END LOOP;
END;
/