package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ODARow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ODARow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ODARowRepository extends JpaRepository<ODARow, Long> {

}
