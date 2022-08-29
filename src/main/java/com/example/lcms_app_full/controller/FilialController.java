package com.example.lcms_app_full.controller;

import com.example.lcms_app_full.dto.ApiResponse;
import com.example.lcms_app_full.entity.Course;
import com.example.lcms_app_full.entity.Filial;
import com.example.lcms_app_full.service.FilialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/filial")
@RequiredArgsConstructor
public class FilialController {

    private final FilialService filialService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Filial filial){
        ApiResponse apiResponse=filialService.addFilial(filial);
        return ResponseEntity.status(apiResponse.isSuccess()? 201 :409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "") String name){
        ApiResponse apiResponse=filialService.getAllFilial(page,size,name);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse apiResponse=filialService.getOneById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Filial filial){
        ApiResponse apiResponse=filialService.update(id,filial);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse apiResponse=filialService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
}
