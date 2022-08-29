package com.example.lcms_app_full.dto;

import com.example.lcms_app_full.entity.Group;
import com.example.lcms_app_full.entity.Payment;
import com.example.lcms_app_full.entity.enums.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDTO {
    private String fullName;
    private String phone;
    private String studentStatus;
    private Long groupId;
    private Character male;
    private String passportNo;
    private double balance;
    private String address;
    private String passportGivenBy;
    private String passportDateOfIssue;
    private String dateOfBirth;
}
