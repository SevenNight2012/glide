package com.bumptech.glide.samples.svg.svga;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.ActiveResources;
import com.bumptech.glide.load.engine.EngineResource;
import com.opensource.svgaplayer.SVGADrawable;

/**
 * 专用于svga的一级内存缓存
 */
public class SvgaActiveResource extends ActiveResources {
    public SvgaActiveResource(boolean isActiveResourceRetentionAllowed) {
        super(isActiveResourceRetentionAllowed);
    }

    @Nullable
    @Override
    public synchronized EngineResource<?> get(Key key) {
        EngineResource<?> engineResource = super.get(key);
        if (engineResource != null) {
            Object resource = engineResource.get();
            if (resource instanceof SVGADrawable) {
                SVGADrawable drawable = (SVGADrawable) resource;
                if (drawable.getCleared()) {
                    return null;
                }
            }
        }
        return engineResource;
    }
}
