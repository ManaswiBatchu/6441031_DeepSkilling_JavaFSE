CREATE TABLE Transactions (
    TransactionID INT PRIMARY KEY,
    AccountID INT,
    Amount NUMBER(10,2),
    TransactionType VARCHAR2(10),
    TransactionDate DATE
);


CREATE TABLE AuditLog (
    LogID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TransactionID INT,
    AccountID INT,
    Amount NUMBER(10,2),
    ActionDate DATE,
    ActionType VARCHAR2(20)
);

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, Amount, ActionDate, ActionType)
    VALUES (:NEW.TransactionID, :NEW.AccountID, :NEW.Amount, SYSDATE, 'TRANSACTION');
END;
/

INSERT INTO Transactions VALUES (1001, 101, 500.00, 'DEPOSIT', SYSDATE);
INSERT INTO Transactions VALUES (1002, 102, 200.00, 'WITHDRAWAL', SYSDATE);
COMMIT;

SELECT * FROM AuditLog;