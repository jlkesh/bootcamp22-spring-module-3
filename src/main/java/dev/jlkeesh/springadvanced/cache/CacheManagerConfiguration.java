package dev.jlkeesh.springadvanced.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnMissingBean(value = CacheManager.class)
public class CacheManagerConfiguration {

    @Bean
    @Primary
    public CacheManager cacheManager(@Value("${spring.cache.basepath}") String basePath) {
        return new CacheManagerImpl( basePath);
    }
}
