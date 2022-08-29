package com.example.lcms_app_full.entity;

import com.example.lcms_app_full.entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "groups")
@Builder
public class Group extends AbsNameEntity {

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Course course;

    @ManyToOne
    private Filial filial;
    @ManyToOne
    private Teacher teacher;
}
