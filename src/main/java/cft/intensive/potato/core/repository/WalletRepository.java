package cft.intensive.potato.core.repository;

import cft.intensive.potato.model.Wallet;

public interface WalletRepository {

    Wallet getById(int id);

    boolean walletIsExist(int id);

    int addWallet(Wallet wallet);

    void addAmountOnBalance(int id, int diffAmount);

    void subAmountFromBalance(int id, int diffAmount);
}
