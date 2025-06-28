CREATE TABLE CustomerAccounts (
    account_id INT PRIMARY KEY,
    customer_name VARCHAR2(50),
    balance NUMBER(10, 2)
);

INSERT INTO CustomerAccounts VALUES (101, 'Manaswi', 5000.00);
INSERT INTO CustomerAccounts VALUES (102, 'Sathvika', 10000.00);
INSERT INTO CustomerAccounts VALUES (103, 'Rekha', 7500.00);
INSERT INTO CustomerAccounts VALUES (104, 'Keerthi', 12000.00);
COMMIT;

CREATE OR REPLACE FUNCTION HasSufficientBalance(
    acc_id IN NUMBER,
    amount IN NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    SELECT balance INTO v_balance FROM CustomerAccounts WHERE account_id = acc_id;
    RETURN v_balance >= amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/

SET SERVEROUTPUT ON;
BEGIN
    IF HasSufficientBalance(101, 4000) THEN
        DBMS_OUTPUT.PUT_LINE('Manaswi has sufficient balance');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Manaswi has insufficient balance');
    END IF;
    
    IF HasSufficientBalance(102, 15000) THEN
        DBMS_OUTPUT.PUT_LINE('Sathvika has sufficient balance');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Sathvika has insufficient balance');
    END IF;
    
    IF HasSufficientBalance(103, 8000) THEN
        DBMS_OUTPUT.PUT_LINE('Rekha has sufficient balance');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Rekha has insufficient balance');
    END IF;
    
    IF HasSufficientBalance(105, 1000) THEN
        DBMS_OUTPUT.PUT_LINE('Keerthi has sufficient balance');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account not found');
    END IF;
END;
/