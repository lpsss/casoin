package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ODARow;
import io.github.jhipster.application.repository.ODARowRepository;
import io.github.jhipster.application.service.dto.ODARowDTO;
import io.github.jhipster.application.service.mapper.ODARowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ODARow.
 */
@Service
@Transactional
public class ODARowService {

    private final Logger log = LoggerFactory.getLogger(ODARowService.class);

    private final ODARowRepository oDARowRepository;

    private final ODARowMapper oDARowMapper;

    public ODARowService(ODARowRepository oDARowRepository, ODARowMapper oDARowMapper) {
        this.oDARowRepository = oDARowRepository;
        this.oDARowMapper = oDARowMapper;
    }

    /**
     * Save a oDARow.
     *
     * @param oDARowDTO the entity to save
     * @return the persisted entity
     */
    public ODARowDTO save(ODARowDTO oDARowDTO) {
        log.debug("Request to save ODARow : {}", oDARowDTO);
        ODARow oDARow = oDARowMapper.toEntity(oDARowDTO);
        oDARow = oDARowRepository.save(oDARow);
        return oDARowMapper.toDto(oDARow);
    }

    /**
     * Get all the oDARows.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ODARowDTO> findAll() {
        log.debug("Request to get all ODARows");
        return oDARowRepository.findAll().stream()
            .map(oDARowMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one oDARow by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ODARowDTO> findOne(Long id) {
        log.debug("Request to get ODARow : {}", id);
        return oDARowRepository.findById(id)
            .map(oDARowMapper::toDto);
    }

    /**
     * Delete the oDARow by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ODARow : {}", id);
        oDARowRepository.deleteById(id);
    }
}
