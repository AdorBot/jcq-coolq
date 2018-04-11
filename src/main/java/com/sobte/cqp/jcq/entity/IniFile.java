package com.sobte.cqp.jcq.entity;


import com.sobte.cqp.jcq.util.StringHelper;

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
//    /**
//     * 被解析的源字符串
//     */
//    private StringBuffer sb = new StringBuffer();
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
        StringBuilder sb = new StringBuilder();
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream(iniFile), charsetName);
            this.encodingName = isr.getEncoding();
            int read, idx = 0;
            char line = StringHelper.lineSeparatorSingle();
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
     * 写取配置项内容，不存在则添加(覆盖)
     *
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Value   配置项值
     */
    @Override
    public void setProfileString(String AppName, String KeyName, String Value) {
        super.setProfileString(AppName, KeyName, Value);
        // 添加到源解析字符串中

    }

    /**
     * 写取配置项内容(添加)
     *
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Value   配置项值
     */
    @Override
    public void addProfileString(String AppName, String KeyName, String Value) {
        super.addProfileString(AppName, KeyName, Value);
        // 添加到源解析字符串中

    }

    /**
     * 解析ini字符串的入口
     *
     * @param exp 将要解析的字符串
     */
    @Override
    public void analysis(String exp) {
//        sb.append(exp);
        super.analysis(exp);
    }

//    /**
//     * 获取源解析字符串
//     *
//     * @return 源解析字符串
//     */
//    public String getString() {
//        return sb.toString();
//    }

    /**
     * 保存内存信息到文件
     */
    public void save() throws IOException {
        OutputStreamWriter osw = null;
        try {
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
//        sb = new StringBuffer();
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
            new IniFile(Path).setProfileString(AppName, KeyName, Value);
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
            new IniFile(Path).addProfileString(AppName, KeyName, Value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
