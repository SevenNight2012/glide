package com.bumptech.glide.samples.svg.bitmap;

import com.bumptech.glide.load.Option;

/**
 * resource转data缓存
 */
public class ResourceToData {

    public static final String TAG = ResourceToData.class.getName();

    public static Option<String> OPTION = Option.memory(TAG, "");

    public static final String CAN_CACHE = "can_cache";
    public static Option<String> CACHE_RESOURCE_AS_DATA = Option.memory(TAG, CAN_CACHE);


    private ResourceToData() {

    }

}
