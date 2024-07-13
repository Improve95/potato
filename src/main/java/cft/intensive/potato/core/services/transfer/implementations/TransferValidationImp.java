package cft.intensive.potato.core.services.transfer.implementations;

import cft.intensive.potato.api.dto.transfer.TransferCreateRequest;
import cft.intensive.potato.core.repository.WalletRepository;
import cft.intensive.potato.core.services.transfer.TransferValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransferValidationImp implements TransferValidation {

    private final WalletRepository walletRepository;

    public boolean validateTransferCreateRequest(TransferCreateRequest transferCreateRequest) {
        if (transferCreateRequest.getSenderWalletId() < 0
                || transferCreateRequest.getReceiverWalletId() < 0
                || transferCreateRequest.getAmount() < 0) {
            /*и другие проверки*/
            return false;
        }

        return true;
    }

    public boolean isEnoughtMoneyForTransfer(TransferCreateRequest transferCreateRequest) {
        if (walletRepository.getById(transferCreateRequest.getSenderWalletId()).getBalance()
                < transferCreateRequest.getAmount()) return false;
        return true;
    }
}
