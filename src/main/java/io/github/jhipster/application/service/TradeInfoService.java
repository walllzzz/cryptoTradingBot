package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.TradeInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TradeInfo.
 */
public interface TradeInfoService {

    /**
     * Save a tradeInfo.
     *
     * @param tradeInfoDTO the entity to save
     * @return the persisted entity
     */
    TradeInfoDTO save(TradeInfoDTO tradeInfoDTO);

    /**
     * Get all the tradeInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TradeInfoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" tradeInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TradeInfoDTO findOne(String id);

    /**
     * Delete the "id" tradeInfo.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
