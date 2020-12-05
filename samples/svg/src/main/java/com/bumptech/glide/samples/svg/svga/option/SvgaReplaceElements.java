package com.bumptech.glide.samples.svg.svga.option;

import com.bumptech.glide.load.Option;

import java.util.ArrayList;
import java.util.List;

public class SvgaReplaceElements {

    public static final String KEY = SvgaReplaceElements.class.getName();
    private static final SvgaReplaceElements DEFAULT_OPTION = new SvgaReplaceElements();
    public static final Option<SvgaReplaceElements> OPTION = Option.memory(KEY, DEFAULT_OPTION);

    private final List<SvgaReplaceElement> mSvgaReplaceElements = new ArrayList<>();

    public SvgaReplaceElements addItem(SvgaReplaceElement item) {
        mSvgaReplaceElements.add(item);
        return this;
    }

    public void removeItem(SvgaReplaceElement item) {
        mSvgaReplaceElements.remove(item);
    }

    public void clearItems() {
        mSvgaReplaceElements.clear();
    }

    public boolean isEmpty() {
        return mSvgaReplaceElements.isEmpty();
    }

    public List<SvgaReplaceElement> getItemsShadow() {
        return new ArrayList<>(mSvgaReplaceElements);
    }

}
