package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ODVHead;
import io.github.jhipster.application.repository.ODVHeadRepository;
import io.github.jhipster.application.service.dto.ODVHeadDTO;
import io.github.jhipster.application.service.mapper.ODVHeadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ODVHead.
 */
@Service
@Transactional
public class ODVHeadService {

    private final Logger log = LoggerFactory.getLogger(ODVHeadService.class);

    private final ODVHeadRepository oDVHeadRepository;

    private final ODVHeadMapper oDVHeadMapper;

    public ODVHeadService(ODVHeadRepository oDVHeadRepository, ODVHeadMapper oDVHeadMapper) {
        this.oDVHeadRepository = oDVHeadRepository;
        this.oDVHeadMapper = oDVHeadMapper;
    }

    /**
     * Save a oDVHead.
     *
     * @param oDVHeadDTO the entity to save
     * @return the persisted entity
     */
    public ODVHeadDTO save(ODVHeadDTO oDVHeadDTO) {
        log.debug("Request to save ODVHead : {}", oDVHeadDTO);
        ODVHead oDVHead = oDVHeadMapper.toEntity(oDVHeadDTO);
        oDVHead = oDVHeadRepository.save(oDVHead);
        return oDVHeadMapper.toDto(oDVHead);
    }

    /**
     * Get all the oDVHeads.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ODVHeadDTO> findAll() {
        log.debug("Request to get all ODVHeads");
        return oDVHeadRepository.findAll().stream()
            .map(oDVHeadMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one oDVHead by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ODVHeadDTO> findOne(Long id) {
        log.debug("Request to get ODVHead : {}", id);
        return oDVHeadRepository.findById(id)
            .map(oDVHeadMapper::toDto);
    }

    /**
     * Delete the oDVHead by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ODVHead : {}", id);
        oDVHeadRepository.deleteById(id);
    }
}
