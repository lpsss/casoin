package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ODVRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ODVRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ODVRowRepository extends JpaRepository<ODVRow, Long> {

}
