package cft.intensive.potato.api.dto.payment;

import cft.intensive.potato.model.payment.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Jacksonized
public class PaymentPostResponse {

    private final UUID paymentId;

    private final PaymentStatus status;

    private final LocalDateTime date;
}
