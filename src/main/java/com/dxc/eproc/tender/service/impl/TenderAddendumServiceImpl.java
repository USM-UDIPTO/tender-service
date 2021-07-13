package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderAddendum;
import com.dxc.eproc.tender.repository.TenderAddendumRepository;
import com.dxc.eproc.tender.service.TenderAddendumService;
import com.dxc.eproc.tender.service.dto.TenderAddendumDTO;
import com.dxc.eproc.tender.service.mapper.TenderAddendumMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderAddendum}.
 */
@Service
@Transactional
public class TenderAddendumServiceImpl implements TenderAddendumService {

    private final Logger log = LoggerFactory.getLogger(TenderAddendumServiceImpl.class);

    private final TenderAddendumRepository tenderAddendumRepository;

    private final TenderAddendumMapper tenderAddendumMapper;

    public TenderAddendumServiceImpl(TenderAddendumRepository tenderAddendumRepository, TenderAddendumMapper tenderAddendumMapper) {
        this.tenderAddendumRepository = tenderAddendumRepository;
        this.tenderAddendumMapper = tenderAddendumMapper;
    }

    @Override
    public TenderAddendumDTO save(TenderAddendumDTO tenderAddendumDTO) {
        log.debug("Request to save TenderAddendum : {}", tenderAddendumDTO);
        TenderAddendum tenderAddendum = tenderAddendumMapper.toEntity(tenderAddendumDTO);
        tenderAddendum = tenderAddendumRepository.save(tenderAddendum);
        return tenderAddendumMapper.toDto(tenderAddendum);
    }

    @Override
    public Optional<TenderAddendumDTO> partialUpdate(TenderAddendumDTO tenderAddendumDTO) {
        log.debug("Request to partially update TenderAddendum : {}", tenderAddendumDTO);

        return tenderAddendumRepository
            .findById(tenderAddendumDTO.getId())
            .map(
                existingTenderAddendum -> {
                    tenderAddendumMapper.partialUpdate(existingTenderAddendum, tenderAddendumDTO);

                    return existingTenderAddendum;
                }
            )
            .map(tenderAddendumRepository::save)
            .map(tenderAddendumMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderAddendumDTO> findAll() {
        log.debug("Request to get all TenderAddenda");
        return tenderAddendumRepository
            .findAll()
            .stream()
            .map(tenderAddendumMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderAddendumDTO> findOne(Long id) {
        log.debug("Request to get TenderAddendum : {}", id);
        return tenderAddendumRepository.findById(id).map(tenderAddendumMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderAddendum : {}", id);
        tenderAddendumRepository.deleteById(id);
    }
}
