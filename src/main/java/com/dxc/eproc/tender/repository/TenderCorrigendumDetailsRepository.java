package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderCorrigendumDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderCorrigendumDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderCorrigendumDetailsRepository extends JpaRepository<TenderCorrigendumDetails, Long> {}
