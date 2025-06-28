CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    loan_amount IN NUMBER,
    interest_rate IN NUMBER,
    years IN NUMBER
) RETURN NUMBER IS
    monthly_rate NUMBER;
    months NUMBER;
    installment NUMBER;
BEGIN
    monthly_rate := interest_rate / 100 / 12;
    months := years * 12;
    installment := loan_amount * monthly_rate * POWER(1 + monthly_rate, months) / 
                  (POWER(1 + monthly_rate, months) - 1);
    RETURN ROUND(installment, 2);
END;
/


SET SERVEROUTPUT ON;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Manaswi loan: $' || CalculateMonthlyInstallment(10000, 5, 5));
    DBMS_OUTPUT.PUT_LINE('Sathvika loan: $' || CalculateMonthlyInstallment(20000, 7, 10));
    DBMS_OUTPUT.PUT_LINE('Rekha loan: $' || CalculateMonthlyInstallment(15000, 6, 3));
    DBMS_OUTPUT.PUT_LINE('Keerthi loan: $' || CalculateMonthlyInstallment(50000, 4, 15));
END;
/
