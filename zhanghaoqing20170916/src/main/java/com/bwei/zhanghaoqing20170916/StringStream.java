package com.bwei.zhanghaoqing20170916;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by MK on 2017/9/14.
 */
public class StringStream {

    static String streamToString(InputStream inputStream, String charset) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,charset);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s = null;
            StringBuilder builder = new StringBuilder();
            while ((s = bufferedReader.readLine()) != null){
                builder.append(s);
            }

            bufferedReader.close();
            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }


}
