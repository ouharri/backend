package com.ouharri.aftas.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ranking")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ranking implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RankingId id;

    @NotNull(message = "Score cannot be null.")
    private Long score;

    @NotNull(message = "Rank cannot be null.")
    private Integer rank;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updatedAt;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Competition.class
    )
    @JoinColumn(name = "competition_id", insertable = false, updatable = false)
    private Competition competition;
}