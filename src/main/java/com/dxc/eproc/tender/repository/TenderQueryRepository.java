package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderQuery;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderQuery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderQueryRepository extends JpaRepository<TenderQuery, Long> {}
