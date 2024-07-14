package cft.intensive.potato.api.controllers;

import cft.intensive.potato.api.dto.transfer.TransferCreateRequest;
import cft.intensive.potato.api.dto.transfer.TransferCreateResponse;
import cft.intensive.potato.api.dto.transfer.TransferGetResponse;
import cft.intensive.potato.core.services.transfer.TransferService;
import cft.intensive.potato.model.transfer.TransferType;
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
@Slf4j
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public TransferCreateResponse createTransfer(@RequestBody TransferCreateRequest transferCreateRequest) {
        log.info("request arrived: " + transferCreateRequest.toString());
        return transferService.createTransfer(transferCreateRequest);
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
