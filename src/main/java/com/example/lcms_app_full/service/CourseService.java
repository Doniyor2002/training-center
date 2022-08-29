package com.example.lcms_app_full.service;

import com.example.lcms_app_full.dto.ApiResponse;
import com.example.lcms_app_full.entity.Course;
import com.example.lcms_app_full.exception.ResourceNotFoundException;
import com.example.lcms_app_full.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    public ApiResponse addCourse(Course course) {
        Course save = courseRepository.save(course);
        if (save!=null){
            return ApiResponse.builder().data(save).success(true).message("Added").build();
        }
        return ApiResponse.builder().success(false).message("Something went wrong").build();
    }

    public ApiResponse getAllCourse(int page, int size, String name) {
        Pageable pageable=PageRequest.of(page, size);
        Page<Course> all=null;
        if (name!=null){
            all=courseRepository.findAllByNameContainingIgnoreCase(name,pageable);
        }
        else if (all.isEmpty()) {
            all=courseRepository.findAll(pageable);
        }
        return ApiResponse.builder().message("Course list").success(true).data(all).build();
    }

    public ApiResponse getOneById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        return ApiResponse.builder().data(course).message("Course").success(true).build();
    }

    public ApiResponse update(Long id, Course course) {
        Course course1 = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        course1.setDuration(course.getDuration());
        course1.setPrice(course.getPrice());
        course1.setName(course.getName());
        course1.setStatus(course.isStatus());
        Course save = courseRepository.save(course1);
        return ApiResponse.builder().success(true).message("Updated").data(save).build();
    }

    public ApiResponse delete(Long id) {
        boolean exists = courseRepository.existsById(id);
        if (exists){
            courseRepository.deleteById(id);
            return ApiResponse.builder().message("Deleted").success(true).build();
        }
        else return ApiResponse.builder().message("Course not found").success(false).build();
    }
}
