package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderScheduleSample;
import com.dxc.eproc.tender.repository.TenderScheduleSampleRepository;
import com.dxc.eproc.tender.service.TenderScheduleSampleService;
import com.dxc.eproc.tender.service.dto.TenderScheduleSampleDTO;
import com.dxc.eproc.tender.service.mapper.TenderScheduleSampleMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderScheduleSample}.
 */
@Service
@Transactional
public class TenderScheduleSampleServiceImpl implements TenderScheduleSampleService {

    private final Logger log = LoggerFactory.getLogger(TenderScheduleSampleServiceImpl.class);

    private final TenderScheduleSampleRepository tenderScheduleSampleRepository;

    private final TenderScheduleSampleMapper tenderScheduleSampleMapper;

    public TenderScheduleSampleServiceImpl(
        TenderScheduleSampleRepository tenderScheduleSampleRepository,
        TenderScheduleSampleMapper tenderScheduleSampleMapper
    ) {
        this.tenderScheduleSampleRepository = tenderScheduleSampleRepository;
        this.tenderScheduleSampleMapper = tenderScheduleSampleMapper;
    }

    @Override
    public TenderScheduleSampleDTO save(TenderScheduleSampleDTO tenderScheduleSampleDTO) {
        log.debug("Request to save TenderScheduleSample : {}", tenderScheduleSampleDTO);
        TenderScheduleSample tenderScheduleSample = tenderScheduleSampleMapper.toEntity(tenderScheduleSampleDTO);
        tenderScheduleSample = tenderScheduleSampleRepository.save(tenderScheduleSample);
        return tenderScheduleSampleMapper.toDto(tenderScheduleSample);
    }

    @Override
    public Optional<TenderScheduleSampleDTO> partialUpdate(TenderScheduleSampleDTO tenderScheduleSampleDTO) {
        log.debug("Request to partially update TenderScheduleSample : {}", tenderScheduleSampleDTO);

        return tenderScheduleSampleRepository
            .findById(tenderScheduleSampleDTO.getId())
            .map(
                existingTenderScheduleSample -> {
                    tenderScheduleSampleMapper.partialUpdate(existingTenderScheduleSample, tenderScheduleSampleDTO);

                    return existingTenderScheduleSample;
                }
            )
            .map(tenderScheduleSampleRepository::save)
            .map(tenderScheduleSampleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderScheduleSampleDTO> findAll() {
        log.debug("Request to get all TenderScheduleSamples");
        return tenderScheduleSampleRepository
            .findAll()
            .stream()
            .map(tenderScheduleSampleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderScheduleSampleDTO> findOne(Long id) {
        log.debug("Request to get TenderScheduleSample : {}", id);
        return tenderScheduleSampleRepository.findById(id).map(tenderScheduleSampleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderScheduleSample : {}", id);
        tenderScheduleSampleRepository.deleteById(id);
    }
}
