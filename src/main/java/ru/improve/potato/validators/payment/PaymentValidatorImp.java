package ru.improve.potato.validators.payment;

import ru.improve.potato.dto.payment.PaymentPostRequest;

public class PaymentValidatorImp implements PaymentValidator {

    @Override
    public boolean validatePaymentPostRequest(PaymentPostRequest paymentPostRequest) {
        return true;
    }
}
