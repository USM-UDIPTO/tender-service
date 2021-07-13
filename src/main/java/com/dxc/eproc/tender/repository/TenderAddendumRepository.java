package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderAddendum;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderAddendum entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderAddendumRepository extends JpaRepository<TenderAddendum, Long> {}
