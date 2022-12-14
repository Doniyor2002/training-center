package com.example.lcms_app_full.repository;

import com.example.lcms_app_full.entity.Expense;
import com.example.lcms_app_full.entity.enums.ExpenseType;
import com.example.lcms_app_full.entity.enums.PayType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {


    Page<Expense> findAllByDescriptionContainingIgnoreCaseAndFilial_NameContainingIgnoreCaseAndExpenseTypeContainingIgnoreCaseAndPayTypeContainingIgnoreCase(String name, String filialName, ExpenseType expenseType, PayType payType, Pageable pageable);
}
