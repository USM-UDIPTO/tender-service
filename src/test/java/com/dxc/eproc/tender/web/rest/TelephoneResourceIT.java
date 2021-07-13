package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.Telephone;
import com.dxc.eproc.tender.repository.TelephoneRepository;
import com.dxc.eproc.tender.service.dto.TelephoneDTO;
import com.dxc.eproc.tender.service.mapper.TelephoneMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TelephoneResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TelephoneResourceIT {

    private static final Integer DEFAULT_COUNTRY_CODE = 1;
    private static final Integer UPDATED_COUNTRY_CODE = 2;

    private static final Integer DEFAULT_AREA_CODE = 1;
    private static final Integer UPDATED_AREA_CODE = 2;

    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MOB_OTP = "AAAAAAAAAA";
    private static final String UPDATED_MOB_OTP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MOB_OTPTS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MOB_OTPTS = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_MOB_OTP_EXPIRED = false;
    private static final Boolean UPDATED_MOB_OTP_EXPIRED = true;

    private static final String ENTITY_API_URL = "/api/telephones";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TelephoneRepository telephoneRepository;

    @Autowired
    private TelephoneMapper telephoneMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTelephoneMockMvc;

    private Telephone telephone;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Telephone createEntity(EntityManager em) {
        Telephone telephone = new Telephone()
            .countryCode(DEFAULT_COUNTRY_CODE)
            .areaCode(DEFAULT_AREA_CODE)
            .number(DEFAULT_NUMBER)
            .type(DEFAULT_TYPE)
            .mobOTP(DEFAULT_MOB_OTP)
            .mobOTPTS(DEFAULT_MOB_OTPTS)
            .mobOTPExpired(DEFAULT_MOB_OTP_EXPIRED);
        return telephone;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Telephone createUpdatedEntity(EntityManager em) {
        Telephone telephone = new Telephone()
            .countryCode(UPDATED_COUNTRY_CODE)
            .areaCode(UPDATED_AREA_CODE)
            .number(UPDATED_NUMBER)
            .type(UPDATED_TYPE)
            .mobOTP(UPDATED_MOB_OTP)
            .mobOTPTS(UPDATED_MOB_OTPTS)
            .mobOTPExpired(UPDATED_MOB_OTP_EXPIRED);
        return telephone;
    }

    @BeforeEach
    public void initTest() {
        telephone = createEntity(em);
    }

    @Test
    @Transactional
    void createTelephone() throws Exception {
        int databaseSizeBeforeCreate = telephoneRepository.findAll().size();
        // Create the Telephone
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);
        restTelephoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isCreated());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeCreate + 1);
        Telephone testTelephone = telephoneList.get(telephoneList.size() - 1);
        assertThat(testTelephone.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testTelephone.getAreaCode()).isEqualTo(DEFAULT_AREA_CODE);
        assertThat(testTelephone.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testTelephone.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testTelephone.getMobOTP()).isEqualTo(DEFAULT_MOB_OTP);
        assertThat(testTelephone.getMobOTPTS()).isEqualTo(DEFAULT_MOB_OTPTS);
        assertThat(testTelephone.getMobOTPExpired()).isEqualTo(DEFAULT_MOB_OTP_EXPIRED);
    }

    @Test
    @Transactional
    void createTelephoneWithExistingId() throws Exception {
        // Create the Telephone with an existing ID
        telephone.setId(1L);
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        int databaseSizeBeforeCreate = telephoneRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTelephoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCountryCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = telephoneRepository.findAll().size();
        // set the field null
        telephone.setCountryCode(null);

        // Create the Telephone, which fails.
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        restTelephoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isBadRequest());

        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAreaCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = telephoneRepository.findAll().size();
        // set the field null
        telephone.setAreaCode(null);

        // Create the Telephone, which fails.
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        restTelephoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isBadRequest());

        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = telephoneRepository.findAll().size();
        // set the field null
        telephone.setNumber(null);

        // Create the Telephone, which fails.
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        restTelephoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isBadRequest());

        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = telephoneRepository.findAll().size();
        // set the field null
        telephone.setType(null);

        // Create the Telephone, which fails.
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        restTelephoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isBadRequest());

        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMobOTPIsRequired() throws Exception {
        int databaseSizeBeforeTest = telephoneRepository.findAll().size();
        // set the field null
        telephone.setMobOTP(null);

        // Create the Telephone, which fails.
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        restTelephoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isBadRequest());

        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMobOTPTSIsRequired() throws Exception {
        int databaseSizeBeforeTest = telephoneRepository.findAll().size();
        // set the field null
        telephone.setMobOTPTS(null);

        // Create the Telephone, which fails.
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        restTelephoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isBadRequest());

        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMobOTPExpiredIsRequired() throws Exception {
        int databaseSizeBeforeTest = telephoneRepository.findAll().size();
        // set the field null
        telephone.setMobOTPExpired(null);

        // Create the Telephone, which fails.
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        restTelephoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isBadRequest());

        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTelephones() throws Exception {
        // Initialize the database
        telephoneRepository.saveAndFlush(telephone);

        // Get all the telephoneList
        restTelephoneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(telephone.getId().intValue())))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].areaCode").value(hasItem(DEFAULT_AREA_CODE)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].mobOTP").value(hasItem(DEFAULT_MOB_OTP)))
            .andExpect(jsonPath("$.[*].mobOTPTS").value(hasItem(DEFAULT_MOB_OTPTS.toString())))
            .andExpect(jsonPath("$.[*].mobOTPExpired").value(hasItem(DEFAULT_MOB_OTP_EXPIRED.booleanValue())));
    }

    @Test
    @Transactional
    void getTelephone() throws Exception {
        // Initialize the database
        telephoneRepository.saveAndFlush(telephone);

        // Get the telephone
        restTelephoneMockMvc
            .perform(get(ENTITY_API_URL_ID, telephone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(telephone.getId().intValue()))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE))
            .andExpect(jsonPath("$.areaCode").value(DEFAULT_AREA_CODE))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.mobOTP").value(DEFAULT_MOB_OTP))
            .andExpect(jsonPath("$.mobOTPTS").value(DEFAULT_MOB_OTPTS.toString()))
            .andExpect(jsonPath("$.mobOTPExpired").value(DEFAULT_MOB_OTP_EXPIRED.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTelephone() throws Exception {
        // Get the telephone
        restTelephoneMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTelephone() throws Exception {
        // Initialize the database
        telephoneRepository.saveAndFlush(telephone);

        int databaseSizeBeforeUpdate = telephoneRepository.findAll().size();

        // Update the telephone
        Telephone updatedTelephone = telephoneRepository.findById(telephone.getId()).get();
        // Disconnect from session so that the updates on updatedTelephone are not directly saved in db
        em.detach(updatedTelephone);
        updatedTelephone
            .countryCode(UPDATED_COUNTRY_CODE)
            .areaCode(UPDATED_AREA_CODE)
            .number(UPDATED_NUMBER)
            .type(UPDATED_TYPE)
            .mobOTP(UPDATED_MOB_OTP)
            .mobOTPTS(UPDATED_MOB_OTPTS)
            .mobOTPExpired(UPDATED_MOB_OTP_EXPIRED);
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(updatedTelephone);

        restTelephoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, telephoneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(telephoneDTO))
            )
            .andExpect(status().isOk());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeUpdate);
        Telephone testTelephone = telephoneList.get(telephoneList.size() - 1);
        assertThat(testTelephone.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testTelephone.getAreaCode()).isEqualTo(UPDATED_AREA_CODE);
        assertThat(testTelephone.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testTelephone.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTelephone.getMobOTP()).isEqualTo(UPDATED_MOB_OTP);
        assertThat(testTelephone.getMobOTPTS()).isEqualTo(UPDATED_MOB_OTPTS);
        assertThat(testTelephone.getMobOTPExpired()).isEqualTo(UPDATED_MOB_OTP_EXPIRED);
    }

    @Test
    @Transactional
    void putNonExistingTelephone() throws Exception {
        int databaseSizeBeforeUpdate = telephoneRepository.findAll().size();
        telephone.setId(count.incrementAndGet());

        // Create the Telephone
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTelephoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, telephoneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(telephoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTelephone() throws Exception {
        int databaseSizeBeforeUpdate = telephoneRepository.findAll().size();
        telephone.setId(count.incrementAndGet());

        // Create the Telephone
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTelephoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(telephoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTelephone() throws Exception {
        int databaseSizeBeforeUpdate = telephoneRepository.findAll().size();
        telephone.setId(count.incrementAndGet());

        // Create the Telephone
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTelephoneMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTelephoneWithPatch() throws Exception {
        // Initialize the database
        telephoneRepository.saveAndFlush(telephone);

        int databaseSizeBeforeUpdate = telephoneRepository.findAll().size();

        // Update the telephone using partial update
        Telephone partialUpdatedTelephone = new Telephone();
        partialUpdatedTelephone.setId(telephone.getId());

        partialUpdatedTelephone.mobOTP(UPDATED_MOB_OTP);

        restTelephoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTelephone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTelephone))
            )
            .andExpect(status().isOk());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeUpdate);
        Telephone testTelephone = telephoneList.get(telephoneList.size() - 1);
        assertThat(testTelephone.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testTelephone.getAreaCode()).isEqualTo(DEFAULT_AREA_CODE);
        assertThat(testTelephone.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testTelephone.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testTelephone.getMobOTP()).isEqualTo(UPDATED_MOB_OTP);
        assertThat(testTelephone.getMobOTPTS()).isEqualTo(DEFAULT_MOB_OTPTS);
        assertThat(testTelephone.getMobOTPExpired()).isEqualTo(DEFAULT_MOB_OTP_EXPIRED);
    }

    @Test
    @Transactional
    void fullUpdateTelephoneWithPatch() throws Exception {
        // Initialize the database
        telephoneRepository.saveAndFlush(telephone);

        int databaseSizeBeforeUpdate = telephoneRepository.findAll().size();

        // Update the telephone using partial update
        Telephone partialUpdatedTelephone = new Telephone();
        partialUpdatedTelephone.setId(telephone.getId());

        partialUpdatedTelephone
            .countryCode(UPDATED_COUNTRY_CODE)
            .areaCode(UPDATED_AREA_CODE)
            .number(UPDATED_NUMBER)
            .type(UPDATED_TYPE)
            .mobOTP(UPDATED_MOB_OTP)
            .mobOTPTS(UPDATED_MOB_OTPTS)
            .mobOTPExpired(UPDATED_MOB_OTP_EXPIRED);

        restTelephoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTelephone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTelephone))
            )
            .andExpect(status().isOk());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeUpdate);
        Telephone testTelephone = telephoneList.get(telephoneList.size() - 1);
        assertThat(testTelephone.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testTelephone.getAreaCode()).isEqualTo(UPDATED_AREA_CODE);
        assertThat(testTelephone.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testTelephone.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTelephone.getMobOTP()).isEqualTo(UPDATED_MOB_OTP);
        assertThat(testTelephone.getMobOTPTS()).isEqualTo(UPDATED_MOB_OTPTS);
        assertThat(testTelephone.getMobOTPExpired()).isEqualTo(UPDATED_MOB_OTP_EXPIRED);
    }

    @Test
    @Transactional
    void patchNonExistingTelephone() throws Exception {
        int databaseSizeBeforeUpdate = telephoneRepository.findAll().size();
        telephone.setId(count.incrementAndGet());

        // Create the Telephone
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTelephoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, telephoneDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(telephoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTelephone() throws Exception {
        int databaseSizeBeforeUpdate = telephoneRepository.findAll().size();
        telephone.setId(count.incrementAndGet());

        // Create the Telephone
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTelephoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(telephoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTelephone() throws Exception {
        int databaseSizeBeforeUpdate = telephoneRepository.findAll().size();
        telephone.setId(count.incrementAndGet());

        // Create the Telephone
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTelephoneMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(telephoneDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTelephone() throws Exception {
        // Initialize the database
        telephoneRepository.saveAndFlush(telephone);

        int databaseSizeBeforeDelete = telephoneRepository.findAll().size();

        // Delete the telephone
        restTelephoneMockMvc
            .perform(delete(ENTITY_API_URL_ID, telephone.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
