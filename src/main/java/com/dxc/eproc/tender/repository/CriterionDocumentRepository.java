package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.CriterionDocument;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CriterionDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CriterionDocumentRepository extends JpaRepository<CriterionDocument, Long> {}
