package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ODVHead;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ODVHead entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ODVHeadRepository extends JpaRepository<ODVHead, Long> {

}
