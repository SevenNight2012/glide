package com.bumptech.glide.samples.svg.svga;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.samples.svg.svga.option.SvgaReplaceElement;
import com.bumptech.glide.samples.svg.svga.option.SvgaReplaceElements;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.util.List;

public class SvgaTranscoder implements ResourceTranscoder<SVGAVideoEntity, SVGADrawable> {

    private final Context mContext;

    public SvgaTranscoder(Context context) {
        mContext = context.getApplicationContext();
    }

    @Nullable
    @Override
    public Resource<SVGADrawable> transcode(@NonNull Resource<SVGAVideoEntity> toTranscode, @NonNull Options options) {
        SVGAVideoEntity svgaVideoEntity = toTranscode.get();
        SVGADrawable svgaDrawable = new SVGADrawable(svgaVideoEntity, instanceEntity(getSafeSvgaOption(options)));
        return new SimpleResource<>(svgaDrawable);
    }

    @NonNull
    private SVGADynamicEntity instanceEntity(SvgaReplaceElements svgaReplaceElements) {
        SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
        //不为空的时候就开始去加载那些需要替换的元素
        List<SvgaReplaceElement> optionItems = svgaReplaceElements.getItemsShadow();
        for (SvgaReplaceElement item : optionItems) {
            try {
                item.replaceElementInBackgroundThread(mContext, dynamicEntity);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return dynamicEntity;
    }

    @NonNull
    private SvgaReplaceElements getSafeSvgaOption(Options options) {
        SvgaReplaceElements svgaReplaceElements = options.get(SvgaReplaceElements.OPTION);
        return null == svgaReplaceElements ? new SvgaReplaceElements() : svgaReplaceElements;
    }
}
