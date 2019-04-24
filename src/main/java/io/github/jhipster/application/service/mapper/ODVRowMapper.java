package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ODVRowDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ODVRow and its DTO ODVRowDTO.
 */
@Mapper(componentModel = "spring", uses = {ODVHeadMapper.class, ProductMapper.class})
public interface ODVRowMapper extends EntityMapper<ODVRowDTO, ODVRow> {

    @Mapping(source = "oDVHead.id", target = "oDVHeadId")
    @Mapping(source = "product.id", target = "productId")
    ODVRowDTO toDto(ODVRow oDVRow);

    @Mapping(source = "oDVHeadId", target = "oDVHead")
    @Mapping(source = "productId", target = "product")
    ODVRow toEntity(ODVRowDTO oDVRowDTO);

    default ODVRow fromId(Long id) {
        if (id == null) {
            return null;
        }
        ODVRow oDVRow = new ODVRow();
        oDVRow.setId(id);
        return oDVRow;
    }
}
