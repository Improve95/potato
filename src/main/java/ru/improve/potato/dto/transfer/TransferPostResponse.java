package ru.improve.potato.dto.transfer;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Builder
@Getter
@Jacksonized
@ToString
public class TransferPostResponse {

    private final int transferId;

    private final boolean status;

    private final LocalDateTime date;
}
