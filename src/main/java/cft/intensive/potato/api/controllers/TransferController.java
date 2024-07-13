package cft.intensive.potato.api.controllers;

import cft.intensive.potato.api.dto.transfer.TransferCreateRequest;
import cft.intensive.potato.api.dto.transfer.TransferCreateResponse;
import cft.intensive.potato.api.dto.transfer.TransferGetResponse;
import cft.intensive.potato.core.services.transfer.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(path = "/{id}")
    public List<TransferGetResponse> getAllTransfersByUserId(int id) {
        log.info("request arrived: get transfers by user id: {}", id);
        return transferService.getAllByUserId(id);
    }
}
