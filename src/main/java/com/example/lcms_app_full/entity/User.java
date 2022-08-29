package com.example.lcms_app_full.entity;

import com.example.lcms_app_full.entity.enums.Permission;
import com.example.lcms_app_full.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "users")
public class User extends AbsEntity {
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

//    @Enumerated
//    @ElementCollection
//    private Set<AuthRole> authRole;

    private String fullName, phone;

    private Double salary;


    @ManyToOne
    private Filial filial;

    @Enumerated
    private Permission positionType;
    private String password;
    private boolean accountNonExpired = true; //accountni vaqti o'tmaganmi?
    private boolean accountNonLocked = true; //bloklanmaganmi?
    private boolean credentialsNonExpired = true; //parol o'znikimi
    private boolean enabled = true; //tizimga kimdir kirganda undan foydalanish huquqi

}
