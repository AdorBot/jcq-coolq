package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.annotation.AuthType;
import com.sobte.cqp.jcq.entity.CQDebug;
import com.sobte.cqp.jcq.entity.CQImage;
import com.sobte.cqp.jcq.entity.CQStatus;
import com.sobte.cqp.jcq.entity.CoolQ;
import com.sobte.cqp.jcq.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Sobte on 2018/6/26.<br>
 * Time: 2018/6/26 16:38<br>
 * Email: i@sobte.me<br>
 * 消息处理的基类,添加数据快
 *
 * @author Sobte
 */
abstract class AbstractMsgBuilder implements Appendable, CharSequence, Message {

    /**
     * 追加酷Q码，酷Q码功能生成类
     */
    protected CQCode code = CQCode.getInstance();

    /**
     * CQ操作变量
     */
    protected CoolQ CQ = CQDebug.getInstance();

    /**
     * 状态码,发送成功则为消息ID
     */
    int status;

    /**
     * 目标ID
     */
    long target;

    /**
     * 函数类型
     */
    AuthType type;

    /**
     * 消息
     */
    StringBuilder sb = new StringBuilder();

    /**
     * 无参构造函数
     */
    public AbstractMsgBuilder() {
    }

    /**
     * 有参构造函数
     *
     * @param CQ     酷Q操作变量
     * @param type   函数类型
     * @param target 目标ID
     */
    public AbstractMsgBuilder(CoolQ CQ, AuthType type, long target) {
        this.CQ = CQ;
        this.type = type;
        this.target = target;
    }

    /**
     * 设置酷Q操作变量
     *
     * @param CQ 酷Q操作变量
     * @return 本消息对象
     */
    public AbstractMsgBuilder setCoolQ(CoolQ CQ) {
        this.CQ = CQ;
        return this;
    }

    /**
     * 设置目标ID
     *
     * @param target 目标ID
     * @return 本消息对象
     */
    public AbstractMsgBuilder setTarget(long target) {
        this.target = target;
        return this;
    }

    /**
     * 获取目标ID
     *
     * @return 目标ID
     */
    public long getTarget() {
        return target;
    }

    /**
     * 设置函数类型
     *
     * @param type 函数类型
     * @return 本消息对象
     */
    public AbstractMsgBuilder setType(AuthType type) {
        this.type = type;
        return this;
    }

    /**
     * 获取函数类型
     *
     * @return 函数类型
     */
    public AuthType getType() {
        return type;
    }

    /**
     * 获取最后发送消息的msgid(未发送或发送失败消息ID为0)
     *
     * @return 消息ID(未发送或发送失败消息ID为0)
     */
    @Override
    public int getMsgId() {
        return status < 0 ? 0 : status;
    }

    /**
     * 发送消息，发送前先指定一下 酷Q操作变量 不然默认就是Debug模式进行操作
     *
     * @param type   函数类型
     * @param target 目标ID
     * @return 本消息对象
     */
    protected AbstractMsgBuilder send(AuthType type, long target) {
        if (target <= 0) throw new NullPointerException("目标ID不能为空!");
        switch (type) {
            case SendPrivateMsg:
                status = CQ.sendPrivateMsg(target, toString());
                break;
            case SendGroupMsg:
                status = CQ.sendGroupMsg(target, toString());
                break;
            case SendDiscussMsg:
                status = CQ.sendDiscussMsg(target, toString());
                break;
        }
        return this;
    }

    /**
     * 撤回消息，发送前先指定一下 酷Q操作变量 不然默认就是Debug模式进行操作
     *
     * @param msgId 消息ID
     * @return 本消息对象
     */
    public AbstractMsgBuilder deleteMsg(long msgId) {
        status = CQ.deleteMsg(msgId);
        return this;
    }

    /**
     * 撤回本消息对象上条发送的消息
     *
     * @return 本消息对象
     */
    public AbstractMsgBuilder deleteMsg() {
        if (status <= 0) throw new NullPointerException("本对象上条消息未发送成功或从未发送消息");
        return deleteMsg(status);
    }

    /**
     * 发送消息
     *
     * @return 本消息对象
     */
    public AbstractMsgBuilder send() {
        return send(type, target);
    }

    /**
     * 发送消息
     *
     * @param target 目标ID
     * @return 本消息对象
     */
    public AbstractMsgBuilder send(long target) {
        return send(type, target);
    }

    /**
     * 发送群消息
     *
     * @param target 目标ID
     * @return 本消息对象
     */
    public AbstractMsgBuilder sendGroupMsg(long target) {
        return send(AuthType.SendGroupMsg, target);
    }

    /**
     * 发送私聊消息
     *
     * @param target 目标ID
     * @return 本消息对象
     */
    public AbstractMsgBuilder sendPrivateMsg(long target) {
        return send(AuthType.SendPrivateMsg, target);
    }

    /**
     * 发送讨论组消息
     *
     * @param target 目标ID
     * @return 本消息对象
     */
    public AbstractMsgBuilder sendDiscussMsg(long target) {
        return send(AuthType.SendDiscussMsg, target);
    }

    /**
     * 发送群消息
     *
     * @return 本消息对象
     */
    public AbstractMsgBuilder sendGroupMsg() {
        return send(AuthType.SendGroupMsg, target);
    }

    /**
     * 发送私聊消息
     *
     * @return 本消息对象
     */
    public AbstractMsgBuilder sendPrivateMsg() {
        return send(AuthType.SendPrivateMsg, target);
    }

    /**
     * 发送讨论组消息
     *
     * @return 本消息对象
     */
    public AbstractMsgBuilder sendDiscussMsg() {
        return send(AuthType.SendDiscussMsg, target);
    }

    /**
     * 获取最后状态
     *
     * @return 描述信息
     */
    public CQStatus getLastStatus() {
        return CQStatus.getStatus(status < 0 ? status : 0);
    }

    /**
     * 表情QQ表情(face)
     *
     * @param faceId 表情ID
     * @return 本消息对象
     */
    public AbstractMsgBuilder face(int... faceId) {
        sb.append(code.face(faceId));
        return this;
    }

    /**
     * 追加酷Q码，emoji表情(emoji)
     *
     * @param emojiId emoji的unicode编号
     * @return 本消息对象
     */
    public AbstractMsgBuilder emoji(int... emojiId) {
        sb.append(code.emoji(emojiId));
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
    public AbstractMsgBuilder at(long... qqId) {
        sb.append(code.at(qqId));
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
    public AbstractMsgBuilder at(long qqId, boolean isNoSpace) {
        sb.append(code.at(qqId, isNoSpace));
        return this;
    }

    /**
     * 追加酷Q码，窗口抖动(shake) - 仅支持好友
     *
     * @return 本消息对象
     */
    public AbstractMsgBuilder shake() {
        sb.append(code.shake());
        return this;
    }

    /**
     * 追加酷Q码，匿名发消息(anonymous) - 仅支持群
     *
     * @param ignore 是否不强制，如果希望匿名失败时，将消息转为普通消息发送(而不是取消发送)，请置本参数为true。
     * @return 本消息对象
     */
    public AbstractMsgBuilder anonymous(boolean ignore) {
        sb.append(code.anonymous(ignore));
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
    public AbstractMsgBuilder music(long musicId, String type, boolean style) {
        sb.append(code.music(musicId, type, style));
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
    public AbstractMsgBuilder music(String url, String audio, String title, String content, String image) {
        sb.append(code.music(url, audio, title, content, image));
        return this;
    }

    /**
     * 追加酷Q码，发送名片分享(contact)
     *
     * @param type 目前支持 qq/好友分享 group/群分享
     * @param id   类型为qq，则为QQID；类型为group，则为群号
     * @return 本消息对象
     */
    public AbstractMsgBuilder contact(String type, long id) {
        sb.append(code.contact(type, id));
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
    public AbstractMsgBuilder share(String url, String title, String content, String image) {
        sb.append(code.share(url, title, content, image));
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
    public AbstractMsgBuilder location(double lat, double lon, int zoom, String title, String content) {
        sb.append(code.location(lat, lon, zoom, title, content));
        return this;
    }

    /**
     * 发送图片(image)
     *
     * @param file 将图片放在 data\image 下，并填写相对路径。如 data\image\1.jpg 则填写 1.jpg
     * @return 本消息对象
     */
    public AbstractMsgBuilder image(String file) {
        sb.append(code.image(file));
        return this;
    }

    /**
     * 追加酷Q码，发送图片(image)
     *
     * @param path 要发送的图片文件对象，位置随意
     * @return 本消息对象
     * @throws IOException IO异常
     */
    public AbstractMsgBuilder image(File path) throws IOException {
        sb.append(code.image(path));
        return this;
    }

    /**
     * 追加酷Q码，发送图片(image)
     *
     * @param image 要发送的CQImage对象
     * @return 本消息对象
     * @throws IOException IO异常
     */
    public AbstractMsgBuilder image(CQImage image) throws IOException {
        sb.append(code.image(image));
        return this;
    }

    /**
     * 追加酷Q码，发送图片(image),使用GET请求
     *
     * @param url 要发送的图片链接
     * @return 本消息对象
     * @throws IOException IO异常
     */
    public AbstractMsgBuilder imageUseGet(String url) throws IOException {
        sb.append(code.imageUseGet(url));
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
    public AbstractMsgBuilder imageUseGet(String url, Map<String, List<String>> requestProperties) throws IOException {
        sb.append(code.imageUseGet(url, requestProperties));
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
    public AbstractMsgBuilder imageUsePost(String url, Map<String, List<String>> requestProperties, byte[] bytes) throws IOException {
        sb.append(code.imageUsePost(url, requestProperties, bytes));
        return this;
    }

    /**
     * 追加酷Q码，发送语音(record)
     *
     * @param file  将语音放在 data\record 下，并填写相对路径。如 data\record\1.amr 则填写 1.amr
     * @param magic 是否是魔法语音
     * @return 本消息对象
     */
    public AbstractMsgBuilder record(String file, boolean magic) {
        sb.append(code.record(file, magic));
        return this;
    }

    /**
     * 追加酷Q码，发送语音(record)
     *
     * @param file 将语音放在 data\record 下，并填写相对路径。如 data\record\1.amr 则填写 1.amr
     * @return 本消息对象
     */
    public AbstractMsgBuilder record(String file) {
        sb.append(code.record(file));
        return this;
    }

    /**
     * 追加一行
     *
     * @return 本消息对象
     */
    public AbstractMsgBuilder newLine() {
        sb.append(StringUtils.lineSeparator);
        return this;
    }

    /**
     * 追加对象
     *
     * @param obj 对象
     * @return 本消息对象
     */
    public AbstractMsgBuilder append(Object obj) {
        return this.append(String.valueOf(obj));
    }

    /**
     * 追加文本
     *
     * @param str 文本
     * @return 本消息对象
     */
    public AbstractMsgBuilder append(String str) {
        sb.append(CQCode.encode(str, true));
        return this;
    }

    /**
     * 追加布尔值
     *
     * @param b 布尔值
     * @return 本消息对象
     */
    public AbstractMsgBuilder append(boolean b) {
        sb.append(b);
        return this;
    }

    /**
     * 追加字符
     *
     * @param c 字符
     * @return 本消息对象
     */
    @Override
    public AbstractMsgBuilder append(char c) {
        sb.append(CQCode.encode(c, true));
        return this;
    }

    /**
     * 追加整数
     *
     * @param i 整数
     * @return 本消息对象
     */
    public AbstractMsgBuilder append(int i) {
        sb.append(i);
        return this;
    }

    /**
     * 追加长整数
     *
     * @param l 长整数
     * @return 本消息对象
     */
    public AbstractMsgBuilder append(long l) {
        sb.append(l);
        return this;
    }

    /**
     * 追加单精度浮点数
     *
     * @param f 单精度浮点数
     * @return 本消息对象
     */
    public AbstractMsgBuilder append(float f) {
        sb.append(f);
        return this;
    }

    /**
     * 追加双精度浮点数
     *
     * @param d 双精度浮点数
     * @return 本消息对象
     */
    public AbstractMsgBuilder append(double d) {
        sb.append(d);
        return this;
    }

    /**
     * 追加消息对象
     *
     * @param amb 消息对象
     * @return 本消息对象
     */
    AbstractMsgBuilder append(AbstractMsgBuilder amb) {
        sb.append(amb);
        return this;
    }

    @Override
    public AbstractMsgBuilder append(CharSequence csq) {
        sb.append(csq);
        if (csq == null)
            return this.append("null");
        if (csq instanceof String)
            return this.append((String) csq);
        if (csq instanceof AbstractMsgBuilder)
            return this.append((AbstractMsgBuilder) csq);
        return this.append(csq, 0, csq.length());
    }

    @Override
    public AbstractMsgBuilder append(CharSequence csq, int start, int end) {
        if (csq == null)
            return this.append("null");
        sb.append(CQCode.encode(csq.toString(), true), start, end);
        return this;
    }

    /**
     * 删除指定长度的字符
     *
     * @param start 起始位置
     * @param end   结束位置
     * @return 本消息对象
     */
    public AbstractMsgBuilder delete(int start, int end) {
        sb.delete(start, end);
        return this;
    }

    /**
     * 删除指定位置的字符
     *
     * @param index 字符位置
     * @return 本消息对象
     */
    public AbstractMsgBuilder deleteCharAt(int index) {
        sb.deleteCharAt(index);
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
    public AbstractMsgBuilder replace(int start, int end, String str) {
        sb.replace(start, end, CQCode.encode(str, true));
        return this;
    }

    /**
     * 截取字符串
     *
     * @param start 起始位置
     * @return 截取的字符串
     */
    public String substring(int start) {
        return sb.substring(start);
    }

    /**
     * 截取字符串
     *
     * @param start 起始位置
     * @param end   结束位置
     * @return 截取的字符串
     */
    public String substring(int start, int end) {
        return sb.substring(start, end);
    }

    /**
     * 插入对象
     *
     * @param offset 偏移量
     * @param obj    对象
     * @return 本消息对象
     */
    public AbstractMsgBuilder insert(int offset, Object obj) {
        return insert(offset, String.valueOf(obj));
    }

    /**
     * 插入字符串
     *
     * @param offset 偏移量
     * @param str    字符串
     * @return 本消息对象
     */
    public AbstractMsgBuilder insert(int offset, String str) {
        sb.insert(offset, CQCode.encode(str, true));
        return this;
    }

    public AbstractMsgBuilder insert(int dstOffset, CharSequence s) {
        if (s == null)
            s = "null";
        if (s instanceof String)
            return insert(dstOffset, (String) s);
        return insert(dstOffset, s, 0, s.length());
    }

    public AbstractMsgBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        if (s == null)
            return this.append("null");
        sb.insert(dstOffset, CQCode.encode(s.toString(), true), start, end);
        return this;
    }

    /**
     * 插入字符串
     *
     * @param offset 偏移量
     * @param b      布尔值
     * @return 本消息对象
     */
    public AbstractMsgBuilder insert(int offset, boolean b) {
        sb.insert(offset, b);
        return this;
    }

    /**
     * 插入字符
     *
     * @param offset 偏移量
     * @param c      字符
     * @return 本消息对象
     */
    public AbstractMsgBuilder insert(int offset, char c) {
        sb.insert(offset, CQCode.encode(c, true));
        return this;
    }

    /**
     * 插入整数
     *
     * @param offset 偏移量
     * @param i      整数
     * @return 本消息对象
     */
    public AbstractMsgBuilder insert(int offset, int i) {
        sb.insert(offset, i);
        return this;
    }

    /**
     * 插入长整数
     *
     * @param offset 偏移量
     * @param l      长整数
     * @return 本消息对象
     */
    public AbstractMsgBuilder insert(int offset, long l) {
        sb.insert(offset, l);
        return this;
    }

    /**
     * 插入单精度浮点数
     *
     * @param offset 偏移量
     * @param f      单精度浮点数
     * @return 本消息对象
     */
    public AbstractMsgBuilder insert(int offset, float f) {
        sb.insert(offset, f);
        return this;
    }

    /**
     * 插入双精度浮点数
     *
     * @param offset 偏移量
     * @param d      双精度浮点数
     * @return 本消息对象
     */
    public AbstractMsgBuilder insert(int offset, double d) {
        sb.insert(offset, d);
        return this;
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified substring. The integer returned is the smallest value
     * <i>k</i> such that:
     * <pre>{@code
     * this.toString().startsWith(str, <i>k</i>)
     * }</pre>
     * is {@code true}.
     *
     * @param str any string.
     * @return if the string argument occurs as a substring within this
     * object, then the index of the first character of the first
     * such substring is returned; if it does not occur as a
     * substring, {@code -1} is returned.
     */
    public int indexOf(String str) {
        return sb.indexOf(CQCode.encode(str, true));
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified substring, starting at the specified index.  The integer
     * returned is the smallest value {@code k} for which:
     * <pre>{@code
     *     k >= Math.min(fromIndex, this.length()) &&
     *                   this.toString().startsWith(str, k)
     * }</pre>
     * If no such value of <i>k</i> exists, then -1 is returned.
     *
     * @param str       the substring for which to search.
     * @param fromIndex the index from which to start the search.
     * @return the index within this string of the first occurrence of the
     * specified substring, starting at the specified index.
     */
    public int indexOf(String str, int fromIndex) {
        return sb.indexOf(CQCode.encode(str, true), fromIndex);
    }

    /**
     * Returns the index within this string of the rightmost occurrence
     * of the specified substring.  The rightmost empty string "" is
     * considered to occur at the index value {@code this.length()}.
     * The returned index is the largest value <i>k</i> such that
     * <pre>{@code
     * this.toString().startsWith(str, k)
     * }</pre>
     * is true.
     *
     * @param str the substring to search for.
     * @return if the string argument occurs one or more times as a substring
     * within this object, then the index of the first character of
     * the last such substring is returned. If it does not occur as
     * a substring, {@code -1} is returned.
     */
    public int lastIndexOf(String str) {
        return sb.lastIndexOf(CQCode.encode(str, true));
    }

    /**
     * Returns the index within this string of the last occurrence of the
     * specified substring. The integer returned is the largest value <i>k</i>
     * such that:
     * <pre>{@code
     *     k <= Math.min(fromIndex, this.length()) &&
     *                   this.toString().startsWith(str, k)
     * }</pre>
     * If no such value of <i>k</i> exists, then -1 is returned.
     *
     * @param str       the substring to search for.
     * @param fromIndex the index to start the search from.
     * @return the index within this sequence of the last occurrence of the
     * specified substring.
     */
    public int lastIndexOf(String str, int fromIndex) {
        return sb.lastIndexOf(CQCode.encode(str, true), fromIndex);
    }

    public void trimToSize() {
        sb.trimToSize();
    }

    public void setLength(int newLength) {
        sb.setLength(newLength);
    }

    public void setCharAt(int index, char ch) {
        sb.setCharAt(index, ch);
    }

    @Override
    public int length() {
        return sb.length();
    }

    @Override
    public char charAt(int index) {
        return sb.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return sb.subSequence(start, end);
    }

    /**
     * 转成字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        return sb.toString();
    }

    /**
     * 获取消息
     *
     * @return 消息
     */
    @Override
    public String getMsg() {
        return toString();
    }

    /**
     * 获取解析消息,CQ码和普通消息
     *
     * @return 解析后消息, CQ码和普通消息
     */
    @Override
    public CoolQMsg getCoolQMsg() {
        return new CoolQMsg(toString());
    }

    /**
     * 获取解析消息,CQ码(不包含普通消息)
     *
     * @return 解析后消息, CQ码(不包含普通消息)
     */
    @Override
    public CoolQCode getCoolQCode() {
        return new CoolQCode(toString());
    }


}
