package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.CryptoTradingBotApp;

import io.github.jhipster.application.domain.TradeInfo;
import io.github.jhipster.application.repository.TradeInfoRepository;
import io.github.jhipster.application.service.TradeInfoService;
import io.github.jhipster.application.service.dto.TradeInfoDTO;
import io.github.jhipster.application.service.mapper.TradeInfoMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.Decision;
import io.github.jhipster.application.domain.enumeration.Decision;
/**
 * Test class for the TradeInfoResource REST controller.
 *
 * @see TradeInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CryptoTradingBotApp.class)
public class TradeInfoResourceIntTest {

    private static final Instant DEFAULT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Decision DEFAULT_ACTION = Decision.BUY;
    private static final Decision UPDATED_ACTION = Decision.SELL;

    private static final Decision DEFAULT_LAST_ACTION = Decision.BUY;
    private static final Decision UPDATED_LAST_ACTION = Decision.SELL;

    private static final Long DEFAULT_ASSET_PRICE = 1L;
    private static final Long UPDATED_ASSET_PRICE = 2L;

    private static final Long DEFAULT_BALANCE = 1L;
    private static final Long UPDATED_BALANCE = 2L;

    @Autowired
    private TradeInfoRepository tradeInfoRepository;

    @Autowired
    private TradeInfoMapper tradeInfoMapper;

    @Autowired
    private TradeInfoService tradeInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restTradeInfoMockMvc;

    private TradeInfo tradeInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TradeInfoResource tradeInfoResource = new TradeInfoResource(tradeInfoService);
        this.restTradeInfoMockMvc = MockMvcBuilders.standaloneSetup(tradeInfoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradeInfo createEntity() {
        TradeInfo tradeInfo = new TradeInfo()
            .time(DEFAULT_TIME)
            .action(DEFAULT_ACTION)
            .lastAction(DEFAULT_LAST_ACTION)
            .assetPrice(DEFAULT_ASSET_PRICE)
            .balance(DEFAULT_BALANCE);
        return tradeInfo;
    }

    @Before
    public void initTest() {
        tradeInfoRepository.deleteAll();
        tradeInfo = createEntity();
    }

    @Test
    public void createTradeInfo() throws Exception {
        int databaseSizeBeforeCreate = tradeInfoRepository.findAll().size();

        // Create the TradeInfo
        TradeInfoDTO tradeInfoDTO = tradeInfoMapper.toDto(tradeInfo);
        restTradeInfoMockMvc.perform(post("/api/trade-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tradeInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the TradeInfo in the database
        List<TradeInfo> tradeInfoList = tradeInfoRepository.findAll();
        assertThat(tradeInfoList).hasSize(databaseSizeBeforeCreate + 1);
        TradeInfo testTradeInfo = tradeInfoList.get(tradeInfoList.size() - 1);
        assertThat(testTradeInfo.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testTradeInfo.getAction()).isEqualTo(DEFAULT_ACTION);
        assertThat(testTradeInfo.getLastAction()).isEqualTo(DEFAULT_LAST_ACTION);
        assertThat(testTradeInfo.getAssetPrice()).isEqualTo(DEFAULT_ASSET_PRICE);
        assertThat(testTradeInfo.getBalance()).isEqualTo(DEFAULT_BALANCE);
    }

    @Test
    public void createTradeInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tradeInfoRepository.findAll().size();

        // Create the TradeInfo with an existing ID
        tradeInfo.setId("existing_id");
        TradeInfoDTO tradeInfoDTO = tradeInfoMapper.toDto(tradeInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTradeInfoMockMvc.perform(post("/api/trade-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tradeInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TradeInfo in the database
        List<TradeInfo> tradeInfoList = tradeInfoRepository.findAll();
        assertThat(tradeInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllTradeInfos() throws Exception {
        // Initialize the database
        tradeInfoRepository.save(tradeInfo);

        // Get all the tradeInfoList
        restTradeInfoMockMvc.perform(get("/api/trade-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tradeInfo.getId())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].action").value(hasItem(DEFAULT_ACTION.toString())))
            .andExpect(jsonPath("$.[*].lastAction").value(hasItem(DEFAULT_LAST_ACTION.toString())))
            .andExpect(jsonPath("$.[*].assetPrice").value(hasItem(DEFAULT_ASSET_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())));
    }

    @Test
    public void getTradeInfo() throws Exception {
        // Initialize the database
        tradeInfoRepository.save(tradeInfo);

        // Get the tradeInfo
        restTradeInfoMockMvc.perform(get("/api/trade-infos/{id}", tradeInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tradeInfo.getId()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.action").value(DEFAULT_ACTION.toString()))
            .andExpect(jsonPath("$.lastAction").value(DEFAULT_LAST_ACTION.toString()))
            .andExpect(jsonPath("$.assetPrice").value(DEFAULT_ASSET_PRICE.intValue()))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()));
    }

    @Test
    public void getNonExistingTradeInfo() throws Exception {
        // Get the tradeInfo
        restTradeInfoMockMvc.perform(get("/api/trade-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTradeInfo() throws Exception {
        // Initialize the database
        tradeInfoRepository.save(tradeInfo);
        int databaseSizeBeforeUpdate = tradeInfoRepository.findAll().size();

        // Update the tradeInfo
        TradeInfo updatedTradeInfo = tradeInfoRepository.findOne(tradeInfo.getId());
        updatedTradeInfo
            .time(UPDATED_TIME)
            .action(UPDATED_ACTION)
            .lastAction(UPDATED_LAST_ACTION)
            .assetPrice(UPDATED_ASSET_PRICE)
            .balance(UPDATED_BALANCE);
        TradeInfoDTO tradeInfoDTO = tradeInfoMapper.toDto(updatedTradeInfo);

        restTradeInfoMockMvc.perform(put("/api/trade-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tradeInfoDTO)))
            .andExpect(status().isOk());

        // Validate the TradeInfo in the database
        List<TradeInfo> tradeInfoList = tradeInfoRepository.findAll();
        assertThat(tradeInfoList).hasSize(databaseSizeBeforeUpdate);
        TradeInfo testTradeInfo = tradeInfoList.get(tradeInfoList.size() - 1);
        assertThat(testTradeInfo.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testTradeInfo.getAction()).isEqualTo(UPDATED_ACTION);
        assertThat(testTradeInfo.getLastAction()).isEqualTo(UPDATED_LAST_ACTION);
        assertThat(testTradeInfo.getAssetPrice()).isEqualTo(UPDATED_ASSET_PRICE);
        assertThat(testTradeInfo.getBalance()).isEqualTo(UPDATED_BALANCE);
    }

    @Test
    public void updateNonExistingTradeInfo() throws Exception {
        int databaseSizeBeforeUpdate = tradeInfoRepository.findAll().size();

        // Create the TradeInfo
        TradeInfoDTO tradeInfoDTO = tradeInfoMapper.toDto(tradeInfo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTradeInfoMockMvc.perform(put("/api/trade-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tradeInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the TradeInfo in the database
        List<TradeInfo> tradeInfoList = tradeInfoRepository.findAll();
        assertThat(tradeInfoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteTradeInfo() throws Exception {
        // Initialize the database
        tradeInfoRepository.save(tradeInfo);
        int databaseSizeBeforeDelete = tradeInfoRepository.findAll().size();

        // Get the tradeInfo
        restTradeInfoMockMvc.perform(delete("/api/trade-infos/{id}", tradeInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TradeInfo> tradeInfoList = tradeInfoRepository.findAll();
        assertThat(tradeInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TradeInfo.class);
        TradeInfo tradeInfo1 = new TradeInfo();
        tradeInfo1.setId("id1");
        TradeInfo tradeInfo2 = new TradeInfo();
        tradeInfo2.setId(tradeInfo1.getId());
        assertThat(tradeInfo1).isEqualTo(tradeInfo2);
        tradeInfo2.setId("id2");
        assertThat(tradeInfo1).isNotEqualTo(tradeInfo2);
        tradeInfo1.setId(null);
        assertThat(tradeInfo1).isNotEqualTo(tradeInfo2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TradeInfoDTO.class);
        TradeInfoDTO tradeInfoDTO1 = new TradeInfoDTO();
        tradeInfoDTO1.setId("id1");
        TradeInfoDTO tradeInfoDTO2 = new TradeInfoDTO();
        assertThat(tradeInfoDTO1).isNotEqualTo(tradeInfoDTO2);
        tradeInfoDTO2.setId(tradeInfoDTO1.getId());
        assertThat(tradeInfoDTO1).isEqualTo(tradeInfoDTO2);
        tradeInfoDTO2.setId("id2");
        assertThat(tradeInfoDTO1).isNotEqualTo(tradeInfoDTO2);
        tradeInfoDTO1.setId(null);
        assertThat(tradeInfoDTO1).isNotEqualTo(tradeInfoDTO2);
    }
}
