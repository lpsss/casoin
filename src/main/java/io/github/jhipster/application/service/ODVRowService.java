package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ODVRow;
import io.github.jhipster.application.repository.ODVRowRepository;
import io.github.jhipster.application.service.dto.ODVRowDTO;
import io.github.jhipster.application.service.mapper.ODVRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ODVRow.
 */
@Service
@Transactional
public class ODVRowService {

    private final Logger log = LoggerFactory.getLogger(ODVRowService.class);

    private final ODVRowRepository oDVRowRepository;

    private final ODVRowMapper oDVRowMapper;

    public ODVRowService(ODVRowRepository oDVRowRepository, ODVRowMapper oDVRowMapper) {
        this.oDVRowRepository = oDVRowRepository;
        this.oDVRowMapper = oDVRowMapper;
    }

    /**
     * Save a oDVRow.
     *
     * @param oDVRowDTO the entity to save
     * @return the persisted entity
     */
    public ODVRowDTO save(ODVRowDTO oDVRowDTO) {
        log.debug("Request to save ODVRow : {}", oDVRowDTO);
        ODVRow oDVRow = oDVRowMapper.toEntity(oDVRowDTO);
        oDVRow = oDVRowRepository.save(oDVRow);
        return oDVRowMapper.toDto(oDVRow);
    }

    /**
     * Get all the oDVRows.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ODVRowDTO> findAll() {
        log.debug("Request to get all ODVRows");
        return oDVRowRepository.findAll().stream()
            .map(oDVRowMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one oDVRow by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ODVRowDTO> findOne(Long id) {
        log.debug("Request to get ODVRow : {}", id);
        return oDVRowRepository.findById(id)
            .map(oDVRowMapper::toDto);
    }

    /**
     * Delete the oDVRow by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ODVRow : {}", id);
        oDVRowRepository.deleteById(id);
    }
}
