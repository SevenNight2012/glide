package com.bumptech.glide.samples.svg.svga;

import com.bumptech.glide.load.engine.ActiveResourceFactory;
import com.bumptech.glide.load.engine.ActiveResources;

/**
 * 一级内存缓存的构造工厂
 */
public class SvgaActiveResourceFactory implements ActiveResourceFactory {
    @Override
    public ActiveResources createResource(boolean isActiveResourceRetentionAllowed) {
        return new SvgaActiveResource(isActiveResourceRetentionAllowed);
    }
}
