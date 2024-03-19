package com.gbocquet.webgateway.service;

import com.gbocquet.webgateway.dto.TransferMoneyDtoRequest;

public interface BalanceActionService {
    void transferMoney(final TransferMoneyDtoRequest request);
}
