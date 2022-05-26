package com.company.controller;

import com.company.dto.response.CardResponseDTO;
import com.company.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
@Api(tags = "card")
public class CardController {

    @Autowired
    private CardService cardService;

    private Logger log = LoggerFactory.getLogger(ClientController.class);

    @ApiOperation(value = "create", notes = "Method used for create info")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CardResponseDTO dto) {
        log.info("Create {}", dto);
        return ResponseEntity.ok(cardService.create(dto));
    }


}
