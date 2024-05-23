package com.transfer.service;

import com.transfer.dto.TransferRequest;
import com.transfer.model.Account;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferExecutorService {

    private final LinkedBlockingQueue<TransferRequest> linkedBlockingQueue;

    private final AccountService accountService;

    private final ExecutorService executorService;

    @Autowired
    public TransferExecutorService(final LinkedBlockingQueue<TransferRequest> linkedBlockingQueue,
            final AccountService accountService) {

        this.linkedBlockingQueue = linkedBlockingQueue;
        this.accountService = accountService;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    synchronized void submitTransferRequest(final TransferRequest transferRequest) throws InterruptedException {

        linkedBlockingQueue.put(transferRequest);
        executorService.submit(this::processTransferRequest);
    }

    void processTransferRequest() {

        final TransferRequest transferRequest = linkedBlockingQueue.poll();

        if (!Objects.isNull(transferRequest)) {
            final Account sourceAccount = accountService.findById(transferRequest.getSourceAccountId());
            synchronized (sourceAccount.getId()) {
                final Account destinationAccount = accountService.findById(transferRequest.getDestinationAccountId());
                synchronized (destinationAccount.getId()) {
                    accountService.transferMoney(sourceAccount, destinationAccount, transferRequest.getAmount());
                }
            }
        }
    }
}
