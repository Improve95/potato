package cft.intensive.potato.core.repository;

import cft.intensive.potato.model.transfer.Transfer;

import java.util.List;

public interface TransferRepository {

    boolean transactionIsExist(int id);

    Transfer getById(int id);

    int addTransfer(Transfer transfer);

    void changeTransferStatus(int id, boolean status);

    List<Transfer> getAllTransfersByWalletId(int id);
}
