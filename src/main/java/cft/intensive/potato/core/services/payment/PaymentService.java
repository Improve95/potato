package cft.intensive.potato.core.services.payment;

import cft.intensive.potato.api.dto.payment.PaymentGetResponse;
import cft.intensive.potato.api.dto.payment.PaymentPostRequest;
import cft.intensive.potato.api.dto.payment.PaymentPostResponse;

import java.util.UUID;

public interface PaymentService {

    PaymentPostResponse createNewPayment(PaymentPostRequest paymentPostRequest);

    void deletePayment(UUID paymentId, int userId);

    PaymentGetResponse getBillPaymentsByUserId(int userId);
}
