package com.example.lcms_app_full.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResStudentForGroupDto {
    private String filialName;
    private String courseName;
    private String groupName;
}
