package com.example.lcms_app_full.entity;

import com.example.lcms_app_full.entity.template.AbsNameEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "filial")
@Builder
public class Filial extends AbsNameEntity {

    //xodimlar soni
    @JsonIgnore
    @OneToMany(mappedBy = "filial",cascade = CascadeType.ALL)
    private List<User> staffList;
    //o'quvchilar soni
    @JsonIgnore
    @OneToMany(mappedBy = "filial",cascade = CascadeType.ALL)
    private List<Group> groups;
    @OneToMany(mappedBy = "filial")
    private List<Expense> expenses;
    @OneToMany(mappedBy = "filial")
    private List<Payment> payments;

}
