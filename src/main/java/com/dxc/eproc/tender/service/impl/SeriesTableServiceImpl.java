package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.SeriesTable;
import com.dxc.eproc.tender.repository.SeriesTableRepository;
import com.dxc.eproc.tender.service.SeriesTableService;
import com.dxc.eproc.tender.service.dto.SeriesTableDTO;
import com.dxc.eproc.tender.service.mapper.SeriesTableMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SeriesTable}.
 */
@Service
@Transactional
public class SeriesTableServiceImpl implements SeriesTableService {

    private final Logger log = LoggerFactory.getLogger(SeriesTableServiceImpl.class);

    private final SeriesTableRepository seriesTableRepository;

    private final SeriesTableMapper seriesTableMapper;

    public SeriesTableServiceImpl(SeriesTableRepository seriesTableRepository, SeriesTableMapper seriesTableMapper) {
        this.seriesTableRepository = seriesTableRepository;
        this.seriesTableMapper = seriesTableMapper;
    }

    @Override
    public SeriesTableDTO save(SeriesTableDTO seriesTableDTO) {
        log.debug("Request to save SeriesTable : {}", seriesTableDTO);
        SeriesTable seriesTable = seriesTableMapper.toEntity(seriesTableDTO);
        seriesTable = seriesTableRepository.save(seriesTable);
        return seriesTableMapper.toDto(seriesTable);
    }

    @Override
    public Optional<SeriesTableDTO> partialUpdate(SeriesTableDTO seriesTableDTO) {
        log.debug("Request to partially update SeriesTable : {}", seriesTableDTO);

        return seriesTableRepository
            .findById(seriesTableDTO.getId())
            .map(
                existingSeriesTable -> {
                    seriesTableMapper.partialUpdate(existingSeriesTable, seriesTableDTO);

                    return existingSeriesTable;
                }
            )
            .map(seriesTableRepository::save)
            .map(seriesTableMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeriesTableDTO> findAll() {
        log.debug("Request to get all SeriesTables");
        return seriesTableRepository.findAll().stream().map(seriesTableMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SeriesTableDTO> findOne(Long id) {
        log.debug("Request to get SeriesTable : {}", id);
        return seriesTableRepository.findById(id).map(seriesTableMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SeriesTable : {}", id);
        seriesTableRepository.deleteById(id);
    }
}
