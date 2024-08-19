package ru.improve.potato.api.controllers;

import ru.improve.potato.api.dto.transfer.TransferPostRequest;
import ru.improve.potato.api.dto.transfer.TransferPostResponse;
import ru.improve.potato.api.dto.transfer.TransferGetResponse;
import ru.improve.potato.core.services.transfer.TransferService;
import ru.improve.potato.model.transfer.TransferType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/transfer")
@RequiredArgsConstructor
@Slf4j
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public TransferPostResponse createTransfer(@RequestBody TransferPostRequest transferPostRequest) {
        log.info("request arrived: " + transferPostRequest.toString());
        return transferService.createTransfer(transferPostRequest);
    }

    @GetMapping(path = "/{transferId}/wallet/{walletId}")
    public TransferGetResponse getTransferById(@PathVariable int transferId, @PathVariable int walletId) {
        log.info("request arrived: get transfer by id: {}", transferId);
        return transferService.getTransferById(transferId, walletId);
    }

    @GetMapping(path = "/wallet/{walletId}")
    public List<TransferGetResponse> getAllTransfersByWalletId(@PathVariable int walletId) {
        log.info("request arrived: get transfers by wallet id: {}", walletId);
        return transferService.getAllTransfersByWalletId(walletId);
    }

    @GetMapping(path = "/wallet/{walletId}/parameters")
    public List<TransferGetResponse> getAllTransfersByWalletIdAndParameters(@PathVariable int walletId,
                                                                            @RequestParam(required = false) TransferType transferType,
                                                                            @RequestParam(required = false) Boolean transferStatus) {
        log.info("request arrived: get transfers by wallet id {} and parameters {}, {}", walletId, transferType, transferStatus);
        return transferService.getAllTransfersByWalletIdAndParameters(walletId, transferType, transferStatus);
    }
}
