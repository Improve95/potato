package cft.intensive.potato.core.repository;

import cft.intensive.potato.model.payment.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository {

    Payment getById(UUID paymentId);

    UUID addNewPayment(Payment payment);

    List<Payment> getPaymentsByCreatorId(int creatorId);

    List<Payment> getPaymentsByPayerId(int creatorId);
}
