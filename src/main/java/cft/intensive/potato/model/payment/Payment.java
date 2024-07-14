package cft.intensive.potato.model.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private int amount;

    @Column(name = "creator_id")
    private int creatorId;

    @Column(name = "payer_id")
    private int payerId;

    private String comment;

    private PaymentStatus status;

    private LocalDateTime date;

    public Payment() {}
}
