package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.CasoinApp;

import io.github.jhipster.application.domain.ODAHead;
import io.github.jhipster.application.repository.ODAHeadRepository;
import io.github.jhipster.application.service.ODAHeadService;
import io.github.jhipster.application.service.dto.ODAHeadDTO;
import io.github.jhipster.application.service.mapper.ODAHeadMapper;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ODAHeadResource REST controller.
 *
 * @see ODAHeadResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CasoinApp.class)
public class ODAHeadResourceIntTest {

    private static final Integer DEFAULT_NR_FT = 1;
    private static final Integer UPDATED_NR_FT = 2;

    private static final LocalDate DEFAULT_DATA_FATTURA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_FATTURA = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TOTALE_FT = 1;
    private static final Integer UPDATED_TOTALE_FT = 2;

    @Autowired
    private ODAHeadRepository oDAHeadRepository;

    @Autowired
    private ODAHeadMapper oDAHeadMapper;

    @Autowired
    private ODAHeadService oDAHeadService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restODAHeadMockMvc;

    private ODAHead oDAHead;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ODAHeadResource oDAHeadResource = new ODAHeadResource(oDAHeadService);
        this.restODAHeadMockMvc = MockMvcBuilders.standaloneSetup(oDAHeadResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ODAHead createEntity(EntityManager em) {
        ODAHead oDAHead = new ODAHead()
            .nrFt(DEFAULT_NR_FT)
            .dataFattura(DEFAULT_DATA_FATTURA)
            .totaleFt(DEFAULT_TOTALE_FT);
        return oDAHead;
    }

    @Before
    public void initTest() {
        oDAHead = createEntity(em);
    }

    @Test
    @Transactional
    public void createODAHead() throws Exception {
        int databaseSizeBeforeCreate = oDAHeadRepository.findAll().size();

        // Create the ODAHead
        ODAHeadDTO oDAHeadDTO = oDAHeadMapper.toDto(oDAHead);
        restODAHeadMockMvc.perform(post("/api/oda-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDAHeadDTO)))
            .andExpect(status().isCreated());

        // Validate the ODAHead in the database
        List<ODAHead> oDAHeadList = oDAHeadRepository.findAll();
        assertThat(oDAHeadList).hasSize(databaseSizeBeforeCreate + 1);
        ODAHead testODAHead = oDAHeadList.get(oDAHeadList.size() - 1);
        assertThat(testODAHead.getNrFt()).isEqualTo(DEFAULT_NR_FT);
        assertThat(testODAHead.getDataFattura()).isEqualTo(DEFAULT_DATA_FATTURA);
        assertThat(testODAHead.getTotaleFt()).isEqualTo(DEFAULT_TOTALE_FT);
    }

    @Test
    @Transactional
    public void createODAHeadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = oDAHeadRepository.findAll().size();

        // Create the ODAHead with an existing ID
        oDAHead.setId(1L);
        ODAHeadDTO oDAHeadDTO = oDAHeadMapper.toDto(oDAHead);

        // An entity with an existing ID cannot be created, so this API call must fail
        restODAHeadMockMvc.perform(post("/api/oda-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDAHeadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ODAHead in the database
        List<ODAHead> oDAHeadList = oDAHeadRepository.findAll();
        assertThat(oDAHeadList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllODAHeads() throws Exception {
        // Initialize the database
        oDAHeadRepository.saveAndFlush(oDAHead);

        // Get all the oDAHeadList
        restODAHeadMockMvc.perform(get("/api/oda-heads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oDAHead.getId().intValue())))
            .andExpect(jsonPath("$.[*].nrFt").value(hasItem(DEFAULT_NR_FT)))
            .andExpect(jsonPath("$.[*].dataFattura").value(hasItem(DEFAULT_DATA_FATTURA.toString())))
            .andExpect(jsonPath("$.[*].totaleFt").value(hasItem(DEFAULT_TOTALE_FT)));
    }
    
    @Test
    @Transactional
    public void getODAHead() throws Exception {
        // Initialize the database
        oDAHeadRepository.saveAndFlush(oDAHead);

        // Get the oDAHead
        restODAHeadMockMvc.perform(get("/api/oda-heads/{id}", oDAHead.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(oDAHead.getId().intValue()))
            .andExpect(jsonPath("$.nrFt").value(DEFAULT_NR_FT))
            .andExpect(jsonPath("$.dataFattura").value(DEFAULT_DATA_FATTURA.toString()))
            .andExpect(jsonPath("$.totaleFt").value(DEFAULT_TOTALE_FT));
    }

    @Test
    @Transactional
    public void getNonExistingODAHead() throws Exception {
        // Get the oDAHead
        restODAHeadMockMvc.perform(get("/api/oda-heads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateODAHead() throws Exception {
        // Initialize the database
        oDAHeadRepository.saveAndFlush(oDAHead);

        int databaseSizeBeforeUpdate = oDAHeadRepository.findAll().size();

        // Update the oDAHead
        ODAHead updatedODAHead = oDAHeadRepository.findById(oDAHead.getId()).get();
        // Disconnect from session so that the updates on updatedODAHead are not directly saved in db
        em.detach(updatedODAHead);
        updatedODAHead
            .nrFt(UPDATED_NR_FT)
            .dataFattura(UPDATED_DATA_FATTURA)
            .totaleFt(UPDATED_TOTALE_FT);
        ODAHeadDTO oDAHeadDTO = oDAHeadMapper.toDto(updatedODAHead);

        restODAHeadMockMvc.perform(put("/api/oda-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDAHeadDTO)))
            .andExpect(status().isOk());

        // Validate the ODAHead in the database
        List<ODAHead> oDAHeadList = oDAHeadRepository.findAll();
        assertThat(oDAHeadList).hasSize(databaseSizeBeforeUpdate);
        ODAHead testODAHead = oDAHeadList.get(oDAHeadList.size() - 1);
        assertThat(testODAHead.getNrFt()).isEqualTo(UPDATED_NR_FT);
        assertThat(testODAHead.getDataFattura()).isEqualTo(UPDATED_DATA_FATTURA);
        assertThat(testODAHead.getTotaleFt()).isEqualTo(UPDATED_TOTALE_FT);
    }

    @Test
    @Transactional
    public void updateNonExistingODAHead() throws Exception {
        int databaseSizeBeforeUpdate = oDAHeadRepository.findAll().size();

        // Create the ODAHead
        ODAHeadDTO oDAHeadDTO = oDAHeadMapper.toDto(oDAHead);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restODAHeadMockMvc.perform(put("/api/oda-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDAHeadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ODAHead in the database
        List<ODAHead> oDAHeadList = oDAHeadRepository.findAll();
        assertThat(oDAHeadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteODAHead() throws Exception {
        // Initialize the database
        oDAHeadRepository.saveAndFlush(oDAHead);

        int databaseSizeBeforeDelete = oDAHeadRepository.findAll().size();

        // Delete the oDAHead
        restODAHeadMockMvc.perform(delete("/api/oda-heads/{id}", oDAHead.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ODAHead> oDAHeadList = oDAHeadRepository.findAll();
        assertThat(oDAHeadList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ODAHead.class);
        ODAHead oDAHead1 = new ODAHead();
        oDAHead1.setId(1L);
        ODAHead oDAHead2 = new ODAHead();
        oDAHead2.setId(oDAHead1.getId());
        assertThat(oDAHead1).isEqualTo(oDAHead2);
        oDAHead2.setId(2L);
        assertThat(oDAHead1).isNotEqualTo(oDAHead2);
        oDAHead1.setId(null);
        assertThat(oDAHead1).isNotEqualTo(oDAHead2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ODAHeadDTO.class);
        ODAHeadDTO oDAHeadDTO1 = new ODAHeadDTO();
        oDAHeadDTO1.setId(1L);
        ODAHeadDTO oDAHeadDTO2 = new ODAHeadDTO();
        assertThat(oDAHeadDTO1).isNotEqualTo(oDAHeadDTO2);
        oDAHeadDTO2.setId(oDAHeadDTO1.getId());
        assertThat(oDAHeadDTO1).isEqualTo(oDAHeadDTO2);
        oDAHeadDTO2.setId(2L);
        assertThat(oDAHeadDTO1).isNotEqualTo(oDAHeadDTO2);
        oDAHeadDTO1.setId(null);
        assertThat(oDAHeadDTO1).isNotEqualTo(oDAHeadDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(oDAHeadMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(oDAHeadMapper.fromId(null)).isNull();
    }
}
