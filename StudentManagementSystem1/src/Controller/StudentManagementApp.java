package Controller;

import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {

    private static Scanner sc = new Scanner(System.in);
    private static StudentManager manager = new StudentManager();

    public static void main(String[] args) {

        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addStudentFlow();
                    break;
                case "2":
                    showAllStudents();
                    break;
                case "3":
                    searchByIdFlow();
                    break;
                case "4":
                    searchByNameFlow();
                    break;
                case "5":
                    deleteStudentFlow();
                    break;
                case "6":
                    showSortedMenu();
                    break;
                case "0":
                    System.out.println("Exiting... Bye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); // empty line for readability
        }
    }

    private static void printMenu() {
        System.out.println("===== Student Management System =====");
        System.out.println("1. Add new student");
        System.out.println("2. View all students");
        System.out.println("3. Search student by ID");
        System.out.println("4. Search student by Name");
        System.out.println("5. Delete student by ID");
        System.out.println("6. View students (sorted)");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudentFlow() {
        try {
            System.out.print("Enter ID (int): ");
            int id = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Enter Name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter Course: ");
            String course = sc.nextLine().trim();

            System.out.print("Enter Marks (double): ");
            double marks = Double.parseDouble(sc.nextLine().trim());

            Student s = new Student(id, name, course, marks);
            boolean added = manager.addStudent(s);

            if (added) {
                System.out.println("Student added successfully!");
            } else {
                System.out.println("ID already exists. Student NOT added.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number input. Student NOT added.");
        }
    }

    private static void showAllStudents() {
        if (manager.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        List<Student> all = manager.getAllStudents();
        System.out.println("All Students:");
        for (Student s : all) {
            System.out.println(s);
        }
    }

    private static void searchByIdFlow() {
        try {
            System.out.print("Enter ID to search: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.findById(id);

            if (s != null) {
                System.out.println("Student found:");
                System.out.println(s);
            } else {
                System.out.println("No student found with ID " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void searchByNameFlow() {
        System.out.print("Enter name or part of name: ");
        String namePart = sc.nextLine().trim();

        List<Student> result = manager.findByName(namePart);
        if (result.isEmpty()) {
            System.out.println("No matching students found.");
        } else {
            System.out.println("Matching students:");
            for (Student s : result) {
                System.out.println(s);
            }
        }
    }

    private static void deleteStudentFlow() {
        try {
            System.out.print("Enter ID to delete: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            boolean removed = manager.removeStudent(id);

            if (removed) {
                System.out.println("Student removed successfully.");
            } else {
                System.out.println("No student found with ID " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void showSortedMenu() {
        if (manager.isEmpty()) {
            System.out.println("No students to show.");
            return;
        }

        System.out.println("1. Sort by ID (ascending)");
        System.out.println("2. Sort by marks (descending)");
        System.out.print("Enter your choice: ");
        String ch = sc.nextLine().trim();

        List<Student> list;

        if ("1".equals(ch)) {
            list = manager.getAllStudentsSortedById();
            System.out.println("Students sorted by ID:");
        } else if ("2".equals(ch)) {
            list = manager.getAllStudentsSortedByMarksDesc();
            System.out.println("Students sorted by Marks (high to low):");
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        for (Student s : list) {
            System.out.println(s);
        }
    }
}
