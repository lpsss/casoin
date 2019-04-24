package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.service.ODARowService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.ODARowDTO;
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

    private final ODARowService oDARowService;

    public ODARowResource(ODARowService oDARowService) {
        this.oDARowService = oDARowService;
    }

    /**
     * POST  /oda-rows : Create a new oDARow.
     *
     * @param oDARowDTO the oDARowDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new oDARowDTO, or with status 400 (Bad Request) if the oDARow has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/oda-rows")
    public ResponseEntity<ODARowDTO> createODARow(@RequestBody ODARowDTO oDARowDTO) throws URISyntaxException {
        log.debug("REST request to save ODARow : {}", oDARowDTO);
        if (oDARowDTO.getId() != null) {
            throw new BadRequestAlertException("A new oDARow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ODARowDTO result = oDARowService.save(oDARowDTO);
        return ResponseEntity.created(new URI("/api/oda-rows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /oda-rows : Updates an existing oDARow.
     *
     * @param oDARowDTO the oDARowDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated oDARowDTO,
     * or with status 400 (Bad Request) if the oDARowDTO is not valid,
     * or with status 500 (Internal Server Error) if the oDARowDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/oda-rows")
    public ResponseEntity<ODARowDTO> updateODARow(@RequestBody ODARowDTO oDARowDTO) throws URISyntaxException {
        log.debug("REST request to update ODARow : {}", oDARowDTO);
        if (oDARowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ODARowDTO result = oDARowService.save(oDARowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, oDARowDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /oda-rows : get all the oDARows.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of oDARows in body
     */
    @GetMapping("/oda-rows")
    public List<ODARowDTO> getAllODARows() {
        log.debug("REST request to get all ODARows");
        return oDARowService.findAll();
    }

    /**
     * GET  /oda-rows/:id : get the "id" oDARow.
     *
     * @param id the id of the oDARowDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the oDARowDTO, or with status 404 (Not Found)
     */
    @GetMapping("/oda-rows/{id}")
    public ResponseEntity<ODARowDTO> getODARow(@PathVariable Long id) {
        log.debug("REST request to get ODARow : {}", id);
        Optional<ODARowDTO> oDARowDTO = oDARowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(oDARowDTO);
    }

    /**
     * DELETE  /oda-rows/:id : delete the "id" oDARow.
     *
     * @param id the id of the oDARowDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/oda-rows/{id}")
    public ResponseEntity<Void> deleteODARow(@PathVariable Long id) {
        log.debug("REST request to delete ODARow : {}", id);
        oDARowService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
