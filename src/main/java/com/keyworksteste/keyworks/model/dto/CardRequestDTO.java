package com.keyworksteste.keyworks.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDTO {

    @NotNull(message = "Title required. ")
    @Length(min = 3, max = 25)
    private String title;

    private String description;
}
