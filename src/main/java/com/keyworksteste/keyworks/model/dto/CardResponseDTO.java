package com.keyworksteste.keyworks.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDTO {

    private Long number;
    private String title;
    private String description;
    private LocalDateTime createdAt;
}
