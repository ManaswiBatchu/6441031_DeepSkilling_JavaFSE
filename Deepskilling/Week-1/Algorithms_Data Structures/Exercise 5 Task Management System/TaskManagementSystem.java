package javaaa;

public class TaskManagementSystem {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        
        taskList.addTask(1, "Complete project proposal", "Pending");
        taskList.addTask(2, "Review code changes", "In Progress");
        taskList.addTask(3, "Prepare presentation", "Pending");
        taskList.addTask(4, "Team meeting", "Completed");
        
        // Display all tasks
        System.out.println("All Tasks:");
        taskList.displayTasks();
        
        // Search for a task
        System.out.println("\nSearching for task with ID 2:");
        Task foundTask = taskList.searchTask(2);
        if (foundTask != null) {
            System.out.println("Found: " + foundTask);
        } else {
            System.out.println("Task not found");
        }
        
        // Update a task status
        System.out.println("\nUpdating task 1 status to 'In Progress':");
        taskList.updateTaskStatus(1, "In Progress");
        
        // Delete a task
        System.out.println("\nDeleting task with ID 3:");
        boolean deleted = taskList.deleteTask(3);
        if (deleted) {
            System.out.println("Task deleted successfully");
        } else {
            System.out.println("Task not found");
        }
        
        // Display after operations
        System.out.println("\nTasks after operations:");
        taskList.displayTasks();
        
        // Add one more task
        taskList.addTask(5, "Submit final report", "Pending");
        
        // Display final state
        System.out.println("\nFinal Task List:");
        taskList.displayTasks();
    }
}

class Task {
    int taskId;
    String taskName;
    String status;
    Task next;
    
    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }
    
    @Override
    public String toString() {
        return "ID: " + taskId + ", Name: " + taskName + ", Status: " + status;
    }
}

class TaskList {
    private Task head;
    private int size;
    
    public TaskList() {
        head = null;
        size = 0;
    }
    
    // Add a task at the end (Time Complexity: O(n))
    public void addTask(int taskId, String taskName, String status) {
        Task newTask = new Task(taskId, taskName, status);
        
        if (head == null) {
            head = newTask;
        } else {
            Task current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTask;
        }
        size++;
        System.out.println("Task added: " + newTask);
    }
    
    // Search for a task (Time Complexity: O(n))
    public Task searchTask(int taskId) {
        Task current = head;
        while (current != null) {
            if (current.taskId == taskId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    
    // Update task status (Time Complexity: O(n))
    public boolean updateTaskStatus(int taskId, String newStatus) {
        Task task = searchTask(taskId);
        if (task != null) {
            String oldStatus = task.status;
            task.status = newStatus;
            System.out.println("Task " + taskId + " status changed from '" + oldStatus + "' to '" + newStatus + "'");
            return true;
        }
        System.out.println("Task " + taskId + " not found");
        return false;
    }
    
    // Delete a task (Time Complexity: O(n))
    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false;
        }
        
        if (head.taskId == taskId) {
            head = head.next;
            size--;
            return true;
        }
        
        Task current = head;
        while (current.next != null) {
            if (current.next.taskId == taskId) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    
    // Display all tasks (Time Complexity: O(n))
    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks in the list.");
            return;
        }
        
        Task current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }
    
    public int getSize() {
        return size;
    }
}