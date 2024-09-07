package com.codex.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description is required!")
    private String description;

    @NotNull(message = "Amount is required!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive!")
    private Double amount;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false )
    @JsonBackReference(value="user-expense")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = true)
    private Category category;


    @PrePersist
    private void updateCreatedAt () {
        this.createdAt = LocalDateTime.now();
    }

}
