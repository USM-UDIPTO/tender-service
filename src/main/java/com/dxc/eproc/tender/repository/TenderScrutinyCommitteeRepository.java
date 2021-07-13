package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderScrutinyCommittee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderScrutinyCommittee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderScrutinyCommitteeRepository extends JpaRepository<TenderScrutinyCommittee, Long> {}
