package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.TradeInfoService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.TradeInfoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TradeInfo.
 */
@RestController
@RequestMapping("/api")
public class TradeInfoResource {

    private final Logger log = LoggerFactory.getLogger(TradeInfoResource.class);

    private static final String ENTITY_NAME = "tradeInfo";

    private final TradeInfoService tradeInfoService;

    public TradeInfoResource(TradeInfoService tradeInfoService) {
        this.tradeInfoService = tradeInfoService;
    }

    /**
     * POST  /trade-infos : Create a new tradeInfo.
     *
     * @param tradeInfoDTO the tradeInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tradeInfoDTO, or with status 400 (Bad Request) if the tradeInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trade-infos")
    @Timed
    public ResponseEntity<TradeInfoDTO> createTradeInfo(@RequestBody TradeInfoDTO tradeInfoDTO) throws URISyntaxException {
        log.debug("REST request to save TradeInfo : {}", tradeInfoDTO);
        if (tradeInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tradeInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TradeInfoDTO result = tradeInfoService.save(tradeInfoDTO);
        return ResponseEntity.created(new URI("/api/trade-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /trade-infos : Updates an existing tradeInfo.
     *
     * @param tradeInfoDTO the tradeInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tradeInfoDTO,
     * or with status 400 (Bad Request) if the tradeInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the tradeInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trade-infos")
    @Timed
    public ResponseEntity<TradeInfoDTO> updateTradeInfo(@RequestBody TradeInfoDTO tradeInfoDTO) throws URISyntaxException {
        log.debug("REST request to update TradeInfo : {}", tradeInfoDTO);
        if (tradeInfoDTO.getId() == null) {
            return createTradeInfo(tradeInfoDTO);
        }
        TradeInfoDTO result = tradeInfoService.save(tradeInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tradeInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trade-infos : get all the tradeInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tradeInfos in body
     */
    @GetMapping("/trade-infos")
    @Timed
    public ResponseEntity<List<TradeInfoDTO>> getAllTradeInfos(Pageable pageable) {
        log.debug("REST request to get a page of TradeInfos");
        Page<TradeInfoDTO> page = tradeInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/trade-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /trade-infos/:id : get the "id" tradeInfo.
     *
     * @param id the id of the tradeInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tradeInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/trade-infos/{id}")
    @Timed
    public ResponseEntity<TradeInfoDTO> getTradeInfo(@PathVariable String id) {
        log.debug("REST request to get TradeInfo : {}", id);
        TradeInfoDTO tradeInfoDTO = tradeInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tradeInfoDTO));
    }

    /**
     * DELETE  /trade-infos/:id : delete the "id" tradeInfo.
     *
     * @param id the id of the tradeInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trade-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTradeInfo(@PathVariable String id) {
        log.debug("REST request to delete TradeInfo : {}", id);
        tradeInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
