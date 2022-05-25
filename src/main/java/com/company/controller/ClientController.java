package com.company.controller;


import com.company.dto.ClientDTO;
import com.company.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
@Api(tags = "client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    private Logger log = LoggerFactory.getLogger(ClientController.class);

    @ApiOperation(value = "Get", notes = "Method used for get client info")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ClientDTO dto) {
        log.info("Create {}", dto);
        return ResponseEntity.ok(clientService.create(dto));
    }


    @ApiOperation(value = "update", notes = "Method used for update info")
    @PutMapping("/update{phone}")
    public ResponseEntity<?> update(@RequestBody ClientDTO dto,
                                    @PathVariable("phone") String phone){
        return ResponseEntity.ok(clientService.update(dto, phone));
    }

}
