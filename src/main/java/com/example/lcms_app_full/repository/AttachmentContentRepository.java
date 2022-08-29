package com.example.lcms_app_full.repository;


import com.example.lcms_app_full.entity.Attachment;
import com.example.lcms_app_full.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {

    AttachmentContent findByAttachment_Id(UUID uuid);
    AttachmentContent findByAttachment(Attachment attachment);

}
