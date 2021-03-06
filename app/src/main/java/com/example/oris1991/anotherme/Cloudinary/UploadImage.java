package com.example.oris1991.anotherme.Cloudinary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Itzik on 30/05/2016.
 */
public class UploadImage {

    Cloudinary cloudinary;
    DownloadImage dd;
    private final static Cloudinary instance = new Cloudinary();

    public UploadImage() {
        cloudinary = new Cloudinary("cloudinary://351742125169825:y3l-NLJcziTM7xMCUrhQH7jjPL0@dqfossdgc");

    }

    public static Cloudinary getInstance() {
        return instance;
    }

    public void saveImage(final Bitmap imageBitmap, final String imageName) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    byte[] bitmapdata = bos.toByteArray();
                    ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
                    String name = imageName.substring(0, imageName.lastIndexOf("."));
                    Log.d("name", "----------------------------------------" + name);
                    Map res = cloudinary.uploader().upload(bs, ObjectUtils.asMap("public_id", name));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }

    public Bitmap loadImage(String imageName) {
        URL url = null;
        try {
            url = new URL(cloudinary.url().generate(imageName));
            Log.d("TAG", "load image from url" + url);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return bmp;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("TAG", "url" + url);

        return null;
    }
}


