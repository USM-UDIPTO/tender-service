package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderCriterionDocument;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderCriterionDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderCriterionDocumentRepository extends JpaRepository<TenderCriterionDocument, Long> {}
