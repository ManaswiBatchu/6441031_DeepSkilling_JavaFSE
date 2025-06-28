CREATE TABLE Accounts (
    account_id INT PRIMARY KEY,
    customer_id INT NOT NULL,
    account_type VARCHAR2(20) NOT NULL,
    balance NUMBER(10, 2) NOT NULL
);

INSERT INTO Accounts VALUES (101, 1, 'Checking', 5000.00);
INSERT INTO Accounts VALUES (102, 1, 'Savings', 10000.00);
INSERT INTO Accounts VALUES (201, 2, 'Checking', 3000.00);
INSERT INTO Accounts VALUES (202, 2, 'Savings', 8000.00);
COMMIT;

CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account IN INT,
    p_to_account IN INT,
    p_amount IN NUMBER
) AS
    v_from_balance NUMBER(10, 2);
    v_from_exists NUMBER;
    v_to_exists NUMBER;
BEGIN
    
    SELECT COUNT(*) INTO v_from_exists FROM Accounts WHERE account_id = p_from_account;
    SELECT COUNT(*) INTO v_to_exists FROM Accounts WHERE account_id = p_to_account;
    
    IF v_from_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Source account does not exist');
    ELSIF v_to_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Destination account does not exist');
    END IF;
    
    
    SELECT balance INTO v_from_balance FROM Accounts WHERE account_id = p_from_account;
    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20004, 'Insufficient funds for transfer');
    END IF;
    
    
    UPDATE Accounts SET balance = balance - p_amount WHERE account_id = p_from_account;
    UPDATE Accounts SET balance = balance + p_amount WHERE account_id = p_to_account;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Successfully transferred ' || p_amount || 
                        ' from account ' || p_from_account || 
                        ' to account ' || p_to_account);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END;
/

-- Execute the procedure
SET SERVEROUTPUT ON;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Test 1: Successful transfer');
    TransferFunds(101, 102, 1000);
    
    DBMS_OUTPUT.PUT_LINE('Test 2: Insufficient funds');
    TransferFunds(201, 202, 5000);
    
    DBMS_OUTPUT.PUT_LINE('Test 3: Non-existent account');
    TransferFunds(101, 999, 500);
END;
/