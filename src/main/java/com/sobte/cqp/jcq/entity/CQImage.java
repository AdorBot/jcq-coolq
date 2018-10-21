package com.sobte.cqp.jcq.entity;

import com.sobte.cqp.jcq.util.DigestUtils;
import com.sobte.cqp.jcq.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private String name;
    private int width;
    private int height;
    private long size;
    private String url;
    private Date addTime;
    private boolean load;

    /**
     * 根据本地文件加载图片信息,默认加载图片大小,不加载大小,请使用 {@link #CQImage(File, boolean) 加载图片}
     *
     * @param file 图片文件
     * @throws IOException IO异常
     * @see #CQImage(File, boolean) 加载图片
     */
    public CQImage(File file) throws IOException {
        this(file, true);
    }

    /**
     * 根据本地文件加载图片信息
     *
     * @param file 图片文件
     * @param load 是否加载图片大小,加载将耗时解析图片信息
     * @throws IOException IO异常
     */
    public CQImage(File file, boolean load) throws IOException {
        this.url = file.toURI().toString();
        InputStream inputStream = new FileInputStream(file);
        this.size = file.length();
        this.md5 = DigestUtils.md5Hex(inputStream).toUpperCase();
        this.name = file.getName();
        if (!this.name.endsWith(".cqimg"))
            this.name = md5;
        if (load) loadSize(new FileInputStream(file));
        this.addTime = new Date(file.lastModified());
        inputStream.close();
    }

    /**
     * 根据网络图片加载图片信息,网络图片默认不加载图片大小,加载图片大小,请在 {@link #download(File, String, Map, byte[], boolean) download} 时加载大小
     *
     * @param url 网络图片地址
     * @throws IOException IO异常
     * @see #download(File, String, Map, byte[], boolean) 下并加载图片大小
     */
    public CQImage(String url) throws IOException {
        this.url = url;
        URLConnection connection = new URL(url).openConnection();
        InputStream inputStream = connection.getInputStream();
        this.size = connection.getContentLength();
        this.md5 = DigestUtils.md5Hex(inputStream).toUpperCase();
        this.name = md5;
        this.addTime = new Date();
        inputStream.close();
    }

    /**
     * 根据配置文件加载
     *
     * @param iniFile 配置文件
     */
    public CQImage(IniFile iniFile) {
        this.md5 = iniFile.getProfileString("image", "md5");
        this.width = Integer.parseInt(iniFile.getProfileString("image", "width"));
        this.height = Integer.parseInt(iniFile.getProfileString("image", "height"));
        this.size = Long.parseLong(iniFile.getProfileString("image", "size"));
        this.url = iniFile.getProfileString("image", "url");
        long time = Long.parseLong(iniFile.getProfileString("image", "addtime"));
        this.addTime = new Date(time * 1000L);
        this.load = true;
    }

    /**
     * 重新加载图片宽高数据,也可以用于检测是否是图片<br>
     * 如果是网络图片,此方法将以GET方式加载图片,其他方式及参数可以在 {@link #download(File, String, Map, byte[], boolean) download} 时加载
     *
     * @throws IOException IO异常
     */
    public void loadSize() throws IOException {
        loadSize(new URL(url).openConnection().getInputStream());
    }

    /**
     * 重新加载图片宽高数据,也可以用于检测是否是图片<br>
     * 如果是网络图片,此方法将以GET方式加载图片,其他方式及参数可以在 {@link #download(File, String, Map, byte[], boolean) download} 时加载
     *
     * @param inputStream 图片字节流
     * @throws IOException IO异常
     */
    private void loadSize(InputStream inputStream) throws IOException {
        BufferedImage image = ImageIO.read(inputStream);
        if (image == null)
            throw new NullPointerException("图片解析失败，请提供正确的图片文件");
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.load = true;
    }

    /**
     * 下载图片置本地,如果是网络图片则采用GET方式下载
     *
     * @param path 图片保存路径
     * @param name 图片文件名称
     * @return 图片文件的file对象
     * @throws IOException IO异常
     */
    public File download(String path, String name) throws IOException {
        File file = new File(path);
        if (file.isDirectory() || file.mkdirs())
            return download(new File(file, name));
        throw new FileNotFoundException(StringUtils.stringConcat(file.getAbsolutePath(), "目录不存在"));
    }

    /**
     * 下载图片置本地,如果是网络图片则采用GET方式下载
     *
     * @param path 图片保存路径
     * @return 图片文件的file对象
     * @throws IOException IO异常
     */
    public File download(String path) throws IOException {
        return download(new File(path));
    }

    /**
     * 下载图片置本地,如果是网络图片则采用GET方式下载
     *
     * @param file 图片保存的文件
     * @return 图片文件的file对象
     * @throws IOException IO异常
     */
    public File download(File file) throws IOException {
        return downloadUseGet(file, null);
    }

    /**
     * 下载图片置本地,如果是网络图片则采用GET方式下载
     *
     * @param file              图片保存的文件
     * @param requestProperties 请求头信息
     * @return 图片文件的file对象
     * @throws IOException IO异常
     */
    public File downloadUseGet(File file, Map<String, List<String>> requestProperties) throws IOException {
        return download(file, "GET", requestProperties, null, false);
    }

    /**
     * 下载图片置本地,如果是网络图片则采用POST方式下载
     *
     * @param file              图片保存的文件
     * @param requestProperties 请求头信息
     * @param data              请求的数据
     * @return 图片文件的file对象
     * @throws IOException IO异常
     */
    public File downloadUsePost(File file, Map<String, List<String>> requestProperties, byte[] data) throws IOException {
        return download(file, "POST", requestProperties, data, false);
    }

    /**
     * 下载图片置本地
     *
     * @param file              图片保存的文件
     * @param method            请求方式,本地文件此参数无效
     * @param requestProperties 请求头信息,本地文件此参数无效
     * @param data              请求的数据,本地文件此参数无效
     * @param load              是否加载图片大小
     * @return 图片文件的file对象
     * @throws IOException IO异常
     */
    public File download(File file, String method, Map<String, List<String>> requestProperties, byte[] data, boolean load) throws IOException {
        download(new FileOutputStream(file), method, requestProperties, data, load);
        return file;
    }

    /**
     * 下载图片置输出流中,更高级的写法请自行继承并重写本方法
     *
     * @param outputStream      图片保存的输出流
     * @param method            请求方式,本地文件此参数无效
     * @param requestProperties 请求头信息,本地文件此参数无效
     * @param data              请求的数据,本地文件此参数无效
     * @param load              是否加载图片大小
     * @throws IOException IO异常
     */
    protected void download(OutputStream outputStream, String method, Map<String, List<String>> requestProperties, byte[] data, boolean load) throws IOException {
        URL url = new URL(this.url);
        URLConnection connection = url.openConnection();
        BufferedInputStream inputStream = null;
        try {
            if (connection instanceof HttpURLConnection) {
                HttpURLConnection urlConnection = (HttpURLConnection) connection;
                if (method != null)
                    urlConnection.setRequestMethod(method.toUpperCase());
                if (requestProperties != null) {
                    for (String key : requestProperties.keySet()) {
                        List<String> properties = requestProperties.get(key);
                        for (String value : properties) {
                            urlConnection.addRequestProperty(key, value);
                        }
                    }
                }
                urlConnection.setConnectTimeout(3500);// 设置超时为3.5秒
                if (data != null) {
                    urlConnection.setDoOutput(true);
                    OutputStream out = connection.getOutputStream();
                    out.write(data);
                    out.flush();
                    out.close();
                }
                int resCode = urlConnection.getResponseCode();
                if (resCode >= 300 && resCode < 400) {
                    String redirect = connection.getHeaderField("Location");
                    if (!StringUtils.isTrimEmpty(redirect)) {
                        String oldUrl = this.url;
                        this.url = redirect;
                        download(outputStream, method, requestProperties, data, load);
                        this.url = oldUrl;
                        return;
                    } else
                        throw new IOException("重定向请求不包含请求地址，返回码：" + urlConnection.getResponseCode());
                }
                if (resCode >= 300 || resCode > 200)
                    throw new IOException("请求发送失败，返回码：" + urlConnection.getResponseCode());
            }
            inputStream = new BufferedInputStream(connection.getInputStream());
            outputStream = new BufferedOutputStream(outputStream);
            ByteArrayOutputStream output = new ByteArrayOutputStream();// 零时存储区，用于加载图片大小
            byte[] bytes = new byte[2048];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                if (load) output.write(bytes, 0, len);
            }
            outputStream.flush();
            if (load) loadSize(new ByteArrayInputStream(output.toByteArray()));
        } finally {
            if (outputStream != null)
                outputStream.close();
            if (inputStream != null)
                inputStream.close();
        }
    }

    /**
     * 下载图片置本地,如果是网络图片则采用GET方式下载
     *
     * @return 图片文件的file对象
     * @throws IOException IO异常
     */
    public byte[] getBytes() throws IOException {
        return getBytesUseGet(null);
    }

    /**
     * 下载图片置本地,如果是网络图片则采用GET方式下载
     *
     * @param requestProperties 请求头信息
     * @return 图片文件的file对象
     * @throws IOException IO异常
     */
    public byte[] getBytesUseGet(Map<String, List<String>> requestProperties) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        download(outputStream, "GET", requestProperties, null, false);
        return outputStream.toByteArray();
    }

    /**
     * 下载图片置本地,如果是网络图片则采用POST方式下载
     *
     * @param requestProperties 请求头信息
     * @param data              请求头信息
     * @return 图片文件的file对象
     * @throws IOException IO异常
     */
    public byte[] getBytesUsePost(Map<String, List<String>> requestProperties, byte[] data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        download(outputStream, "POST", requestProperties, data, false);
        return outputStream.toByteArray();
    }

    public String getMd5() {
        return md5;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getSize() {
        return size;
    }

    public String getUrl() {
        return url;
    }

    public Date getAddTime() {
        return addTime;
    }

    public String getName() {
        return name;
    }

    /**
     * 检测是否已经加载过图片大小了
     *
     * @return 是否加载
     */
    public boolean isLoad() {
        return load;
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
