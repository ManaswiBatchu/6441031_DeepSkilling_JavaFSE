package javaaa;
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        EmployeeDatabase db = new EmployeeDatabase(5);
        
        // Adding employees
        db.addEmployee(101, "John Doe", "Developer", 75000);
        db.addEmployee(102, "Jane Smith", "Manager", 90000);
        db.addEmployee(103, "Bob Johnson", "Designer", 65000);
        
        // Display all employees
        System.out.println("All Employees:");
        db.displayAllEmployees();
        
        // Search for an employee
        System.out.println("\nSearching for employee with ID 102:");
        Employee found = db.searchEmployee(102);
        if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("Employee not found");
        }
        
        // Delete an employee
        System.out.println("\nDeleting employee with ID 101:");
        boolean deleted = db.deleteEmployee(101);
        if (deleted) {
            System.out.println("Employee deleted successfully");
        } else {
            System.out.println("Employee not found");
        }
        
        // Display after deletion
        System.out.println("\nEmployees after deletion:");
        db.displayAllEmployees();
        
        // Try to add more employees
        db.addEmployee(104, "Alice Brown", "Tester", 60000);
        db.addEmployee(105, "Charlie Wilson", "Analyst", 70000);
        db.addEmployee(106, "Diana Miller", "HR", 80000); // This will fail (array full)
        
        // Display final state
        System.out.println("\nFinal Employee List:");
        db.displayAllEmployees();
    }
}

class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;
    
    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    
    public int getEmployeeId() {
        return employeeId;
    }
    
    @Override
    public String toString() {
        return "ID: " + employeeId + ", Name: " + name + 
               ", Position: " + position + ", Salary: $" + salary;
    }
}

class EmployeeDatabase {
    private Employee[] employees;
    private int count;
    
    public EmployeeDatabase(int capacity) {
        employees = new Employee[capacity];
        count = 0;
    }
    
    // Add an employee (Time Complexity: O(1) average, O(n) when array needs to be resized)
    public boolean addEmployee(int id, String name, String position, double salary) {
        if (count == employees.length) {
            System.out.println("Database is full. Cannot add employee " + id);
            return false;
        }
        
        // Check if employee ID already exists
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == id) {
                System.out.println("Employee ID " + id + " already exists.");
                return false;
            }
        }
        
        employees[count++] = new Employee(id, name, position, salary);
        return true;
    }
    
    // Search for an employee (Time Complexity: O(n))
    public Employee searchEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == id) {
                return employees[i];
            }
        }
        return null;
    }
    
    // Delete an employee (Time Complexity: O(n))
    public boolean deleteEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == id) {
                // Shift all elements after the found employee one position left
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }
    
    // Display all employees (Time Complexity: O(n))
    public void displayAllEmployees() {
        if (count == 0) {
            System.out.println("No employees in the database.");
            return;
        }
        
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }
}