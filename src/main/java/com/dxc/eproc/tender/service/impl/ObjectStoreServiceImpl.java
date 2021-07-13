package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.ObjectStore;
import com.dxc.eproc.tender.repository.ObjectStoreRepository;
import com.dxc.eproc.tender.service.ObjectStoreService;
import com.dxc.eproc.tender.service.dto.ObjectStoreDTO;
import com.dxc.eproc.tender.service.mapper.ObjectStoreMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ObjectStore}.
 */
@Service
@Transactional
public class ObjectStoreServiceImpl implements ObjectStoreService {

    private final Logger log = LoggerFactory.getLogger(ObjectStoreServiceImpl.class);

    private final ObjectStoreRepository objectStoreRepository;

    private final ObjectStoreMapper objectStoreMapper;

    public ObjectStoreServiceImpl(ObjectStoreRepository objectStoreRepository, ObjectStoreMapper objectStoreMapper) {
        this.objectStoreRepository = objectStoreRepository;
        this.objectStoreMapper = objectStoreMapper;
    }

    @Override
    public ObjectStoreDTO save(ObjectStoreDTO objectStoreDTO) {
        log.debug("Request to save ObjectStore : {}", objectStoreDTO);
        ObjectStore objectStore = objectStoreMapper.toEntity(objectStoreDTO);
        objectStore = objectStoreRepository.save(objectStore);
        return objectStoreMapper.toDto(objectStore);
    }

    @Override
    public Optional<ObjectStoreDTO> partialUpdate(ObjectStoreDTO objectStoreDTO) {
        log.debug("Request to partially update ObjectStore : {}", objectStoreDTO);

        return objectStoreRepository
            .findById(objectStoreDTO.getId())
            .map(
                existingObjectStore -> {
                    objectStoreMapper.partialUpdate(existingObjectStore, objectStoreDTO);

                    return existingObjectStore;
                }
            )
            .map(objectStoreRepository::save)
            .map(objectStoreMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObjectStoreDTO> findAll() {
        log.debug("Request to get all ObjectStores");
        return objectStoreRepository.findAll().stream().map(objectStoreMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ObjectStoreDTO> findOne(Long id) {
        log.debug("Request to get ObjectStore : {}", id);
        return objectStoreRepository.findById(id).map(objectStoreMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ObjectStore : {}", id);
        objectStoreRepository.deleteById(id);
    }
}
