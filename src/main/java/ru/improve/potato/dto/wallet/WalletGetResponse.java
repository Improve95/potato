package ru.improve.potato.dto.wallet;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
@ToString
public class WalletGetResponse {

    private final int id;

    private final int balance;

    private final int userId;
}
