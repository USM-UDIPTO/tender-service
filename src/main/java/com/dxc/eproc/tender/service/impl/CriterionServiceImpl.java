package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.Criterion;
import com.dxc.eproc.tender.repository.CriterionRepository;
import com.dxc.eproc.tender.service.CriterionService;
import com.dxc.eproc.tender.service.dto.CriterionDTO;
import com.dxc.eproc.tender.service.mapper.CriterionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Criterion}.
 */
@Service
@Transactional
public class CriterionServiceImpl implements CriterionService {

    private final Logger log = LoggerFactory.getLogger(CriterionServiceImpl.class);

    private final CriterionRepository criterionRepository;

    private final CriterionMapper criterionMapper;

    public CriterionServiceImpl(CriterionRepository criterionRepository, CriterionMapper criterionMapper) {
        this.criterionRepository = criterionRepository;
        this.criterionMapper = criterionMapper;
    }

    @Override
    public CriterionDTO save(CriterionDTO criterionDTO) {
        log.debug("Request to save Criterion : {}", criterionDTO);
        Criterion criterion = criterionMapper.toEntity(criterionDTO);
        criterion = criterionRepository.save(criterion);
        return criterionMapper.toDto(criterion);
    }

    @Override
    public Optional<CriterionDTO> partialUpdate(CriterionDTO criterionDTO) {
        log.debug("Request to partially update Criterion : {}", criterionDTO);

        return criterionRepository
            .findById(criterionDTO.getId())
            .map(
                existingCriterion -> {
                    criterionMapper.partialUpdate(existingCriterion, criterionDTO);

                    return existingCriterion;
                }
            )
            .map(criterionRepository::save)
            .map(criterionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CriterionDTO> findAll() {
        log.debug("Request to get all Criteria");
        return criterionRepository.findAll().stream().map(criterionMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CriterionDTO> findOne(Long id) {
        log.debug("Request to get Criterion : {}", id);
        return criterionRepository.findById(id).map(criterionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Criterion : {}", id);
        criterionRepository.deleteById(id);
    }
}
