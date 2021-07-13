package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderScheduleSample;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderScheduleSample entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderScheduleSampleRepository extends JpaRepository<TenderScheduleSample, Long> {}
