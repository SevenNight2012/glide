package com.bumptech.glide.samples.svg.svga;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;

public class SvgaListener implements RequestListener<SVGADrawable> {
    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<SVGADrawable> target, boolean isFirstResource) {
        return false;
    }

    @Override
    public boolean onResourceReady(SVGADrawable resource, Object model, Target<SVGADrawable> target, DataSource dataSource, boolean isFirstResource) {
        if (target instanceof ImageViewTarget) {
            ImageViewTarget<SVGADrawable> imageViewTarget = (ImageViewTarget<SVGADrawable>) target;
            ImageView targetView = imageViewTarget.getView();
            if (targetView instanceof SVGAImageView) {
                SVGAImageView svgaImageView = (SVGAImageView) targetView;
                svgaImageView.setImageDrawable(resource);
                svgaImageView.startAnimation();
            }
        }
        return false;
    }
}
