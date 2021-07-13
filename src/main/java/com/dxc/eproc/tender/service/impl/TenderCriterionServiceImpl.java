package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderCriterion;
import com.dxc.eproc.tender.repository.TenderCriterionRepository;
import com.dxc.eproc.tender.service.TenderCriterionService;
import com.dxc.eproc.tender.service.dto.TenderCriterionDTO;
import com.dxc.eproc.tender.service.mapper.TenderCriterionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderCriterion}.
 */
@Service
@Transactional
public class TenderCriterionServiceImpl implements TenderCriterionService {

    private final Logger log = LoggerFactory.getLogger(TenderCriterionServiceImpl.class);

    private final TenderCriterionRepository tenderCriterionRepository;

    private final TenderCriterionMapper tenderCriterionMapper;

    public TenderCriterionServiceImpl(TenderCriterionRepository tenderCriterionRepository, TenderCriterionMapper tenderCriterionMapper) {
        this.tenderCriterionRepository = tenderCriterionRepository;
        this.tenderCriterionMapper = tenderCriterionMapper;
    }

    @Override
    public TenderCriterionDTO save(TenderCriterionDTO tenderCriterionDTO) {
        log.debug("Request to save TenderCriterion : {}", tenderCriterionDTO);
        TenderCriterion tenderCriterion = tenderCriterionMapper.toEntity(tenderCriterionDTO);
        tenderCriterion = tenderCriterionRepository.save(tenderCriterion);
        return tenderCriterionMapper.toDto(tenderCriterion);
    }

    @Override
    public Optional<TenderCriterionDTO> partialUpdate(TenderCriterionDTO tenderCriterionDTO) {
        log.debug("Request to partially update TenderCriterion : {}", tenderCriterionDTO);

        return tenderCriterionRepository
            .findById(tenderCriterionDTO.getId())
            .map(
                existingTenderCriterion -> {
                    tenderCriterionMapper.partialUpdate(existingTenderCriterion, tenderCriterionDTO);

                    return existingTenderCriterion;
                }
            )
            .map(tenderCriterionRepository::save)
            .map(tenderCriterionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderCriterionDTO> findAll() {
        log.debug("Request to get all TenderCriteria");
        return tenderCriterionRepository
            .findAll()
            .stream()
            .map(tenderCriterionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderCriterionDTO> findOne(Long id) {
        log.debug("Request to get TenderCriterion : {}", id);
        return tenderCriterionRepository.findById(id).map(tenderCriterionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderCriterion : {}", id);
        tenderCriterionRepository.deleteById(id);
    }
}
