package com.bumptech.glide.samples.svg.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.UriLoader;
import com.bumptech.glide.signature.ObjectKey;

import java.io.InputStream;

public class UriWrapModelLoader implements ModelLoader<UriWrap, InputStream> {

    private final UriLoader.LocalUriFetcherFactory<InputStream> mLocalUriFetcherFactory;

    public UriWrapModelLoader(UriLoader.LocalUriFetcherFactory<InputStream> localUriFetcherFactory) {
        mLocalUriFetcherFactory = localUriFetcherFactory;
    }

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(@NonNull UriWrap uriWrap, int width, int height, @NonNull Options options) {
        return new LoadData<>(new ObjectKey(uriWrap), mLocalUriFetcherFactory.build(uriWrap.mUri));
    }

    @Override
    public boolean handles(@NonNull UriWrap uriWrap) {
        //所有UriWrap格式的数据都支持
        return true;
    }
}
