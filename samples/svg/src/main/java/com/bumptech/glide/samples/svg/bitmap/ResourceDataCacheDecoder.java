package com.bumptech.glide.samples.svg.bitmap;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;

import java.io.File;

public class ResourceDataCacheDecoder extends BitmapEncoder {

    public ResourceDataCacheDecoder(@NonNull ArrayPool arrayPool) {
        super(arrayPool);
    }

    @Override
    public boolean encode(@NonNull Resource<Bitmap> resource, @NonNull File file, @NonNull Options options) {
        return super.encode(resource, file, options);
    }

    @NonNull
    @Override
    public EncodeStrategy getEncodeStrategy(@NonNull Options options) {
        String canCache = options.get(ResourceToData.OPTION);
        return ResourceToData.CAN_CACHE.equalsIgnoreCase(canCache) ? EncodeStrategy.SOURCE : super.getEncodeStrategy(options);
    }
}
