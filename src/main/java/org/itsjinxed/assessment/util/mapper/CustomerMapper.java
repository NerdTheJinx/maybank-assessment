package org.itsjinxed.assessment.util.mapper;

import org.itsjinxed.assessment.api.model.Customer;
import org.itsjinxed.assessment.persistence.model.CustomerEntity;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static Customer map(CustomerEntity customerEntity) {
        return new Customer(
                customerEntity.getId(),
                customerEntity.getName(),
                customerEntity.getEmail(),
                customerEntity.getMobile()
        );
    }

    public static CustomerEntity map(Customer customer) {
        var entity = new CustomerEntity();
        entity.setId(customer.id());
        entity.setName(customer.name());
        entity.setEmail(customer.email());
        entity.setMobile(customer.mobile());

        return entity;
    }
}
