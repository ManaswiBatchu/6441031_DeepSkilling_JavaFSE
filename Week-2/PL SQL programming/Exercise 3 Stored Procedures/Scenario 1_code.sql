CREATE TABLE SavingsAccounts (
    account_id INT PRIMARY KEY,
    customer_id INT NOT NULL,
    balance NUMBER(10, 2) NOT NULL,
    last_interest_date DATE
);

INSERT INTO SavingsAccounts VALUES (101, 1, 5000.00, TO_DATE('2023-05-01', 'YYYY-MM-DD'));
INSERT INTO SavingsAccounts VALUES (102, 2, 10000.00, TO_DATE('2023-05-01', 'YYYY-MM-DD'));
INSERT INTO SavingsAccounts VALUES (103, 3, 7500.00, TO_DATE('2023-05-01', 'YYYY-MM-DD'));
COMMIT;

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    CURSOR c_accounts IS SELECT * FROM SavingsAccounts;
    v_interest NUMBER(10, 2);
BEGIN
    FOR r_account IN c_accounts LOOP
        v_interest := r_account.balance * 0.01; 
        
        UPDATE SavingsAccounts
        SET balance = balance + v_interest,
            last_interest_date = SYSDATE
        WHERE account_id = r_account.account_id;
        
        DBMS_OUTPUT.PUT_LINE('Account ' || r_account.account_id || 
                            ': Added interest of ' || v_interest || 
                            ', New balance: ' || (r_account.balance + v_interest));
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest processing completed successfully');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
END;
/

-- Execute the procedure
SET SERVEROUTPUT ON;
BEGIN
    ProcessMonthlyInterest;
END;
/