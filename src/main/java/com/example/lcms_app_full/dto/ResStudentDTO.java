package com.example.lcms_app_full.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AssociationOverride;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResStudentDTO {
    private String fullName;
    private String phone;
    private String studentStatus;
    private List<ResStudentForGroupDto> groups;
    private Character male;
    private String address;
    private String dateOfBirth;

}
