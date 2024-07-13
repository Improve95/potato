package cft.intensive.potato.core.services.transfer.implementations;

import cft.intensive.potato.api.dto.transfer.TransferCreateRequest;
import cft.intensive.potato.api.dto.transfer.TransferCreateResponse;
import cft.intensive.potato.core.exceptions.IncorrectRequestException;
import cft.intensive.potato.core.exceptions.transfer.NotEnoughtMoneyException;
import cft.intensive.potato.core.exceptions.transfer.NotFoundException;
import cft.intensive.potato.core.repository.TransferRepository;
import cft.intensive.potato.core.repository.UsersRepository;
import cft.intensive.potato.core.repository.WalletRepository;
import cft.intensive.potato.core.services.transfer.TransferService;
import cft.intensive.potato.core.services.transfer.TransferValidation;
import cft.intensive.potato.model.Wallet;
import cft.intensive.potato.model.transfer.Transfer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransferServiceImp implements TransferService {

    private final UsersRepository usersRepository;
    private final WalletRepository walletRepository;
    private final TransferRepository transferRepository;

    private final TransferValidation transferValidation;

    @Transactional
    @Override
    public TransferCreateResponse createTransfer(TransferCreateRequest transferCreateRequest) throws NotFoundException {
        if (!transferValidation.validateTransferCreateRequest(transferCreateRequest)) {
            throw new IncorrectRequestException("incorrect parameters", 400);
        }

        Wallet senderWallet = Optional.ofNullable(walletRepository.getById(transferCreateRequest.getSenderWalletId()))
                .orElseThrow(() -> new NotFoundException("wallet not found", 404));

        if (!transferValidation.isEnoughtMoneyForTransfer(transferCreateRequest)) {
            throw new NotEnoughtMoneyException("not enought money for transfer", 405);
        }

        int receiverWalletId = 0;
        switch (transferCreateRequest.getDestination()) {
            case USER -> {
                receiverWalletId = getReceiverWalletIdWhenUserDestination(transferCreateRequest);
            }
            case SERVICE -> { /*TODO (in new life)*/ }
        }

        Transfer transfer = Transfer.builder()
                .senderWalletId(transferCreateRequest.getSenderWalletId())
                .destination(transferCreateRequest.getDestination())
                .userTransferWay(transferCreateRequest.getUserTransferWay())
                .receiverWalletId(transferCreateRequest.getSenderWalletId())
                .receiverTelephoneNumber(transferCreateRequest.getReceiverTelephoneNumber())
                .serviceId(transferCreateRequest.getServiceId())
                .amount(transferCreateRequest.getAmount())
                .status(false)
                .date(LocalDateTime.parse(LocalDateTime.now().format( DateTimeFormatter.ISO_DATE_TIME )))
                .build();

        transferRepository.addTransfer(transfer);

        changeWalletsAmount(senderWallet, receiverWalletId, transferCreateRequest.getAmount());

        transferRepository.changeTransferStatus(transfer.getId(), true);

        return TransferCreateResponse.builder()
                .transferId(transfer.getId())
                .status(transfer.getStatus())
                .date(transfer.getDate())
                .build();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    private void changeWalletsAmount(Wallet senderWallet, int receiverWalletId, int amountDiff) {
        senderWallet.subAmountFromBalance(amountDiff);
        walletRepository.addAmountOnBalance(receiverWalletId, amountDiff);
    }

    private int getReceiverWalletIdWhenUserDestination(TransferCreateRequest transferCreateRequest) {
        int receiverWalletId = 0;
        switch (transferCreateRequest.getUserTransferWay()) {
            case BY_ID -> {
                receiverWalletId = transferWayByIdHandler(transferCreateRequest);
            }
            case BY_TELEPHONE -> {
                receiverWalletId = transferWayByTelephone(transferCreateRequest);
            }
        }
        return receiverWalletId;
    }

    private int transferWayByIdHandler(TransferCreateRequest transferCreateRequest) {
        if (!walletRepository.walletIsExist(transferCreateRequest.getReceiverWalletId())) {
            throw new NotEnoughtMoneyException("not found receiver wallet", 404);
        }
        return transferCreateRequest.getReceiverWalletId();
    }

    private int transferWayByTelephone(TransferCreateRequest transferCreateRequest) {
        if (!usersRepository.userIsExist(transferCreateRequest.getReceiverTelephoneNumber())) {
            throw new NotEnoughtMoneyException("not found receiver wallet", 404);
        }
        return usersRepository.getByTelephone(transferCreateRequest.getReceiverTelephoneNumber())
                .getWallet().getId();
    }

    private void ServiceDestinationHandler(TransferCreateRequest transferCreateRequest) { /*TODO (in new life)*/ }

    @Override
    public List<Transfer> getById() {
        //TODO
        return null;
    }
}
