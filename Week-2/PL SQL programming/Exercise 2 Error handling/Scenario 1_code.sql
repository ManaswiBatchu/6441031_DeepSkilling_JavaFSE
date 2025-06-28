CREATE TABLE Accounts (
    account_id INT PRIMARY KEY,
    balance NUMBER(10, 2) NOT NULL
);

INSERT INTO Accounts VALUES (1, 1000.00);
INSERT INTO Accounts VALUES (2, 500.00);
COMMIT;

CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    from_account IN INT,
    to_account IN INT,
    amount IN NUMBER
) AS
    from_balance NUMBER;
BEGIN
    DECLARE
        from_exists NUMBER;
        to_exists NUMBER;
    BEGIN
        SELECT COUNT(*) INTO from_exists FROM Accounts WHERE account_id = from_account;
        SELECT COUNT(*) INTO to_exists FROM Accounts WHERE account_id = to_account;
        
        IF from_exists = 0 THEN
            RAISE_APPLICATION_ERROR(-20001, 'Source account does not exist');
        ELSIF to_exists = 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Destination account does not exist');
        END IF;
    END;
    
    SELECT balance INTO from_balance FROM Accounts WHERE account_id = from_account;
    IF from_balance < amount THEN
        RAISE_APPLICATION_ERROR(-20003, 'Insufficient funds for transfer');
    END IF;
    
    UPDATE Accounts SET balance = balance - amount WHERE account_id = from_account;
    UPDATE Accounts SET balance = balance + amount WHERE account_id = to_account;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer completed successfully');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


BEGIN
    DBMS_OUTPUT.PUT_LINE('Test 1: Successful transfer');
    SafeTransferFunds(1, 2, 100.00);
    
    DBMS_OUTPUT.PUT_LINE('Test 2: Insufficient funds');
    SafeTransferFunds(1, 2, 2000.00);
    
    DBMS_OUTPUT.PUT_LINE('Test 3: Non-existent account');
    SafeTransferFunds(99, 2, 100.00);
END;
/