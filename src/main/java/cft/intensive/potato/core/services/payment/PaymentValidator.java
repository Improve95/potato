package cft.intensive.potato.core.services.payment;

import cft.intensive.potato.api.dto.payment.PaymentPostRequest;

public interface PaymentValidator {

    boolean validatePaymentPostRequest(PaymentPostRequest paymentPostRequest);
}
