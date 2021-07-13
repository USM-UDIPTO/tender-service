package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderCriterionParameter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderCriterionParameter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderCriterionParameterRepository extends JpaRepository<TenderCriterionParameter, Long> {}
