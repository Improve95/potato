package ru.improve.potato.core.validators.payment;

import ru.improve.potato.api.dto.payment.PaymentPostRequest;

public interface PaymentValidator {

    boolean validatePaymentPostRequest(PaymentPostRequest paymentPostRequest);
}
