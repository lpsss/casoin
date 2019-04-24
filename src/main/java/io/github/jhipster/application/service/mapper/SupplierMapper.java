package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.SupplierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Supplier and its DTO SupplierDTO.
 */
@Mapper(componentModel = "spring", uses = {ODVHeadMapper.class})
public interface SupplierMapper extends EntityMapper<SupplierDTO, Supplier> {

    @Mapping(source = "oDVHead.id", target = "oDVHeadId")
    SupplierDTO toDto(Supplier supplier);

    @Mapping(source = "oDVHeadId", target = "oDVHead")
    Supplier toEntity(SupplierDTO supplierDTO);

    default Supplier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Supplier supplier = new Supplier();
        supplier.setId(id);
        return supplier;
    }
}
