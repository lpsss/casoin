package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ODAHead;
import io.github.jhipster.application.repository.ODAHeadRepository;
import io.github.jhipster.application.service.dto.ODAHeadDTO;
import io.github.jhipster.application.service.mapper.ODAHeadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ODAHead.
 */
@Service
@Transactional
public class ODAHeadService {

    private final Logger log = LoggerFactory.getLogger(ODAHeadService.class);

    private final ODAHeadRepository oDAHeadRepository;

    private final ODAHeadMapper oDAHeadMapper;

    public ODAHeadService(ODAHeadRepository oDAHeadRepository, ODAHeadMapper oDAHeadMapper) {
        this.oDAHeadRepository = oDAHeadRepository;
        this.oDAHeadMapper = oDAHeadMapper;
    }

    /**
     * Save a oDAHead.
     *
     * @param oDAHeadDTO the entity to save
     * @return the persisted entity
     */
    public ODAHeadDTO save(ODAHeadDTO oDAHeadDTO) {
        log.debug("Request to save ODAHead : {}", oDAHeadDTO);
        ODAHead oDAHead = oDAHeadMapper.toEntity(oDAHeadDTO);
        oDAHead = oDAHeadRepository.save(oDAHead);
        return oDAHeadMapper.toDto(oDAHead);
    }

    /**
     * Get all the oDAHeads.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ODAHeadDTO> findAll() {
        log.debug("Request to get all ODAHeads");
        return oDAHeadRepository.findAll().stream()
            .map(oDAHeadMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one oDAHead by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ODAHeadDTO> findOne(Long id) {
        log.debug("Request to get ODAHead : {}", id);
        return oDAHeadRepository.findById(id)
            .map(oDAHeadMapper::toDto);
    }

    /**
     * Delete the oDAHead by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ODAHead : {}", id);
        oDAHeadRepository.deleteById(id);
    }
}
