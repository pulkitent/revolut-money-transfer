package com.transfer.controller;

import com.transfer.dto.TransferRequest;
import com.transfer.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TransferController {

    @Autowired
    private final TransferService transferService;

    @PostMapping("/api/v1/transfer")
    public ResponseEntity<String> createTransferRequest(@RequestBody final TransferRequest transferRequest)
            throws InterruptedException {

        transferService.createTransferRequest(transferRequest);

        return ResponseEntity.ok("Request submitted successully");
    }
}
