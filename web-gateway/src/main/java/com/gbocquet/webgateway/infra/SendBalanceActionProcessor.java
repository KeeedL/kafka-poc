package com.gbocquet.webgateway.infra;

import com.gbocquet.webgateway.dto.TransferMoneyDtoRequest;

public interface SendBalanceActionProcessor {
    void sendMessage(TransferMoneyDtoRequest request);
}
