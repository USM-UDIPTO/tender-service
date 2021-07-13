package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderSchedule;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderSchedule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderScheduleRepository extends JpaRepository<TenderSchedule, Long> {}
