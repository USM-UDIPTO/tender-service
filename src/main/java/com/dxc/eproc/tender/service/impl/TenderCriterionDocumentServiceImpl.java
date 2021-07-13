package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderCriterionDocument;
import com.dxc.eproc.tender.repository.TenderCriterionDocumentRepository;
import com.dxc.eproc.tender.service.TenderCriterionDocumentService;
import com.dxc.eproc.tender.service.dto.TenderCriterionDocumentDTO;
import com.dxc.eproc.tender.service.mapper.TenderCriterionDocumentMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderCriterionDocument}.
 */
@Service
@Transactional
public class TenderCriterionDocumentServiceImpl implements TenderCriterionDocumentService {

    private final Logger log = LoggerFactory.getLogger(TenderCriterionDocumentServiceImpl.class);

    private final TenderCriterionDocumentRepository tenderCriterionDocumentRepository;

    private final TenderCriterionDocumentMapper tenderCriterionDocumentMapper;

    public TenderCriterionDocumentServiceImpl(
        TenderCriterionDocumentRepository tenderCriterionDocumentRepository,
        TenderCriterionDocumentMapper tenderCriterionDocumentMapper
    ) {
        this.tenderCriterionDocumentRepository = tenderCriterionDocumentRepository;
        this.tenderCriterionDocumentMapper = tenderCriterionDocumentMapper;
    }

    @Override
    public TenderCriterionDocumentDTO save(TenderCriterionDocumentDTO tenderCriterionDocumentDTO) {
        log.debug("Request to save TenderCriterionDocument : {}", tenderCriterionDocumentDTO);
        TenderCriterionDocument tenderCriterionDocument = tenderCriterionDocumentMapper.toEntity(tenderCriterionDocumentDTO);
        tenderCriterionDocument = tenderCriterionDocumentRepository.save(tenderCriterionDocument);
        return tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);
    }

    @Override
    public Optional<TenderCriterionDocumentDTO> partialUpdate(TenderCriterionDocumentDTO tenderCriterionDocumentDTO) {
        log.debug("Request to partially update TenderCriterionDocument : {}", tenderCriterionDocumentDTO);

        return tenderCriterionDocumentRepository
            .findById(tenderCriterionDocumentDTO.getId())
            .map(
                existingTenderCriterionDocument -> {
                    tenderCriterionDocumentMapper.partialUpdate(existingTenderCriterionDocument, tenderCriterionDocumentDTO);

                    return existingTenderCriterionDocument;
                }
            )
            .map(tenderCriterionDocumentRepository::save)
            .map(tenderCriterionDocumentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderCriterionDocumentDTO> findAll() {
        log.debug("Request to get all TenderCriterionDocuments");
        return tenderCriterionDocumentRepository
            .findAll()
            .stream()
            .map(tenderCriterionDocumentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderCriterionDocumentDTO> findOne(Long id) {
        log.debug("Request to get TenderCriterionDocument : {}", id);
        return tenderCriterionDocumentRepository.findById(id).map(tenderCriterionDocumentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderCriterionDocument : {}", id);
        tenderCriterionDocumentRepository.deleteById(id);
    }
}
