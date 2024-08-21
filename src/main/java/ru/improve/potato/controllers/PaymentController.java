package ru.improve.potato.controllers;

import ru.improve.potato.dto.payment.PaymentGetResponse;
import ru.improve.potato.dto.payment.PaymentPostRequest;
import ru.improve.potato.dto.payment.PaymentPostResponse;
import ru.improve.potato.services.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public PaymentPostResponse createPayment(@RequestBody PaymentPostRequest paymentPostRequest) {
        log.info("request arrived: create new payment: {}", paymentPostRequest);
        return paymentService.createNewPayment(paymentPostRequest);
    }

    @PostMapping("/pay/{paymentId}/users/{userId}")
    public void PayPayment(@PathVariable UUID paymentId, @PathVariable int userId) {
        log.info("request arrived: pay payment {} by user {}", paymentId, userId);
        paymentService.payPaymentByUser(paymentId, userId);
    }

    @DeleteMapping("/{paymentId}/users/{creatorId}")
    public void deletePayment(@PathVariable UUID paymentId, @PathVariable int creatorId) {
        log.info("request arrived: delete payment with id {} by user {}", paymentId, creatorId);
        paymentService.deletePayment(paymentId, creatorId);
    }

    @GetMapping("/{paymentId}/unpaid/users/{userId}")
    public PaymentGetResponse getBillPaymentByIdForPayer(@PathVariable UUID paymentId, @PathVariable int userId) {
        log.info("request arrived: delete payment with id {} by user {}", paymentId, userId);
        return paymentService.getBillPaymentForUser(paymentId, userId);
    }

    @GetMapping("/billed/users/{userId}")
    public List<PaymentGetResponse> getBillPaymentsByUserId(@PathVariable int userId) {
        log.info("request arrived: get bill payments by user id {}", userId);
        return paymentService.getBillPaymentsForUser(userId);
    }

    @GetMapping("/unpaid/users/{userId}")
    public List<PaymentGetResponse> getUnpaidPaymentsByUserId(@PathVariable int userId) {
        log.info("request arrived: get bill payments by user id {}", userId);
        return paymentService.getUnpaidPaymentsForUser(userId);
    }
}
