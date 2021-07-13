package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderScheduleGroupItems;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderScheduleGroupItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderScheduleGroupItemsRepository extends JpaRepository<TenderScheduleGroupItems, Long> {}
