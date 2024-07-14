package cft.intensive.potato.core.services.transfer.implementations;

import cft.intensive.potato.api.dto.transfer.TransferCreateRequest;
import cft.intensive.potato.api.dto.transfer.TransferCreateResponse;
import cft.intensive.potato.api.dto.transfer.TransferGetResponse;
import cft.intensive.potato.core.exceptions.IncorrectRequestException;
import cft.intensive.potato.core.exceptions.transfer.NotEnoughtMoneyException;
import cft.intensive.potato.core.exceptions.transfer.NotFoundException;
import cft.intensive.potato.core.repository.TransferRepository;
import cft.intensive.potato.core.repository.UsersRepository;
import cft.intensive.potato.core.repository.WalletRepository;
import cft.intensive.potato.core.services.transfer.TransferService;
import cft.intensive.potato.core.services.transfer.TransferValidation;
import cft.intensive.potato.model.User;
import cft.intensive.potato.model.Wallet;
import cft.intensive.potato.model.transfer.Transfer;
import cft.intensive.potato.model.transfer.TransferType;
import cft.intensive.potato.model.transfer.UserTransferWay;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
            throw new IncorrectRequestException("incorrect parameters");
        }

        Wallet senderWallet = Optional.ofNullable(walletRepository.getById(transferCreateRequest.getSenderWalletId()))
                .orElseThrow(() -> new NotFoundException("wallet not found by id"));

        if (!transferValidation.isEnoughtMoneyForTransfer(transferCreateRequest)) {
            throw new NotEnoughtMoneyException("not enought money for transfer");
        }

        int receiverWalletId = 0;
        switch (transferCreateRequest.getDestination()) {
            case USER -> {
                receiverWalletId = getReceiverWalletIdWhenUserIsDestination(transferCreateRequest);
            }
            case SERVICE -> { /*TODO
            по заданию непонятно, зачем отправлять за услугу, ведь для этого есть выставление счета,
            а непосредственно выставление услуг не было)*/ }
        }

        Transfer transfer = Transfer.builder()
                .senderWalletId(transferCreateRequest.getSenderWalletId())
                .destination(transferCreateRequest.getDestination())
                .userTransferWay(transferCreateRequest.getUserTransferWay())
                .receiverWalletId(receiverWalletId)
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

    private int getReceiverWalletIdWhenUserIsDestination(TransferCreateRequest transferCreateRequest) {
        User receiverUser;
        if (transferCreateRequest.getUserTransferWay() == UserTransferWay.BY_TELEPHONE) {
            receiverUser = Optional.ofNullable(usersRepository.getByTelephone(transferCreateRequest.getReceiverPhone()))
                    .orElseThrow(() -> new NotFoundException("user not found by phone"));
        } else {
            receiverUser = Optional.ofNullable(usersRepository.getById(transferCreateRequest.getReceiverWalletId()))
                    .orElseThrow(() -> new NotFoundException("user not found by wallet id"));
        }
        return receiverUser.getWallet().getId();
    }

    @Override
    public TransferGetResponse getTransferById(int transferId, int walletId) {
        Transfer transfer = Optional.ofNullable(transferRepository.getById(transferId))
                .orElseThrow(() -> new NotFoundException("transfer not found by id"));

        if (transfer.getSenderWalletId() != walletId && transfer.getReceiverWalletId() != walletId) {
            throw new IncorrectRequestException("incorrect wallet id");
        }

        TransferGetResponseMapper transferGetResponseMapper = new TransferGetResponseMapper(usersRepository);
        return transferGetResponseMapper.mapTransferGetResponse(transfer, walletId);
    }

    @Override
    public List<TransferGetResponse> getAllByWalletId(int walletId) {
        TransferGetResponseMapper transferGetResponseMapper = new TransferGetResponseMapper(usersRepository);
        List<Transfer> transferList = transferRepository.getAllTransfersByUserId(walletId);
        return transferList.stream()
                .map(transfer -> transferGetResponseMapper.mapTransferGetResponse(transfer, walletId))
                .collect(Collectors.toList());
    }
}

class TransferGetResponseMapper {

    private Map<Integer, Integer> walletIdAndUserId = new HashMap<>();
    private UsersRepository usersRepository;

    TransferGetResponseMapper(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public TransferGetResponse mapTransferGetResponse(Transfer transfer, int walletId) {
        TransferType transferType = TransferType.SEND;
        int secondSideWalletId = transfer.getReceiverWalletId();

        if (transfer.getReceiverWalletId() == walletId) {
            transferType = TransferType.RECEIVE;
            secondSideWalletId = transfer.getSenderWalletId();
        }

        int secondSideUserId;
        try {
            secondSideUserId = walletIdAndUserId.get(secondSideWalletId);
        } catch (NullPointerException ex) {
            secondSideUserId = usersRepository.getByWalletId(secondSideWalletId).getId();
        }

        return TransferGetResponse.builder()
                .id(transfer.getId())
                .transferType(transferType)
                .secondSideUserId(secondSideUserId)
                .secondSideWalletId(secondSideWalletId)
                .amount(transfer.getAmount())
                .status(transfer.getStatus())
                .date(transfer.getDate())
                .build();
    }
}
