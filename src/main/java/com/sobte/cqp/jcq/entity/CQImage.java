package com.sobte.cqp.jcq.entity;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

/**
 * Created by Sobte on 2018/4/11.<br>
 * Time: 2018/4/11 17:57<br>
 * Email: i@sobte.me<br>
 * 酷Q图片类，用于获取图片信息和下载图片
 *
 * @author Sobte
 */
public class CQImage {

    private String md5;
    private int width;
    private int height;
    private int size;
    private String url;
    private Date addTime;

    public CQImage() {
    }

    public CQImage(String md5, int width, int height, int size, String url, Date addTime) {
        this.md5 = md5;
        this.width = width;
        this.height = height;
        this.size = size;
        this.url = url;
        this.addTime = addTime;
    }

    public CQImage(IniFile iniFile) {
        this.md5 = iniFile.getProfileString("image", "md5");
        this.width = Integer.parseInt(iniFile.getProfileString("image", "width"));
        this.height = Integer.parseInt(iniFile.getProfileString("image", "height"));
        this.size = Integer.parseInt(iniFile.getProfileString("image", "size"));
        this.url = iniFile.getProfileString("image", "url");
        long time = Long.parseLong(iniFile.getProfileString("image", "addtime"));
        this.addTime = new Date(time * 1000L);
    }

    /**
     * 下载图片置本地
     *
     * @param path 图片保存路径
     * @param name 图片文件名称
     * @return 图片文件的file对象
     * @throws IOException IO异常
     */
    public File download(String path, String name) throws IOException {
        URL url = new URL(this.url);
        URLConnection connection = url.openConnection();
        File file = new File(path, name);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = connection.getInputStream();
            outputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();
        } finally {
            if (outputStream != null)
                outputStream.close();
            if (inputStream != null)
                inputStream.close();
        }
        return file;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getAddtime() {
        return addTime;
    }

    public void setAddtime(Date addTime) {
        this.addTime = addTime;
    }

    public List<CQImage> toCQImage() {
        return null;
    }

    @Override
    public String toString() {
        return "CQImage{" +
                "md5='" + md5 + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", size=" + size +
                ", url='" + url + '\'' +
                ", addTime=" + addTime +
                '}';
    }

}
