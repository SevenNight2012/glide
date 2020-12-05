package com.bumptech.glide.samples.svg.svga.option;

import android.content.Context;

import com.opensource.svgaplayer.SVGADynamicEntity;

/**
 *
 */
public interface SvgaReplaceElement {

    /**
     * 在子线程中设置替换的元素
     *
     * @param applicationContext applicationContext对象
     * @param entity             元素包装对象，所有的OptionItem都可以向SVGADynamicEntity中设置不同的替换元素
     */
    void replaceElementInBackgroundThread(Context applicationContext, SVGADynamicEntity entity) throws Exception;
}
