package org.example.trackersystem_lab1.Controller;

import org.example.trackersystem_lab1.ApiResponse.ApiResponse;
import org.example.trackersystem_lab1.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();

    // 1. Create a new project
    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody Project project) {
        projects.add(project);
        return new ApiResponse("Project added successfully");
    }

    // 2. Display all projects
    @GetMapping("/get")
    public ArrayList<Project> getProjects() {
        return projects;
    }

    // 3. Update a project
    @PutMapping("/update/{index}")
    public ApiResponse updateEvent(@PathVariable int index, @RequestBody Project project) {
        if (index >= 0 && index < projects.size()) {
            projects.set(index, project);
            return new ApiResponse("Project updated successfully");
        }
        return new ApiResponse("Index not found");
    }

    // 4. Delete a project
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteProject(@PathVariable String id) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getId().equalsIgnoreCase(id)) {
                projects.remove(i);
                return new ApiResponse("Project deleted successfully");
            }
        }
        return new ApiResponse("Project not found");
    }

    // 5. Change the project status (done or not done)
    @PutMapping("/change-status/{id}")
    public ApiResponse changeStatus(@PathVariable String id,@RequestBody String status) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getId().equalsIgnoreCase(id)) {
                projects.get(i).setStatus(status);
                return new ApiResponse("Status updated successfully");
            }
        }
        return new ApiResponse("Project not found");
    }

    // 6. Search for a project by given title
    @GetMapping("/search/{title}")
    public Project searchByTitle(@PathVariable String title) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getTitle().equalsIgnoreCase(title)) {
                return projects.get(i);
            }
        }
        return null;
    }

    // 7. Display All projects for one company by companyName
    @GetMapping("/company/{companyName}")
    public ArrayList<Project> getByCompany(@PathVariable String companyName) {
        ArrayList<Project> companyProjects = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getCompanyName().equalsIgnoreCase(companyName)) {
                companyProjects.add(projects.get(i));
            }
        }
        return companyProjects;
    }
}
