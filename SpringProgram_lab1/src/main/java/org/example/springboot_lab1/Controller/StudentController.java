package org.example.springboot_lab1.Controller;

import org.example.springboot_lab1.ApiResponse.ApiResponse;
import org.example.springboot_lab1.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    ArrayList<Student> students = new ArrayList<>();

    // 1. Create a new student
    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        students.add(student);
        return new ApiResponse("Student added successfully");
    }

    // 2. Display all students
    @GetMapping("/get")
    public ArrayList<Student> getStudents() {
        return students;
    }

    // 3. Update a student
    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@PathVariable int index, @RequestBody Student student) {
        if (index >= 0 && index < students.size()) {
            students.set(index, student);
            return new ApiResponse("Updated successfully");
        }
        return new ApiResponse("Student not found");
    }

    // 4. Delete a student
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteStudent(@PathVariable String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equalsIgnoreCase(id)) {
                students.remove(i);
                return new ApiResponse("Student deleted successfully");
            }
        }
        return new ApiResponse("Student not found");
    }

    // 5. Classify students into honors categories based on GPA
    @GetMapping("/honors")
    public ApiResponse getHonors() {
        ArrayList<Student> firstHonor = new ArrayList<>();
        ArrayList<Student> secondHonor = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            double gpa = students.get(i).getGpa();
            if (gpa >= 4.75) {
                firstHonor.add(students.get(i));
            } else if (gpa >= 4.50) {
                secondHonor.add(students.get(i));
            }
        }
        return new ApiResponse("First Honor: " + firstHonor + "\nSecond Honor: " + secondHonor);
    }

    // 6. Display students whose GPA is greater than the average GPA
    @GetMapping("/above-average")
    public ArrayList<Student> getAboveAverage() {
        if (students.isEmpty()) {
            return null;
        }

        double sum = 0;
        for (int i = 0; i < students.size(); i++) {
            sum += students.get(i).getGpa();
        }

        double average = sum / students.size();
        ArrayList<Student> aboveAverageStudents = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getGpa() > average) {
                aboveAverageStudents.add(students.get(i));
            }
        }
        return aboveAverageStudents;
    }
}
