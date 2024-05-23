package com.transfer.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransferRequest {

    private String id;
    private String sourceAccountId;
    private String destinationAccountId;
    private BigDecimal amount;

}
