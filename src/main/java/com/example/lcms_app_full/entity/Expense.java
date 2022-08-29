package com.example.lcms_app_full.entity;

import com.example.lcms_app_full.entity.enums.ExpenseType;
import com.example.lcms_app_full.entity.enums.PayType;
import com.example.lcms_app_full.entity.template.AbsEntity;
import com.example.lcms_app_full.entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "expense")
public class Expense extends AbsEntity {
    private String description;
    @ManyToOne
    private Filial filial;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PayType payType;
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

}
