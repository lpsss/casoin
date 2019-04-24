package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ODAHeadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ODAHead and its DTO ODAHeadDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ODAHeadMapper extends EntityMapper<ODAHeadDTO, ODAHead> {


    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "customers", ignore = true)
    ODAHead toEntity(ODAHeadDTO oDAHeadDTO);

    default ODAHead fromId(Long id) {
        if (id == null) {
            return null;
        }
        ODAHead oDAHead = new ODAHead();
        oDAHead.setId(id);
        return oDAHead;
    }
}
