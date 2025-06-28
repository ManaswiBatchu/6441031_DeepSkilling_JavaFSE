CREATE TABLE Employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    salary NUMBER(10, 2) NOT NULL
);

INSERT INTO Employees VALUES (101, 'John Doe', 50000.00);
INSERT INTO Employees VALUES (102, 'Jane Smith', 60000.00);
COMMIT;

CREATE OR REPLACE PROCEDURE UpdateSalary(
    emp_id IN INT,
    percent_increase IN NUMBER
) AS
    emp_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO emp_count FROM Employees WHERE employee_id = emp_id;
    
    IF emp_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Employee ID does not exist');
    ELSE
        UPDATE Employees 
        SET salary = salary * (1 + percent_increase/100) 
        WHERE employee_id = emp_id;
        
        DBMS_OUTPUT.PUT_LINE('Salary updated successfully for employee ID ' || emp_id);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error updating salary: ' || SQLERRM);
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('Test 1: Successful update');
    UpdateSalary(101, 10.0);
    
    DBMS_OUTPUT.PUT_LINE('Test 2: Non-existent employee');
    UpdateSalary(999, 5.0);
END;
/