package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ODAHead;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ODAHead entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ODAHeadRepository extends JpaRepository<ODAHead, Long> {

}
