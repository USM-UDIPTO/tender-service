package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderScheduleGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderScheduleGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderScheduleGroupRepository extends JpaRepository<TenderScheduleGroup, Long> {}
