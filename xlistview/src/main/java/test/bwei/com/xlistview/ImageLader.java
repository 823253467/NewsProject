package test.bwei.com.xlistview;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by MK on 2017/9/10.
 */
public class ImageLader extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);

        ImageLoader.getInstance().init(configuration);
    }
}
