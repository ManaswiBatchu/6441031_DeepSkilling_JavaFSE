SET SERVEROUTPUT ON;

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE transactions';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE customers';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE customers (
    cust_id NUMBER PRIMARY KEY,
    cust_name VARCHAR2(50)
);

CREATE TABLE transactions (
    txn_id NUMBER PRIMARY KEY,
    cust_id NUMBER,
    txn_date DATE,
    amount NUMBER,
    txn_type VARCHAR2(20),
    FOREIGN KEY (cust_id) REFERENCES customers(cust_id)
);

INSERT INTO customers VALUES (1, 'Manu');
INSERT INTO customers VALUES (2, 'Sathvi');

INSERT INTO transactions VALUES (101, 1, SYSDATE - 5, 1000, 'DEPOSIT');
INSERT INTO transactions VALUES (102, 1, SYSDATE - 3, 500, 'WITHDRAW');
INSERT INTO transactions VALUES (103, 2, SYSDATE - 2, 2000, 'DEPOSIT');

COMMIT;

DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.cust_id, c.cust_name, t.amount, t.txn_type, t.txn_date
        FROM customers c
        JOIN transactions t ON c.cust_id = t.cust_id
        WHERE TO_CHAR(t.txn_date, 'MM-YYYY') = TO_CHAR(SYSDATE, 'MM-YYYY')
        ORDER BY c.cust_id, t.txn_date;

    prev_cust_id transactions.cust_id%TYPE := NULL;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Monthly Statements ---');
    FOR rec IN GenerateMonthlyStatements LOOP
        IF prev_cust_id IS NULL OR prev_cust_id != rec.cust_id THEN
            DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Customer ID: ' || rec.cust_id || ' | Name: ' || rec.cust_name);
            DBMS_OUTPUT.PUT_LINE('Date         | Type     | Amount');
            DBMS_OUTPUT.PUT_LINE('-------------|----------|--------');
        END IF;
        DBMS_OUTPUT.PUT_LINE(TO_CHAR(rec.txn_date, 'DD-Mon-YYYY') || ' | ' ||
                             RPAD(rec.txn_type, 8) || ' | ' || rec.amount);
        prev_cust_id := rec.cust_id;
    END LOOP;
END;
/
