package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderScrutinyMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderScrutinyMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderScrutinyMasterRepository extends JpaRepository<TenderScrutinyMaster, Long> {}
