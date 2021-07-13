package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.ObjectStore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ObjectStore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObjectStoreRepository extends JpaRepository<ObjectStore, Long> {}
