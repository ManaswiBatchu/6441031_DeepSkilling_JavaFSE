CREATE TABLE Employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    department VARCHAR2(50) NOT NULL,
    salary NUMBER(10, 2) NOT NULL,
    bonus NUMBER(10, 2) DEFAULT 0
);

INSERT INTO Employees VALUES (1, 'Manaswi', 'Sales', 50000.00, 0);
INSERT INTO Employees VALUES (2, 'Sathvika', 'Sales', 55000.00, 0);
INSERT INTO Employees VALUES (3, 'Rekha', 'IT', 60000.00, 0);
INSERT INTO Employees VALUES (4, 'Keerthi', 'IT', 65000.00, 0);
COMMIT;

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department IN VARCHAR2,
    p_bonus_percent IN NUMBER
) AS
    v_count NUMBER;
    v_total_bonus NUMBER(10, 2) := 0;
BEGIN

    SELECT COUNT(*) INTO v_count 
    FROM Employees 
    WHERE department = p_department;
    
    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Department ' || p_department || ' does not exist');
    END IF;
    
    
    FOR emp_rec IN (SELECT * FROM Employees WHERE department = p_department) LOOP
        v_total_bonus := v_total_bonus + (emp_rec.salary * p_bonus_percent/100);
        
        UPDATE Employees
        SET bonus = salary * p_bonus_percent/100
        WHERE employee_id = emp_rec.employee_id;
        
        DBMS_OUTPUT.PUT_LINE(emp_rec.name || ': Added bonus of ' || 
                            (emp_rec.salary * p_bonus_percent/100) || 
                            ' (New total salary: ' || 
                            (emp_rec.salary + (emp_rec.salary * p_bonus_percent/100)) || ')');
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Total bonus awarded to ' || p_department || 
                        ' department: ' || v_total_bonus);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonuses: ' || SQLERRM);
END;
/


SET SERVEROUTPUT ON;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Test 1: Sales department bonus (10%)');
    UpdateEmployeeBonus('Sales', 10);
    
    DBMS_OUTPUT.PUT_LINE('Test 2: Non-existent department');
    UpdateEmployeeBonus('Marketing', 5);
END;
/