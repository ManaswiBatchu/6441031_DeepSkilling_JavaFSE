SET SERVEROUTPUT ON;

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Employees CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
);

INSERT INTO Employees VALUES (1, 'Rekha', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));
INSERT INTO Employees VALUES (2, 'Sathvika', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));
COMMIT;

CREATE OR REPLACE PACKAGE EmployeeManagement IS
    PROCEDURE HireEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_dept VARCHAR2,
        p_hiredate DATE
    );

    PROCEDURE UpdateEmployee(
        p_id NUMBER,
        p_salary NUMBER,
        p_position VARCHAR2
    );

    FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement IS

    PROCEDURE HireEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_dept VARCHAR2,
        p_hiredate DATE
    ) IS
    BEGIN
        INSERT INTO Employees
        VALUES (p_id, p_name, p_position, p_salary, p_dept, p_hiredate);
        DBMS_OUTPUT.PUT_LINE('Employee hired: ' || p_name);
    END HireEmployee;

    PROCEDURE UpdateEmployee(
        p_id NUMBER,
        p_salary NUMBER,
        p_position VARCHAR2
    ) IS
    BEGIN
        UPDATE Employees
        SET Salary = p_salary,
            Position = p_position
        WHERE EmployeeID = p_id;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Employee not found: ' || p_id);
        ELSE
            DBMS_OUTPUT.PUT_LINE('Employee updated: ' || p_id);
        END IF;
    END UpdateEmployee;

    FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER IS
        v_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_id;
        RETURN v_salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END CalculateAnnualSalary;

END EmployeeManagement;
/

BEGIN
    EmployeeManagement.HireEmployee(3, 'Manaswi', 'Analyst', 50000, 'Finance', SYSDATE);
    EmployeeManagement.UpdateEmployee(3, 52000, 'Senior Analyst');
    DBMS_OUTPUT.PUT_LINE('Annual Salary: ' || EmployeeManagement.CalculateAnnualSalary(3));
END;
/
