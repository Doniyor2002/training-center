package com.example.lcms_app_full.repository;


import com.example.lcms_app_full.entity.Group;
import com.example.lcms_app_full.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

}
