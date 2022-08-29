package com.example.lcms_app_full.repository;


import com.example.lcms_app_full.entity.Group;
import com.example.lcms_app_full.entity.Payment;
import com.example.lcms_app_full.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findAllByGroup(List<Group> group);

}
