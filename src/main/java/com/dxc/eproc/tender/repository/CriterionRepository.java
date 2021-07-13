package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.Criterion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Criterion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CriterionRepository extends JpaRepository<Criterion, Long> {}
