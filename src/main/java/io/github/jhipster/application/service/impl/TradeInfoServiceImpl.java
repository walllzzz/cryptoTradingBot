package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.TradeInfoService;
import io.github.jhipster.application.domain.TradeInfo;
import io.github.jhipster.application.repository.TradeInfoRepository;
import io.github.jhipster.application.service.dto.TradeInfoDTO;
import io.github.jhipster.application.service.mapper.TradeInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing TradeInfo.
 */
@Service
public class TradeInfoServiceImpl implements TradeInfoService{

    private final Logger log = LoggerFactory.getLogger(TradeInfoServiceImpl.class);

    private final TradeInfoRepository tradeInfoRepository;

    private final TradeInfoMapper tradeInfoMapper;

    public TradeInfoServiceImpl(TradeInfoRepository tradeInfoRepository, TradeInfoMapper tradeInfoMapper) {
        this.tradeInfoRepository = tradeInfoRepository;
        this.tradeInfoMapper = tradeInfoMapper;
    }

    /**
     * Save a tradeInfo.
     *
     * @param tradeInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TradeInfoDTO save(TradeInfoDTO tradeInfoDTO) {
        log.debug("Request to save TradeInfo : {}", tradeInfoDTO);
        TradeInfo tradeInfo = tradeInfoMapper.toEntity(tradeInfoDTO);
        tradeInfo = tradeInfoRepository.save(tradeInfo);
        return tradeInfoMapper.toDto(tradeInfo);
    }

    /**
     * Get all the tradeInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<TradeInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TradeInfos");
        return tradeInfoRepository.findAll(pageable)
            .map(tradeInfoMapper::toDto);
    }

    /**
     * Get one tradeInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public TradeInfoDTO findOne(String id) {
        log.debug("Request to get TradeInfo : {}", id);
        TradeInfo tradeInfo = tradeInfoRepository.findOne(id);
        return tradeInfoMapper.toDto(tradeInfo);
    }

    /**
     * Delete the tradeInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete TradeInfo : {}", id);
        tradeInfoRepository.delete(id);
    }
}
