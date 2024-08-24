package ru.improve.potato.services.wallet;

import ru.improve.potato.dto.wallet.WalletGetResponse;

public interface WalletService {

    WalletGetResponse getById(int id);
}
