package com.bumptech.glide.samples.svg.cache;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.Key;

import java.security.MessageDigest;

public class KeyWrap implements Key {
    public static final String TAG = "DataCacheKey";

    private final Key sourceKey;
    private final Key signature;

    public KeyWrap(Key sourceKey, Key signature) {
        this.sourceKey = sourceKey;
        this.signature = signature;
    }

    Key getSourceKey() {
        return sourceKey;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof KeyWrap) {
            KeyWrap other = (KeyWrap) o;
            return sourceKey.equals(other.sourceKey) && signature.equals(other.signature);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = sourceKey.hashCode();
        result = 31 * result + signature.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String string = "DataCacheKey{" + "sourceKey=" + sourceKey + ", signature=" + signature + '}';
        Log.d(TAG, "toString: " + string);
        return string;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        sourceKey.updateDiskCacheKey(messageDigest);
        signature.updateDiskCacheKey(messageDigest);
    }

}
