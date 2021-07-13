package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderCorrigendum;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderCorrigendum entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderCorrigendumRepository extends JpaRepository<TenderCorrigendum, Long> {}
