package com.kxky.demo.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by kxky on 2017/12/15.
 */

public class LoadImage {
    private static volatile LoadImage instance;
    public static Md5FileNameGenerator md5FileNameGenerator = new Md5FileNameGenerator();

    private LoadImage() {

    }

    public static LoadImage getInstance() {
        if (instance == null) {
            synchronized (LoadImage.class) {
                instance = new LoadImage();
            }
        }
        return instance;
    }

    public void init(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "ACMED_IM/image");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(1)//线程池内加载的数量
//                .memoryCacheExtraOptions(240, 320)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCache(new WeakMemoryCache())
                // .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(md5FileNameGenerator)
                // 将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCache(new UnlimitedDiskCache(cacheDir, null, md5FileNameGenerator))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                // .writeDebugLogs() // Remove for release app
                .build();//开始构建
        ImageLoader.getInstance().init(config);
    }

    public static void loadcircleAvatar(String avatarUrl, ImageView imageView) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .considerExifParams(true)                          //启用EXIF和JPEG图像格式
                .displayer(new CircleBitmapDisplayer())            //设置圆形图片
                .build();

        ImageLoader.getInstance().displayImage(avatarUrl, imageView, options);
    }

    public static void loadAvatar5(String avatarUrl, ImageView imageView) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .considerExifParams(true)                          //启用EXIF和JPEG图像格式
                .displayer(new RoundedBitmapDisplayer(5))         //设置显示风格这里是圆角矩形
                .build();

        ImageLoader.getInstance().displayImage(avatarUrl, imageView, options);
    }

    public static void loadImage(String avatarUrl, ImageView imageView) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .considerExifParams(true)//启用EXIF和JPEG图像格式
                .build();

        ImageLoader.getInstance().displayImage(avatarUrl, imageView, options);
    }
    public static void loadImage(String avatarUrl, ImageView imageView, boolean cacheInMemory, boolean cacheOnDisk, ImageLoadingListener loadingListener){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(cacheInMemory)//启用内存缓存
                .cacheOnDisk(cacheOnDisk)//启用外存缓存
                .considerExifParams(true)//启用EXIF和JPEG图像格式
                .imageScaleType(ImageScaleType.NONE)// 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.ARGB_8888)// 设置图片的解码类型//
                .build();
        if(loadingListener == null) {
            ImageLoader.getInstance().displayImage(avatarUrl, imageView, options);
        }else {
            ImageLoader.getInstance().displayImage(avatarUrl, imageView, options,loadingListener);
        }
    }
    public static void cacelTask(ImageView imageView){
        ImageLoader.getInstance().cancelDisplayTask(imageView);
    }
    public static String getRealImagePath(String imgUrl) {
        return ImageLoader.getInstance().getDiskCache().get(imgUrl).getAbsolutePath();
    }

    public static void clearCach(){
        ImageLoader.getInstance().clearDiskCache();
        ImageLoader.getInstance().clearMemoryCache();
    }
}
