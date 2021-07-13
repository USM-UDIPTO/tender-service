package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderCorrigendumDetails;
import com.dxc.eproc.tender.repository.TenderCorrigendumDetailsRepository;
import com.dxc.eproc.tender.service.TenderCorrigendumDetailsService;
import com.dxc.eproc.tender.service.dto.TenderCorrigendumDetailsDTO;
import com.dxc.eproc.tender.service.mapper.TenderCorrigendumDetailsMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderCorrigendumDetails}.
 */
@Service
@Transactional
public class TenderCorrigendumDetailsServiceImpl implements TenderCorrigendumDetailsService {

    private final Logger log = LoggerFactory.getLogger(TenderCorrigendumDetailsServiceImpl.class);

    private final TenderCorrigendumDetailsRepository tenderCorrigendumDetailsRepository;

    private final TenderCorrigendumDetailsMapper tenderCorrigendumDetailsMapper;

    public TenderCorrigendumDetailsServiceImpl(
        TenderCorrigendumDetailsRepository tenderCorrigendumDetailsRepository,
        TenderCorrigendumDetailsMapper tenderCorrigendumDetailsMapper
    ) {
        this.tenderCorrigendumDetailsRepository = tenderCorrigendumDetailsRepository;
        this.tenderCorrigendumDetailsMapper = tenderCorrigendumDetailsMapper;
    }

    @Override
    public TenderCorrigendumDetailsDTO save(TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO) {
        log.debug("Request to save TenderCorrigendumDetails : {}", tenderCorrigendumDetailsDTO);
        TenderCorrigendumDetails tenderCorrigendumDetails = tenderCorrigendumDetailsMapper.toEntity(tenderCorrigendumDetailsDTO);
        tenderCorrigendumDetails = tenderCorrigendumDetailsRepository.save(tenderCorrigendumDetails);
        return tenderCorrigendumDetailsMapper.toDto(tenderCorrigendumDetails);
    }

    @Override
    public Optional<TenderCorrigendumDetailsDTO> partialUpdate(TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO) {
        log.debug("Request to partially update TenderCorrigendumDetails : {}", tenderCorrigendumDetailsDTO);

        return tenderCorrigendumDetailsRepository
            .findById(tenderCorrigendumDetailsDTO.getId())
            .map(
                existingTenderCorrigendumDetails -> {
                    tenderCorrigendumDetailsMapper.partialUpdate(existingTenderCorrigendumDetails, tenderCorrigendumDetailsDTO);

                    return existingTenderCorrigendumDetails;
                }
            )
            .map(tenderCorrigendumDetailsRepository::save)
            .map(tenderCorrigendumDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderCorrigendumDetailsDTO> findAll() {
        log.debug("Request to get all TenderCorrigendumDetails");
        return tenderCorrigendumDetailsRepository
            .findAll()
            .stream()
            .map(tenderCorrigendumDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderCorrigendumDetailsDTO> findOne(Long id) {
        log.debug("Request to get TenderCorrigendumDetails : {}", id);
        return tenderCorrigendumDetailsRepository.findById(id).map(tenderCorrigendumDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderCorrigendumDetails : {}", id);
        tenderCorrigendumDetailsRepository.deleteById(id);
    }
}
