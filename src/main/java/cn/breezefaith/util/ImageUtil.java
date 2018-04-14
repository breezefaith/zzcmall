package cn.breezefaith.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageUtil {
    private static BASE64Decoder decoder=new BASE64Decoder();
    private static BASE64Encoder encoder=new BASE64Encoder();

    //将图片转化为二进制字符串
    public static String getBinaryString(String imgUrl){
        URL url= null;
        try {
            url = new URL(imgUrl);
            url.getFile().getBytes();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.19 Safari/537.36");
            connection.setConnectTimeout(5 * 1000);
            InputStream inputStream = connection.getInputStream();
            StringBuffer stringBuffer=new StringBuffer();
            byte[] bytes=new byte[1024];
            int length;
            while ((length=inputStream.read(bytes)) != -1){
                stringBuffer.append(encoder.encodeBuffer(bytes).trim());
            }
            return stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }   finally {

        }
    }

    //将二进制字符串转化为图片数组
    public static byte[] getBinaryBytes(String binaryString){
        byte[] bytes= null;
        try {
            bytes = decoder.decodeBuffer(binaryString);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
