package com.dxc.eproc.tender.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.dxc.eproc.tender.domain.TenderSchedule.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderScheduleSample.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderScheduleSampleAddress.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderCriterionParameter.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderGoodsItems.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderScheduleGroupItems.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderScheduleGroup.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderCriterion.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.Criterion.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.CriterionDocument.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderCriterionDocument.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.ObjectStore.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderAddendum.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderCorrigendum.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderCorrigendumDetails.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderQuery.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderScrutinyCommittee.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderScrutinyMaster.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderQueryResponse.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.TenderScheduleTelephone.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.Telephone.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.SeriesTable.class.getName());
            createCache(cm, com.dxc.eproc.tender.domain.NoticeInvitingTender.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
