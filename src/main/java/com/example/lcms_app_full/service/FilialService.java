package com.example.lcms_app_full.service;

import com.example.lcms_app_full.dto.ApiResponse;
import com.example.lcms_app_full.entity.Course;
import com.example.lcms_app_full.entity.Filial;
import com.example.lcms_app_full.exception.ResourceNotFoundException;
import com.example.lcms_app_full.repository.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilialService {

    private final FilialRepository filialRepository;
    public ApiResponse addFilial(Filial filial) {

        Filial save = filialRepository.save(filial);
        if (save!=null){
            return ApiResponse.builder().data(save).success(true).message("Added").build();
        }
        return ApiResponse.builder().success(false).message("Something went wrong").build();
    }

    public ApiResponse getAllFilial(int page, int size, String name) {
        Pageable pageable= PageRequest.of(page, size);
        Page<Filial> all=null;
        if (name!=null){
            all=filialRepository.findAllByNameContainingIgnoreCase(name,pageable);
        }
        else if (all.isEmpty()) {
            all=filialRepository.findAll(pageable);
        }
        return ApiResponse.builder().message("Filial list").success(true).data(all).build();
    }

    public ApiResponse getOneById(Long id) {
        Filial filial = filialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Filial", "id", id));
        return ApiResponse.builder().data(filial).message("Filial").success(true).build();
    }

    public ApiResponse update(Long id, Filial filial) {
        Filial filial1 = filialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Filial", "id", id));
        filial1.setName(filial.getName());
        filial1.setStatus(filial.isStatus());
        Filial save = filialRepository.save(filial1);
        return ApiResponse.builder().success(true).message("Updated").data(save).build();
    }

    public ApiResponse delete(Long id) {
        boolean exists = filialRepository.existsById(id);
        if (exists){
            filialRepository.deleteById(id);
            return ApiResponse.builder().message("Deleted").success(true).build();
        }
        else return ApiResponse.builder().message("Filial not found").success(false).build();
    }
}
