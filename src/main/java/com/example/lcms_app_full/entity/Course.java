package com.example.lcms_app_full.entity;

import com.example.lcms_app_full.entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "course")
public class Course extends AbsNameEntity {

    private double duration;//davomiyligi
    private double price;//narxi
    @OneToMany(mappedBy = "course")
    private List<Group> groupList;//guruhlar ro'yhati
    @OneToMany(mappedBy = "course")
    private List<Teacher> teachers;//o'qituvchilar ro'yhati

}
