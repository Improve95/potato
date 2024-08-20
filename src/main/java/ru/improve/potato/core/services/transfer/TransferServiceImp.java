package ru.improve.potato.core.services.transfer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.improve.potato.api.dto.transfer.TransferGetResponse;
import ru.improve.potato.api.dto.transfer.TransferPostRequest;
import ru.improve.potato.api.dto.transfer.TransferPostResponse;
import ru.improve.potato.core.dao.transfer.TransferRepository;
import ru.improve.potato.core.dao.wallet.WalletRepository;
import ru.improve.potato.core.exceptions.NotFoundException;
import ru.improve.potato.core.validators.transfer.TransferValidation;
import ru.improve.potato.model.user.User;
import ru.improve.potato.model.Wallet;
import ru.improve.potato.model.transfer.Transfer;
import ru.improve.potato.model.transfer.TransferType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransferServiceImp implements TransferService {

//    private final UsersDAO usersDAO;
    private final WalletRepository walletRepository;
    private final TransferRepository transferRepository;

    private final TransferValidation transferValidation;

    @Transactional
    @Override
    public TransferPostResponse createTransfer(TransferPostRequest transferPostRequest) {
        Wallet senderWallet = Optional.ofNullable(walletRepository.getById(transferPostRequest.getSenderWalletId()))
                .orElseThrow(() -> new NotFoundException("wallet not found", List.of("id")));

       /* if (!transferValidation.isEnoughtMoneyForTransfer(transferPostRequest)) {
            throw new NotEnoughtMoneyException("not enought money for transfer");
        }*/

        int receiverWalletId = 0;
        switch (transferPostRequest.getDestination()) {
            case USER -> {
                receiverWalletId = getReceiverWalletIdWhenUserIsDestination(transferPostRequest);
            }
            case SERVICE -> { /*TODO
            по заданию непонятно, зачем отправлять за услугу, ведь для этого есть выставление счета,
            а непосредственно выставление услуг не было)*/ }
        }

        Transfer transfer = Transfer.builder()
                .senderWalletId(transferPostRequest.getSenderWalletId())
                .destination(transferPostRequest.getDestination())
                .userTransferWay(transferPostRequest.getUserTransferWay())
                .receiverWalletId(receiverWalletId)
                .serviceId(transferPostRequest.getServiceId())
                .amount(transferPostRequest.getAmount())
                .status(false)
                .date(LocalDateTime.parse(LocalDateTime.now().format( DateTimeFormatter.ISO_DATE_TIME )))
                .build();

        transferRepository.addTransfer(transfer);

        changeWalletsAmount(senderWallet, receiverWalletId, transferPostRequest.getAmount());

        transferRepository.changeTransferStatus(transfer.getId(), true);

        return TransferPostResponse.builder()
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

    private int getReceiverWalletIdWhenUserIsDestination(TransferPostRequest transferPostRequest) {
        User receiverUser;
       /* if (transferPostRequest.getUserTransferWay() == UserTransferWay.BY_TELEPHONE) {
            receiverUser = Optional.ofNullable(usersDAO.getByTelephone(transferPostRequest.getReceiverPhone()))
                    .orElseThrow(() -> new NotFoundException("user not found", List.of("telephoneNumber")));
        } else {
            receiverUser = Optional.ofNullable(usersDAO.getById(transferPostRequest.getReceiverWalletId()))
                    .orElseThrow(() -> new NotFoundException("user not found", List.of("walletId")));
        }
        return receiverUser.getWallet().getId();*/
        return 0;
    }

    @Override
    public TransferGetResponse getTransferById(int transferId, int walletId) {
        Transfer transfer = Optional.ofNullable(transferRepository.getById(transferId))
                .orElseThrow(() -> new NotFoundException("transfer not found", List.of("id")));

        /*if (transfer.getSenderWalletId() != walletId && transfer.getReceiverWalletId() != walletId) {
            throw new IncorrectRequestException("incorrect wallet id");
        }*/

//        TransferGetResponseMapper transferGetResponseMapper = new TransferGetResponseMapper(usersDAO);
//        return transferGetResponseMapper.mapTransferGetResponse(transfer, walletId);
        return null;
    }

    @Override
    public List<TransferGetResponse> getAllTransfersByWalletId(int walletId) {
        List<Transfer> transferList = transferRepository.getAllTransfersByWalletId(walletId);
        /*TransferGetResponseMapper transferGetResponseMapper = new TransferGetResponseMapper(usersDAO);
        return transferList.stream()
                .map(transfer -> transferGetResponseMapper.mapTransferGetResponse(transfer, walletId))
                .collect(Collectors.toList());*/
        return null;
    }

    @Override
    public List<TransferGetResponse> getAllTransfersByWalletIdAndParameters(int walletId,
                                                                            TransferType transferType,
                                                                            Boolean transferStatus) {
        if (transferType == null && transferStatus == null) {
            return getAllTransfersByWalletId(walletId);
        }

        List<Transfer> transferList = transferRepository.getAllTransfersByWalletId(walletId);
//        TransferGetResponseMapper transferGetResponseMapper = new TransferGetResponseMapper(usersDAO);

        /*вот эту грязь неплохо было бы переделать на function и красивые конструкции*/
        /*List<TransferGetResponse> transferGetResponseList;
        if (transferType == null) {
            log.info(String.format("%b", transferStatus.booleanValue()));
            transferGetResponseList = transferList.stream()
                    .filter(transfer -> transfer.getStatus() == transferStatus.booleanValue())
                    .map(transfer -> transferGetResponseMapper.mapTransferGetResponse(transfer, walletId))
                    .collect(Collectors.toList());
        } else if (transferStatus == null) {
            transferGetResponseList = transferList.stream()
                    .map(transfer -> transferGetResponseMapper.mapTransferGetResponse(transfer, walletId))
                    .filter(transferGetResponse -> transferGetResponse.getTransferType() == transferType)
                    .collect(Collectors.toList());
        } else {
            transferGetResponseList = transferList.stream()
                    .map(transfer -> transferGetResponseMapper.mapTransferGetResponse(transfer, walletId))
                    .filter(transferGetResponse -> transferGetResponse.getTransferType() == transferType &&
                                transferGetResponse.getStatus() == transferStatus.booleanValue()
                    ).collect(Collectors.toList());
        }
        return transferGetResponseList;*/
        return null;
    }
}
