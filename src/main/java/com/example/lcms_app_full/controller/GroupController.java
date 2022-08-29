package com.example.lcms_app_full.controller;

import com.example.lcms_app_full.dto.ApiResponse;
import com.example.lcms_app_full.dto.GroupDTO;
import com.example.lcms_app_full.service.GroupService;
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
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody GroupDTO groupDTO) {
        ApiResponse response = groupService.add(groupDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse response = groupService.getOne(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "") String name,
                                    @RequestParam(value = "filial", defaultValue = "") String filialName,
                                    @RequestParam(value = "course", defaultValue = "") String courseName,
                                    @RequestParam(value = "teacher", defaultValue = "") String teacherName) {
        ApiResponse response = groupService.getAll(page, size, name, filialName, courseName,teacherName);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody GroupDTO groupDTO) {
        ApiResponse response = groupService.edit(id, groupDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = groupService.remove(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
    @PostMapping("/addTeacher/{id}")
    public ResponseEntity<?> addTeacherToGroup(@PathVariable Long id, @RequestParam UUID teacherId) {
        ApiResponse response = groupService.addTeacherToGroup(id,teacherId);
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
