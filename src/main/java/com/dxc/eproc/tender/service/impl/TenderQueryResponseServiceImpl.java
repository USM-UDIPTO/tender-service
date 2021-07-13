package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderQueryResponse;
import com.dxc.eproc.tender.repository.TenderQueryResponseRepository;
import com.dxc.eproc.tender.service.TenderQueryResponseService;
import com.dxc.eproc.tender.service.dto.TenderQueryResponseDTO;
import com.dxc.eproc.tender.service.mapper.TenderQueryResponseMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderQueryResponse}.
 */
@Service
@Transactional
public class TenderQueryResponseServiceImpl implements TenderQueryResponseService {

    private final Logger log = LoggerFactory.getLogger(TenderQueryResponseServiceImpl.class);

    private final TenderQueryResponseRepository tenderQueryResponseRepository;

    private final TenderQueryResponseMapper tenderQueryResponseMapper;

    public TenderQueryResponseServiceImpl(
        TenderQueryResponseRepository tenderQueryResponseRepository,
        TenderQueryResponseMapper tenderQueryResponseMapper
    ) {
        this.tenderQueryResponseRepository = tenderQueryResponseRepository;
        this.tenderQueryResponseMapper = tenderQueryResponseMapper;
    }

    @Override
    public TenderQueryResponseDTO save(TenderQueryResponseDTO tenderQueryResponseDTO) {
        log.debug("Request to save TenderQueryResponse : {}", tenderQueryResponseDTO);
        TenderQueryResponse tenderQueryResponse = tenderQueryResponseMapper.toEntity(tenderQueryResponseDTO);
        tenderQueryResponse = tenderQueryResponseRepository.save(tenderQueryResponse);
        return tenderQueryResponseMapper.toDto(tenderQueryResponse);
    }

    @Override
    public Optional<TenderQueryResponseDTO> partialUpdate(TenderQueryResponseDTO tenderQueryResponseDTO) {
        log.debug("Request to partially update TenderQueryResponse : {}", tenderQueryResponseDTO);

        return tenderQueryResponseRepository
            .findById(tenderQueryResponseDTO.getId())
            .map(
                existingTenderQueryResponse -> {
                    tenderQueryResponseMapper.partialUpdate(existingTenderQueryResponse, tenderQueryResponseDTO);

                    return existingTenderQueryResponse;
                }
            )
            .map(tenderQueryResponseRepository::save)
            .map(tenderQueryResponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderQueryResponseDTO> findAll() {
        log.debug("Request to get all TenderQueryResponses");
        return tenderQueryResponseRepository
            .findAll()
            .stream()
            .map(tenderQueryResponseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderQueryResponseDTO> findOne(Long id) {
        log.debug("Request to get TenderQueryResponse : {}", id);
        return tenderQueryResponseRepository.findById(id).map(tenderQueryResponseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderQueryResponse : {}", id);
        tenderQueryResponseRepository.deleteById(id);
    }
}
