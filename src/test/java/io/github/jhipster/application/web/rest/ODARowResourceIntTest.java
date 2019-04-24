package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.CasoinApp;

import io.github.jhipster.application.domain.ODARow;
import io.github.jhipster.application.repository.ODARowRepository;
import io.github.jhipster.application.service.ODARowService;
import io.github.jhipster.application.service.dto.ODARowDTO;
import io.github.jhipster.application.service.mapper.ODARowMapper;
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
import java.math.BigDecimal;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ODARowResource REST controller.
 *
 * @see ODARowResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CasoinApp.class)
public class ODARowResourceIntTest {

    private static final Integer DEFAULT_QTA = 1;
    private static final Integer UPDATED_QTA = 2;

    private static final BigDecimal DEFAULT_COSTO = new BigDecimal(1);
    private static final BigDecimal UPDATED_COSTO = new BigDecimal(2);

    @Autowired
    private ODARowRepository oDARowRepository;

    @Autowired
    private ODARowMapper oDARowMapper;

    @Autowired
    private ODARowService oDARowService;

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

    private MockMvc restODARowMockMvc;

    private ODARow oDARow;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ODARowResource oDARowResource = new ODARowResource(oDARowService);
        this.restODARowMockMvc = MockMvcBuilders.standaloneSetup(oDARowResource)
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
    public static ODARow createEntity(EntityManager em) {
        ODARow oDARow = new ODARow()
            .qta(DEFAULT_QTA)
            .costo(DEFAULT_COSTO);
        return oDARow;
    }

    @Before
    public void initTest() {
        oDARow = createEntity(em);
    }

    @Test
    @Transactional
    public void createODARow() throws Exception {
        int databaseSizeBeforeCreate = oDARowRepository.findAll().size();

        // Create the ODARow
        ODARowDTO oDARowDTO = oDARowMapper.toDto(oDARow);
        restODARowMockMvc.perform(post("/api/oda-rows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDARowDTO)))
            .andExpect(status().isCreated());

        // Validate the ODARow in the database
        List<ODARow> oDARowList = oDARowRepository.findAll();
        assertThat(oDARowList).hasSize(databaseSizeBeforeCreate + 1);
        ODARow testODARow = oDARowList.get(oDARowList.size() - 1);
        assertThat(testODARow.getQta()).isEqualTo(DEFAULT_QTA);
        assertThat(testODARow.getCosto()).isEqualTo(DEFAULT_COSTO);
    }

    @Test
    @Transactional
    public void createODARowWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = oDARowRepository.findAll().size();

        // Create the ODARow with an existing ID
        oDARow.setId(1L);
        ODARowDTO oDARowDTO = oDARowMapper.toDto(oDARow);

        // An entity with an existing ID cannot be created, so this API call must fail
        restODARowMockMvc.perform(post("/api/oda-rows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDARowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ODARow in the database
        List<ODARow> oDARowList = oDARowRepository.findAll();
        assertThat(oDARowList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllODARows() throws Exception {
        // Initialize the database
        oDARowRepository.saveAndFlush(oDARow);

        // Get all the oDARowList
        restODARowMockMvc.perform(get("/api/oda-rows?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oDARow.getId().intValue())))
            .andExpect(jsonPath("$.[*].qta").value(hasItem(DEFAULT_QTA)))
            .andExpect(jsonPath("$.[*].costo").value(hasItem(DEFAULT_COSTO.intValue())));
    }
    
    @Test
    @Transactional
    public void getODARow() throws Exception {
        // Initialize the database
        oDARowRepository.saveAndFlush(oDARow);

        // Get the oDARow
        restODARowMockMvc.perform(get("/api/oda-rows/{id}", oDARow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(oDARow.getId().intValue()))
            .andExpect(jsonPath("$.qta").value(DEFAULT_QTA))
            .andExpect(jsonPath("$.costo").value(DEFAULT_COSTO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingODARow() throws Exception {
        // Get the oDARow
        restODARowMockMvc.perform(get("/api/oda-rows/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateODARow() throws Exception {
        // Initialize the database
        oDARowRepository.saveAndFlush(oDARow);

        int databaseSizeBeforeUpdate = oDARowRepository.findAll().size();

        // Update the oDARow
        ODARow updatedODARow = oDARowRepository.findById(oDARow.getId()).get();
        // Disconnect from session so that the updates on updatedODARow are not directly saved in db
        em.detach(updatedODARow);
        updatedODARow
            .qta(UPDATED_QTA)
            .costo(UPDATED_COSTO);
        ODARowDTO oDARowDTO = oDARowMapper.toDto(updatedODARow);

        restODARowMockMvc.perform(put("/api/oda-rows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDARowDTO)))
            .andExpect(status().isOk());

        // Validate the ODARow in the database
        List<ODARow> oDARowList = oDARowRepository.findAll();
        assertThat(oDARowList).hasSize(databaseSizeBeforeUpdate);
        ODARow testODARow = oDARowList.get(oDARowList.size() - 1);
        assertThat(testODARow.getQta()).isEqualTo(UPDATED_QTA);
        assertThat(testODARow.getCosto()).isEqualTo(UPDATED_COSTO);
    }

    @Test
    @Transactional
    public void updateNonExistingODARow() throws Exception {
        int databaseSizeBeforeUpdate = oDARowRepository.findAll().size();

        // Create the ODARow
        ODARowDTO oDARowDTO = oDARowMapper.toDto(oDARow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restODARowMockMvc.perform(put("/api/oda-rows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDARowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ODARow in the database
        List<ODARow> oDARowList = oDARowRepository.findAll();
        assertThat(oDARowList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteODARow() throws Exception {
        // Initialize the database
        oDARowRepository.saveAndFlush(oDARow);

        int databaseSizeBeforeDelete = oDARowRepository.findAll().size();

        // Delete the oDARow
        restODARowMockMvc.perform(delete("/api/oda-rows/{id}", oDARow.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ODARow> oDARowList = oDARowRepository.findAll();
        assertThat(oDARowList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ODARow.class);
        ODARow oDARow1 = new ODARow();
        oDARow1.setId(1L);
        ODARow oDARow2 = new ODARow();
        oDARow2.setId(oDARow1.getId());
        assertThat(oDARow1).isEqualTo(oDARow2);
        oDARow2.setId(2L);
        assertThat(oDARow1).isNotEqualTo(oDARow2);
        oDARow1.setId(null);
        assertThat(oDARow1).isNotEqualTo(oDARow2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ODARowDTO.class);
        ODARowDTO oDARowDTO1 = new ODARowDTO();
        oDARowDTO1.setId(1L);
        ODARowDTO oDARowDTO2 = new ODARowDTO();
        assertThat(oDARowDTO1).isNotEqualTo(oDARowDTO2);
        oDARowDTO2.setId(oDARowDTO1.getId());
        assertThat(oDARowDTO1).isEqualTo(oDARowDTO2);
        oDARowDTO2.setId(2L);
        assertThat(oDARowDTO1).isNotEqualTo(oDARowDTO2);
        oDARowDTO1.setId(null);
        assertThat(oDARowDTO1).isNotEqualTo(oDARowDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(oDARowMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(oDARowMapper.fromId(null)).isNull();
    }
}
