package com.bumptech.glide.samples.svg.cache;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.engine.DataCacheKey;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.samples.svg.model.ForeverEqualsKey;
import com.bumptech.glide.signature.ObjectKey;
import com.mrcd.utils.app.AppContextHolder;

import java.io.File;

public class GlideDiskCache {

    public static final String CACHE_DIR_NAME = "glide_cache";

    private GlideDiskCache() {

    }

    public static DiskCache getDiskCache() {
        return DiskLruCacheWrapper.get(getCacheDir(), 1024);
    }

    @Nullable
    public static File findCacheFile(Uri uri) {
        if (null == uri) {
            return null;
        }
        return getDiskCache().get(new DataCacheKey(new ObjectKey(uri), ForeverEqualsKey.obtain()));
    }


    public static File getCacheDir() {
        Context context = AppContextHolder.getAppContext();
        File externalCacheDir = context.getExternalCacheDir();
        File dir = null != externalCacheDir ? externalCacheDir : context.getCacheDir();
        return new File(dir, CACHE_DIR_NAME);
    }
}
