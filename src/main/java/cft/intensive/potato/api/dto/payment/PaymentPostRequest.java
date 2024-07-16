package cft.intensive.potato.api.dto.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
public class PaymentPostRequest {

    private int creatorId;

    private int payerId;

    private int amount;

    private String comment;
}
