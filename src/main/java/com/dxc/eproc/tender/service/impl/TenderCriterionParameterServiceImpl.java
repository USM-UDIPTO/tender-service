package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderCriterionParameter;
import com.dxc.eproc.tender.repository.TenderCriterionParameterRepository;
import com.dxc.eproc.tender.service.TenderCriterionParameterService;
import com.dxc.eproc.tender.service.dto.TenderCriterionParameterDTO;
import com.dxc.eproc.tender.service.mapper.TenderCriterionParameterMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderCriterionParameter}.
 */
@Service
@Transactional
public class TenderCriterionParameterServiceImpl implements TenderCriterionParameterService {

    private final Logger log = LoggerFactory.getLogger(TenderCriterionParameterServiceImpl.class);

    private final TenderCriterionParameterRepository tenderCriterionParameterRepository;

    private final TenderCriterionParameterMapper tenderCriterionParameterMapper;

    public TenderCriterionParameterServiceImpl(
        TenderCriterionParameterRepository tenderCriterionParameterRepository,
        TenderCriterionParameterMapper tenderCriterionParameterMapper
    ) {
        this.tenderCriterionParameterRepository = tenderCriterionParameterRepository;
        this.tenderCriterionParameterMapper = tenderCriterionParameterMapper;
    }

    @Override
    public TenderCriterionParameterDTO save(TenderCriterionParameterDTO tenderCriterionParameterDTO) {
        log.debug("Request to save TenderCriterionParameter : {}", tenderCriterionParameterDTO);
        TenderCriterionParameter tenderCriterionParameter = tenderCriterionParameterMapper.toEntity(tenderCriterionParameterDTO);
        tenderCriterionParameter = tenderCriterionParameterRepository.save(tenderCriterionParameter);
        return tenderCriterionParameterMapper.toDto(tenderCriterionParameter);
    }

    @Override
    public Optional<TenderCriterionParameterDTO> partialUpdate(TenderCriterionParameterDTO tenderCriterionParameterDTO) {
        log.debug("Request to partially update TenderCriterionParameter : {}", tenderCriterionParameterDTO);

        return tenderCriterionParameterRepository
            .findById(tenderCriterionParameterDTO.getId())
            .map(
                existingTenderCriterionParameter -> {
                    tenderCriterionParameterMapper.partialUpdate(existingTenderCriterionParameter, tenderCriterionParameterDTO);

                    return existingTenderCriterionParameter;
                }
            )
            .map(tenderCriterionParameterRepository::save)
            .map(tenderCriterionParameterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderCriterionParameterDTO> findAll() {
        log.debug("Request to get all TenderCriterionParameters");
        return tenderCriterionParameterRepository
            .findAll()
            .stream()
            .map(tenderCriterionParameterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderCriterionParameterDTO> findOne(Long id) {
        log.debug("Request to get TenderCriterionParameter : {}", id);
        return tenderCriterionParameterRepository.findById(id).map(tenderCriterionParameterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderCriterionParameter : {}", id);
        tenderCriterionParameterRepository.deleteById(id);
    }
}
