package com.bumptech.glide.samples.svg.svga.option;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.FutureTarget;
import com.opensource.svgaplayer.SVGADynamicEntity;

/**
 * svga 替换图片的配置项
 */
public class SvgaImageElement implements SvgaReplaceElement {

    private final String mReplaceKey;

    private Bitmap mBitmap;
    private String mUrl;
    private CompatSize mSize;
    private BitmapTransformation mTransformation;

    public SvgaImageElement(Bitmap bitmap, String replaceKey) {
        mBitmap = bitmap;
        mReplaceKey = replaceKey;
    }

    public SvgaImageElement(String replaceKey, String url, CompatSize size) {
        this(replaceKey, url, size, null);
    }

    public SvgaImageElement(String replaceKey, String url, CompatSize size, BitmapTransformation transformation) {
        mReplaceKey = replaceKey;
        mUrl = url;
        mSize = size;
        mTransformation = transformation;
    }

    @Override
    public void replaceElementInBackgroundThread(Context applicationContext, SVGADynamicEntity entity) throws Exception {
        if (TextUtils.isEmpty(mReplaceKey)) {
            return;
        }
        if (null != mBitmap) {
            entity.setDynamicImage(mBitmap, mReplaceKey);
        } else {
            loadBitmapFromGlide(applicationContext, entity);
        }
    }

    private void loadBitmapFromGlide(Context context, SVGADynamicEntity entity) throws Exception {
        if (TextUtils.isEmpty(mUrl)) {
            return;
        }
        RequestBuilder<Bitmap> builder = Glide.with(context).asBitmap().load(mUrl);
        if (mTransformation != null) {
            builder = builder.transform(mTransformation);
        }
        FutureTarget<Bitmap> submit = builder.submit(mSize.getWidth(), mSize.getHeight());
        Bitmap bitmap = submit.get();
        if (bitmap != null) {
            entity.setDynamicImage(bitmap, mReplaceKey);
        }
    }
}
