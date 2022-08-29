package com.example.lcms_app_full.repository;


import com.example.lcms_app_full.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
