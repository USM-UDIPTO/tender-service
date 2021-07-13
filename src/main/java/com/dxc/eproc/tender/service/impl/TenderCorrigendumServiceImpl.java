package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderCorrigendum;
import com.dxc.eproc.tender.repository.TenderCorrigendumRepository;
import com.dxc.eproc.tender.service.TenderCorrigendumService;
import com.dxc.eproc.tender.service.dto.TenderCorrigendumDTO;
import com.dxc.eproc.tender.service.mapper.TenderCorrigendumMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderCorrigendum}.
 */
@Service
@Transactional
public class TenderCorrigendumServiceImpl implements TenderCorrigendumService {

    private final Logger log = LoggerFactory.getLogger(TenderCorrigendumServiceImpl.class);

    private final TenderCorrigendumRepository tenderCorrigendumRepository;

    private final TenderCorrigendumMapper tenderCorrigendumMapper;

    public TenderCorrigendumServiceImpl(
        TenderCorrigendumRepository tenderCorrigendumRepository,
        TenderCorrigendumMapper tenderCorrigendumMapper
    ) {
        this.tenderCorrigendumRepository = tenderCorrigendumRepository;
        this.tenderCorrigendumMapper = tenderCorrigendumMapper;
    }

    @Override
    public TenderCorrigendumDTO save(TenderCorrigendumDTO tenderCorrigendumDTO) {
        log.debug("Request to save TenderCorrigendum : {}", tenderCorrigendumDTO);
        TenderCorrigendum tenderCorrigendum = tenderCorrigendumMapper.toEntity(tenderCorrigendumDTO);
        tenderCorrigendum = tenderCorrigendumRepository.save(tenderCorrigendum);
        return tenderCorrigendumMapper.toDto(tenderCorrigendum);
    }

    @Override
    public Optional<TenderCorrigendumDTO> partialUpdate(TenderCorrigendumDTO tenderCorrigendumDTO) {
        log.debug("Request to partially update TenderCorrigendum : {}", tenderCorrigendumDTO);

        return tenderCorrigendumRepository
            .findById(tenderCorrigendumDTO.getId())
            .map(
                existingTenderCorrigendum -> {
                    tenderCorrigendumMapper.partialUpdate(existingTenderCorrigendum, tenderCorrigendumDTO);

                    return existingTenderCorrigendum;
                }
            )
            .map(tenderCorrigendumRepository::save)
            .map(tenderCorrigendumMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderCorrigendumDTO> findAll() {
        log.debug("Request to get all TenderCorrigendums");
        return tenderCorrigendumRepository
            .findAll()
            .stream()
            .map(tenderCorrigendumMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderCorrigendumDTO> findOne(Long id) {
        log.debug("Request to get TenderCorrigendum : {}", id);
        return tenderCorrigendumRepository.findById(id).map(tenderCorrigendumMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderCorrigendum : {}", id);
        tenderCorrigendumRepository.deleteById(id);
    }
}
