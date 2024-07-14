package cft.intensive.potato.api.dto.payment;

import cft.intensive.potato.model.payment.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Builder
@Getter
@Jacksonized
public class PaymentPostResponse {

    private final char[] paymentId;

    private final LocalDateTime date;

    private final PaymentStatus status;
}
