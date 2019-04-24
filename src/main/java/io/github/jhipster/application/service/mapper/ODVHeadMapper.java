package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ODVHeadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ODVHead and its DTO ODVHeadDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ODVHeadMapper extends EntityMapper<ODVHeadDTO, ODVHead> {


    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "suppliers", ignore = true)
    ODVHead toEntity(ODVHeadDTO oDVHeadDTO);

    default ODVHead fromId(Long id) {
        if (id == null) {
            return null;
        }
        ODVHead oDVHead = new ODVHead();
        oDVHead.setId(id);
        return oDVHead;
    }
}
