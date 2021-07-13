package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderScrutinyMaster;
import com.dxc.eproc.tender.repository.TenderScrutinyMasterRepository;
import com.dxc.eproc.tender.service.TenderScrutinyMasterService;
import com.dxc.eproc.tender.service.dto.TenderScrutinyMasterDTO;
import com.dxc.eproc.tender.service.mapper.TenderScrutinyMasterMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderScrutinyMaster}.
 */
@Service
@Transactional
public class TenderScrutinyMasterServiceImpl implements TenderScrutinyMasterService {

    private final Logger log = LoggerFactory.getLogger(TenderScrutinyMasterServiceImpl.class);

    private final TenderScrutinyMasterRepository tenderScrutinyMasterRepository;

    private final TenderScrutinyMasterMapper tenderScrutinyMasterMapper;

    public TenderScrutinyMasterServiceImpl(
        TenderScrutinyMasterRepository tenderScrutinyMasterRepository,
        TenderScrutinyMasterMapper tenderScrutinyMasterMapper
    ) {
        this.tenderScrutinyMasterRepository = tenderScrutinyMasterRepository;
        this.tenderScrutinyMasterMapper = tenderScrutinyMasterMapper;
    }

    @Override
    public TenderScrutinyMasterDTO save(TenderScrutinyMasterDTO tenderScrutinyMasterDTO) {
        log.debug("Request to save TenderScrutinyMaster : {}", tenderScrutinyMasterDTO);
        TenderScrutinyMaster tenderScrutinyMaster = tenderScrutinyMasterMapper.toEntity(tenderScrutinyMasterDTO);
        tenderScrutinyMaster = tenderScrutinyMasterRepository.save(tenderScrutinyMaster);
        return tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);
    }

    @Override
    public Optional<TenderScrutinyMasterDTO> partialUpdate(TenderScrutinyMasterDTO tenderScrutinyMasterDTO) {
        log.debug("Request to partially update TenderScrutinyMaster : {}", tenderScrutinyMasterDTO);

        return tenderScrutinyMasterRepository
            .findById(tenderScrutinyMasterDTO.getId())
            .map(
                existingTenderScrutinyMaster -> {
                    tenderScrutinyMasterMapper.partialUpdate(existingTenderScrutinyMaster, tenderScrutinyMasterDTO);

                    return existingTenderScrutinyMaster;
                }
            )
            .map(tenderScrutinyMasterRepository::save)
            .map(tenderScrutinyMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderScrutinyMasterDTO> findAll() {
        log.debug("Request to get all TenderScrutinyMasters");
        return tenderScrutinyMasterRepository
            .findAll()
            .stream()
            .map(tenderScrutinyMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderScrutinyMasterDTO> findOne(Long id) {
        log.debug("Request to get TenderScrutinyMaster : {}", id);
        return tenderScrutinyMasterRepository.findById(id).map(tenderScrutinyMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderScrutinyMaster : {}", id);
        tenderScrutinyMasterRepository.deleteById(id);
    }
}
