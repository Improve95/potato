package cft.intensive.potato.core.services.payment.implementations;

import cft.intensive.potato.api.dto.payment.PaymentGetResponse;
import cft.intensive.potato.api.dto.payment.PaymentPostRequest;
import cft.intensive.potato.api.dto.payment.PaymentPostResponse;
import cft.intensive.potato.core.exceptions.NoAccessException;
import cft.intensive.potato.core.exceptions.transfer.NotFoundException;
import cft.intensive.potato.core.repository.PaymentRepository;
import cft.intensive.potato.core.repository.UsersRepository;
import cft.intensive.potato.core.services.payment.PaymentService;
import cft.intensive.potato.model.payment.Payment;
import cft.intensive.potato.model.payment.PaymentStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {

    private final UsersRepository usersRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    @Override
    public PaymentPostResponse createNewPayment(PaymentPostRequest paymentPostRequest) {
        if (!usersRepository.userIsExist(paymentPostRequest.getCreatorId())
                || !usersRepository.userIsExist(paymentPostRequest.getPayerId())) {
            throw new NotFoundException("users not found by id");
        }

        Payment newPayment = Payment.builder()
                .creatorId(paymentPostRequest.getCreatorId())
                .payerId(paymentPostRequest.getPayerId())
                .amount(paymentPostRequest.getAmount())
                .comment(paymentPostRequest.getComment())
                .status(PaymentStatus.UNPAID)
                .date(LocalDateTime.now())
                .build();

        paymentRepository.addNewPayment(newPayment);

        return PaymentPostResponse.builder()
                .paymentId(newPayment.getUuid())
                .status(newPayment.getStatus())
                .date(newPayment.getDate())
                .build();
    }

    @Transactional
    @Override
    public void deletePayment(UUID paymentId, int userId) {
        Payment payment = Optional.ofNullable(paymentRepository.getById(paymentId))
                .orElseThrow(() -> new NotFoundException("payment not found by id"));

        if (payment.getCreatorId() != userId) {
            throw new NoAccessException("you cannot delete this payment");
        }

        payment.setStatus(PaymentStatus.CANCELED);
    }

    @Override
    public PaymentGetResponse getBillPaymentsByUserId(int userId) {
        return null;
    }
}
