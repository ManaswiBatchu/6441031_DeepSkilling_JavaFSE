CREATE TABLE Customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE
);

INSERT INTO Customers VALUES (1, 'Manu', 'manu@example.com');
INSERT INTO Customers VALUES (2, 'Sathvika', 'sathvika@example.com');
COMMIT;

CREATE OR REPLACE PROCEDURE AddNewCustomer(
    c_id IN INT,
    c_name IN VARCHAR2,
    c_email IN VARCHAR2
) AS
BEGIN
    INSERT INTO Customers (customer_id, name, email)
    VALUES (c_id, c_name, c_email);
    
    DBMS_OUTPUT.PUT_LINE('Customer ' || c_name || ' added successfully with ID ' || c_id);
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || c_id || ' or email already exists');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error adding customer: ' || SQLERRM);
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('Test 1: Successful addition');
    AddNewCustomer(3, 'Akshaya', 'akhi@example.com');
    
    DBMS_OUTPUT.PUT_LINE('Test 2: Duplicate ID');
    AddNewCustomer(1, 'Manu another', 'another@example.com');
    
    DBMS_OUTPUT.PUT_LINE('Test 3: Duplicate email');
    AddNewCustomer(4, 'Duplicate Email', 'manu@example.com');
END;
/