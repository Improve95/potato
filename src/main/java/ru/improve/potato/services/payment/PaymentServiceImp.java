package ru.improve.potato.services.payment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.improve.potato.dto.payment.PaymentGetResponse;
import ru.improve.potato.dto.payment.PaymentPostRequest;
import ru.improve.potato.dto.payment.PaymentPostResponse;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {
//
//    private final UsersDAO usersDAO;
//    private final PaymentRepository paymentRepository;

//    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public PaymentPostResponse createNewPayment(PaymentPostRequest paymentPostRequest) {
        /*if (!usersDAO.userIsExist(paymentPostRequest.getCreatorId())
                || !usersDAO.userIsExist(paymentPostRequest.getPayerId())) {
            throw new NotFoundException("user not found", List.of("id"));
        }*/

        /*Payment newPayment = modelMapper.map(paymentPostRequest, Payment.class);
        newPayment.setStatus(PaymentStatus.UNPAID);
        newPayment.setDate(LocalDateTime.now());

        paymentRepository.addNewPayment(newPayment);

        return modelMapper.map(newPayment, PaymentPostResponse.class);*/
        return null;
    }

    @Transactional
    @Override
    public void payPaymentByUser(UUID paymentId, int userId) {
//        Payment payment = Optional.ofNullable(paymentRepository.getById(paymentId))
//                .orElseThrow(() -> new NotFoundException("user not found", List.of("id")));

        /*User user = Optional.ofNullable(usersDAO.getById(userId))
                .orElseThrow(() -> new NotFoundException("user not found", List.of("id")));

        if (payment.getPayerId() != userId) {
            throw new NoAccessException("you cannot pay this payment");
        }

        if (user.getWallet().getBalance() < payment.getAmount()) {
            throw new NotEnoughtMoneyException("not enought money for this operation");
        }*/

        /*user.getWallet().subAmountFromBalance(payment.getAmount());
        payment.setStatus(PaymentStatus.PAID);*/
    }

    @Transactional
    @Override
    public void deletePayment(UUID paymentId, int userId) {
//        Payment payment = Optional.ofNullable(paymentRepository.getById(paymentId))
//                .orElseThrow(() -> new NotFoundException("payment not found", List.of("id")));

        /*if (payment.getCreatorId() != userId) {
            throw new NoAccessException("you cannot delete this payment");
        }*/

//        payment.setStatus(PaymentStatus.CANCELED);
    }

    @Override
    public List<PaymentGetResponse> getBillPaymentsForUser(int userId) {
        /*if (!usersDAO.userIsExist(userId)) {
            throw new NotFoundException("user not found", List.of("id"));
        }*/

//        List<Payment> paymentList = paymentRepository.getPaymentsByCreatorId(userId);

        /*return paymentList.stream()
                .map(payment -> modelMapper.map(payment, PaymentGetResponse.class))
                .collect(Collectors.toList());*/
        return null;
    }

    @Override
    public PaymentGetResponse getBillPaymentForUser(UUID paymentId, int userId) {
//        Payment payment = Optional.ofNullable(paymentRepository.getById(paymentId))
//                .orElseThrow(() -> new NotFoundException("payment not found", List.of("id")));

        /*if (payment.getPayerId() != userId) {
            throw new NoAccessException("you cannot get this payment");
        }*/

//        return modelMapper.map(payment, PaymentGetResponse.class);
        return null;
    }

    @Override
    public List<PaymentGetResponse> getUnpaidPaymentsForUser(int userId) {
        /*if (!usersDAO.userIsExist(userId)) {
            throw new NotFoundException("user not found", List.of("id"));
        }*/

//        List<Payment> paymentList = paymentRepository.getPaymentsByPayerId(userId);

       /* return paymentList.stream()
                .filter(payment -> payment.getStatus() == PaymentStatus.UNPAID)
                .map(payment -> modelMapper.map(payment, PaymentGetResponse.class))
                .collect(Collectors.toList());*/
        return null;
    }
}
