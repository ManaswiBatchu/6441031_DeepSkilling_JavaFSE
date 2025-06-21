package javaaa;

public class MVCPattern {

    public static class Student {
        private String name;
        private String id;
        private String grade;

        public Student(String name, String id, String grade) {
            this.name = name;
            this.id = id;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }

    public static class StudentView {
        public void displayStudentDetails(String name, String id, String grade) {
            System.out.println("Student Details:");
            System.out.println("Name: " + name);
            System.out.println("ID: " + id);
            System.out.println("Grade: " + grade);
            System.out.println("---------------------");
        }
    }

    public static class StudentController {
        private Student model;
        private StudentView view;

        public StudentController(Student model, StudentView view) {
            this.model = model;
            this.view = view;
        }

        public void setStudentName(String name) {
            model.setName(name);
        }

        public String getStudentName() {
            return model.getName();
        }

        public void setStudentId(String id) {
            model.setId(id);
        }

        public String getStudentId() {
            return model.getId();
        }

        public void setStudentGrade(String grade) {
            model.setGrade(grade);
        }

        public String getStudentGrade() {
            return model.getGrade();
        }

        public void updateView() {
            view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
        }
    }

    public static class MVCTest {
        public static void main(String[] args) {
            System.out.println("Student Records System using MVC Pattern\n");

            // Create model (student data)
            Student student = new Student("John Doe", "S001", "A");

            // Create view
            StudentView view = new StudentView();

            // Create controller
            StudentController controller = new StudentController(student, view);

            // Display initial student data
            System.out.println("Initial Student Data:");
            controller.updateView();

            // Update model through controller
            System.out.println("Updating student data...");
            controller.setStudentName("Jane Smith");
            controller.setStudentId("S002");
            controller.setStudentGrade("B+");

            // Display updated data
            System.out.println("\nUpdated Student Data:");
            controller.updateView();

            // Create another student
            System.out.println("Creating another student...");
            Student anotherStudent = new Student("Alex Johnson", "S003", "C");
            StudentController anotherController = new StudentController(anotherStudent, view);

            // Display second student
            System.out.println("\nSecond Student Data:");
            anotherController.updateView();
        }
    }
}