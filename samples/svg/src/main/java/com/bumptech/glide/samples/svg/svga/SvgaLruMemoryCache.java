package com.bumptech.glide.samples.svg.svga;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.opensource.svgaplayer.SVGADrawable;

/**
 * 专用于svga的二级内存缓存
 */
public class SvgaLruMemoryCache extends LruResourceCache {
    /**
     * Constructor for LruResourceCache.
     *
     * @param size The maximum size in bytes the in memory cache can use.
     */
    public SvgaLruMemoryCache(long size) {
        super(size);
    }

    @Nullable
    @Override
    public synchronized Resource<?> get(@NonNull Key key) {
        Resource<?> resource = super.get(key);
        if (null == resource) {
            return null;
        }
        Object res = resource.get();
        if (res instanceof SVGADrawable) {
            SVGADrawable svgaDrawable = (SVGADrawable) res;
            if (svgaDrawable.getCleared()) {
                remove(key);
                return null;
            }
        }
        return resource;
    }

    @Nullable
    @Override
    public synchronized Resource<?> remove(@NonNull Key key) {
        Resource<?> resource = super.remove(key);
        if (null == resource) {
            return null;
        }
        Object res = resource.get();
        if (res instanceof SVGADrawable) {
            SVGADrawable svgaDrawable = (SVGADrawable) res;
            if (svgaDrawable.getCleared()) {
                return null;
            }
        }
        return resource;
    }
}
