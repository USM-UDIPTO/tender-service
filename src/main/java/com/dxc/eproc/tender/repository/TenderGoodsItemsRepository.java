package com.dxc.eproc.tender.repository;

import com.dxc.eproc.tender.domain.TenderGoodsItems;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderGoodsItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderGoodsItemsRepository extends JpaRepository<TenderGoodsItems, Long> {}
