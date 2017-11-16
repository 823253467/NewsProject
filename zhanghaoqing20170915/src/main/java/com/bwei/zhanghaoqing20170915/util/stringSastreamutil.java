package com.bwei.zhanghaoqing20170915.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by MK on 2017/9/15.
 */
public class stringSastreamutil {
    public static String getstringread(InputStream inputStream, String s) {

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,s);

            StringBuilder stringBuilder = new StringBuilder();

            String string=null;
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((string=bufferedReader.readLine())!=null){
                stringBuilder.append(string);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
