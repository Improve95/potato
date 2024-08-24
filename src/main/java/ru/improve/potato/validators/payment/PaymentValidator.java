package ru.improve.potato.validators.payment;

import ru.improve.potato.dto.payment.PaymentPostRequest;

public interface PaymentValidator {

    boolean validatePaymentPostRequest(PaymentPostRequest paymentPostRequest);
}
