package cft.intensive.potato.api.dto.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
public class PaymentPostRequest {

    private final int creatorId;

    private final int payerId;

    private final int amount;

    private final String comment;
}
