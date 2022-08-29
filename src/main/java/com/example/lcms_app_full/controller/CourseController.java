package com.example.lcms_app_full.controller;

import com.example.lcms_app_full.dto.ApiResponse;
import com.example.lcms_app_full.entity.Course;
import com.example.lcms_app_full.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Course course){
        ApiResponse apiResponse=courseService.addCourse(course);
        return ResponseEntity.status(apiResponse.isSuccess()? 201 :409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "") String name){
        ApiResponse apiResponse=courseService.getAllCourse(page,size,name);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse apiResponse=courseService.getOneById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Course course){
        ApiResponse apiResponse=courseService.update(id,course);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse apiResponse=courseService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
}
