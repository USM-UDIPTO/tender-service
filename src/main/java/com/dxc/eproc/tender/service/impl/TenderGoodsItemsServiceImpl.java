package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderGoodsItems;
import com.dxc.eproc.tender.repository.TenderGoodsItemsRepository;
import com.dxc.eproc.tender.service.TenderGoodsItemsService;
import com.dxc.eproc.tender.service.dto.TenderGoodsItemsDTO;
import com.dxc.eproc.tender.service.mapper.TenderGoodsItemsMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderGoodsItems}.
 */
@Service
@Transactional
public class TenderGoodsItemsServiceImpl implements TenderGoodsItemsService {

    private final Logger log = LoggerFactory.getLogger(TenderGoodsItemsServiceImpl.class);

    private final TenderGoodsItemsRepository tenderGoodsItemsRepository;

    private final TenderGoodsItemsMapper tenderGoodsItemsMapper;

    public TenderGoodsItemsServiceImpl(
        TenderGoodsItemsRepository tenderGoodsItemsRepository,
        TenderGoodsItemsMapper tenderGoodsItemsMapper
    ) {
        this.tenderGoodsItemsRepository = tenderGoodsItemsRepository;
        this.tenderGoodsItemsMapper = tenderGoodsItemsMapper;
    }

    @Override
    public TenderGoodsItemsDTO save(TenderGoodsItemsDTO tenderGoodsItemsDTO) {
        log.debug("Request to save TenderGoodsItems : {}", tenderGoodsItemsDTO);
        TenderGoodsItems tenderGoodsItems = tenderGoodsItemsMapper.toEntity(tenderGoodsItemsDTO);
        tenderGoodsItems = tenderGoodsItemsRepository.save(tenderGoodsItems);
        return tenderGoodsItemsMapper.toDto(tenderGoodsItems);
    }

    @Override
    public Optional<TenderGoodsItemsDTO> partialUpdate(TenderGoodsItemsDTO tenderGoodsItemsDTO) {
        log.debug("Request to partially update TenderGoodsItems : {}", tenderGoodsItemsDTO);

        return tenderGoodsItemsRepository
            .findById(tenderGoodsItemsDTO.getId())
            .map(
                existingTenderGoodsItems -> {
                    tenderGoodsItemsMapper.partialUpdate(existingTenderGoodsItems, tenderGoodsItemsDTO);

                    return existingTenderGoodsItems;
                }
            )
            .map(tenderGoodsItemsRepository::save)
            .map(tenderGoodsItemsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderGoodsItemsDTO> findAll() {
        log.debug("Request to get all TenderGoodsItems");
        return tenderGoodsItemsRepository
            .findAll()
            .stream()
            .map(tenderGoodsItemsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderGoodsItemsDTO> findOne(Long id) {
        log.debug("Request to get TenderGoodsItems : {}", id);
        return tenderGoodsItemsRepository.findById(id).map(tenderGoodsItemsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderGoodsItems : {}", id);
        tenderGoodsItemsRepository.deleteById(id);
    }
}
