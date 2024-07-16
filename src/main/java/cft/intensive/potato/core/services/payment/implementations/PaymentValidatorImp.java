package cft.intensive.potato.core.services.payment.implementations;

import cft.intensive.potato.api.dto.payment.PaymentPostRequest;
import cft.intensive.potato.core.services.payment.PaymentValidator;

public class PaymentValidatorImp implements PaymentValidator {

    @Override
    public boolean validatePaymentPostRequest(PaymentPostRequest paymentPostRequest) {
        return true;
    }
}
