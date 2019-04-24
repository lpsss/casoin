package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.CasoinApp;

import io.github.jhipster.application.domain.ODVRow;
import io.github.jhipster.application.repository.ODVRowRepository;
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
 * Test class for the ODVRowResource REST controller.
 *
 * @see ODVRowResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CasoinApp.class)
public class ODVRowResourceIntTest {

    private static final Integer DEFAULT_QTA = 1;
    private static final Integer UPDATED_QTA = 2;

    private static final BigDecimal DEFAULT_COSTO = new BigDecimal(1);
    private static final BigDecimal UPDATED_COSTO = new BigDecimal(2);

    @Autowired
    private ODVRowRepository oDVRowRepository;

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

    private MockMvc restODVRowMockMvc;

    private ODVRow oDVRow;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ODVRowResource oDVRowResource = new ODVRowResource(oDVRowRepository);
        this.restODVRowMockMvc = MockMvcBuilders.standaloneSetup(oDVRowResource)
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
    public static ODVRow createEntity(EntityManager em) {
        ODVRow oDVRow = new ODVRow()
            .qta(DEFAULT_QTA)
            .costo(DEFAULT_COSTO);
        return oDVRow;
    }

    @Before
    public void initTest() {
        oDVRow = createEntity(em);
    }

    @Test
    @Transactional
    public void createODVRow() throws Exception {
        int databaseSizeBeforeCreate = oDVRowRepository.findAll().size();

        // Create the ODVRow
        restODVRowMockMvc.perform(post("/api/odv-rows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDVRow)))
            .andExpect(status().isCreated());

        // Validate the ODVRow in the database
        List<ODVRow> oDVRowList = oDVRowRepository.findAll();
        assertThat(oDVRowList).hasSize(databaseSizeBeforeCreate + 1);
        ODVRow testODVRow = oDVRowList.get(oDVRowList.size() - 1);
        assertThat(testODVRow.getQta()).isEqualTo(DEFAULT_QTA);
        assertThat(testODVRow.getCosto()).isEqualTo(DEFAULT_COSTO);
    }

    @Test
    @Transactional
    public void createODVRowWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = oDVRowRepository.findAll().size();

        // Create the ODVRow with an existing ID
        oDVRow.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restODVRowMockMvc.perform(post("/api/odv-rows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDVRow)))
            .andExpect(status().isBadRequest());

        // Validate the ODVRow in the database
        List<ODVRow> oDVRowList = oDVRowRepository.findAll();
        assertThat(oDVRowList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllODVRows() throws Exception {
        // Initialize the database
        oDVRowRepository.saveAndFlush(oDVRow);

        // Get all the oDVRowList
        restODVRowMockMvc.perform(get("/api/odv-rows?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oDVRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].qta").value(hasItem(DEFAULT_QTA)))
            .andExpect(jsonPath("$.[*].costo").value(hasItem(DEFAULT_COSTO.intValue())));
    }
    
    @Test
    @Transactional
    public void getODVRow() throws Exception {
        // Initialize the database
        oDVRowRepository.saveAndFlush(oDVRow);

        // Get the oDVRow
        restODVRowMockMvc.perform(get("/api/odv-rows/{id}", oDVRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(oDVRow.getId().intValue()))
            .andExpect(jsonPath("$.qta").value(DEFAULT_QTA))
            .andExpect(jsonPath("$.costo").value(DEFAULT_COSTO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingODVRow() throws Exception {
        // Get the oDVRow
        restODVRowMockMvc.perform(get("/api/odv-rows/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateODVRow() throws Exception {
        // Initialize the database
        oDVRowRepository.saveAndFlush(oDVRow);

        int databaseSizeBeforeUpdate = oDVRowRepository.findAll().size();

        // Update the oDVRow
        ODVRow updatedODVRow = oDVRowRepository.findById(oDVRow.getId()).get();
        // Disconnect from session so that the updates on updatedODVRow are not directly saved in db
        em.detach(updatedODVRow);
        updatedODVRow
            .qta(UPDATED_QTA)
            .costo(UPDATED_COSTO);

        restODVRowMockMvc.perform(put("/api/odv-rows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedODVRow)))
            .andExpect(status().isOk());

        // Validate the ODVRow in the database
        List<ODVRow> oDVRowList = oDVRowRepository.findAll();
        assertThat(oDVRowList).hasSize(databaseSizeBeforeUpdate);
        ODVRow testODVRow = oDVRowList.get(oDVRowList.size() - 1);
        assertThat(testODVRow.getQta()).isEqualTo(UPDATED_QTA);
        assertThat(testODVRow.getCosto()).isEqualTo(UPDATED_COSTO);
    }

    @Test
    @Transactional
    public void updateNonExistingODVRow() throws Exception {
        int databaseSizeBeforeUpdate = oDVRowRepository.findAll().size();

        // Create the ODVRow

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restODVRowMockMvc.perform(put("/api/odv-rows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oDVRow)))
            .andExpect(status().isBadRequest());

        // Validate the ODVRow in the database
        List<ODVRow> oDVRowList = oDVRowRepository.findAll();
        assertThat(oDVRowList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteODVRow() throws Exception {
        // Initialize the database
        oDVRowRepository.saveAndFlush(oDVRow);

        int databaseSizeBeforeDelete = oDVRowRepository.findAll().size();

        // Delete the oDVRow
        restODVRowMockMvc.perform(delete("/api/odv-rows/{id}", oDVRow.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ODVRow> oDVRowList = oDVRowRepository.findAll();
        assertThat(oDVRowList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ODVRow.class);
        ODVRow oDVRow1 = new ODVRow();
        oDVRow1.setId(1L);
        ODVRow oDVRow2 = new ODVRow();
        oDVRow2.setId(oDVRow1.getId());
        assertThat(oDVRow1).isEqualTo(oDVRow2);
        oDVRow2.setId(2L);
        assertThat(oDVRow1).isNotEqualTo(oDVRow2);
        oDVRow1.setId(null);
        assertThat(oDVRow1).isNotEqualTo(oDVRow2);
    }
}
