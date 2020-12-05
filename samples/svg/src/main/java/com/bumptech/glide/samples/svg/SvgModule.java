package com.bumptech.glide.samples.svg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.PictureDrawable;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.samples.svg.bitmap.ResourceDataCacheDecoder;
import com.bumptech.glide.samples.svg.cache.GlideDiskCache;
import com.bumptech.glide.samples.svg.model.UriWrap;
import com.bumptech.glide.samples.svg.model.UriWrapModelLoadFactory;
import com.bumptech.glide.samples.svg.svga.SvgaActiveResourceFactory;
import com.bumptech.glide.samples.svg.svga.SvgaDecoder;
import com.bumptech.glide.samples.svg.svga.SvgaLruMemoryCache;
import com.bumptech.glide.samples.svg.svga.SvgaTranscoder;
import com.caverock.androidsvg.SVG;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.InputStream;

/**
 * Module for the SVG sample app.
 */
@GlideModule
public class SvgModule extends AppGlideModule {


  @Override
  public void applyOptions(@NonNull final Context context, @NonNull GlideBuilder builder) {
    super.applyOptions(context, builder);
    MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
    int diskCacheSize = DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE;
    DiskLruCacheFactory diskCacheFactory = new DiskLruCacheFactory(GlideDiskCache::getCacheDir, diskCacheSize);

    builder.setActiveResourceFactory(new SvgaActiveResourceFactory())
           .setMemorySizeCalculator(calculator)
           .setMemoryCache(new SvgaLruMemoryCache(calculator.getMemoryCacheSize()))
           .setDiskCache(diskCacheFactory);
  }

  @Override
  public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
    registry.prepend(Bitmap.class, new ResourceDataCacheDecoder(glide.getArrayPool()))
            .prepend(UriWrap.class, InputStream.class, new UriWrapModelLoadFactory(context.getContentResolver()))
            .register(SVG.class, PictureDrawable.class, new SvgDrawableTranscoder())
            .append(InputStream.class, SVG.class, new SvgDecoder())
            .register(SVGAVideoEntity.class, SVGADrawable.class, new SvgaTranscoder(context))
            .append(InputStream.class, SVGAVideoEntity.class, new SvgaDecoder(context));
  }

  // Disable manifest parsing to avoid adding similar modules twice.
  @Override
  public boolean isManifestParsingEnabled() {
    return false;
  }
}
