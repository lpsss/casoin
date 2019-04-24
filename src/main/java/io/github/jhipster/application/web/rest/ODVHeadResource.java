package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.ODVHead;
import io.github.jhipster.application.repository.ODVHeadRepository;
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
 * REST controller for managing ODVHead.
 */
@RestController
@RequestMapping("/api")
public class ODVHeadResource {

    private final Logger log = LoggerFactory.getLogger(ODVHeadResource.class);

    private static final String ENTITY_NAME = "oDVHead";

    private final ODVHeadRepository oDVHeadRepository;

    public ODVHeadResource(ODVHeadRepository oDVHeadRepository) {
        this.oDVHeadRepository = oDVHeadRepository;
    }

    /**
     * POST  /odv-heads : Create a new oDVHead.
     *
     * @param oDVHead the oDVHead to create
     * @return the ResponseEntity with status 201 (Created) and with body the new oDVHead, or with status 400 (Bad Request) if the oDVHead has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/odv-heads")
    public ResponseEntity<ODVHead> createODVHead(@RequestBody ODVHead oDVHead) throws URISyntaxException {
        log.debug("REST request to save ODVHead : {}", oDVHead);
        if (oDVHead.getId() != null) {
            throw new BadRequestAlertException("A new oDVHead cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ODVHead result = oDVHeadRepository.save(oDVHead);
        return ResponseEntity.created(new URI("/api/odv-heads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /odv-heads : Updates an existing oDVHead.
     *
     * @param oDVHead the oDVHead to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated oDVHead,
     * or with status 400 (Bad Request) if the oDVHead is not valid,
     * or with status 500 (Internal Server Error) if the oDVHead couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/odv-heads")
    public ResponseEntity<ODVHead> updateODVHead(@RequestBody ODVHead oDVHead) throws URISyntaxException {
        log.debug("REST request to update ODVHead : {}", oDVHead);
        if (oDVHead.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ODVHead result = oDVHeadRepository.save(oDVHead);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, oDVHead.getId().toString()))
            .body(result);
    }

    /**
     * GET  /odv-heads : get all the oDVHeads.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of oDVHeads in body
     */
    @GetMapping("/odv-heads")
    public List<ODVHead> getAllODVHeads() {
        log.debug("REST request to get all ODVHeads");
        return oDVHeadRepository.findAll();
    }

    /**
     * GET  /odv-heads/:id : get the "id" oDVHead.
     *
     * @param id the id of the oDVHead to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the oDVHead, or with status 404 (Not Found)
     */
    @GetMapping("/odv-heads/{id}")
    public ResponseEntity<ODVHead> getODVHead(@PathVariable Long id) {
        log.debug("REST request to get ODVHead : {}", id);
        Optional<ODVHead> oDVHead = oDVHeadRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(oDVHead);
    }

    /**
     * DELETE  /odv-heads/:id : delete the "id" oDVHead.
     *
     * @param id the id of the oDVHead to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/odv-heads/{id}")
    public ResponseEntity<Void> deleteODVHead(@PathVariable Long id) {
        log.debug("REST request to delete ODVHead : {}", id);
        oDVHeadRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
