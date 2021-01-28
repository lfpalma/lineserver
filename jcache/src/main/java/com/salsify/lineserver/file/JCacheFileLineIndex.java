package com.salsify.lineserver.file;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URI;

public class JCacheFileLineIndex<E> implements FileLineIndex<E> {
    private final Cache<Long, E> fileLineIndexCache;

    public JCacheFileLineIndex()
            throws FileLineIndexException {
        try {
            CachingProvider cachingProvider = Caching.getCachingProvider();
            CacheManager manager = cachingProvider.getCacheManager(
                    getCacheConfigurationURI(),
                    getClass().getClassLoader()
            );
            this.fileLineIndexCache = manager.getCache("fileLineIndexCache");
        }
        catch (Exception e) {
            throw new FileLineIndexException("Could not create a jcache based file line index", e);
        }
    }

    private URI getCacheConfigurationURI()
            throws Exception {
        return getClass()
                .getClassLoader()
                .getResource("jsr107-config.xml")
                .toURI();
    }

    @Override
    public void put(long index, E entry) {
        fileLineIndexCache.put(index, entry);
    }

    @Override
    public E get(long index) {
        return fileLineIndexCache.get(index);
    }
}
