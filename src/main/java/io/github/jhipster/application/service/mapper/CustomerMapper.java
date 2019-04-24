package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.CustomerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Customer and its DTO CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = {ODAHeadMapper.class})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

    @Mapping(source = "oDAHead.id", target = "oDAHeadId")
    CustomerDTO toDto(Customer customer);

    @Mapping(source = "oDAHeadId", target = "oDAHead")
    Customer toEntity(CustomerDTO customerDTO);

    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
