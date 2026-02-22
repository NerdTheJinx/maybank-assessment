package org.itsjinxed.assessment.service.in;

import org.itsjinxed.assessment.api.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CustomerService {

    Page<Customer> get(Pageable pageable);

    Customer save(Customer customer);

    Customer update(UUID id, Customer customer);
}
