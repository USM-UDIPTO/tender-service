package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.Telephone;
import com.dxc.eproc.tender.repository.TelephoneRepository;
import com.dxc.eproc.tender.service.TelephoneService;
import com.dxc.eproc.tender.service.dto.TelephoneDTO;
import com.dxc.eproc.tender.service.mapper.TelephoneMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Telephone}.
 */
@Service
@Transactional
public class TelephoneServiceImpl implements TelephoneService {

    private final Logger log = LoggerFactory.getLogger(TelephoneServiceImpl.class);

    private final TelephoneRepository telephoneRepository;

    private final TelephoneMapper telephoneMapper;

    public TelephoneServiceImpl(TelephoneRepository telephoneRepository, TelephoneMapper telephoneMapper) {
        this.telephoneRepository = telephoneRepository;
        this.telephoneMapper = telephoneMapper;
    }

    @Override
    public TelephoneDTO save(TelephoneDTO telephoneDTO) {
        log.debug("Request to save Telephone : {}", telephoneDTO);
        Telephone telephone = telephoneMapper.toEntity(telephoneDTO);
        telephone = telephoneRepository.save(telephone);
        return telephoneMapper.toDto(telephone);
    }

    @Override
    public Optional<TelephoneDTO> partialUpdate(TelephoneDTO telephoneDTO) {
        log.debug("Request to partially update Telephone : {}", telephoneDTO);

        return telephoneRepository
            .findById(telephoneDTO.getId())
            .map(
                existingTelephone -> {
                    telephoneMapper.partialUpdate(existingTelephone, telephoneDTO);

                    return existingTelephone;
                }
            )
            .map(telephoneRepository::save)
            .map(telephoneMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TelephoneDTO> findAll() {
        log.debug("Request to get all Telephones");
        return telephoneRepository.findAll().stream().map(telephoneMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TelephoneDTO> findOne(Long id) {
        log.debug("Request to get Telephone : {}", id);
        return telephoneRepository.findById(id).map(telephoneMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Telephone : {}", id);
        telephoneRepository.deleteById(id);
    }
}
