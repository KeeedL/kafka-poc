package com.gbocquet.webgateway.infra.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbocquet.webgateway.dto.TransferMoneyDtoRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static com.gbocquet.webgateway.constant.KafkaConstant.TRANSFER_MONEY_TOPIC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaProducerTest {

    @InjectMocks
    KafkaProducer kafkaProducer;

    @Mock
    KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void sendMessageShouldCallKafkaTemplateToTopic() throws JsonProcessingException {
        // GIVEN
        final var transferRequest = new TransferMoneyDtoRequest("source", "target", 12.5f);
        final var jsonRequest = new ObjectMapper().writeValueAsString(transferRequest);

        // WHEN
        kafkaProducer.sendMessage(transferRequest);

        //THEN
        final var jsonObjectCapture = ArgumentCaptor.forClass(String.class);
        verify(kafkaTemplate, times(1)).send(eq(TRANSFER_MONEY_TOPIC), jsonObjectCapture.capture());
        assertEquals(jsonRequest, jsonObjectCapture.getValue());


    }

}