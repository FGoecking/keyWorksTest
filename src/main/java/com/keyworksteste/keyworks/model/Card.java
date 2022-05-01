package com.keyworksteste.keyworks.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @SequenceGenerator(name = "card_sequece", sequenceName = "card_sequece", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long number;

    @Column(nullable = false, length = 25)
    private String title;

    private String description;

    private LocalDateTime createdAt;
}
