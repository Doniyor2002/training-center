package com.example.lcms_app_full.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GroupDTO {
    @NotNull(message = "Nomini to'ldirish shart")
    private String name;
    @NotNull(message = "Kursni tanlash majburiy")
    private Long courseId;
    @NotNull(message = "Filialni tanlash majburiy")
    private Long filialId;
    private UUID teacherId;
}
