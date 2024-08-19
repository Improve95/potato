package ru.improve.potato.api.dto.payment;

import ru.improve.potato.model.payment.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PaymentGetResponse {

    private UUID paymentId;

    private int amount;

    private int creatorId;

    private int payerId;

    private String comment;

    private LocalDateTime date;

    private PaymentStatus status;
}
