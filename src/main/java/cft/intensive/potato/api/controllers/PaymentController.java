package cft.intensive.potato.api.controllers;

import cft.intensive.potato.api.dto.payment.PaymentGetResponse;
import cft.intensive.potato.api.dto.payment.PaymentPostRequest;
import cft.intensive.potato.api.dto.payment.PaymentPostResponse;
import cft.intensive.potato.core.services.payment.PaymentService;
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

    @DeleteMapping("/{paymentId}/users/{creatorId}")
    public void deletePayment(@PathVariable UUID paymentId, @PathVariable int creatorId) {
        log.info("request arrived: delete payment with id {} by user {}", paymentId, creatorId);
        paymentService.deletePayment(paymentId, creatorId);
    }

    @GetMapping("/billed/users/{userId}")
    public List<PaymentGetResponse> getBillPaymentsByUserId(@PathVariable int userId) {
        log.info("request arrived: get bill payments by user id {}", userId);
        return paymentService.getBillPaymentsForUserByUserId(userId);
    }

    @GetMapping("/unpaid/users/{userId}")
    public List<PaymentGetResponse> getUnpaidPaymentsByUserId(@PathVariable int userId) {
        log.info("request arrived: get bill payments by user id {}", userId);
        return paymentService.getUnpaidPaymentsForUserByUserID(userId);
    }
}
