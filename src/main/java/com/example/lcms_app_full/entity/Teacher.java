package com.example.lcms_app_full.entity;

import com.example.lcms_app_full.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "teacher")
public class Teacher extends AbsEntity {

    private String fullName,phone;

    private Double balance=0.0;

    @ManyToOne
    private Course course;
    @OneToMany(mappedBy = "teacher")
    private List<Group> groupList;

    @ManyToMany
    private List<Role> role;
    private Character male;

    @Column(length = 9)
    private String passportNo;

    private String address;

    private String passportGivenBy;

    private LocalDate passportDateOfIssue;

    private LocalDate dateOfBirth;
}
