package com.desafio.backendsbr.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false, name = "user_document")
    private String userDocument;

    @Column(nullable = false, name = "credit_card_token")
    private String creditCardToken;

    @Column(nullable = false, name = "\"value\"")
    private Long value;
}
