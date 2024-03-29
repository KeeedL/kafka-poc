package com.gbocquet.fundsprocessor.controller;

import com.gbocquet.fundsprocessor.dto.AccountDto;
import com.gbocquet.fundsprocessor.dto.request.TransferMoneyRequestDto;
import com.gbocquet.fundsprocessor.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/v1/accounts",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("{uuid}")
    public ResponseEntity<AccountDto> getAccountByUuid(@PathVariable final String uuid) {
        return accountService.getAccountByUuid(uuid)
                .map(accountFound -> ResponseEntity.status(HttpStatus.OK).body(accountFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{uuid}/deposit")
    public ResponseEntity<AccountDto> depositMoneyOnAccountByUuid(@PathVariable final String uuid,
                                                                  @RequestBody final float amount) {
        return accountService.depositMoneyOnAccount(uuid, amount)
                .map(updatedAccount -> ResponseEntity.status(HttpStatus.OK).body(updatedAccount))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{uuid}/withdraw")
    public ResponseEntity<AccountDto> withdrawMoneyOnAccountByUuid(@PathVariable final String uuid,
                                                                   @RequestBody final float amount) {
        return accountService.withdrawMoneyOnAccount(uuid, amount)
                .map(updatedAccount -> ResponseEntity.status(HttpStatus.OK).body(updatedAccount))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("transfer")
    public ResponseEntity<AccountDto> transferMoneyOnAccountByUuid(@RequestBody final TransferMoneyRequestDto request) {
        return accountService.transferMoneyToOtherAccount(request)
                .map(updatedAccount -> ResponseEntity.status(HttpStatus.OK).body(updatedAccount))
                .orElse(ResponseEntity.notFound().build());
    }
}
