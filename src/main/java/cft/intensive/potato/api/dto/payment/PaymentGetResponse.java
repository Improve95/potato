package cft.intensive.potato.api.dto.payment;

import cft.intensive.potato.model.payment.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@ToString
@Jacksonized
public class PaymentGetResponse {

    private final UUID paymentId;

    private final int amount;

    private final int creatorId;

    private final int payerId;

    private final String comment;

    private final LocalDateTime date;

    private final PaymentStatus status;
}
