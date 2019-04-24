package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Product and its DTO ProductDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {


    @Mapping(target = "oDVRows", ignore = true)
    @Mapping(target = "oDARows", ignore = true)
    Product toEntity(ProductDTO productDTO);

    default Product fromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }
}
