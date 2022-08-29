package com.example.lcms_app_full.entity;

import com.example.lcms_app_full.entity.enums.PayType;
import com.example.lcms_app_full.entity.enums.Permission;
import com.example.lcms_app_full.entity.template.AbsEntity;
import com.example.lcms_app_full.entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Role extends AbsNameEntity {
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Permission> permissionList;

}
