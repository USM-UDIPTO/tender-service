package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderScheduleGroupItems;
import com.dxc.eproc.tender.repository.TenderScheduleGroupItemsRepository;
import com.dxc.eproc.tender.service.TenderScheduleGroupItemsService;
import com.dxc.eproc.tender.service.dto.TenderScheduleGroupItemsDTO;
import com.dxc.eproc.tender.service.mapper.TenderScheduleGroupItemsMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderScheduleGroupItems}.
 */
@Service
@Transactional
public class TenderScheduleGroupItemsServiceImpl implements TenderScheduleGroupItemsService {

    private final Logger log = LoggerFactory.getLogger(TenderScheduleGroupItemsServiceImpl.class);

    private final TenderScheduleGroupItemsRepository tenderScheduleGroupItemsRepository;

    private final TenderScheduleGroupItemsMapper tenderScheduleGroupItemsMapper;

    public TenderScheduleGroupItemsServiceImpl(
        TenderScheduleGroupItemsRepository tenderScheduleGroupItemsRepository,
        TenderScheduleGroupItemsMapper tenderScheduleGroupItemsMapper
    ) {
        this.tenderScheduleGroupItemsRepository = tenderScheduleGroupItemsRepository;
        this.tenderScheduleGroupItemsMapper = tenderScheduleGroupItemsMapper;
    }

    @Override
    public TenderScheduleGroupItemsDTO save(TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO) {
        log.debug("Request to save TenderScheduleGroupItems : {}", tenderScheduleGroupItemsDTO);
        TenderScheduleGroupItems tenderScheduleGroupItems = tenderScheduleGroupItemsMapper.toEntity(tenderScheduleGroupItemsDTO);
        tenderScheduleGroupItems = tenderScheduleGroupItemsRepository.save(tenderScheduleGroupItems);
        return tenderScheduleGroupItemsMapper.toDto(tenderScheduleGroupItems);
    }

    @Override
    public Optional<TenderScheduleGroupItemsDTO> partialUpdate(TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO) {
        log.debug("Request to partially update TenderScheduleGroupItems : {}", tenderScheduleGroupItemsDTO);

        return tenderScheduleGroupItemsRepository
            .findById(tenderScheduleGroupItemsDTO.getId())
            .map(
                existingTenderScheduleGroupItems -> {
                    tenderScheduleGroupItemsMapper.partialUpdate(existingTenderScheduleGroupItems, tenderScheduleGroupItemsDTO);

                    return existingTenderScheduleGroupItems;
                }
            )
            .map(tenderScheduleGroupItemsRepository::save)
            .map(tenderScheduleGroupItemsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderScheduleGroupItemsDTO> findAll() {
        log.debug("Request to get all TenderScheduleGroupItems");
        return tenderScheduleGroupItemsRepository
            .findAll()
            .stream()
            .map(tenderScheduleGroupItemsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderScheduleGroupItemsDTO> findOne(Long id) {
        log.debug("Request to get TenderScheduleGroupItems : {}", id);
        return tenderScheduleGroupItemsRepository.findById(id).map(tenderScheduleGroupItemsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderScheduleGroupItems : {}", id);
        tenderScheduleGroupItemsRepository.deleteById(id);
    }
}
