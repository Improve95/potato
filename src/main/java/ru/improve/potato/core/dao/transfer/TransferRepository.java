package ru.improve.potato.core.dao.transfer;

import ru.improve.potato.model.transfer.Transfer;

import java.util.List;

public interface TransferRepository {

    boolean transactionIsExist(int id);

    Transfer getById(int id);

    int addTransfer(Transfer transfer);

    void changeTransferStatus(int id, boolean status);

    List<Transfer> getAllTransfersByWalletId(int id);
}
