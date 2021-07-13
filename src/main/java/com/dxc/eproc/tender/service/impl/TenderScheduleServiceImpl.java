package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderSchedule;
import com.dxc.eproc.tender.repository.TenderScheduleRepository;
import com.dxc.eproc.tender.service.TenderScheduleService;
import com.dxc.eproc.tender.service.dto.TenderScheduleDTO;
import com.dxc.eproc.tender.service.mapper.TenderScheduleMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderSchedule}.
 */
@Service
@Transactional
public class TenderScheduleServiceImpl implements TenderScheduleService {

    private final Logger log = LoggerFactory.getLogger(TenderScheduleServiceImpl.class);

    private final TenderScheduleRepository tenderScheduleRepository;

    private final TenderScheduleMapper tenderScheduleMapper;

    public TenderScheduleServiceImpl(TenderScheduleRepository tenderScheduleRepository, TenderScheduleMapper tenderScheduleMapper) {
        this.tenderScheduleRepository = tenderScheduleRepository;
        this.tenderScheduleMapper = tenderScheduleMapper;
    }

    @Override
    public TenderScheduleDTO save(TenderScheduleDTO tenderScheduleDTO) {
        log.debug("Request to save TenderSchedule : {}", tenderScheduleDTO);
        TenderSchedule tenderSchedule = tenderScheduleMapper.toEntity(tenderScheduleDTO);
        tenderSchedule = tenderScheduleRepository.save(tenderSchedule);
        return tenderScheduleMapper.toDto(tenderSchedule);
    }

    @Override
    public Optional<TenderScheduleDTO> partialUpdate(TenderScheduleDTO tenderScheduleDTO) {
        log.debug("Request to partially update TenderSchedule : {}", tenderScheduleDTO);

        return tenderScheduleRepository
            .findById(tenderScheduleDTO.getId())
            .map(
                existingTenderSchedule -> {
                    tenderScheduleMapper.partialUpdate(existingTenderSchedule, tenderScheduleDTO);

                    return existingTenderSchedule;
                }
            )
            .map(tenderScheduleRepository::save)
            .map(tenderScheduleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TenderScheduleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TenderSchedules");
        return tenderScheduleRepository.findAll(pageable).map(tenderScheduleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderScheduleDTO> findOne(Long id) {
        log.debug("Request to get TenderSchedule : {}", id);
        return tenderScheduleRepository.findById(id).map(tenderScheduleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderSchedule : {}", id);
        tenderScheduleRepository.deleteById(id);
    }
}
