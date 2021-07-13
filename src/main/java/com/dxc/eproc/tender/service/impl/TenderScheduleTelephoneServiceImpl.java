package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderScheduleTelephone;
import com.dxc.eproc.tender.repository.TenderScheduleTelephoneRepository;
import com.dxc.eproc.tender.service.TenderScheduleTelephoneService;
import com.dxc.eproc.tender.service.dto.TenderScheduleTelephoneDTO;
import com.dxc.eproc.tender.service.mapper.TenderScheduleTelephoneMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderScheduleTelephone}.
 */
@Service
@Transactional
public class TenderScheduleTelephoneServiceImpl implements TenderScheduleTelephoneService {

    private final Logger log = LoggerFactory.getLogger(TenderScheduleTelephoneServiceImpl.class);

    private final TenderScheduleTelephoneRepository tenderScheduleTelephoneRepository;

    private final TenderScheduleTelephoneMapper tenderScheduleTelephoneMapper;

    public TenderScheduleTelephoneServiceImpl(
        TenderScheduleTelephoneRepository tenderScheduleTelephoneRepository,
        TenderScheduleTelephoneMapper tenderScheduleTelephoneMapper
    ) {
        this.tenderScheduleTelephoneRepository = tenderScheduleTelephoneRepository;
        this.tenderScheduleTelephoneMapper = tenderScheduleTelephoneMapper;
    }

    @Override
    public TenderScheduleTelephoneDTO save(TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO) {
        log.debug("Request to save TenderScheduleTelephone : {}", tenderScheduleTelephoneDTO);
        TenderScheduleTelephone tenderScheduleTelephone = tenderScheduleTelephoneMapper.toEntity(tenderScheduleTelephoneDTO);
        tenderScheduleTelephone = tenderScheduleTelephoneRepository.save(tenderScheduleTelephone);
        return tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);
    }

    @Override
    public Optional<TenderScheduleTelephoneDTO> partialUpdate(TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO) {
        log.debug("Request to partially update TenderScheduleTelephone : {}", tenderScheduleTelephoneDTO);

        return tenderScheduleTelephoneRepository
            .findById(tenderScheduleTelephoneDTO.getId())
            .map(
                existingTenderScheduleTelephone -> {
                    tenderScheduleTelephoneMapper.partialUpdate(existingTenderScheduleTelephone, tenderScheduleTelephoneDTO);

                    return existingTenderScheduleTelephone;
                }
            )
            .map(tenderScheduleTelephoneRepository::save)
            .map(tenderScheduleTelephoneMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderScheduleTelephoneDTO> findAll() {
        log.debug("Request to get all TenderScheduleTelephones");
        return tenderScheduleTelephoneRepository
            .findAll()
            .stream()
            .map(tenderScheduleTelephoneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderScheduleTelephoneDTO> findOne(Long id) {
        log.debug("Request to get TenderScheduleTelephone : {}", id);
        return tenderScheduleTelephoneRepository.findById(id).map(tenderScheduleTelephoneMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderScheduleTelephone : {}", id);
        tenderScheduleTelephoneRepository.deleteById(id);
    }
}
