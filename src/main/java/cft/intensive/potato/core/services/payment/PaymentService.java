package cft.intensive.potato.core.services.payment;

import cft.intensive.potato.api.dto.payment.PaymentPostRequest;
import cft.intensive.potato.api.dto.payment.PaymentPostResponse;

public interface PaymentService {

    PaymentPostResponse createNewPayment(PaymentPostRequest paymentPostRequest);
}
