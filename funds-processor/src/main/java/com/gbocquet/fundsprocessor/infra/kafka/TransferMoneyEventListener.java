package com.gbocquet.fundsprocessor.infra.kafka;

import com.gbocquet.fundsprocessor.dto.request.TransferMoneyRequestDto;
import com.gbocquet.fundsprocessor.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.gbocquet.fundsprocessor.constant.KafkaConstant.TRANSFER_MONEY_TOPIC;

@Component
public class TransferMoneyEventListener {
    private static final Logger logger = LoggerFactory.getLogger(TransferMoneyEventListener.class);
    private final AccountService accountService;

    public TransferMoneyEventListener(AccountService accountService) {
        this.accountService = accountService;
    }

    @KafkaListener(topics = TRANSFER_MONEY_TOPIC, groupId = "transfer-money-group-processor", autoStartup = "${listen.auto.start:true}")
    public void listenTransferMoneyEvent(final TransferMoneyRequestDto request) {
        logger.info("#### -> Consuming message -> {}", request.toString());
        // Fixme
        accountService.transferMoneyToOtherAccount(request);
    }
}
