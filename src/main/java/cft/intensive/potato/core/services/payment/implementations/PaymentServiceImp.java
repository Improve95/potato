package cft.intensive.potato.core.services.payment.implementations;

import cft.intensive.potato.api.dto.payment.PaymentPostRequest;
import cft.intensive.potato.api.dto.payment.PaymentPostResponse;
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
}
