package org.itsjinxed.assessment.service.in;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.itsjinxed.assessment.api.model.Customer;
import org.itsjinxed.assessment.persistence.repo.CustomerRepository;
import org.itsjinxed.assessment.util.ValidationUtil;
import org.itsjinxed.assessment.util.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Value("${models.customer.sortable}")
    private List<String> sortable;

    private final CustomerRepository customerRepository;
    private final Validator validator;

    @Override
    public Page<Customer> get(Pageable pageable) {
        ValidationUtil.validateSort(pageable, sortable);

        var pagedCustomerEntities = customerRepository.findAll(pageable);
        return pagedCustomerEntities.map(CustomerMapper::map);
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        var violations = validator.validate(customer);
        ValidationUtil.validateConstraint(violations);

        var customerEntity = CustomerMapper.map(customer);
        customerEntity = customerRepository.save(customerEntity);
        return CustomerMapper.map(customerEntity);
    }

    @Transactional
    @Override
    public Customer update(UUID id, Customer customer) {
        var violations = validator.validate(customer);
        ValidationUtil.validateConstraint(violations);

        var customerEntity = customerRepository.findById(id);
        var savedCustomer = customerEntity.orElseThrow(() -> new EntityNotFoundException("The customer does not exist."));
        savedCustomer.setName(customer.name());
        savedCustomer.setEmail(customer.email());
        savedCustomer.setMobile(customer.mobile());
        return CustomerMapper.map(customerRepository.save(savedCustomer));
    }
}
