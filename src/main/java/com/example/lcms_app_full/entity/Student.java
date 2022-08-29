package com.example.lcms_app_full.entity;

import com.example.lcms_app_full.entity.enums.StudentStatus;
import com.example.lcms_app_full.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "student")
public class Student extends AbsEntity {
    private String fullName,phone;
    private double balance;
    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;

    //group
    @ManyToMany
    private List<Group> group;
    @OneToMany(mappedBy = "student")
    private List<Payment> payments;

    private Character male;

    @Column(length = 9)
    private String passportNo;

    private String address;

    private String passportGivenBy;

    private Date passportDateOfIssue;

    private Date dateOfBirth;

}
