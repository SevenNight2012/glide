package com.bumptech.glide.samples.svg.svga.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * IOUtils
 */
public class IOUtils {

    private IOUtils() {

    }

    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
