package com.transfer.service;

import com.transfer.dto.TransferRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferService {

    @Autowired
    private final TransferExecutorService transferExecutorService;

    public void createTransferRequest(final TransferRequest transferRequest) throws InterruptedException {
        transferExecutorService.submitTransferRequest(transferRequest);
    }
}
