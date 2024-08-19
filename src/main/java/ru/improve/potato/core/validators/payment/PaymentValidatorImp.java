package ru.improve.potato.core.validators.payment;

import ru.improve.potato.api.dto.payment.PaymentPostRequest;
import ru.improve.potato.core.validators.payment.PaymentValidator;

public class PaymentValidatorImp implements PaymentValidator {

    @Override
    public boolean validatePaymentPostRequest(PaymentPostRequest paymentPostRequest) {
        return true;
    }
}
