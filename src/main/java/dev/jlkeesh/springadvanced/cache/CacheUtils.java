package dev.jlkeesh.springadvanced.cache;

public class CacheUtils {

    public static String getCacheName(Object o) {
        Class<?> clazz = o.getClass();
        CacheIt cacheIt = clazz.getAnnotation(CacheIt.class);
        if (cacheIt == null)
            throw new RuntimeException("Class [%s] Not Annotated With [%s] Annotation"
                    .formatted(clazz.getName(), CacheIt.class.getName()));
        return cacheIt.name();
    }

    public static int getCacheExpiry(Object o) {
        Class<?> clazz = o.getClass();
        CacheIt cacheIt = clazz.getAnnotation(CacheIt.class);
        if (cacheIt == null)
            throw new RuntimeException("Class [%s] Not Annotated With [%s] Annotation"
                    .formatted(clazz.getName(), CacheIt.class.getName()));
        return cacheIt.expiresIn();
    }
}
