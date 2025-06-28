CREATE OR REPLACE FUNCTION CalculateAge(dob IN DATE) RETURN NUMBER IS
    v_age NUMBER;
BEGIN
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, dob) / 12);
    RETURN v_age;
END;
/

-- Test cases
SET SERVEROUTPUT ON;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Manaswi age: ' || CalculateAge(TO_DATE('1990-05-15', 'YYYY-MM-DD')));
    DBMS_OUTPUT.PUT_LINE('Sathvika age: ' || CalculateAge(TO_DATE('1985-11-22', 'YYYY-MM-DD')));
    DBMS_OUTPUT.PUT_LINE('Rekha age: ' || CalculateAge(TO_DATE('1995-03-10', 'YYYY-MM-DD')));
    DBMS_OUTPUT.PUT_LINE('Keerthi age: ' || CalculateAge(TO_DATE('1988-07-30', 'YYYY-MM-DD')));
END;
/