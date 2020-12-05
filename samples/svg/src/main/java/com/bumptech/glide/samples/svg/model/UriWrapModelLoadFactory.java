package com.bumptech.glide.samples.svg.model;

import android.content.ContentResolver;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.StreamLocalUriFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.model.UriLoader;

import java.io.InputStream;

public class UriWrapModelLoadFactory implements ModelLoaderFactory<UriWrap, InputStream>, UriLoader.LocalUriFetcherFactory<InputStream> {

    private final ContentResolver mContentResolver;

    public UriWrapModelLoadFactory(ContentResolver contentResolver) {
        mContentResolver = contentResolver;
    }

    @NonNull
    @Override
    public ModelLoader<UriWrap, InputStream> build(@NonNull MultiModelLoaderFactory multiFactory) {
        return new UriWrapModelLoader(this);
    }

    @Override
    public void teardown() {

    }

    @Override
    public DataFetcher<InputStream> build(Uri uri) {
        return new StreamLocalUriFetcher(mContentResolver, uri);
    }
}
