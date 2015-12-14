package effectmobi.com.zhihu.effectmobi.com.zhihu.application;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * User: Jesse Pinkman
 * Date: 2015-12-11
 * Time: 15:56
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getCacheDirectory(context);
        /* ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCache(new UnlimitedDiskCache(cacheDir)).writeDebugLogs()
                .build();*/
        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(config);
    }
}