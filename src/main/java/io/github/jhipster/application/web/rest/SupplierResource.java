package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.service.SupplierService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.SupplierDTO;
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
 * REST controller for managing Supplier.
 */
@RestController
@RequestMapping("/api")
public class SupplierResource {

    private final Logger log = LoggerFactory.getLogger(SupplierResource.class);

    private static final String ENTITY_NAME = "supplier";

    private final SupplierService supplierService;

    public SupplierResource(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    /**
     * POST  /suppliers : Create a new supplier.
     *
     * @param supplierDTO the supplierDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new supplierDTO, or with status 400 (Bad Request) if the supplier has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/suppliers")
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) throws URISyntaxException {
        log.debug("REST request to save Supplier : {}", supplierDTO);
        if (supplierDTO.getId() != null) {
            throw new BadRequestAlertException("A new supplier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SupplierDTO result = supplierService.save(supplierDTO);
        return ResponseEntity.created(new URI("/api/suppliers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /suppliers : Updates an existing supplier.
     *
     * @param supplierDTO the supplierDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated supplierDTO,
     * or with status 400 (Bad Request) if the supplierDTO is not valid,
     * or with status 500 (Internal Server Error) if the supplierDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/suppliers")
    public ResponseEntity<SupplierDTO> updateSupplier(@RequestBody SupplierDTO supplierDTO) throws URISyntaxException {
        log.debug("REST request to update Supplier : {}", supplierDTO);
        if (supplierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SupplierDTO result = supplierService.save(supplierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, supplierDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /suppliers : get all the suppliers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of suppliers in body
     */
    @GetMapping("/suppliers")
    public List<SupplierDTO> getAllSuppliers() {
        log.debug("REST request to get all Suppliers");
        return supplierService.findAll();
    }

    /**
     * GET  /suppliers/:id : get the "id" supplier.
     *
     * @param id the id of the supplierDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the supplierDTO, or with status 404 (Not Found)
     */
    @GetMapping("/suppliers/{id}")
    public ResponseEntity<SupplierDTO> getSupplier(@PathVariable Long id) {
        log.debug("REST request to get Supplier : {}", id);
        Optional<SupplierDTO> supplierDTO = supplierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(supplierDTO);
    }

    /**
     * DELETE  /suppliers/:id : delete the "id" supplier.
     *
     * @param id the id of the supplierDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        log.debug("REST request to delete Supplier : {}", id);
        supplierService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
