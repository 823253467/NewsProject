package test.bwei.com.zhoukaotest02;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by MK on 2017/9/9.
 */
public class ImageLoder extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);

        ImageLoader.getInstance().init(configuration);
    }
}
