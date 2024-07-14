package cft.intensive.potato.core.repository.implementations;

import cft.intensive.potato.core.repository.PaymentRepository;
import cft.intensive.potato.model.payment.Payment;
import cft.intensive.potato.model.payment.PaymentStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
public class PaymentRepositoryImp implements PaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Payment getById(UUID paymentId) {
        return em.find(Payment.class, paymentId);
    }

    @Override
    public UUID addNewPayment(Payment payment) {
        em.persist(payment);
        return payment.getUuid();
    }
}
