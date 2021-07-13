package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderQuery;
import com.dxc.eproc.tender.repository.TenderQueryRepository;
import com.dxc.eproc.tender.service.TenderQueryService;
import com.dxc.eproc.tender.service.dto.TenderQueryDTO;
import com.dxc.eproc.tender.service.mapper.TenderQueryMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderQuery}.
 */
@Service
@Transactional
public class TenderQueryServiceImpl implements TenderQueryService {

    private final Logger log = LoggerFactory.getLogger(TenderQueryServiceImpl.class);

    private final TenderQueryRepository tenderQueryRepository;

    private final TenderQueryMapper tenderQueryMapper;

    public TenderQueryServiceImpl(TenderQueryRepository tenderQueryRepository, TenderQueryMapper tenderQueryMapper) {
        this.tenderQueryRepository = tenderQueryRepository;
        this.tenderQueryMapper = tenderQueryMapper;
    }

    @Override
    public TenderQueryDTO save(TenderQueryDTO tenderQueryDTO) {
        log.debug("Request to save TenderQuery : {}", tenderQueryDTO);
        TenderQuery tenderQuery = tenderQueryMapper.toEntity(tenderQueryDTO);
        tenderQuery = tenderQueryRepository.save(tenderQuery);
        return tenderQueryMapper.toDto(tenderQuery);
    }

    @Override
    public Optional<TenderQueryDTO> partialUpdate(TenderQueryDTO tenderQueryDTO) {
        log.debug("Request to partially update TenderQuery : {}", tenderQueryDTO);

        return tenderQueryRepository
            .findById(tenderQueryDTO.getId())
            .map(
                existingTenderQuery -> {
                    tenderQueryMapper.partialUpdate(existingTenderQuery, tenderQueryDTO);

                    return existingTenderQuery;
                }
            )
            .map(tenderQueryRepository::save)
            .map(tenderQueryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderQueryDTO> findAll() {
        log.debug("Request to get all TenderQueries");
        return tenderQueryRepository.findAll().stream().map(tenderQueryMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderQueryDTO> findOne(Long id) {
        log.debug("Request to get TenderQuery : {}", id);
        return tenderQueryRepository.findById(id).map(tenderQueryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderQuery : {}", id);
        tenderQueryRepository.deleteById(id);
    }
}
