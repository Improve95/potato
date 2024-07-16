package cft.intensive.potato.core.services.payment.implementations;

import cft.intensive.potato.api.dto.payment.PaymentGetResponse;
import cft.intensive.potato.api.dto.payment.PaymentPostRequest;
import cft.intensive.potato.api.dto.payment.PaymentPostResponse;
import cft.intensive.potato.core.exceptions.NoAccessException;
import cft.intensive.potato.core.exceptions.transfer.NotEnoughtMoneyException;
import cft.intensive.potato.core.exceptions.transfer.NotFoundException;
import cft.intensive.potato.core.repository.PaymentRepository;
import cft.intensive.potato.core.repository.UsersRepository;
import cft.intensive.potato.core.services.payment.PaymentService;
import cft.intensive.potato.model.User;
import cft.intensive.potato.model.payment.Payment;
import cft.intensive.potato.model.payment.PaymentStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {

    private final UsersRepository usersRepository;
    private final PaymentRepository paymentRepository;

    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public PaymentPostResponse createNewPayment(PaymentPostRequest paymentPostRequest) {
        if (!usersRepository.userIsExist(paymentPostRequest.getCreatorId())
                || !usersRepository.userIsExist(paymentPostRequest.getPayerId())) {
            throw new NotFoundException("users not found by id");
        }

        Payment newPayment = modelMapper.map(paymentPostRequest, Payment.class);
        newPayment.setStatus(PaymentStatus.UNPAID);
        newPayment.setDate(LocalDateTime.now());
        /*Payment newPayment = Payment.builder()
                .creatorId(paymentPostRequest.getCreatorId())
                .payerId(paymentPostRequest.getPayerId())
                .amount(paymentPostRequest.getAmount())
                .comment(paymentPostRequest.getComment())
                .status(PaymentStatus.UNPAID)
                .date(LocalDateTime.now())
                .build();*/

        paymentRepository.addNewPayment(newPayment);

        return modelMapper.map(newPayment, PaymentPostResponse.class);

        /*return PaymentPostResponse.builder()
                .paymentId(newPayment.getUuid())
                .status(newPayment.getStatus())
                .date(newPayment.getDate())
                .build();*/
    }

    @Transactional
    @Override
    public void payPaymentByUser(UUID paymentId, int userId) {
        Payment payment = Optional.ofNullable(paymentRepository.getById(paymentId))
                .orElseThrow(() -> new NotFoundException("payment not found by id"));

        User user = Optional.ofNullable(usersRepository.getById(userId))
                .orElseThrow(() -> new NotFoundException("user not found by id"));

        if (user.getWallet().getBalance() < payment.getAmount()) {
            throw new NotEnoughtMoneyException("not enought money for this operation");
        }

        user.getWallet().subAmountFromBalance(payment.getAmount());
        payment.setStatus(PaymentStatus.PAID);
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
    public List<PaymentGetResponse> getBillPaymentsForUserByUserId(int userId) {
        if (!usersRepository.userIsExist(userId)) {
            throw new NotFoundException("user not found by id");
        }

        List<Payment> paymentList = paymentRepository.getPaymentsByCreatorId(userId);

        return paymentList.stream()
                .map(payment -> modelMapper.map(payment, PaymentGetResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentGetResponse> getUnpaidPaymentsForUserByUserID(int userId) {
        if (!usersRepository.userIsExist(userId)) {
            throw new NotFoundException("user not found by id");
        }

        List<Payment> paymentList = paymentRepository.getPaymentsByPayerId(userId);

        return paymentList.stream()
                .filter(payment -> payment.getStatus() == PaymentStatus.UNPAID)
                .map(payment -> modelMapper.map(payment, PaymentGetResponse.class))
                .collect(Collectors.toList());
    }
}
