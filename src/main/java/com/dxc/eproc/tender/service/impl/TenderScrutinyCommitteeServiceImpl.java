package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderScrutinyCommittee;
import com.dxc.eproc.tender.repository.TenderScrutinyCommitteeRepository;
import com.dxc.eproc.tender.service.TenderScrutinyCommitteeService;
import com.dxc.eproc.tender.service.dto.TenderScrutinyCommitteeDTO;
import com.dxc.eproc.tender.service.mapper.TenderScrutinyCommitteeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderScrutinyCommittee}.
 */
@Service
@Transactional
public class TenderScrutinyCommitteeServiceImpl implements TenderScrutinyCommitteeService {

    private final Logger log = LoggerFactory.getLogger(TenderScrutinyCommitteeServiceImpl.class);

    private final TenderScrutinyCommitteeRepository tenderScrutinyCommitteeRepository;

    private final TenderScrutinyCommitteeMapper tenderScrutinyCommitteeMapper;

    public TenderScrutinyCommitteeServiceImpl(
        TenderScrutinyCommitteeRepository tenderScrutinyCommitteeRepository,
        TenderScrutinyCommitteeMapper tenderScrutinyCommitteeMapper
    ) {
        this.tenderScrutinyCommitteeRepository = tenderScrutinyCommitteeRepository;
        this.tenderScrutinyCommitteeMapper = tenderScrutinyCommitteeMapper;
    }

    @Override
    public TenderScrutinyCommitteeDTO save(TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO) {
        log.debug("Request to save TenderScrutinyCommittee : {}", tenderScrutinyCommitteeDTO);
        TenderScrutinyCommittee tenderScrutinyCommittee = tenderScrutinyCommitteeMapper.toEntity(tenderScrutinyCommitteeDTO);
        tenderScrutinyCommittee = tenderScrutinyCommitteeRepository.save(tenderScrutinyCommittee);
        return tenderScrutinyCommitteeMapper.toDto(tenderScrutinyCommittee);
    }

    @Override
    public Optional<TenderScrutinyCommitteeDTO> partialUpdate(TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO) {
        log.debug("Request to partially update TenderScrutinyCommittee : {}", tenderScrutinyCommitteeDTO);

        return tenderScrutinyCommitteeRepository
            .findById(tenderScrutinyCommitteeDTO.getId())
            .map(
                existingTenderScrutinyCommittee -> {
                    tenderScrutinyCommitteeMapper.partialUpdate(existingTenderScrutinyCommittee, tenderScrutinyCommitteeDTO);

                    return existingTenderScrutinyCommittee;
                }
            )
            .map(tenderScrutinyCommitteeRepository::save)
            .map(tenderScrutinyCommitteeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderScrutinyCommitteeDTO> findAll() {
        log.debug("Request to get all TenderScrutinyCommittees");
        return tenderScrutinyCommitteeRepository
            .findAll()
            .stream()
            .map(tenderScrutinyCommitteeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderScrutinyCommitteeDTO> findOne(Long id) {
        log.debug("Request to get TenderScrutinyCommittee : {}", id);
        return tenderScrutinyCommitteeRepository.findById(id).map(tenderScrutinyCommitteeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderScrutinyCommittee : {}", id);
        tenderScrutinyCommitteeRepository.deleteById(id);
    }
}
