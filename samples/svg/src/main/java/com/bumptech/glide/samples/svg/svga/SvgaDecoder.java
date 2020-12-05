package com.bumptech.glide.samples.svg.svga;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.samples.svg.svga.utils.IOUtils;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.opensource.svgaplayer.proto.MovieEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;

public class SvgaDecoder implements ResourceDecoder<InputStream, SVGAVideoEntity> {

    public static final String TAG = "SvgaDecoder";

    private Context mContext;
    private File mCacheDir;

    public SvgaDecoder(Context context) {
        mContext = context.getApplicationContext();
        mCacheDir = new File(context.getExternalCacheDir(), "Svga/");
        if (!mCacheDir.exists()) {
            boolean mkdirs = mCacheDir.mkdirs();
            Log.d(TAG, "Svga make cache dir : " + mkdirs);
        }
    }

    @Override
    public boolean handles(@NonNull InputStream source, @NonNull Options options) throws IOException {
        return true;
    }

    @Nullable
    @Override
    public Resource<SVGAVideoEntity> decode(@NonNull InputStream source, int width, int height, @NonNull Options options) throws IOException {
        byte[] readAsBytes = readAsBytes(source);
        if (readAsBytes != null) {
            byte[] inflate = inflate(readAsBytes);
            if (null == inflate) {
                throw new IOException("inflate failure");
            }
            SVGAVideoEntity entity = new SVGAVideoEntity(MovieEntity.ADAPTER.decode(inflate), mCacheDir);
            return new SimpleResource<>(entity);
        } else {
            throw new IOException("Can not read bytes from InputStream when decode svga");
        }
    }


    private byte[] readAsBytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] byteArray = new byte[2048];
        int readFlag;
        try {
            while ((readFlag = inputStream.read(byteArray, 0, 2048)) > 0) {
                byteArrayOutputStream.write(byteArray, 0, readFlag);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            IOUtils.close(byteArrayOutputStream);
        }
        return null;
    }

    private byte[] inflate(byte[] byteArray) {
        ByteArrayOutputStream inflatedOutputStream = new ByteArrayOutputStream();
        try {
            Inflater inflater = new Inflater();
            inflater.setInput(byteArray, 0, byteArray.length);
            byte[] inflatedBytes = new byte[2048];

            while (true) {
                int count = inflater.inflate(inflatedBytes, 0, 2048);
                if (count <= 0) {
                    break;
                } else {
                    inflatedOutputStream.write(inflatedBytes, 0, count);
                }
            }
            inflater.end();
            return inflatedOutputStream.toByteArray();
        } catch (Throwable e) {
            IOUtils.close(inflatedOutputStream);
        }
        return null;
    }
}
