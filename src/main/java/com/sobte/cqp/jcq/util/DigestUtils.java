package com.sobte.cqp.jcq.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Sobte on 2018/5/31.<br>
 * Time: 2018/5/31 15:12<br>
 * Email: i@sobte.me<br>
 * 一些常用的数据摘要
 *
 * @author Sobte
 */
public class DigestUtils {

    /**
     * 获取字节流数据的MD5摘要
     *
     * @param inputStream 字节流
     * @return MD5
     * @throws IOException IO异常
     */
    public static String md5Hex(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        String result = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                md5.update(bytes, 0, len);
            }
            result = new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        return result;
    }

    /**
     * 获取字节数组的MD5摘要
     *
     * @param bytes 字节数组
     * @return MD5
     */
    public static String md5Hex(byte[] bytes) {
        String result = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            result = new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

}
