package com.example.lcms_app_full.entity;

import com.example.lcms_app_full.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Attachment extends AbsEntity {
    private String fileName;
    private Long size;
    private String contenType; //.xlsx,.docx
    private String url;

}
