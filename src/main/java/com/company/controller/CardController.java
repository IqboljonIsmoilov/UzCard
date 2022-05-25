package com.company.controller;

import com.company.service.CardService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@Api(tags = "client")
public class CardController {

    @Autowired
    private CardService cardService;

    private Logger log = LoggerFactory.getLogger(ClientController.class);
}
