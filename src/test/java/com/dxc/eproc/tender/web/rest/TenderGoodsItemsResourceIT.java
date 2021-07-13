package com.dxc.eproc.tender.web.rest;

import static com.dxc.eproc.tender.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderGoodsItems;
import com.dxc.eproc.tender.repository.TenderGoodsItemsRepository;
import com.dxc.eproc.tender.service.dto.TenderGoodsItemsDTO;
import com.dxc.eproc.tender.service.mapper.TenderGoodsItemsMapper;
import java.math.BigDecimal;
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
 * Integration tests for the {@link TenderGoodsItemsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderGoodsItemsResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final Long DEFAULT_INDENT_ID = 1L;
    private static final Long UPDATED_INDENT_ID = 2L;

    private static final Long DEFAULT_CATEGORY_ITEM_ID = 1L;
    private static final Long UPDATED_CATEGORY_ITEM_ID = 2L;

    private static final Integer DEFAULT_ENTRY_ORDER = 1;
    private static final Integer UPDATED_ENTRY_ORDER = 2;

    private static final String DEFAULT_ITEM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_NET_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_NET_AMT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QUANTITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTITY = new BigDecimal(2);

    private static final String DEFAULT_SPECIFICATIONS = "AAAAAAAAAA";
    private static final String UPDATED_SPECIFICATIONS = "BBBBBBBBBB";

    private static final Integer DEFAULT_UOM_ID = 1;
    private static final Integer UPDATED_UOM_ID = 2;

    private static final String DEFAULT_UOM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UOM_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tender-goods-items";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderGoodsItemsRepository tenderGoodsItemsRepository;

    @Autowired
    private TenderGoodsItemsMapper tenderGoodsItemsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderGoodsItemsMockMvc;

    private TenderGoodsItems tenderGoodsItems;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderGoodsItems createEntity(EntityManager em) {
        TenderGoodsItems tenderGoodsItems = new TenderGoodsItems()
            .nitId(DEFAULT_NIT_ID)
            .indentId(DEFAULT_INDENT_ID)
            .categoryItemId(DEFAULT_CATEGORY_ITEM_ID)
            .entryOrder(DEFAULT_ENTRY_ORDER)
            .itemCode(DEFAULT_ITEM_CODE)
            .itemName(DEFAULT_ITEM_NAME)
            .netAmt(DEFAULT_NET_AMT)
            .price(DEFAULT_PRICE)
            .quantity(DEFAULT_QUANTITY)
            .specifications(DEFAULT_SPECIFICATIONS)
            .uomId(DEFAULT_UOM_ID)
            .uomName(DEFAULT_UOM_NAME);
        return tenderGoodsItems;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderGoodsItems createUpdatedEntity(EntityManager em) {
        TenderGoodsItems tenderGoodsItems = new TenderGoodsItems()
            .nitId(UPDATED_NIT_ID)
            .indentId(UPDATED_INDENT_ID)
            .categoryItemId(UPDATED_CATEGORY_ITEM_ID)
            .entryOrder(UPDATED_ENTRY_ORDER)
            .itemCode(UPDATED_ITEM_CODE)
            .itemName(UPDATED_ITEM_NAME)
            .netAmt(UPDATED_NET_AMT)
            .price(UPDATED_PRICE)
            .quantity(UPDATED_QUANTITY)
            .specifications(UPDATED_SPECIFICATIONS)
            .uomId(UPDATED_UOM_ID)
            .uomName(UPDATED_UOM_NAME);
        return tenderGoodsItems;
    }

    @BeforeEach
    public void initTest() {
        tenderGoodsItems = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderGoodsItems() throws Exception {
        int databaseSizeBeforeCreate = tenderGoodsItemsRepository.findAll().size();
        // Create the TenderGoodsItems
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);
        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderGoodsItems in the database
        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeCreate + 1);
        TenderGoodsItems testTenderGoodsItems = tenderGoodsItemsList.get(tenderGoodsItemsList.size() - 1);
        assertThat(testTenderGoodsItems.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderGoodsItems.getIndentId()).isEqualTo(DEFAULT_INDENT_ID);
        assertThat(testTenderGoodsItems.getCategoryItemId()).isEqualTo(DEFAULT_CATEGORY_ITEM_ID);
        assertThat(testTenderGoodsItems.getEntryOrder()).isEqualTo(DEFAULT_ENTRY_ORDER);
        assertThat(testTenderGoodsItems.getItemCode()).isEqualTo(DEFAULT_ITEM_CODE);
        assertThat(testTenderGoodsItems.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testTenderGoodsItems.getNetAmt()).isEqualByComparingTo(DEFAULT_NET_AMT);
        assertThat(testTenderGoodsItems.getPrice()).isEqualByComparingTo(DEFAULT_PRICE);
        assertThat(testTenderGoodsItems.getQuantity()).isEqualByComparingTo(DEFAULT_QUANTITY);
        assertThat(testTenderGoodsItems.getSpecifications()).isEqualTo(DEFAULT_SPECIFICATIONS);
        assertThat(testTenderGoodsItems.getUomId()).isEqualTo(DEFAULT_UOM_ID);
        assertThat(testTenderGoodsItems.getUomName()).isEqualTo(DEFAULT_UOM_NAME);
    }

    @Test
    @Transactional
    void createTenderGoodsItemsWithExistingId() throws Exception {
        // Create the TenderGoodsItems with an existing ID
        tenderGoodsItems.setId(1L);
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        int databaseSizeBeforeCreate = tenderGoodsItemsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderGoodsItems in the database
        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderGoodsItemsRepository.findAll().size();
        // set the field null
        tenderGoodsItems.setNitId(null);

        // Create the TenderGoodsItems, which fails.
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIndentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderGoodsItemsRepository.findAll().size();
        // set the field null
        tenderGoodsItems.setIndentId(null);

        // Create the TenderGoodsItems, which fails.
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCategoryItemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderGoodsItemsRepository.findAll().size();
        // set the field null
        tenderGoodsItems.setCategoryItemId(null);

        // Create the TenderGoodsItems, which fails.
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEntryOrderIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderGoodsItemsRepository.findAll().size();
        // set the field null
        tenderGoodsItems.setEntryOrder(null);

        // Create the TenderGoodsItems, which fails.
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkItemCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderGoodsItemsRepository.findAll().size();
        // set the field null
        tenderGoodsItems.setItemCode(null);

        // Create the TenderGoodsItems, which fails.
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkItemNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderGoodsItemsRepository.findAll().size();
        // set the field null
        tenderGoodsItems.setItemName(null);

        // Create the TenderGoodsItems, which fails.
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNetAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderGoodsItemsRepository.findAll().size();
        // set the field null
        tenderGoodsItems.setNetAmt(null);

        // Create the TenderGoodsItems, which fails.
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderGoodsItemsRepository.findAll().size();
        // set the field null
        tenderGoodsItems.setPrice(null);

        // Create the TenderGoodsItems, which fails.
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderGoodsItemsRepository.findAll().size();
        // set the field null
        tenderGoodsItems.setQuantity(null);

        // Create the TenderGoodsItems, which fails.
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSpecificationsIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderGoodsItemsRepository.findAll().size();
        // set the field null
        tenderGoodsItems.setSpecifications(null);

        // Create the TenderGoodsItems, which fails.
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUomIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderGoodsItemsRepository.findAll().size();
        // set the field null
        tenderGoodsItems.setUomId(null);

        // Create the TenderGoodsItems, which fails.
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUomNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderGoodsItemsRepository.findAll().size();
        // set the field null
        tenderGoodsItems.setUomName(null);

        // Create the TenderGoodsItems, which fails.
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderGoodsItems() throws Exception {
        // Initialize the database
        tenderGoodsItemsRepository.saveAndFlush(tenderGoodsItems);

        // Get all the tenderGoodsItemsList
        restTenderGoodsItemsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderGoodsItems.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].indentId").value(hasItem(DEFAULT_INDENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].categoryItemId").value(hasItem(DEFAULT_CATEGORY_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].entryOrder").value(hasItem(DEFAULT_ENTRY_ORDER)))
            .andExpect(jsonPath("$.[*].itemCode").value(hasItem(DEFAULT_ITEM_CODE)))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].netAmt").value(hasItem(sameNumber(DEFAULT_NET_AMT))))
            .andExpect(jsonPath("$.[*].price").value(hasItem(sameNumber(DEFAULT_PRICE))))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(sameNumber(DEFAULT_QUANTITY))))
            .andExpect(jsonPath("$.[*].specifications").value(hasItem(DEFAULT_SPECIFICATIONS)))
            .andExpect(jsonPath("$.[*].uomId").value(hasItem(DEFAULT_UOM_ID)))
            .andExpect(jsonPath("$.[*].uomName").value(hasItem(DEFAULT_UOM_NAME)));
    }

    @Test
    @Transactional
    void getTenderGoodsItems() throws Exception {
        // Initialize the database
        tenderGoodsItemsRepository.saveAndFlush(tenderGoodsItems);

        // Get the tenderGoodsItems
        restTenderGoodsItemsMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderGoodsItems.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderGoodsItems.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.indentId").value(DEFAULT_INDENT_ID.intValue()))
            .andExpect(jsonPath("$.categoryItemId").value(DEFAULT_CATEGORY_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.entryOrder").value(DEFAULT_ENTRY_ORDER))
            .andExpect(jsonPath("$.itemCode").value(DEFAULT_ITEM_CODE))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.netAmt").value(sameNumber(DEFAULT_NET_AMT)))
            .andExpect(jsonPath("$.price").value(sameNumber(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.quantity").value(sameNumber(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.specifications").value(DEFAULT_SPECIFICATIONS))
            .andExpect(jsonPath("$.uomId").value(DEFAULT_UOM_ID))
            .andExpect(jsonPath("$.uomName").value(DEFAULT_UOM_NAME));
    }

    @Test
    @Transactional
    void getNonExistingTenderGoodsItems() throws Exception {
        // Get the tenderGoodsItems
        restTenderGoodsItemsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderGoodsItems() throws Exception {
        // Initialize the database
        tenderGoodsItemsRepository.saveAndFlush(tenderGoodsItems);

        int databaseSizeBeforeUpdate = tenderGoodsItemsRepository.findAll().size();

        // Update the tenderGoodsItems
        TenderGoodsItems updatedTenderGoodsItems = tenderGoodsItemsRepository.findById(tenderGoodsItems.getId()).get();
        // Disconnect from session so that the updates on updatedTenderGoodsItems are not directly saved in db
        em.detach(updatedTenderGoodsItems);
        updatedTenderGoodsItems
            .nitId(UPDATED_NIT_ID)
            .indentId(UPDATED_INDENT_ID)
            .categoryItemId(UPDATED_CATEGORY_ITEM_ID)
            .entryOrder(UPDATED_ENTRY_ORDER)
            .itemCode(UPDATED_ITEM_CODE)
            .itemName(UPDATED_ITEM_NAME)
            .netAmt(UPDATED_NET_AMT)
            .price(UPDATED_PRICE)
            .quantity(UPDATED_QUANTITY)
            .specifications(UPDATED_SPECIFICATIONS)
            .uomId(UPDATED_UOM_ID)
            .uomName(UPDATED_UOM_NAME);
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(updatedTenderGoodsItems);

        restTenderGoodsItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderGoodsItemsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderGoodsItems in the database
        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeUpdate);
        TenderGoodsItems testTenderGoodsItems = tenderGoodsItemsList.get(tenderGoodsItemsList.size() - 1);
        assertThat(testTenderGoodsItems.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderGoodsItems.getIndentId()).isEqualTo(UPDATED_INDENT_ID);
        assertThat(testTenderGoodsItems.getCategoryItemId()).isEqualTo(UPDATED_CATEGORY_ITEM_ID);
        assertThat(testTenderGoodsItems.getEntryOrder()).isEqualTo(UPDATED_ENTRY_ORDER);
        assertThat(testTenderGoodsItems.getItemCode()).isEqualTo(UPDATED_ITEM_CODE);
        assertThat(testTenderGoodsItems.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testTenderGoodsItems.getNetAmt()).isEqualTo(UPDATED_NET_AMT);
        assertThat(testTenderGoodsItems.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testTenderGoodsItems.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testTenderGoodsItems.getSpecifications()).isEqualTo(UPDATED_SPECIFICATIONS);
        assertThat(testTenderGoodsItems.getUomId()).isEqualTo(UPDATED_UOM_ID);
        assertThat(testTenderGoodsItems.getUomName()).isEqualTo(UPDATED_UOM_NAME);
    }

    @Test
    @Transactional
    void putNonExistingTenderGoodsItems() throws Exception {
        int databaseSizeBeforeUpdate = tenderGoodsItemsRepository.findAll().size();
        tenderGoodsItems.setId(count.incrementAndGet());

        // Create the TenderGoodsItems
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderGoodsItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderGoodsItemsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderGoodsItems in the database
        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderGoodsItems() throws Exception {
        int databaseSizeBeforeUpdate = tenderGoodsItemsRepository.findAll().size();
        tenderGoodsItems.setId(count.incrementAndGet());

        // Create the TenderGoodsItems
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderGoodsItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderGoodsItems in the database
        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderGoodsItems() throws Exception {
        int databaseSizeBeforeUpdate = tenderGoodsItemsRepository.findAll().size();
        tenderGoodsItems.setId(count.incrementAndGet());

        // Create the TenderGoodsItems
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderGoodsItemsMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderGoodsItems in the database
        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderGoodsItemsWithPatch() throws Exception {
        // Initialize the database
        tenderGoodsItemsRepository.saveAndFlush(tenderGoodsItems);

        int databaseSizeBeforeUpdate = tenderGoodsItemsRepository.findAll().size();

        // Update the tenderGoodsItems using partial update
        TenderGoodsItems partialUpdatedTenderGoodsItems = new TenderGoodsItems();
        partialUpdatedTenderGoodsItems.setId(tenderGoodsItems.getId());

        partialUpdatedTenderGoodsItems
            .categoryItemId(UPDATED_CATEGORY_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .netAmt(UPDATED_NET_AMT)
            .quantity(UPDATED_QUANTITY);

        restTenderGoodsItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderGoodsItems.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderGoodsItems))
            )
            .andExpect(status().isOk());

        // Validate the TenderGoodsItems in the database
        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeUpdate);
        TenderGoodsItems testTenderGoodsItems = tenderGoodsItemsList.get(tenderGoodsItemsList.size() - 1);
        assertThat(testTenderGoodsItems.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderGoodsItems.getIndentId()).isEqualTo(DEFAULT_INDENT_ID);
        assertThat(testTenderGoodsItems.getCategoryItemId()).isEqualTo(UPDATED_CATEGORY_ITEM_ID);
        assertThat(testTenderGoodsItems.getEntryOrder()).isEqualTo(DEFAULT_ENTRY_ORDER);
        assertThat(testTenderGoodsItems.getItemCode()).isEqualTo(DEFAULT_ITEM_CODE);
        assertThat(testTenderGoodsItems.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testTenderGoodsItems.getNetAmt()).isEqualByComparingTo(UPDATED_NET_AMT);
        assertThat(testTenderGoodsItems.getPrice()).isEqualByComparingTo(DEFAULT_PRICE);
        assertThat(testTenderGoodsItems.getQuantity()).isEqualByComparingTo(UPDATED_QUANTITY);
        assertThat(testTenderGoodsItems.getSpecifications()).isEqualTo(DEFAULT_SPECIFICATIONS);
        assertThat(testTenderGoodsItems.getUomId()).isEqualTo(DEFAULT_UOM_ID);
        assertThat(testTenderGoodsItems.getUomName()).isEqualTo(DEFAULT_UOM_NAME);
    }

    @Test
    @Transactional
    void fullUpdateTenderGoodsItemsWithPatch() throws Exception {
        // Initialize the database
        tenderGoodsItemsRepository.saveAndFlush(tenderGoodsItems);

        int databaseSizeBeforeUpdate = tenderGoodsItemsRepository.findAll().size();

        // Update the tenderGoodsItems using partial update
        TenderGoodsItems partialUpdatedTenderGoodsItems = new TenderGoodsItems();
        partialUpdatedTenderGoodsItems.setId(tenderGoodsItems.getId());

        partialUpdatedTenderGoodsItems
            .nitId(UPDATED_NIT_ID)
            .indentId(UPDATED_INDENT_ID)
            .categoryItemId(UPDATED_CATEGORY_ITEM_ID)
            .entryOrder(UPDATED_ENTRY_ORDER)
            .itemCode(UPDATED_ITEM_CODE)
            .itemName(UPDATED_ITEM_NAME)
            .netAmt(UPDATED_NET_AMT)
            .price(UPDATED_PRICE)
            .quantity(UPDATED_QUANTITY)
            .specifications(UPDATED_SPECIFICATIONS)
            .uomId(UPDATED_UOM_ID)
            .uomName(UPDATED_UOM_NAME);

        restTenderGoodsItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderGoodsItems.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderGoodsItems))
            )
            .andExpect(status().isOk());

        // Validate the TenderGoodsItems in the database
        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeUpdate);
        TenderGoodsItems testTenderGoodsItems = tenderGoodsItemsList.get(tenderGoodsItemsList.size() - 1);
        assertThat(testTenderGoodsItems.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderGoodsItems.getIndentId()).isEqualTo(UPDATED_INDENT_ID);
        assertThat(testTenderGoodsItems.getCategoryItemId()).isEqualTo(UPDATED_CATEGORY_ITEM_ID);
        assertThat(testTenderGoodsItems.getEntryOrder()).isEqualTo(UPDATED_ENTRY_ORDER);
        assertThat(testTenderGoodsItems.getItemCode()).isEqualTo(UPDATED_ITEM_CODE);
        assertThat(testTenderGoodsItems.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testTenderGoodsItems.getNetAmt()).isEqualByComparingTo(UPDATED_NET_AMT);
        assertThat(testTenderGoodsItems.getPrice()).isEqualByComparingTo(UPDATED_PRICE);
        assertThat(testTenderGoodsItems.getQuantity()).isEqualByComparingTo(UPDATED_QUANTITY);
        assertThat(testTenderGoodsItems.getSpecifications()).isEqualTo(UPDATED_SPECIFICATIONS);
        assertThat(testTenderGoodsItems.getUomId()).isEqualTo(UPDATED_UOM_ID);
        assertThat(testTenderGoodsItems.getUomName()).isEqualTo(UPDATED_UOM_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingTenderGoodsItems() throws Exception {
        int databaseSizeBeforeUpdate = tenderGoodsItemsRepository.findAll().size();
        tenderGoodsItems.setId(count.incrementAndGet());

        // Create the TenderGoodsItems
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderGoodsItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderGoodsItemsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderGoodsItems in the database
        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderGoodsItems() throws Exception {
        int databaseSizeBeforeUpdate = tenderGoodsItemsRepository.findAll().size();
        tenderGoodsItems.setId(count.incrementAndGet());

        // Create the TenderGoodsItems
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderGoodsItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderGoodsItems in the database
        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderGoodsItems() throws Exception {
        int databaseSizeBeforeUpdate = tenderGoodsItemsRepository.findAll().size();
        tenderGoodsItems.setId(count.incrementAndGet());

        // Create the TenderGoodsItems
        TenderGoodsItemsDTO tenderGoodsItemsDTO = tenderGoodsItemsMapper.toDto(tenderGoodsItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderGoodsItemsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderGoodsItemsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderGoodsItems in the database
        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderGoodsItems() throws Exception {
        // Initialize the database
        tenderGoodsItemsRepository.saveAndFlush(tenderGoodsItems);

        int databaseSizeBeforeDelete = tenderGoodsItemsRepository.findAll().size();

        // Delete the tenderGoodsItems
        restTenderGoodsItemsMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderGoodsItems.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderGoodsItems> tenderGoodsItemsList = tenderGoodsItemsRepository.findAll();
        assertThat(tenderGoodsItemsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
