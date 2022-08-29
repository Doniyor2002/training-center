package com.example.lcms_app_full.entity;

import com.example.lcms_app_full.entity.enums.PayType;
import com.example.lcms_app_full.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class Payment extends AbsEntity {
    @ManyToOne
    private Student student;
    @ManyToOne
    private Filial filial;
    private Double amount;
    @Enumerated
    private PayType payType;
}
