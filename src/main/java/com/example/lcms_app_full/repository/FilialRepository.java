package com.example.lcms_app_full.repository;

import com.example.lcms_app_full.entity.Filial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;



//@RepositoryRestResource(path = "filial")
public interface FilialRepository extends JpaRepository<Filial, Long> {
    //alohida yana controller yozdik
//    @RestResource(path = "some")
//    List<Filial> findAllByNameStartsWith(@Param("name") String name);
    Page<Filial> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
