package ru.improve.potato.dto.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentPostRequest {

    private int creatorId;

    private int payerId;

    private int amount;

    private String comment;
}
