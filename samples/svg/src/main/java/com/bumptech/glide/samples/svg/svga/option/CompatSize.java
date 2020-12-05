package com.bumptech.glide.samples.svg.svga.option;

/**
 * 尺寸类，系统的{@link android.util.Size}有api版本兼容问题，所以参照此类自己写一个，坑爹的版本兼容
 */
public final class CompatSize {

    private final int width;
    private final int height;

    public CompatSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static CompatSize parseSize(String string) {
        int sep_ix = string.indexOf('*');
        if (sep_ix < 0) {
            sep_ix = string.indexOf('x');
        }
        if (sep_ix < 0) {
            return new CompatSize(0, 0);
        }
        try {
            int width = Integer.parseInt(string.substring(0, sep_ix));
            int height = Integer.parseInt(string.substring(sep_ix + 1));
            return new CompatSize(width, height);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return new CompatSize(0, 0);
    }
}
