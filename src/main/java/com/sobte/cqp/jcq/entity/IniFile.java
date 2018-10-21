package com.sobte.cqp.jcq.entity;


import com.sobte.cqp.jcq.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by Sobte on 2018/4/9.<br>
 * Time: 2018/4/9 19:40<br>
 * Email: i@sobte.me<br>
 * ini文件读取类，不建议用ini当数据库，存配置即可，文件一旦过大，读文件和修改保存效率极其的受影响
 *
 * @author Sobte
 */
public class IniFile extends Ini {

    /**
     * 编码
     */
    private String encodingName;

    /**
     * ini配置文件所在处
     */
    private File iniFile;

    /**
     * 初始化
     *
     * @param iniPath ini路径
     * @throws IOException IO异常
     */
    public IniFile(String iniPath) throws IOException {
        // 使用JDK默认编码
        init(new File(iniPath), Charset.defaultCharset().name());
    }

    /**
     * 初始化
     *
     * @param iniPath     ini路径
     * @param charsetName 编码名称
     * @throws IOException IO异常
     */
    public IniFile(String iniPath, String charsetName) throws IOException {
        init(new File(iniPath), charsetName);
    }

    /**
     * 初始化
     *
     * @param iniFile ini文件
     * @throws IOException IO异常
     */
    public IniFile(File iniFile) throws IOException {
        // 使用JDK默认编码
        init(iniFile, Charset.defaultCharset().name());
    }

    /**
     * 初始化
     *
     * @param iniFile     ini文件
     * @param charsetName 编码名称
     * @throws IOException IO异常
     */
    public IniFile(File iniFile, String charsetName) throws IOException {
        init(iniFile, charsetName);
    }

    /**
     * 初始并加载解析ini文件
     *
     * @param iniFile     ini文件
     * @param charsetName 编码
     */
    private void init(File iniFile, String charsetName) throws IOException {
        this.iniFile = iniFile;
        this.encodingName = charsetName;
        StringBuilder sb = new StringBuilder();
        InputStreamReader isr = null;
        try {
            if (!iniFile.isFile())
                return;
            isr = new InputStreamReader(new FileInputStream(iniFile), charsetName);
            int read, idx = 0;
            char line = StringUtils.lineSeparatorSingle();
            // 读取文件
            while ((read = isr.read()) != -1) {
                // 过滤每行开头空格
                char ch = (char) read;
                if (ch > ' ')
                    idx++;
                if (idx > 0) {
                    if (line == ch)
                        idx = 0;
                    sb.append(ch);
                }
            }
        } finally {
            // 关闭
            if (isr != null)
                isr.close();
        }
        // 解析
        this.analysis(sb.toString());
    }

    /**
     * 写入配置项内容，指定第几个的内容，不存在则添加(覆盖)
     *
     * @param index   下标
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Value   配置项值
     * @return 当前对象
     */
    @Override
    public IniFile setProfileString(int index, String AppName, String KeyName, String Value) {
        super.setProfileString(index, AppName, KeyName, Value);
        return this;
    }

    /**
     * 写入配置项内容，指定第几个的内容(添加)
     *
     * @param index   下标
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Value   配置项值
     * @return 当前对象
     */
    @Override
    public IniFile addProfileString(int index, String AppName, String KeyName, String Value) {
        super.addProfileString(index, AppName, KeyName, Value);
        return this;
    }

    /**
     * 写取配置项内容，不存在则添加(覆盖)
     *
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Value   配置项值
     * @return 当前对象
     */
    @Override
    public IniFile setProfileString(String AppName, String KeyName, String Value) {
        super.setProfileString(AppName, KeyName, Value);
        // 添加到源解析字符串中
        return this;
    }

    /**
     * 写取配置项内容(添加)
     *
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Value   配置项值
     * @return 当前对象
     */
    @Override
    public IniFile addProfileString(String AppName, String KeyName, String Value) {
        super.addProfileString(AppName, KeyName, Value);
        // 添加到源解析字符串中
        return this;
    }

    /**
     * 解析ini字符串的入口
     *
     * @param exp 将要解析的字符串
     */
    @Override
    public void analysis(String exp) {
        super.analysis(exp);
    }

    /**
     * 保存内存信息到文件,不存在则创建
     *
     * @throws IOException IO异常
     */
    public void save() throws IOException {
        OutputStreamWriter osw = null;
        try {
            if (!iniFile.isFile() && !iniFile.createNewFile())
                return;
            osw = new OutputStreamWriter(new FileOutputStream(iniFile), encodingName);
            osw.write(super.toString());
            osw.flush();
        } finally {
            // 关闭
            if (osw != null)
                osw.close();
        }
    }

    /**
     * 清空
     */
    @Override
    public void clear() {
        super.clear();
    }

    /**
     * 获取当前字符编码
     *
     * @return 编码
     */
    public String getEncoding() {
        return encodingName;
    }

    /**
     * 获取文件名
     *
     * @return 文件名
     */
    public String getName() {
        return iniFile.getName();
    }

    public String getPath() {
        return iniFile.getPath();
    }

    public String getAbsolutePath() {
        return iniFile.getAbsolutePath();
    }

    public String getCanonicalPath() throws IOException {
        return iniFile.getCanonicalPath();
    }

    public String getParent() {
        return iniFile.getParent();
    }

    /**
     * 读取配置项内容
     *
     * @param Path    ini配置文件路径
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Default 默认值 (当项不存在或为空时返回)
     * @return 值内容
     */
    public static String GetPrivateProfileString(String Path, String AppName, String KeyName, String Default) {
        try {
            return new IniFile(Path).getProfileString(AppName, KeyName, Default);
        } catch (Exception e) {
            return Default;
        }
    }

    /**
     * 写入配置项内容，不存在则添加(覆盖)
     *
     * @param Path    ini配置文件路径
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Value   配置项值
     * @return 是否成功
     */
    public static boolean SetPrivateProfileString(String Path, String AppName, String KeyName, String Value) {
        try {
            new IniFile(Path).setProfileString(AppName, KeyName, Value).save();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 写入配置项内容(添加)
     *
     * @param Path    ini配置文件路径
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Value   配置项值
     * @return 是否成功
     */
    public static boolean AddPrivateProfileString(String Path, String AppName, String KeyName, String Value) {
        try {
            new IniFile(Path).addProfileString(AppName, KeyName, Value).save();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
