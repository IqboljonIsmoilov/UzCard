package com.company.controller;


import com.company.dto.response.ClientResponseDTO;
import com.company.dto.update.UpdateClientPhoneDTO;
import com.company.dto.update.UpdateClientStatusDTO;
import com.company.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@Api(tags = "client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    private Logger log = LoggerFactory.getLogger(ClientController.class);


    @ApiOperation(value = "create", notes = "Method used for create info")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ClientResponseDTO dto) {
        log.info("Create {}", dto);
        return ResponseEntity.ok(clientService.create(dto));
    }


    @ApiOperation(value = "update", notes = "Method used for update info")
    @PutMapping("/update{phone}")
    public ResponseEntity<?> update(@RequestBody ClientResponseDTO dto,
                                    @PathVariable("phone") String phone) {
        log.info("update {}", dto);
        return ResponseEntity.ok(clientService.update(dto, phone));
    }


    @ApiOperation(value = "changeStatus", notes = "Method used for changeStatus info")
    @PutMapping("/changeStatus{id}")
    public ResponseEntity<?> changeStatus(@RequestBody UpdateClientStatusDTO dto,
                                          @PathVariable("id") String id) {
        return ResponseEntity.ok(clientService.changeStatus(dto, id));
    }


    @ApiOperation(value = "changePhone", notes = "Method used for changePhone info")
    @PutMapping("/changePhone{id}")
    public ResponseEntity<?> changePhone(@RequestBody UpdateClientPhoneDTO dto,
                                         @PathVariable("id") String id) {
        return ResponseEntity.ok(clientService.changePhone(dto, id));
    }


    @ApiOperation(value = "List", notes = "Method used for get list of profiles")
    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        log.info("LIST page={} size={}", page, size);
        return ResponseEntity.ok(clientService.list(page, size));
    }




}
