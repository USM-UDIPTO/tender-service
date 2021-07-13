package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderQueryResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderQueryResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderQueryResponseRepository extends JpaRepository<TenderQueryResponse, Long> {}
