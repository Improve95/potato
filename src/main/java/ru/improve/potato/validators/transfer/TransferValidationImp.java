package ru.improve.potato.validators.transfer;

import ru.improve.potato.dto.transfer.TransferPostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransferValidationImp implements TransferValidation {

//    private final WalletRepository walletRepository;

    public boolean validateTransferCreateRequest(TransferPostRequest transferPostRequest) {
        if (transferPostRequest.getSenderWalletId() < 0
                || transferPostRequest.getReceiverWalletId() < 0
                || transferPostRequest.getAmount() < 0) {
            /*и другие проверки*/
            return false;
        }

        return true;
    }

    public boolean isEnoughtMoneyForTransfer(TransferPostRequest transferPostRequest) {
//        if (walletRepository.getById(transferPostRequest.getSenderWalletId()).getBalance()
//                < transferPostRequest.getAmount()) return false;
        return true;
    }
}
