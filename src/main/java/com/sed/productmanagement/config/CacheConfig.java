package com.sed.productmanagement.config;


import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {

    @Value("${cache.ttl}")
    private int cacheTTL;

    @Value("${cache.max.entries}")
    private int cacheMaxEntries;

    @Bean("inMemoryCacheManager")
    public CacheManager inMemoryCacheManager() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setShared(true);
        factoryBean.afterPropertiesSet();

        net.sf.ehcache.CacheManager cacheManager = factoryBean.getObject();
        if (cacheManager == null) {
            throw new IllegalStateException("cache manager must not be null");
        }

        PersistenceConfiguration persistenceConfiguration = new PersistenceConfiguration();
        persistenceConfiguration.strategy(PersistenceConfiguration.Strategy.NONE);

        CacheConfiguration product = new CacheConfiguration().persistence(persistenceConfiguration)
                .timeToLiveSeconds(cacheTTL).maxEntriesLocalHeap(cacheMaxEntries).copyOnRead(true).copyOnWrite(true).name("product");
        product.validateCompleteConfiguration();
        cacheManager.addCacheIfAbsent(new Cache(product));

        CacheConfiguration commentLite = new CacheConfiguration().persistence(persistenceConfiguration)
                .timeToLiveSeconds(cacheTTL).maxEntriesLocalHeap(cacheMaxEntries).copyOnRead(false)
                .copyOnWrite(false).name("commentLite");
        commentLite.validateCompleteConfiguration();
        cacheManager.addCacheIfAbsent(new Cache(commentLite));

        CacheConfiguration comment = new CacheConfiguration().persistence(persistenceConfiguration)
                .timeToLiveSeconds(900).maxEntriesLocalHeap(10).copyOnRead(false)
                .copyOnWrite(false).name("comment");
        comment.validateCompleteConfiguration();
        cacheManager.addCacheIfAbsent(new Cache(comment));


        return new EhCacheCacheManager(cacheManager);
    }
}

