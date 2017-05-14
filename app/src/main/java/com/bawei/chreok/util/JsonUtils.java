package com.bawei.chreok.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/5/14.
 */
public class JsonUtils {

    public static String jsonUtils(InputStream inputStream) throws IOException {
        int len=0;
        byte[] bytes=new byte[1024];
        ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
        while ((len=inputStream.read(bytes))!=-1){
            arrayOutputStream.write(bytes,0,len);
        }
        String s = arrayOutputStream.toString();
        inputStream.close();
        arrayOutputStream.close();

        return s;
    }

}
