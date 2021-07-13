package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.CriterionDocument;
import com.dxc.eproc.tender.repository.CriterionDocumentRepository;
import com.dxc.eproc.tender.service.CriterionDocumentService;
import com.dxc.eproc.tender.service.dto.CriterionDocumentDTO;
import com.dxc.eproc.tender.service.mapper.CriterionDocumentMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CriterionDocument}.
 */
@Service
@Transactional
public class CriterionDocumentServiceImpl implements CriterionDocumentService {

    private final Logger log = LoggerFactory.getLogger(CriterionDocumentServiceImpl.class);

    private final CriterionDocumentRepository criterionDocumentRepository;

    private final CriterionDocumentMapper criterionDocumentMapper;

    public CriterionDocumentServiceImpl(
        CriterionDocumentRepository criterionDocumentRepository,
        CriterionDocumentMapper criterionDocumentMapper
    ) {
        this.criterionDocumentRepository = criterionDocumentRepository;
        this.criterionDocumentMapper = criterionDocumentMapper;
    }

    @Override
    public CriterionDocumentDTO save(CriterionDocumentDTO criterionDocumentDTO) {
        log.debug("Request to save CriterionDocument : {}", criterionDocumentDTO);
        CriterionDocument criterionDocument = criterionDocumentMapper.toEntity(criterionDocumentDTO);
        criterionDocument = criterionDocumentRepository.save(criterionDocument);
        return criterionDocumentMapper.toDto(criterionDocument);
    }

    @Override
    public Optional<CriterionDocumentDTO> partialUpdate(CriterionDocumentDTO criterionDocumentDTO) {
        log.debug("Request to partially update CriterionDocument : {}", criterionDocumentDTO);

        return criterionDocumentRepository
            .findById(criterionDocumentDTO.getId())
            .map(
                existingCriterionDocument -> {
                    criterionDocumentMapper.partialUpdate(existingCriterionDocument, criterionDocumentDTO);

                    return existingCriterionDocument;
                }
            )
            .map(criterionDocumentRepository::save)
            .map(criterionDocumentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CriterionDocumentDTO> findAll() {
        log.debug("Request to get all CriterionDocuments");
        return criterionDocumentRepository
            .findAll()
            .stream()
            .map(criterionDocumentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CriterionDocumentDTO> findOne(Long id) {
        log.debug("Request to get CriterionDocument : {}", id);
        return criterionDocumentRepository.findById(id).map(criterionDocumentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CriterionDocument : {}", id);
        criterionDocumentRepository.deleteById(id);
    }
}
