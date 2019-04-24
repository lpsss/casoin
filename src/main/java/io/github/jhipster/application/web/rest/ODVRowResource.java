package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.ODVRow;
import io.github.jhipster.application.repository.ODVRowRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
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

    private final ODVRowRepository oDVRowRepository;

    public ODVRowResource(ODVRowRepository oDVRowRepository) {
        this.oDVRowRepository = oDVRowRepository;
    }

    /**
     * POST  /odv-rows : Create a new oDVRow.
     *
     * @param oDVRow the oDVRow to create
     * @return the ResponseEntity with status 201 (Created) and with body the new oDVRow, or with status 400 (Bad Request) if the oDVRow has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/odv-rows")
    public ResponseEntity<ODVRow> createODVRow(@RequestBody ODVRow oDVRow) throws URISyntaxException {
        log.debug("REST request to save ODVRow : {}", oDVRow);
        if (oDVRow.getId() != null) {
            throw new BadRequestAlertException("A new oDVRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ODVRow result = oDVRowRepository.save(oDVRow);
        return ResponseEntity.created(new URI("/api/odv-rows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /odv-rows : Updates an existing oDVRow.
     *
     * @param oDVRow the oDVRow to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated oDVRow,
     * or with status 400 (Bad Request) if the oDVRow is not valid,
     * or with status 500 (Internal Server Error) if the oDVRow couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/odv-rows")
    public ResponseEntity<ODVRow> updateODVRow(@RequestBody ODVRow oDVRow) throws URISyntaxException {
        log.debug("REST request to update ODVRow : {}", oDVRow);
        if (oDVRow.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ODVRow result = oDVRowRepository.save(oDVRow);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, oDVRow.getId().toString()))
            .body(result);
    }

    /**
     * GET  /odv-rows : get all the oDVRows.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of oDVRows in body
     */
    @GetMapping("/odv-rows")
    public List<ODVRow> getAllODVRows() {
        log.debug("REST request to get all ODVRows");
        return oDVRowRepository.findAll();
    }

    /**
     * GET  /odv-rows/:id : get the "id" oDVRow.
     *
     * @param id the id of the oDVRow to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the oDVRow, or with status 404 (Not Found)
     */
    @GetMapping("/odv-rows/{id}")
    public ResponseEntity<ODVRow> getODVRow(@PathVariable Long id) {
        log.debug("REST request to get ODVRow : {}", id);
        Optional<ODVRow> oDVRow = oDVRowRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(oDVRow);
    }

    /**
     * DELETE  /odv-rows/:id : delete the "id" oDVRow.
     *
     * @param id the id of the oDVRow to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/odv-rows/{id}")
    public ResponseEntity<Void> deleteODVRow(@PathVariable Long id) {
        log.debug("REST request to delete ODVRow : {}", id);
        oDVRowRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
