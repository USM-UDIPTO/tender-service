package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderScheduleTelephone;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderScheduleTelephone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderScheduleTelephoneRepository extends JpaRepository<TenderScheduleTelephone, Long> {}
