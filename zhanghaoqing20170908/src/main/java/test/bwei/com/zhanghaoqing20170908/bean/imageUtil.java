package test.bwei.com.zhanghaoqing20170908.bean;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

import test.bwei.com.zhanghaoqing20170908.ImageCuWei;

/**
 * Created by MK on 2017/9/8.
 */
public class imageUtil {
    ImageCuWei imageCuWei;
    public imageUtil(final String thumbnail_pic_s, final ImageView image01, final ImageCuWei imageCuWei) {
        this.imageCuWei=imageCuWei;
        AsyncTask<Void, Void, Bitmap> asyncTask = new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Void... params) {



                try {
                    SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());

                    HttpClient clinent = new DefaultHttpClient();

                    HttpGet httpget = new HttpGet(thumbnail_pic_s);
                    HttpResponse response = clinent.execute(httpget);

                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode==200){
                        InputStream inputStream = response.getEntity().getContent();

                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        return bitmap;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {

                //image01.setImageBitmap(bitmap);
                imageCuWei.Imageback(image01,bitmap);
            }
    };
        asyncTask.execute();
    }


}
