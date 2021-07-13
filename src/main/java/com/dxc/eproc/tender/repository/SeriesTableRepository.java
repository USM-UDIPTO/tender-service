package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.SeriesTable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SeriesTable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeriesTableRepository extends JpaRepository<SeriesTable, Long> {}
