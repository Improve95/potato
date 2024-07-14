package cft.intensive.potato.core.services.payment.implementations;

import cft.intensive.potato.api.dto.payment.PaymentPostRequest;
import cft.intensive.potato.api.dto.payment.PaymentPostResponse;
import cft.intensive.potato.core.services.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {



    @Override
    public PaymentPostResponse createNewPayment(PaymentPostRequest paymentPostRequest) {
        return null;
    }
}
