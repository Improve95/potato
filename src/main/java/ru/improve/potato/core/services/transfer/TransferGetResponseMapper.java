package ru.improve.potato.core.services.transfer;

import ru.improve.potato.api.dto.transfer.TransferGetResponse;
import ru.improve.potato.model.transfer.Transfer;
import ru.improve.potato.model.transfer.TransferType;

import java.util.HashMap;
import java.util.Map;

class TransferGetResponseMapper {

    private Map<Integer, Integer> walletIdAndUserId = new HashMap<>();
//    private UsersDAO usersDAO;

    /*TransferGetResponseMapper(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }*/

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
//            secondSideUserId = usersDAO.getByWalletId(secondSideWalletId).getId();
        }

        return TransferGetResponse.builder()
                .id(transfer.getId())
                .transferType(transferType)
//                .secondSideUserId(secondSideUserId)
                .secondSideWalletId(secondSideWalletId)
                .amount(transfer.getAmount())
                .status(transfer.getStatus())
                .date(transfer.getDate())
                .build();
    }
}
