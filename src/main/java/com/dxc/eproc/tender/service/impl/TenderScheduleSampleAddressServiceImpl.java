package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.TenderScheduleSampleAddress;
import com.dxc.eproc.tender.repository.TenderScheduleSampleAddressRepository;
import com.dxc.eproc.tender.service.TenderScheduleSampleAddressService;
import com.dxc.eproc.tender.service.dto.TenderScheduleSampleAddressDTO;
import com.dxc.eproc.tender.service.mapper.TenderScheduleSampleAddressMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderScheduleSampleAddress}.
 */
@Service
@Transactional
public class TenderScheduleSampleAddressServiceImpl implements TenderScheduleSampleAddressService {

    private final Logger log = LoggerFactory.getLogger(TenderScheduleSampleAddressServiceImpl.class);

    private final TenderScheduleSampleAddressRepository tenderScheduleSampleAddressRepository;

    private final TenderScheduleSampleAddressMapper tenderScheduleSampleAddressMapper;

    public TenderScheduleSampleAddressServiceImpl(
        TenderScheduleSampleAddressRepository tenderScheduleSampleAddressRepository,
        TenderScheduleSampleAddressMapper tenderScheduleSampleAddressMapper
    ) {
        this.tenderScheduleSampleAddressRepository = tenderScheduleSampleAddressRepository;
        this.tenderScheduleSampleAddressMapper = tenderScheduleSampleAddressMapper;
    }

    @Override
    public TenderScheduleSampleAddressDTO save(TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO) {
        log.debug("Request to save TenderScheduleSampleAddress : {}", tenderScheduleSampleAddressDTO);
        TenderScheduleSampleAddress tenderScheduleSampleAddress = tenderScheduleSampleAddressMapper.toEntity(
            tenderScheduleSampleAddressDTO
        );
        tenderScheduleSampleAddress = tenderScheduleSampleAddressRepository.save(tenderScheduleSampleAddress);
        return tenderScheduleSampleAddressMapper.toDto(tenderScheduleSampleAddress);
    }

    @Override
    public Optional<TenderScheduleSampleAddressDTO> partialUpdate(TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO) {
        log.debug("Request to partially update TenderScheduleSampleAddress : {}", tenderScheduleSampleAddressDTO);

        return tenderScheduleSampleAddressRepository
            .findById(tenderScheduleSampleAddressDTO.getId())
            .map(
                existingTenderScheduleSampleAddress -> {
                    tenderScheduleSampleAddressMapper.partialUpdate(existingTenderScheduleSampleAddress, tenderScheduleSampleAddressDTO);

                    return existingTenderScheduleSampleAddress;
                }
            )
            .map(tenderScheduleSampleAddressRepository::save)
            .map(tenderScheduleSampleAddressMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderScheduleSampleAddressDTO> findAll() {
        log.debug("Request to get all TenderScheduleSampleAddresses");
        return tenderScheduleSampleAddressRepository
            .findAll()
            .stream()
            .map(tenderScheduleSampleAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderScheduleSampleAddressDTO> findOne(Long id) {
        log.debug("Request to get TenderScheduleSampleAddress : {}", id);
        return tenderScheduleSampleAddressRepository.findById(id).map(tenderScheduleSampleAddressMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderScheduleSampleAddress : {}", id);
        tenderScheduleSampleAddressRepository.deleteById(id);
    }
}
