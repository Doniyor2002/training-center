package com.example.lcms_app_full.entity;

import com.example.lcms_app_full.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class AttachmentContent extends AbsEntity {
    @OneToOne
    private Attachment attachment;
    private byte[] bytes;
}
