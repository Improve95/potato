package ru.improve.potato.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session extends BaseEntity {

    private String accessToken;

    private String refreshToken;

    private boolean enabled;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private User user;
}
