package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.ODARow;
import io.github.jhipster.application.repository.ODARowRepository;
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
 * REST controller for managing ODARow.
 */
@RestController
@RequestMapping("/api")
public class ODARowResource {

    private final Logger log = LoggerFactory.getLogger(ODARowResource.class);

    private static final String ENTITY_NAME = "oDARow";

    private final ODARowRepository oDARowRepository;

    public ODARowResource(ODARowRepository oDARowRepository) {
        this.oDARowRepository = oDARowRepository;
    }

    /**
     * POST  /oda-rows : Create a new oDARow.
     *
     * @param oDARow the oDARow to create
     * @return the ResponseEntity with status 201 (Created) and with body the new oDARow, or with status 400 (Bad Request) if the oDARow has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/oda-rows")
    public ResponseEntity<ODARow> createODARow(@RequestBody ODARow oDARow) throws URISyntaxException {
        log.debug("REST request to save ODARow : {}", oDARow);
        if (oDARow.getId() != null) {
            throw new BadRequestAlertException("A new oDARow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ODARow result = oDARowRepository.save(oDARow);
        return ResponseEntity.created(new URI("/api/oda-rows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /oda-rows : Updates an existing oDARow.
     *
     * @param oDARow the oDARow to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated oDARow,
     * or with status 400 (Bad Request) if the oDARow is not valid,
     * or with status 500 (Internal Server Error) if the oDARow couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/oda-rows")
    public ResponseEntity<ODARow> updateODARow(@RequestBody ODARow oDARow) throws URISyntaxException {
        log.debug("REST request to update ODARow : {}", oDARow);
        if (oDARow.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ODARow result = oDARowRepository.save(oDARow);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, oDARow.getId().toString()))
            .body(result);
    }

    /**
     * GET  /oda-rows : get all the oDARows.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of oDARows in body
     */
    @GetMapping("/oda-rows")
    public List<ODARow> getAllODARows() {
        log.debug("REST request to get all ODARows");
        return oDARowRepository.findAll();
    }

    /**
     * GET  /oda-rows/:id : get the "id" oDARow.
     *
     * @param id the id of the oDARow to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the oDARow, or with status 404 (Not Found)
     */
    @GetMapping("/oda-rows/{id}")
    public ResponseEntity<ODARow> getODARow(@PathVariable Long id) {
        log.debug("REST request to get ODARow : {}", id);
        Optional<ODARow> oDARow = oDARowRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(oDARow);
    }

    /**
     * DELETE  /oda-rows/:id : delete the "id" oDARow.
     *
     * @param id the id of the oDARow to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/oda-rows/{id}")
    public ResponseEntity<Void> deleteODARow(@PathVariable Long id) {
        log.debug("REST request to delete ODARow : {}", id);
        oDARowRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
