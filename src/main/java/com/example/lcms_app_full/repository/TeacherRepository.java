package com.example.lcms_app_full.repository;


import com.example.lcms_app_full.entity.Payment;
import com.example.lcms_app_full.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

}
