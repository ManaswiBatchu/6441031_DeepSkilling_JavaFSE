SET SERVEROUTPUT ON;

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE loans';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE loans (
    loan_id NUMBER PRIMARY KEY,
    amount NUMBER,
    interest_rate NUMBER
);

INSERT INTO loans VALUES (201, 40000, 5);
INSERT INTO loans VALUES (202, 75000, 6.5);
INSERT INTO loans VALUES (203, 120000, 8);

COMMIT;

DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT loan_id, amount, interest_rate FROM loans;

    new_rate NUMBER;
BEGIN
    FOR loan_rec IN UpdateLoanInterestRates LOOP
        IF loan_rec.amount < 50000 THEN
            new_rate := 6;
        ELSIF loan_rec.amount <= 100000 THEN
            new_rate := 7.5;
        ELSE
            new_rate := 9;
        END IF;

        UPDATE loans
        SET interest_rate = new_rate
        WHERE loan_id = loan_rec.loan_id;

        DBMS_OUTPUT.PUT_LINE('Loan ID: ' || loan_rec.loan_id ||
                             ' | Old Rate: ' || loan_rec.interest_rate ||
                             '% | New Rate: ' || new_rate || '%');
    END LOOP;
    COMMIT;
END;
/
