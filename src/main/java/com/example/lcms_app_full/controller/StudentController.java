package com.example.lcms_app_full.controller;

import com.example.lcms_app_full.dto.ApiResponse;
import com.example.lcms_app_full.dto.GroupDTO;
import com.example.lcms_app_full.dto.StudentDTO;
import com.example.lcms_app_full.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
private final StudentService studentService;
    

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody StudentDTO studentDTO) {
        ApiResponse response = studentService.add(studentDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable UUID id) {
        ApiResponse response = studentService.getOne(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "") String phone,
                                    @RequestParam(value = "filial", defaultValue = "") String filialName,
                                    @RequestParam(value = "course", defaultValue = "") String courseName,
                                    @RequestParam(value = "group", defaultValue = "") String groupName) {
        ApiResponse response = studentService.getAll(page, size, phone, filialName, courseName,groupName);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @Valid @RequestBody StudentDTO studentDTO) {
        ApiResponse response = studentService.edit(id, studentDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        ApiResponse response = studentService.remove(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }


    //validation ishlashi un metod
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    
}
