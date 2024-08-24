package ru.improve.potato.services.payment;

import ru.improve.potato.dto.payment.PaymentGetResponse;
import ru.improve.potato.dto.payment.PaymentPostRequest;
import ru.improve.potato.dto.payment.PaymentPostResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    PaymentPostResponse createNewPayment(PaymentPostRequest paymentPostRequest);

    void payPaymentByUser(UUID paymentId, int userId);

    void deletePayment(UUID paymentId, int userId);

    PaymentGetResponse getBillPaymentForUser(UUID paymentId, int userId);

    List<PaymentGetResponse> getBillPaymentsForUser(int userId);

    List<PaymentGetResponse> getUnpaidPaymentsForUser(int userId);
}
