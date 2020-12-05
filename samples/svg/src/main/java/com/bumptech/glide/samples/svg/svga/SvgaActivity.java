package com.bumptech.glide.samples.svg.svga;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.samples.svg.R;
import com.bumptech.glide.samples.svg.svga.option.CompatSize;
import com.bumptech.glide.samples.svg.svga.option.SvgaImageElement;
import com.bumptech.glide.samples.svg.svga.option.SvgaReplaceElements;
import com.opensource.svgaplayer.SVGADrawable;

public class SvgaActivity extends Activity {

    public static final String SVGA_URL = "http://static2.miniviapp.com/atlas_op_common_file/1587533052967322.svga";
    public static final String REPLACE_URL = "file:///android_asset/assignment_broadcast.svga";

    public static final String GIRL_AVATAR = "http://static2.miniviapp.com/image/2020-02-15/thumb_5e47bda3626ff30001f1f7ff";
    public static final String BOY_AVATAR = "http://static2.miniviapp.com/image/2019-09-17/thumb_5d80d728cf506a0001558792";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svga);
        ImageView svgaImage = findViewById(R.id.svga_image);

        CompatSize size = new CompatSize(120, 120);
        SvgaReplaceElements elements = new SvgaReplaceElements();
        SvgaImageElement profileAnchor = new SvgaImageElement("profile_anchor", GIRL_AVATAR, size, new CircleCrop());
        SvgaImageElement manAvatar = new SvgaImageElement("profile_man", BOY_AVATAR, size, new CircleCrop());
        elements.addItem(profileAnchor).addItem(manAvatar);
        Glide.with(getApplicationContext())
             .as(SVGADrawable.class)
             .error(R.drawable.image_error)
             .placeholder(R.drawable.image_loading)
             .load(REPLACE_URL)
             .listener(new SvgaListener())
             .set(SvgaReplaceElements.OPTION, elements)
             .into(svgaImage);
    }
}
