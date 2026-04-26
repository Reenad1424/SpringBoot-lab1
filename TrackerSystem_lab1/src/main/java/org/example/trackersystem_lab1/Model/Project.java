package org.example.trackersystem_lab1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    private String id;
    private String title;
    private String description;
    private String status;
    private String companyName;
}
