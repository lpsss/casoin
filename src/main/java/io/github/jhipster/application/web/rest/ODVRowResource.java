package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.service.ODVRowService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.ODVRowDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ODVRow.
 */
@RestController
@RequestMapping("/api")
public class ODVRowResource {

    private final Logger log = LoggerFactory.getLogger(ODVRowResource.class);

    private static final String ENTITY_NAME = "oDVRow";

    private final ODVRowService oDVRowService;

    public ODVRowResource(ODVRowService oDVRowService) {
        this.oDVRowService = oDVRowService;
    }

    /**
     * POST  /odv-rows : Create a new oDVRow.
     *
     * @param oDVRowDTO the oDVRowDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new oDVRowDTO, or with status 400 (Bad Request) if the oDVRow has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/odv-rows")
    public ResponseEntity<ODVRowDTO> createODVRow(@RequestBody ODVRowDTO oDVRowDTO) throws URISyntaxException {
        log.debug("REST request to save ODVRow : {}", oDVRowDTO);
        if (oDVRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new oDVRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ODVRowDTO result = oDVRowService.save(oDVRowDTO);
        return ResponseEntity.created(new URI("/api/odv-rows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /odv-rows : Updates an existing oDVRow.
     *
     * @param oDVRowDTO the oDVRowDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated oDVRowDTO,
     * or with status 400 (Bad Request) if the oDVRowDTO is not valid,
     * or with status 500 (Internal Server Error) if the oDVRowDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/odv-rows")
    public ResponseEntity<ODVRowDTO> updateODVRow(@RequestBody ODVRowDTO oDVRowDTO) throws URISyntaxException {
        log.debug("REST request to update ODVRow : {}", oDVRowDTO);
        if (oDVRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ODVRowDTO result = oDVRowService.save(oDVRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, oDVRowDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /odv-rows : get all the oDVRows.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of oDVRows in body
     */
    @GetMapping("/odv-rows")
    public List<ODVRowDTO> getAllODVRows() {
        log.debug("REST request to get all ODVRows");
        return oDVRowService.findAll();
    }

    /**
     * GET  /odv-rows/:id : get the "id" oDVRow.
     *
     * @param id the id of the oDVRowDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the oDVRowDTO, or with status 404 (Not Found)
     */
    @GetMapping("/odv-rows/{id}")
    public ResponseEntity<ODVRowDTO> getODVRow(@PathVariable Long id) {
        log.debug("REST request to get ODVRow : {}", id);
        Optional<ODVRowDTO> oDVRowDTO = oDVRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(oDVRowDTO);
    }

    /**
     * DELETE  /odv-rows/:id : delete the "id" oDVRow.
     *
     * @param id the id of the oDVRowDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/odv-rows/{id}")
    public ResponseEntity<Void> deleteODVRow(@PathVariable Long id) {
        log.debug("REST request to delete ODVRow : {}", id);
        oDVRowService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
