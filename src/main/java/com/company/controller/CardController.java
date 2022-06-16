package com.company.controller;

import com.company.dto.responce.CardResponseDTO;
import com.company.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/card")
@Api(tags = "card")
public class CardController {


    private final CardService cardService;


    @ApiOperation(value = "create", notes = "Method used for create info")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CardResponseDTO dto) {
        log.info("Create {}", dto);
        return ResponseEntity.ok(cardService.create(dto));
    }
}