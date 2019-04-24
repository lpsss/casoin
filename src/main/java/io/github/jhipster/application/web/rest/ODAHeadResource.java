package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.ODAHead;
import io.github.jhipster.application.repository.ODAHeadRepository;
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
 * REST controller for managing ODAHead.
 */
@RestController
@RequestMapping("/api")
public class ODAHeadResource {

    private final Logger log = LoggerFactory.getLogger(ODAHeadResource.class);

    private static final String ENTITY_NAME = "oDAHead";

    private final ODAHeadRepository oDAHeadRepository;

    public ODAHeadResource(ODAHeadRepository oDAHeadRepository) {
        this.oDAHeadRepository = oDAHeadRepository;
    }

    /**
     * POST  /oda-heads : Create a new oDAHead.
     *
     * @param oDAHead the oDAHead to create
     * @return the ResponseEntity with status 201 (Created) and with body the new oDAHead, or with status 400 (Bad Request) if the oDAHead has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/oda-heads")
    public ResponseEntity<ODAHead> createODAHead(@RequestBody ODAHead oDAHead) throws URISyntaxException {
        log.debug("REST request to save ODAHead : {}", oDAHead);
        if (oDAHead.getId() != null) {
            throw new BadRequestAlertException("A new oDAHead cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ODAHead result = oDAHeadRepository.save(oDAHead);
        return ResponseEntity.created(new URI("/api/oda-heads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /oda-heads : Updates an existing oDAHead.
     *
     * @param oDAHead the oDAHead to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated oDAHead,
     * or with status 400 (Bad Request) if the oDAHead is not valid,
     * or with status 500 (Internal Server Error) if the oDAHead couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/oda-heads")
    public ResponseEntity<ODAHead> updateODAHead(@RequestBody ODAHead oDAHead) throws URISyntaxException {
        log.debug("REST request to update ODAHead : {}", oDAHead);
        if (oDAHead.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ODAHead result = oDAHeadRepository.save(oDAHead);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, oDAHead.getId().toString()))
            .body(result);
    }

    /**
     * GET  /oda-heads : get all the oDAHeads.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of oDAHeads in body
     */
    @GetMapping("/oda-heads")
    public List<ODAHead> getAllODAHeads() {
        log.debug("REST request to get all ODAHeads");
        return oDAHeadRepository.findAll();
    }

    /**
     * GET  /oda-heads/:id : get the "id" oDAHead.
     *
     * @param id the id of the oDAHead to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the oDAHead, or with status 404 (Not Found)
     */
    @GetMapping("/oda-heads/{id}")
    public ResponseEntity<ODAHead> getODAHead(@PathVariable Long id) {
        log.debug("REST request to get ODAHead : {}", id);
        Optional<ODAHead> oDAHead = oDAHeadRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(oDAHead);
    }

    /**
     * DELETE  /oda-heads/:id : delete the "id" oDAHead.
     *
     * @param id the id of the oDAHead to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/oda-heads/{id}")
    public ResponseEntity<Void> deleteODAHead(@PathVariable Long id) {
        log.debug("REST request to delete ODAHead : {}", id);
        oDAHeadRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
