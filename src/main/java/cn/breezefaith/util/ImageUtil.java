package cn.breezefaith.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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


    /**
     * 将网络图片进行Base64位编码
     *
     * @param imageUrl
     *            图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String encodeImgageToBase64(String imageUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            URL url=new URL(imageUrl);
            BufferedImage bufferedImage = ImageIO.read(url);
            outputStream = new ByteArrayOutputStream();
            String imgType=imageUrl.substring(imageUrl.lastIndexOf(".")+1);
            ImageIO.write(bufferedImage, imgType, outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }

    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     *
     * @param base64String
     *            base64编码的图片信息
     * @return
     */
    public static byte[] decodeBase64ToImage(String base64String) {
        try {
            byte[] decoderBytes = decoder.decodeBuffer(base64String);
            return decoderBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将目标url的图片转化为byte数组
     */
    public static byte[] encodeImageToBytes(String imageUrl){
        ByteArrayOutputStream outputStream = null;
        try {
            URL url=new URL(imageUrl);
            BufferedImage bufferedImage = ImageIO.read(url);
            outputStream = new ByteArrayOutputStream();
            String imgType=imageUrl.substring(imageUrl.lastIndexOf(".")+1);
            ImageIO.write(bufferedImage, imgType, outputStream);
            return outputStream.toByteArray();// 返回字节数组
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return new byte[1];
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[1];
        } finally {

        }

    }
}
