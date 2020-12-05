package com.bumptech.glide.samples.svg.model;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.mrcd.utils.app.AppContextHolder;
import com.mrcd.utils.io.FileUtils;

public class UriWrap {

    public static final String TAG = "UriWrap";

    public final Uri mUri;
    private String mPath;

    public UriWrap(@NonNull Uri uri) {
        mUri = uri;
        parseUri(uri);
    }

    private void parseUri(Uri uri) {
        mPath = FileUtils.getRealPathFromUri(AppContextHolder.getAppContext(), uri);
        Log.d(TAG, "parseUri: " + mPath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UriWrap uriWrap = (UriWrap) o;

        String thisUriString = mPath;
        String otherUriString = uriWrap.mPath;
        return TextUtils.equals(thisUriString, otherUriString);
    }

    @Override
    public int hashCode() {
        return mUri != null ? mPath.hashCode() : 0;
    }

    @Override
    public String toString() {
        return mPath;
    }
}
