package com.bumptech.glide.samples.svg.svga.option;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;

import com.opensource.svgaplayer.SVGADynamicEntity;

/**
 * svga替换文案的配置项
 */
public class SvgaTextElement implements SvgaReplaceElement {

    private final String mText;
    private final TextPaint mTextPaint;
    private final String mReplaceKey;

    public SvgaTextElement(String text, TextPaint textPaint, String replaceKey) {
        mText = text;
        mTextPaint = textPaint;
        mReplaceKey = replaceKey;
        if (mTextPaint != null) {
            mTextPaint.setAntiAlias(true);//抗锯齿
        }
    }

    @Override
    public void replaceElementInBackgroundThread(Context applicationContext, SVGADynamicEntity entity) {
        if (TextUtils.isEmpty(mReplaceKey)) {
            return;
        }
        entity.setDynamicText(mText, mTextPaint, mReplaceKey);
    }
}
