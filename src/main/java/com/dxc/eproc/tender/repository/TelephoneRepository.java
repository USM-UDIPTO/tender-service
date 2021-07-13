package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.Telephone;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Telephone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {}
