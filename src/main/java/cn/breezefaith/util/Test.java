package cn.breezefaith.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;


class A {
    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    public static void main(String[] args) {
        System.out.println(getImageBinary());
        base64StringToImage(getImageBinary());
    }

    /**
     * 将图片转换成二进制
     *
     * @return
     */
    static String getImageBinary() {
        File f = new File("e:/1.jpg");
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);  //经测试转换的图片是格式这里就什么格式，否则会失真
            byte[] bytes = baos.toByteArray();

            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换为图片
     *
     * @param base64String
     */
    static void base64StringToImage(String base64String) {
        try {
            byte[] bytes1 = decoder.decodeBuffer(base64String);

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File("e://QQ.jpg");// 可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

public class Test {
    public static void main(String[] args) {
//        try {
//            getPicture();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public static void getPicture() throws IOException {
        URL url=new URL("http://192.168.181.134/zzcmall/img/test.jpg");
        InputStream inputStream=url.openStream();
        byte[] bytes=new byte[inputStream.available()];
        StringBuffer stringBuffer=new StringBuffer();
        inputStream.read(bytes);
        for (byte b:bytes){
            stringBuffer.append(Integer.toBinaryString(b));
        }
        System.out.println(stringBuffer);
    }
}
