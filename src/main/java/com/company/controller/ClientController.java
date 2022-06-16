package com.company.controller;


import com.company.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/client")
@RequiredArgsConstructor
@Api(tags = "client")
public class ClientController {


    private final ClientService clientService;


/*
    @ApiOperation(value = "create", notes = "Method used for create info")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ClientRequestDTO dto) {
        log.info("Client create: {}", dto);
        return ResponseEntity.ok(clientService.create(dto));
    }


    @ApiOperation(value = "update", notes = "Method used for update info")
    @PutMapping("/update{id}")
    public ResponseEntity<?> update(@RequestBody @Valid ClientRequestDTO dto,
                                    @PathVariable("id") String id) {
        log.info("Client update: {}", dto);
        return ResponseEntity.ok(clientService.update(id, dto));
    }


    @ApiOperation(value = "changeStatus", notes = "Method used for changeStatus info")
    @PutMapping("/changeStatus{id}")
    public ResponseEntity<?> changeStatus(@RequestBody ChangeStatusDTO dto,
                                          @PathVariable("id") String id) {
        return ResponseEntity.ok(clientService.changeStatus(id, dto));
    }


    @ApiOperation(value = "changePhone", notes = "Method used for changePhone info")
    @PutMapping("/changePhone{id}")
    public ResponseEntity<?> changePhone(@RequestBody @Valid ChangePhoneDTO dto,
                                         @PathVariable("id") String id) {
        log.info("Client changePhone: {}", dto);
        return ResponseEntity.ok(clientService.changePhone(id, dto));
    }
*/

    @ApiOperation(value = "List", notes = "Method used for get list of profiles")
    @GetMapping("/pagination")
    public ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(clientService.paginationlist(page, size));
    }


    @ApiOperation(value = "getById", notes = "Method for get clients by id", nickname = "Mazgi")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(clientService.getDtoById(id));
    }


    @ApiOperation(value = "delete", notes = "Method for delete client", nickname = "Dev")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(clientService.delete(id));
    }
}