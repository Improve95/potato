package cft.intensive.potato.core.repository;

import cft.intensive.potato.model.payment.Payment;

import java.util.UUID;

public interface PaymentRepository {

    Payment getById(UUID paymentId);

    UUID addNewPayment(Payment payment);

//    void changePaymentStatus(PaymentStatus paymentStatus);
}
