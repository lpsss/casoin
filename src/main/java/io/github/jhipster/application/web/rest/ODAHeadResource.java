package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.service.ODAHeadService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.ODAHeadDTO;
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

    private final ODAHeadService oDAHeadService;

    public ODAHeadResource(ODAHeadService oDAHeadService) {
        this.oDAHeadService = oDAHeadService;
    }

    /**
     * POST  /oda-heads : Create a new oDAHead.
     *
     * @param oDAHeadDTO the oDAHeadDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new oDAHeadDTO, or with status 400 (Bad Request) if the oDAHead has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/oda-heads")
    public ResponseEntity<ODAHeadDTO> createODAHead(@RequestBody ODAHeadDTO oDAHeadDTO) throws URISyntaxException {
        log.debug("REST request to save ODAHead : {}", oDAHeadDTO);
        if (oDAHeadDTO.getId() != null) {
            throw new BadRequestAlertException("A new oDAHead cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ODAHeadDTO result = oDAHeadService.save(oDAHeadDTO);
        return ResponseEntity.created(new URI("/api/oda-heads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /oda-heads : Updates an existing oDAHead.
     *
     * @param oDAHeadDTO the oDAHeadDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated oDAHeadDTO,
     * or with status 400 (Bad Request) if the oDAHeadDTO is not valid,
     * or with status 500 (Internal Server Error) if the oDAHeadDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/oda-heads")
    public ResponseEntity<ODAHeadDTO> updateODAHead(@RequestBody ODAHeadDTO oDAHeadDTO) throws URISyntaxException {
        log.debug("REST request to update ODAHead : {}", oDAHeadDTO);
        if (oDAHeadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ODAHeadDTO result = oDAHeadService.save(oDAHeadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, oDAHeadDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /oda-heads : get all the oDAHeads.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of oDAHeads in body
     */
    @GetMapping("/oda-heads")
    public List<ODAHeadDTO> getAllODAHeads() {
        log.debug("REST request to get all ODAHeads");
        return oDAHeadService.findAll();
    }

    /**
     * GET  /oda-heads/:id : get the "id" oDAHead.
     *
     * @param id the id of the oDAHeadDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the oDAHeadDTO, or with status 404 (Not Found)
     */
    @GetMapping("/oda-heads/{id}")
    public ResponseEntity<ODAHeadDTO> getODAHead(@PathVariable Long id) {
        log.debug("REST request to get ODAHead : {}", id);
        Optional<ODAHeadDTO> oDAHeadDTO = oDAHeadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(oDAHeadDTO);
    }

    /**
     * DELETE  /oda-heads/:id : delete the "id" oDAHead.
     *
     * @param id the id of the oDAHeadDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/oda-heads/{id}")
    public ResponseEntity<Void> deleteODAHead(@PathVariable Long id) {
        log.debug("REST request to delete ODAHead : {}", id);
        oDAHeadService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
