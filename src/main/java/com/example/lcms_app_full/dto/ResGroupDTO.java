package com.example.lcms_app_full.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResGroupDTO {
    private String name;
    private String filialName;
    private String courseName;
    private String teacherName;
    private List<String> StudentName;
}
