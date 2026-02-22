package org.itsjinxed.assessment.api.controller;

import lombok.RequiredArgsConstructor;
import org.itsjinxed.assessment.api.model.Customer;
import org.itsjinxed.assessment.service.in.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<Customer>> get(Pageable pageable) {
        var pagedCustomers = customerService.get(pageable);
        return ResponseEntity.ok(pagedCustomers);
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        var savedCustomer = customerService.save(customer);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustomer.id())
                .toUri();
        return ResponseEntity.created(location).body(savedCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable UUID id, @RequestBody Customer customer) {
        customer = customerService.update(id, customer);
        return ResponseEntity.ok(customer);
    }
}
