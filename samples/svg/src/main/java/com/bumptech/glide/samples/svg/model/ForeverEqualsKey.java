package com.bumptech.glide.samples.svg.model;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.Key;

import java.security.MessageDigest;

public class ForeverEqualsKey implements Key {

    private static final ForeverEqualsKey INSTANCE = new ForeverEqualsKey();

    @NonNull
    public static ForeverEqualsKey obtain() {
        return INSTANCE;
    }

    /**
     * 占位用，没有实际意义
     */
    private final String mPlaceHolder = "ForeverEqualsKey";

    private ForeverEqualsKey() {

    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        // do nothing
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForeverEqualsKey that = (ForeverEqualsKey) o;

        return mPlaceHolder != null ? mPlaceHolder.equals(that.mPlaceHolder) : that.mPlaceHolder == null;
    }

    @Override
    public int hashCode() {
        return mPlaceHolder.hashCode();
    }

    @Override
    public String toString() {
        return mPlaceHolder;
    }
}
