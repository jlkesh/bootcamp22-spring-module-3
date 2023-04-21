package dev.jlkeesh.springadvanced.cache;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

import static org.reflections.scanners.Scanners.TypesAnnotated;

@Slf4j
public class CacheManagerImpl implements CacheManager {
    private final String basePath;


    public CacheManagerImpl( String basePath) {
        this.basePath = basePath;
        this.init();
    }

    private void init() {
        Reflections reflections = new Reflections(new ConfigurationBuilder().forPackage(basePath).setScanners(TypesAnnotated));
        Set<Class<?>> classes = reflections.get(TypesAnnotated.get(CacheIt.class).asClass());
        classes.forEach(clazz -> {
            CacheIt cacheIt = clazz.getAnnotation(CacheIt.class);
            String name = cacheIt.name();
            int expiresIn = cacheIt.expiresIn();
            log.info("DEFAULT CACHE CREATED FOR BEAN : {}", clazz.getSimpleName());
            CACHES.put(name, new Cache<>(name, expiresIn));
        });
    }

    @Override
    public void register() {
    }
}
