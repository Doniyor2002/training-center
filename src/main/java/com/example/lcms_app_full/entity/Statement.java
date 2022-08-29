package com.example.lcms_app_full.entity;

import com.example.lcms_app_full.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class Statement extends AbsEntity {

    @OneToOne
    private Teacher teacher;
    private Long students;//studentlar soni
    private double percent;//foiz
    private double summa;
    private boolean paid;//to'langan

}
