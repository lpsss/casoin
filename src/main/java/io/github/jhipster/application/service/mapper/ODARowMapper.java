package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ODARowDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ODARow and its DTO ODARowDTO.
 */
@Mapper(componentModel = "spring", uses = {ODAHeadMapper.class, ProductMapper.class})
public interface ODARowMapper extends EntityMapper<ODARowDTO, ODARow> {

    @Mapping(source = "oDAHead.id", target = "oDAHeadId")
    @Mapping(source = "product.id", target = "productId")
    ODARowDTO toDto(ODARow oDARow);

    @Mapping(source = "oDAHeadId", target = "oDAHead")
    @Mapping(source = "productId", target = "product")
    ODARow toEntity(ODARowDTO oDARowDTO);

    default ODARow fromId(Long id) {
        if (id == null) {
            return null;
        }
        ODARow oDARow = new ODARow();
        oDARow.setId(id);
        return oDARow;
    }
}
