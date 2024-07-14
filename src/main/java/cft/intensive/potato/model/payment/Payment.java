package cft.intensive.potato.model.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@RequiredArgsConstructor
public class Payment {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid_generator")
    private byte[] uuid = new byte[128];

    private int amount;

    @Column(name = "creator_id")
    private int creatorId;

    @Column(name = "payer_id")
    private int payerId;

    private String comment;

    private PaymentStatus status;

    private LocalDateTime date;
}
