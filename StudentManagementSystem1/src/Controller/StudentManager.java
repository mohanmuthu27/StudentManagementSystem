package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentManager {

    // Collections Framework: using List and ArrayList
    private List<Student> students = new ArrayList<>();

    // Add new student (duplicate ID check)
    public boolean addStudent(Student s) {
        // same ID already iruka nu check panrom
        if (findById(s.getId()) != null) {
            return false; // duplicate ID
        }
        students.add(s);
        return true;
    }

    // Remove by ID
    public boolean removeStudent(int id) {
        Student toRemove = findById(id);
        if (toRemove != null) {
            students.remove(toRemove);
            return true;
        }
        return false;
    }

    // Find student by ID
    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null; // not found
    }

    // Find students by name (partial match, case-insensitive)
    public List<Student> findByName(String namePart) {
        List<Student> result = new ArrayList<>();
        String keyword = namePart.toLowerCase();

        for (Student s : students) {
            if (s.getName().toLowerCase().contains(keyword)) {
                result.add(s);
            }
        }
        return result;
    }

    // Return all students (original order)
    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // return copy for safety
    }

    // Return students sorted by ID (ascending)
    public List<Student> getAllStudentsSortedById() {
        List<Student> copy = new ArrayList<>(students);
        Collections.sort(copy, Comparator.comparingInt(Student::getId));
        return copy;
    }

    // Return students sorted by marks (descending)
    public List<Student> getAllStudentsSortedByMarksDesc() {
        List<Student> copy = new ArrayList<>(students);
        Collections.sort(copy, (a, b) -> Double.compare(b.getMarks(), a.getMarks()));
        return copy;
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }
}
