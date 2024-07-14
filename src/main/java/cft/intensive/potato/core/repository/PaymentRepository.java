package cft.intensive.potato.core.repository;

import cft.intensive.potato.model.payment.Payment;

import java.util.UUID;

public interface PaymentRepository {

    UUID addNewPayment(Payment payment);
}
