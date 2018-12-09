package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.annotation.AuthType;
import com.sobte.cqp.jcq.entity.CQImage;
import com.sobte.cqp.jcq.entity.CoolQ;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Sobte on 2018/7/4.<br>
 * Time: 2018/7/4 21:26<br>
 * Email: i@sobte.me<br>
 * 消息处理的基类,添加数据快(非线程安全)
 *
 * @author Sobte
 */
public final class MsgBuilder extends AbstractMsgBuilder implements Serializable, CharSequence {

    private static final long serialVersionUID = -7154508768527804069L;

    /**
     * 无参构造函数
     */
    public MsgBuilder() {
    }

    /**
     * 有参构造函数
     *
     * @param CQ     CQ操作变量
     * @param type   函数类型
     * @param target 目标ID
     */
    public MsgBuilder(CoolQ CQ, AuthType type, long target) {
        super(CQ, type, target);
    }

    /**
     * 设置酷Q操作变量
     *
     * @param CQ 酷Q操作变量
     * @return 本消息对象
     */
    @Override
    public MsgBuilder setCoolQ(CoolQ CQ) {
        super.setCoolQ(CQ);
        return this;
    }

    /**
     * 设置目标ID
     *
     * @param target 目标ID
     * @return 本消息对象
     */
    @Override
    public MsgBuilder setTarget(long target) {
        super.setTarget(target);
        return this;
    }

    /**
     * 设置函数类型
     *
     * @param type 函数类型
     * @return 本消息对象
     */
    @Override
    public MsgBuilder setType(AuthType type) {
        super.setType(type);
        return this;
    }

    /**
     * 撤回消息，发送前先指定一下 酷Q操作变量 不然默认就是Debug模式进行操作
     *
     * @param msgId 消息ID
     * @return 本消息对象
     */
    @Override
    public MsgBuilder deleteMsg(long msgId) {
        super.deleteMsg(msgId);
        return this;
    }

    /**
     * 撤回本消息对象上条发送的消息
     *
     * @return 本消息对象
     */
    @Override
    public MsgBuilder deleteMsg() {
        super.deleteMsg();
        return this;
    }

    /**
     * 发送消息，发送前先指定一下 酷Q操作变量 不然默认就是Debug模式进行操作
     *
     * @return 本消息对象
     */
    @Override
    public MsgBuilder send() {
        super.send();
        return this;
    }

    /**
     * 发送消息
     *
     * @param target 目标ID
     * @return 本消息对象
     */
    @Override
    public MsgBuilder send(long target) {
        super.send(target);
        return this;
    }

    /**
     * 发送群消息
     *
     * @param target 目标ID
     * @return 本消息对象
     */
    @Override
    public MsgBuilder sendGroupMsg(long target) {
        super.sendGroupMsg(target);
        return this;
    }

    /**
     * 发送私聊消息
     *
     * @param target 目标ID
     * @return 本消息对象
     */
    @Override
    public MsgBuilder sendPrivateMsg(long target) {
        super.sendPrivateMsg(target);
        return this;
    }

    /**
     * 发送讨论组消息
     *
     * @param target 目标ID
     * @return 本消息对象
     */
    @Override
    public MsgBuilder sendDiscussMsg(long target) {
        super.sendDiscussMsg(target);
        return this;
    }

    /**
     * 发送群消息
     *
     * @return 本消息对象
     */
    @Override
    public MsgBuilder sendGroupMsg() {
        super.sendGroupMsg();
        return this;
    }

    /**
     * 发送私聊消息
     *
     * @return 本消息对象
     */
    @Override
    public MsgBuilder sendPrivateMsg() {
        super.sendPrivateMsg();
        return this;
    }

    /**
     * 发送讨论组消息
     *
     * @return 本消息对象
     */
    @Override
    public MsgBuilder sendDiscussMsg() {
        super.sendDiscussMsg();
        return this;
    }

    /**
     * 表情QQ表情(face)
     *
     * @param faceId 表情ID
     * @return 本消息对象
     */
    @Override
    public MsgBuilder face(int... faceId) {
        super.face(faceId);
        return this;
    }

    /**
     * 追加酷Q码，emoji表情(emoji)
     *
     * @param emojiId emoji的unicode编号
     * @return 本消息对象
     */
    @Override
    public MsgBuilder emoji(int... emojiId) {
        super.emoji(emojiId);
        return this;
    }

    /**
     * 追加酷Q码，提醒某人，@某人(at)，末尾有空格<br>
     * At后添加空格，可使At更规范美观。如果不需要添加空格，请参考{@link #at(long, boolean) @某人}
     *
     * @param qqId qq号，-1 为全体
     * @return 本消息对象
     * @see #at(long, boolean) @某人
     */
    @Override
    public MsgBuilder at(long... qqId) {
        super.at(qqId);
        return this;
    }

    /**
     * 追加酷Q码，提醒某人，@某人(at)
     *
     * @param qqId      qq号，-1 为全体
     * @param isNoSpace At后添加空格，可使At更规范美观。如果不需要添加空格，请置本参数为true。
     * @return 本消息对象
     * @see #at(long...) @某人(末尾加空格)
     */
    @Override
    public MsgBuilder at(long qqId, boolean isNoSpace) {
        super.at(qqId, isNoSpace);
        return this;
    }

    /**
     * 追加酷Q码，窗口抖动(shake) - 仅支持好友
     *
     * @return 本消息对象
     */
    @Override
    public MsgBuilder shake() {
        super.shake();
        return this;
    }

    /**
     * 追加酷Q码，匿名发消息(anonymous) - 仅支持群
     *
     * @param ignore 是否不强制，如果希望匿名失败时，将消息转为普通消息发送(而不是取消发送)，请置本参数为true。
     * @return 本消息对象
     */
    @Override
    public MsgBuilder anonymous(boolean ignore) {
        super.anonymous(ignore);
        return this;
    }

    /**
     * 追加酷Q码，发送音乐(music)
     *
     * @param musicId 音乐的歌曲数字ID
     * @param type    目前支持 qq/QQ音乐 163/网易云音乐 xiami/虾米音乐，默认为qq
     * @param style   启用新版样式，目前仅 QQ音乐 支持
     * @return 本消息对象
     */
    @Override
    public MsgBuilder music(long musicId, String type, boolean style) {
        super.music(musicId, type, style);
        return this;
    }

    /**
     * 追加酷Q码，发送音乐自定义分享(music)
     *
     * @param url     分享链接,点击分享后进入的音乐页面（如歌曲介绍页）
     * @param audio   音频链接,音乐的音频链接（如mp3链接）
     * @param title   标题,音乐的标题，建议12字以内
     * @param content 内容,音乐的简介，建议30字以内
     * @param image   封面图片链接,音乐的封面图片链接，留空则为默认图片
     * @return 本消息对象
     */
    @Override
    public MsgBuilder music(String url, String audio, String title, String content, String image) {
        super.music(url, audio, title, content, image);
        return this;
    }

    /**
     * 追加酷Q码，发送名片分享(contact)
     *
     * @param type 目前支持 qq/好友分享 group/群分享
     * @param id   类型为qq，则为QQID；类型为group，则为群号
     * @return 本消息对象
     */
    @Override
    public MsgBuilder contact(String type, long id) {
        super.contact(type, id);
        return this;
    }

    /**
     * 追加酷Q码，发送链接分享(share)
     *
     * @param url     分享的链接
     * @param title   分享的标题，建议12字以内
     * @param content 分享的简介，建议30字以内
     * @param image   分享的图片链接，留空则为默认图片
     * @return 本消息对象
     */
    @Override
    public MsgBuilder share(String url, String title, String content, String image) {
        super.share(url, title, content, image);
        return this;
    }

    /**
     * 追加酷Q码，发送位置分享(location)
     *
     * @param lat     纬度
     * @param lon     经度
     * @param zoom    放大倍数，默认为 15
     * @param title   地点名称，建议12字以内
     * @param content 地址，建议20字以内
     * @return 本消息对象
     */
    @Override
    public MsgBuilder location(double lat, double lon, int zoom, String title, String content) {
        super.location(lat, lon, zoom, title, content);
        return this;
    }

    /**
     * 发送图片(image)
     *
     * @param file 将图片放在 data\image 下，并填写相对路径。如 data\image\1.jpg 则填写 1.jpg
     * @return 本消息对象
     */
    @Override
    public MsgBuilder image(String file) {
        super.image(file);
        return this;
    }

    /**
     * 追加酷Q码，发送图片(image)
     *
     * @param path 要发送的图片文件对象，位置随意
     * @return 本消息对象
     * @throws IOException IO异常
     */
    @Override
    public MsgBuilder image(File path) throws IOException {
        super.image(path);
        return this;
    }

    /**
     * 追加酷Q码，发送图片(image)
     *
     * @param image 要发送的CQImage对象
     * @return 本消息对象
     * @throws IOException IO异常
     */
    @Override
    public MsgBuilder image(CQImage image) throws IOException {
        super.image(image);
        return this;
    }

    /**
     * 追加酷Q码，发送图片(image),使用GET请求
     *
     * @param url 要发送的图片链接
     * @return 本消息对象
     * @throws IOException IO异常
     */
    @Override
    public MsgBuilder imageUseGet(String url) throws IOException {
        super.imageUseGet(url);
        return this;
    }

    /**
     * 追加酷Q码，发送图片(image),使用GET请求
     *
     * @param url               要发送的图片链接
     * @param requestProperties 请求头信息
     * @return 本消息对象
     * @throws IOException IO异常
     */
    @Override
    public MsgBuilder imageUseGet(String url, Map<String, List<String>> requestProperties) throws IOException {
        super.imageUseGet(url, requestProperties);
        return this;
    }

    /**
     * 追加酷Q码，发送图片(image),使用POST请求
     *
     * @param url               要发送的图片链接
     * @param requestProperties 请求头信息
     * @param bytes             请求的数据
     * @return 本消息对象
     * @throws IOException IO异常
     */
    @Override
    public MsgBuilder imageUsePost(String url, Map<String, List<String>> requestProperties, byte[] bytes) throws IOException {
        super.imageUsePost(url, requestProperties, bytes);
        return this;
    }

    /**
     * 追加酷Q码，发送语音(record)
     *
     * @param file  将语音放在 data\record 下，并填写相对路径。如 data\record\1.amr 则填写 1.amr
     * @param magic 是否是魔法语音
     * @return 本消息对象
     */
    @Override
    public MsgBuilder record(String file, boolean magic) {
        super.record(file, magic);
        return this;
    }

    /**
     * 追加酷Q码，发送语音(record)
     *
     * @param file 将语音放在 data\record 下，并填写相对路径。如 data\record\1.amr 则填写 1.amr
     * @return 本消息对象
     */
    @Override
    public MsgBuilder record(String file) {
        super.record(file);
        return this;
    }

    /**
     * 追加一行
     *
     * @return 本消息对象
     */
    @Override
    public MsgBuilder newLine() {
        super.newLine();
        return this;
    }

    /**
     * 追加对象
     *
     * @param obj 对象
     * @return 本消息对象
     */
    @Override
    public MsgBuilder append(Object obj) {
        super.append(obj);
        return this;
    }

    /**
     * 追加文本
     *
     * @param str 文本
     * @return 本消息对象
     */
    @Override
    public MsgBuilder append(String str) {
        super.append(str);
        return this;
    }

    /**
     * 追加布尔值
     *
     * @param b 布尔值
     * @return 本消息对象
     */
    @Override
    public MsgBuilder append(boolean b) {
        super.append(b);
        return this;
    }

    /**
     * 追加字符
     *
     * @param c 字符
     * @return 本消息对象
     */
    @Override
    public MsgBuilder append(char c) {
        super.append(c);
        return this;
    }

    /**
     * 追加整数
     *
     * @param i 整数
     * @return 本消息对象
     */
    @Override
    public MsgBuilder append(int i) {
        super.append(i);
        return this;
    }

    /**
     * 追加长整数
     *
     * @param l 长整数
     * @return 本消息对象
     */
    @Override
    public MsgBuilder append(long l) {
        super.append(l);
        return this;
    }

    /**
     * 追加单精度浮点数
     *
     * @param f 单精度浮点数
     * @return 本消息对象
     */
    @Override
    public MsgBuilder append(float f) {
        super.append(f);
        return this;
    }

    /**
     * 追加双精度浮点数
     *
     * @param d 双精度浮点数
     * @return 本消息对象
     */
    @Override
    public MsgBuilder append(double d) {
        super.append(d);
        return this;
    }

    @Override
    public MsgBuilder append(CharSequence csq) {
        super.append(csq);
        return this;
    }

    @Override
    public MsgBuilder append(CharSequence csq, int start, int end) {
        super.append(csq, start, end);
        return this;
    }

    /**
     * 删除指定长度的字符
     *
     * @param start 起始位置
     * @param end   结束位置
     * @return 本消息对象
     */
    @Override
    public MsgBuilder delete(int start, int end) {
        super.delete(start, end);
        return this;
    }

    /**
     * 删除指定位置的字符
     *
     * @param index 字符位置
     * @return 本消息对象
     */
    @Override
    public MsgBuilder deleteCharAt(int index) {
        super.deleteCharAt(index);
        return this;
    }

    /**
     * 替换指定位置的字符串
     *
     * @param start 起始位置
     * @param end   结束位置
     * @param str   替换的字符串
     * @return 本消息对象
     */
    @Override
    public MsgBuilder replace(int start, int end, String str) {
        super.replace(start, end, str);
        return this;
    }

    /**
     * 插入对象
     *
     * @param offset 偏移量
     * @param obj    对象
     * @return 本消息对象
     */
    @Override
    public MsgBuilder insert(int offset, Object obj) {
        super.insert(offset, obj);
        return this;
    }

    /**
     * 插入字符串
     *
     * @param offset 偏移量
     * @param str    字符串
     * @return 本消息对象
     */
    @Override
    public MsgBuilder insert(int offset, String str) {
        super.insert(offset, str);
        return this;
    }

    @Override
    public MsgBuilder insert(int dstOffset, CharSequence s) {
        super.insert(dstOffset, s);
        return this;
    }

    @Override
    public MsgBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        super.insert(dstOffset, s, start, end);
        return this;
    }

    /**
     * 插入字符串
     *
     * @param offset 偏移量
     * @param b      布尔值
     * @return 本消息对象
     */
    @Override
    public MsgBuilder insert(int offset, boolean b) {
        super.insert(offset, b);
        return this;
    }

    /**
     * 插入字符
     *
     * @param offset 偏移量
     * @param c      字符
     * @return 本消息对象
     */
    @Override
    public MsgBuilder insert(int offset, char c) {
        super.insert(offset, c);
        return this;
    }

    /**
     * 插入整数
     *
     * @param offset 偏移量
     * @param i      整数
     * @return 本消息对象
     */
    @Override
    public MsgBuilder insert(int offset, int i) {
        super.insert(offset, i);
        return this;
    }

    /**
     * 插入长整数
     *
     * @param offset 偏移量
     * @param l      长整数
     * @return 本消息对象
     */
    @Override
    public MsgBuilder insert(int offset, long l) {
        super.insert(offset, l);
        return this;
    }

    /**
     * 插入单精度浮点数
     *
     * @param offset 偏移量
     * @param f      单精度浮点数
     * @return 本消息对象
     */
    @Override
    public MsgBuilder insert(int offset, float f) {
        super.insert(offset, f);
        return this;
    }

    /**
     * 插入双精度浮点数
     *
     * @param offset 偏移量
     * @param d      双精度浮点数
     * @return 本消息对象
     */
    @Override
    public MsgBuilder insert(int offset, double d) {
        super.insert(offset, d);
        return this;
    }

    private void writeObject(ObjectOutputStream s)
            throws IOException {
        s.defaultWriteObject();
        s.writeInt(status);
        s.writeLong(target);
        s.writeObject(type);
        s.writeObject(sb);
    }

    private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        status = s.readInt();
        target = s.readLong();
        type = (AuthType) s.readObject();
        Object obj = s.readObject();
        if (type == null)
            type = AuthType.SendPrivateMsg;
        else
            type = (AuthType) obj;
        obj = s.readObject();
        if (sb == null)
            sb = new StringBuilder();
        else
            sb = (StringBuilder) obj;
    }

}
