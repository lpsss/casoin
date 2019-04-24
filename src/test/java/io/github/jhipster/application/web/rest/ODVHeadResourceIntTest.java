package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.CasoinApp;

import io.github.jhipster.application.domain.ODVHead;
import io.github.jhipster.application.repository.ODVHeadRepository;
import io.github.jhipster.application.service.ODVHeadService;
import io.github.jhipster.application.service.dto.ODVHeadDTO;
import io.github.jhipster.application.service.mapper.ODVHeadMapper;
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
 * Test class for the ODVHeadResource REST controller.
 *
 * @see ODVHeadResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CasoinApp.class)
public class ODVHeadResourceIntTest {

    private static final Integer DEFAULT_NR_FT = 1;
    private static final Integer UPDATED_NR_FT = 2;

    private static final LocalDate DEFAULT_DATA_FATTURA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_FATTURA = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TOTALE_FT = 1;
    private static final Integer UPDATED_TOTALE_FT = 2;

    @Autowired
    private ODVHeadRepository oDVHeadRepository;

    @Autowired
    private ODVHeadMapper oDVHeadMapper;

    @Autowired
    private ODVHeadService oDVHeadService;

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

    private MockMvc restODVHeadMockMvc;

    private ODVHead oDVHead;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ODVHeadResource oDVHeadResource = new ODVHeadResource(oDVHeadService);
        this.restODVHeadMockMvc = MockMvcBuilders.standaloneSetup(oDVHeadResource)
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
    public static ODVHead createEntity(EntityManager em) {
        ODVHead oDVHead = new ODVHead()
            .nrFt(DEFAULT_NR_FT)
            .dataFattura(DEFAULT_DATA_FATTURA)
            .totaleFt(DEFAULT_TOTALE_FT);
        return oDVHead;
    }

    @Before
    public void initTest() {
        oDVHead = createEntity(em);
    }

    @Test
    @Transactional
    public void createODVHead() throws Exception {
        int databaseSizeBeforeCreate = oDVHeadRepository.findAll().size();

        // Create the ODVHead
        ODVHeadDTO oDVHeadDTO = oDVHeadMapper.toDto(oDVHead);
        restODVHeadMockMvc.perform(post("/api/odv-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDVHeadDTO)))
            .andExpect(status().isCreated());

        // Validate the ODVHead in the database
        List<ODVHead> oDVHeadList = oDVHeadRepository.findAll();
        assertThat(oDVHeadList).hasSize(databaseSizeBeforeCreate + 1);
        ODVHead testODVHead = oDVHeadList.get(oDVHeadList.size() - 1);
        assertThat(testODVHead.getNrFt()).isEqualTo(DEFAULT_NR_FT);
        assertThat(testODVHead.getDataFattura()).isEqualTo(DEFAULT_DATA_FATTURA);
        assertThat(testODVHead.getTotaleFt()).isEqualTo(DEFAULT_TOTALE_FT);
    }

    @Test
    @Transactional
    public void createODVHeadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = oDVHeadRepository.findAll().size();

        // Create the ODVHead with an existing ID
        oDVHead.setId(1L);
        ODVHeadDTO oDVHeadDTO = oDVHeadMapper.toDto(oDVHead);

        // An entity with an existing ID cannot be created, so this API call must fail
        restODVHeadMockMvc.perform(post("/api/odv-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDVHeadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ODVHead in the database
        List<ODVHead> oDVHeadList = oDVHeadRepository.findAll();
        assertThat(oDVHeadList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllODVHeads() throws Exception {
        // Initialize the database
        oDVHeadRepository.saveAndFlush(oDVHead);

        // Get all the oDVHeadList
        restODVHeadMockMvc.perform(get("/api/odv-heads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oDVHead.getId().intValue())))
            .andExpect(jsonPath("$.[*].nrFt").value(hasItem(DEFAULT_NR_FT)))
            .andExpect(jsonPath("$.[*].dataFattura").value(hasItem(DEFAULT_DATA_FATTURA.toString())))
            .andExpect(jsonPath("$.[*].totaleFt").value(hasItem(DEFAULT_TOTALE_FT)));
    }
    
    @Test
    @Transactional
    public void getODVHead() throws Exception {
        // Initialize the database
        oDVHeadRepository.saveAndFlush(oDVHead);

        // Get the oDVHead
        restODVHeadMockMvc.perform(get("/api/odv-heads/{id}", oDVHead.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(oDVHead.getId().intValue()))
            .andExpect(jsonPath("$.nrFt").value(DEFAULT_NR_FT))
            .andExpect(jsonPath("$.dataFattura").value(DEFAULT_DATA_FATTURA.toString()))
            .andExpect(jsonPath("$.totaleFt").value(DEFAULT_TOTALE_FT));
    }

    @Test
    @Transactional
    public void getNonExistingODVHead() throws Exception {
        // Get the oDVHead
        restODVHeadMockMvc.perform(get("/api/odv-heads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateODVHead() throws Exception {
        // Initialize the database
        oDVHeadRepository.saveAndFlush(oDVHead);

        int databaseSizeBeforeUpdate = oDVHeadRepository.findAll().size();

        // Update the oDVHead
        ODVHead updatedODVHead = oDVHeadRepository.findById(oDVHead.getId()).get();
        // Disconnect from session so that the updates on updatedODVHead are not directly saved in db
        em.detach(updatedODVHead);
        updatedODVHead
            .nrFt(UPDATED_NR_FT)
            .dataFattura(UPDATED_DATA_FATTURA)
            .totaleFt(UPDATED_TOTALE_FT);
        ODVHeadDTO oDVHeadDTO = oDVHeadMapper.toDto(updatedODVHead);

        restODVHeadMockMvc.perform(put("/api/odv-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDVHeadDTO)))
            .andExpect(status().isOk());

        // Validate the ODVHead in the database
        List<ODVHead> oDVHeadList = oDVHeadRepository.findAll();
        assertThat(oDVHeadList).hasSize(databaseSizeBeforeUpdate);
        ODVHead testODVHead = oDVHeadList.get(oDVHeadList.size() - 1);
        assertThat(testODVHead.getNrFt()).isEqualTo(UPDATED_NR_FT);
        assertThat(testODVHead.getDataFattura()).isEqualTo(UPDATED_DATA_FATTURA);
        assertThat(testODVHead.getTotaleFt()).isEqualTo(UPDATED_TOTALE_FT);
    }

    @Test
    @Transactional
    public void updateNonExistingODVHead() throws Exception {
        int databaseSizeBeforeUpdate = oDVHeadRepository.findAll().size();

        // Create the ODVHead
        ODVHeadDTO oDVHeadDTO = oDVHeadMapper.toDto(oDVHead);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restODVHeadMockMvc.perform(put("/api/odv-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDVHeadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ODVHead in the database
        List<ODVHead> oDVHeadList = oDVHeadRepository.findAll();
        assertThat(oDVHeadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteODVHead() throws Exception {
        // Initialize the database
        oDVHeadRepository.saveAndFlush(oDVHead);

        int databaseSizeBeforeDelete = oDVHeadRepository.findAll().size();

        // Delete the oDVHead
        restODVHeadMockMvc.perform(delete("/api/odv-heads/{id}", oDVHead.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ODVHead> oDVHeadList = oDVHeadRepository.findAll();
        assertThat(oDVHeadList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ODVHead.class);
        ODVHead oDVHead1 = new ODVHead();
        oDVHead1.setId(1L);
        ODVHead oDVHead2 = new ODVHead();
        oDVHead2.setId(oDVHead1.getId());
        assertThat(oDVHead1).isEqualTo(oDVHead2);
        oDVHead2.setId(2L);
        assertThat(oDVHead1).isNotEqualTo(oDVHead2);
        oDVHead1.setId(null);
        assertThat(oDVHead1).isNotEqualTo(oDVHead2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ODVHeadDTO.class);
        ODVHeadDTO oDVHeadDTO1 = new ODVHeadDTO();
        oDVHeadDTO1.setId(1L);
        ODVHeadDTO oDVHeadDTO2 = new ODVHeadDTO();
        assertThat(oDVHeadDTO1).isNotEqualTo(oDVHeadDTO2);
        oDVHeadDTO2.setId(oDVHeadDTO1.getId());
        assertThat(oDVHeadDTO1).isEqualTo(oDVHeadDTO2);
        oDVHeadDTO2.setId(2L);
        assertThat(oDVHeadDTO1).isNotEqualTo(oDVHeadDTO2);
        oDVHeadDTO1.setId(null);
        assertThat(oDVHeadDTO1).isNotEqualTo(oDVHeadDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(oDVHeadMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(oDVHeadMapper.fromId(null)).isNull();
    }
}
