package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderScheduleGroup;
import com.dxc.eproc.tender.repository.TenderScheduleGroupRepository;
import com.dxc.eproc.tender.service.TenderScheduleGroupService;
import com.dxc.eproc.tender.service.dto.TenderScheduleGroupDTO;
import com.dxc.eproc.tender.service.mapper.TenderScheduleGroupMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderScheduleGroup}.
 */
@Service
@Transactional
public class TenderScheduleGroupServiceImpl implements TenderScheduleGroupService {

    private final Logger log = LoggerFactory.getLogger(TenderScheduleGroupServiceImpl.class);

    private final TenderScheduleGroupRepository tenderScheduleGroupRepository;

    private final TenderScheduleGroupMapper tenderScheduleGroupMapper;

    public TenderScheduleGroupServiceImpl(
        TenderScheduleGroupRepository tenderScheduleGroupRepository,
        TenderScheduleGroupMapper tenderScheduleGroupMapper
    ) {
        this.tenderScheduleGroupRepository = tenderScheduleGroupRepository;
        this.tenderScheduleGroupMapper = tenderScheduleGroupMapper;
    }

    @Override
    public TenderScheduleGroupDTO save(TenderScheduleGroupDTO tenderScheduleGroupDTO) {
        log.debug("Request to save TenderScheduleGroup : {}", tenderScheduleGroupDTO);
        TenderScheduleGroup tenderScheduleGroup = tenderScheduleGroupMapper.toEntity(tenderScheduleGroupDTO);
        tenderScheduleGroup = tenderScheduleGroupRepository.save(tenderScheduleGroup);
        return tenderScheduleGroupMapper.toDto(tenderScheduleGroup);
    }

    @Override
    public Optional<TenderScheduleGroupDTO> partialUpdate(TenderScheduleGroupDTO tenderScheduleGroupDTO) {
        log.debug("Request to partially update TenderScheduleGroup : {}", tenderScheduleGroupDTO);

        return tenderScheduleGroupRepository
            .findById(tenderScheduleGroupDTO.getId())
            .map(
                existingTenderScheduleGroup -> {
                    tenderScheduleGroupMapper.partialUpdate(existingTenderScheduleGroup, tenderScheduleGroupDTO);

                    return existingTenderScheduleGroup;
                }
            )
            .map(tenderScheduleGroupRepository::save)
            .map(tenderScheduleGroupMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderScheduleGroupDTO> findAll() {
        log.debug("Request to get all TenderScheduleGroups");
        return tenderScheduleGroupRepository
            .findAll()
            .stream()
            .map(tenderScheduleGroupMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderScheduleGroupDTO> findOne(Long id) {
        log.debug("Request to get TenderScheduleGroup : {}", id);
        return tenderScheduleGroupRepository.findById(id).map(tenderScheduleGroupMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderScheduleGroup : {}", id);
        tenderScheduleGroupRepository.deleteById(id);
    }
}
