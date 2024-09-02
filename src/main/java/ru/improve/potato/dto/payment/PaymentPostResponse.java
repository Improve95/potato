package ru.improve.potato.dto.payment;

import ru.improve.potato.models.payment.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PaymentPostResponse {

    private UUID paymentId;

    private PaymentStatus status;

    private LocalDateTime date;
}
