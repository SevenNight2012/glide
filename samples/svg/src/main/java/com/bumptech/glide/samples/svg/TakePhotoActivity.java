package com.bumptech.glide.samples.svg;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.samples.svg.bitmap.ResourceToData;
import com.bumptech.glide.samples.svg.cache.GlideDiskCache;
import com.bumptech.glide.samples.svg.model.ForeverEqualsKey;
import com.bumptech.glide.samples.svg.model.UriWrap;
import com.xxc.dev.permission.Sudo;

import java.io.File;

public class TakePhotoActivity extends AppCompatActivity {

    public static final String TAG = "TakePhotoActivity";

    private static final int GET_CONTENT_PHOTO = 100;
    private static final String[] PERMISSIONS = new String[]{//
            Manifest.permission.READ_EXTERNAL_STORAGE,//读取
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//存储
            Manifest.permission.CAMERA//相机
    };
    private ImageView mPreviewImage;
    private Uri mMediaUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        mPreviewImage = findViewById(R.id.photo_preview);
        Button takePhotoBtn = findViewById(R.id.take_photo);
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转去拍照
                Sudo.getInstance()
                    .prepare()
                    .setPermissions(PERMISSIONS)
                    .setGranted(permissions -> takePhoto())
                    .request(TakePhotoActivity.this);
            }
        });
        mPreviewImage.setOnClickListener(v -> {
            printKey();
        });
    }

    private void printKey() {
//        Uri uri = Uri.parse("content://media/external/images" + "/media/15478");
        File cacheFile = GlideDiskCache.findCacheFile(mMediaUri);
        String path = null == cacheFile ? "cache do not exist" : cacheFile.getAbsolutePath();
        long size = null == cacheFile ? -1 : cacheFile.length();
        Log.d(TAG, "cache file: " + path + "  file size is: " + size);
//        Glide.with(this).asFile().load(new UriWrap(uri)).preload();
    }

    private void takePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GET_CONTENT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_CONTENT_PHOTO && resultCode == RESULT_OK) {
            mMediaUri = data.getData();
            String uriString = mMediaUri.toString();
            Log.d(TAG, "onActivityResult: " + uriString);
            Glide.with(TakePhotoActivity.this)
                 .load(mMediaUri)
                 .signature(ForeverEqualsKey.obtain())
                 .diskCacheStrategy(DiskCacheStrategy.ALL)
                 .set(ResourceToData.OPTION, ResourceToData.CAN_CACHE)
                 .into(mPreviewImage);
        }
    }
}
