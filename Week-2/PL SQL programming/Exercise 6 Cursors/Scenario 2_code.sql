SET SERVEROUTPUT ON;

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE accounts';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE accounts (
    account_id NUMBER PRIMARY KEY,
    cust_id NUMBER,
    balance NUMBER
);

INSERT INTO accounts VALUES (101, 1, 10000);
INSERT INTO accounts VALUES (102, 2, 8000);
INSERT INTO accounts VALUES (103, 3, 12000);

COMMIT;

DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT account_id, balance FROM accounts;

    annual_fee NUMBER := 500;
BEGIN
    FOR acc IN ApplyAnnualFee LOOP
        UPDATE accounts
        SET balance = acc.balance - annual_fee
        WHERE account_id = acc.account_id;

        DBMS_OUTPUT.PUT_LINE('Annual fee of ' || annual_fee || 
                             ' applied to Account ID: ' || acc.account_id ||
                             '. New Balance: ' || (acc.balance - annual_fee));
    END LOOP;
    COMMIT;
END;
/
