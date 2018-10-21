package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.entity.CQImage;
import com.sobte.cqp.jcq.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Sobte on 2018/4/11.<br>
 * Time: 2018/4/11 0:26<br>
 * Email: i@sobte.me<br>
 * 酷Q码类，用于解析和合并酷Q码，酷Q码集合
 *
 * @author Sobte
 */
public final class CoolQCode extends BaseMsg<ActionCode> {

    private static final long serialVersionUID = 8071105058225418081L;

    /**
     * 无参初始化
     */
    public CoolQCode() {

    }

    /**
     * 初始化
     *
     * @param code 要解析的CQ码
     */
    public CoolQCode(String code) {
        analysis(code);
    }

    /**
     * 解析CQ码,不含普通消息
     *
     * @param code 要解析的CQ码
     * @return 本消息对象
     */
    public CoolQCode analysis(String code) {
        if (StringUtils.isTrimEmpty(code))
            return this;
        int length = code.length();
        for (int i = 0; i < length; i++) {
            switch (code.charAt(i)) {
                case '[':
                    int idx = code.indexOf(']', i);
                    if (idx == -1) {
                        i = length;// 设置为超出，则退出循环
                        continue;
                    }
                    ActionCode ac = ActionCode.analysis(null, code.substring(i, idx + 1));
                    if (ac != null)
                        add(ac);
                    i = idx;
                    break;
            }
        }
        return this;
    }

    /**
     * 追加CQ码
     *
     * @param ac CQ码
     */
    @Override
    protected void addCode(ActionCode ac) {
        add(ac);
    }

    /**
     * 修改CQ码，不存在则添加至末尾(覆盖)
     *
     * @param ac CQ码
     */
    @Override
    protected void setCode(ActionCode ac) {
        set(ac);
    }

    /**
     * 根据功能名称，获取CQ码
     *
     * @param ac 包含功能名称的CQ码
     * @return CQ码
     */
    @Override
    protected ActionCode getCode(ActionCode ac) {
        return get(ac);
    }

    /**
     * 根据索引，获取CQ码
     *
     * @param index 索引
     * @return CQ码
     */
    @Override
    protected ActionCode getCode(int index) {
        return get(index);
    }

    /**
     * 添加CQ功能(添加)
     *
     * @param key 功能名称
     * @return 是否成功
     */
    public boolean add(String key) {
        return add(new ActionCode(key));
    }

    /**
     * 添加CQ功能(添加)
     *
     * @param key       功能名称
     * @param actionKey 功能中的键
     * @param value     功能中的值
     * @return 是否成功
     */
    public boolean add(String key, String actionKey, String value) {
        ActionCode ac = new ActionCode(key);
        ac.put(actionKey, value);
        return add(ac);
    }

    /**
     * 修改CQ功能，不存在则添加(覆盖)
     *
     * @param key 功能名称
     * @return 覆盖前CQ功能
     */
    public ActionCode set(String key) {
        return set(new ActionCode(key));// 调用
    }

    /**
     * 获取指定功能名称的 CQ功能<br>
     * 遇到相同功能名称，则返回最先找到的 CQ功能
     *
     * @param key 功能名称
     * @return CQ功能
     */
    public ActionCode get(String key) {
        return get(new ActionCode(key));// 利用重写的equals方法进行判定
    }

    /**
     * 删除指定功能名称的CQ功能
     *
     * @param key 功能名称
     * @return 是否成功
     */
    public boolean remove(String key) {
        return remove(new ActionCode(key));
    }

    /**
     * 表情QQ表情(face)
     *
     * @param faceId 表情ID
     * @return 本消息对象
     */
    @Override
    public CoolQCode face(int... faceId) {
        super.face(faceId);
        return this;
    }

    /**
     * emoji表情(emoji)
     *
     * @param emojiId emoji的unicode编号
     * @return 本消息对象
     */
    @Override
    public CoolQCode emoji(int... emojiId) {
        super.emoji(emojiId);
        return this;
    }

    /**
     * 提醒某人，@某人(at)
     *
     * @param qqId qq号，-1 为全体
     * @return 本消息对象
     */
    @Override
    public CoolQCode at(long... qqId) {
        super.at(qqId);
        return this;
    }

    /**
     * 窗口抖动(shake) - 仅支持好友
     *
     * @return 本消息对象
     */
    @Override
    public CoolQCode shake() {
        super.shake();
        return this;
    }

    /**
     * 匿名发消息(anonymous) - 仅支持群
     *
     * @param ignore 是否不强制，如果希望匿名失败时，将消息转为普通消息发送(而不是取消发送)，请置本参数为true。
     * @return 本消息对象
     */
    @Override
    public CoolQCode anonymous(boolean ignore) {
        super.anonymous(ignore);
        return this;
    }

    /**
     * 发送音乐(music)
     *
     * @param musicId 音乐的歌曲数字ID
     * @param type    目前支持 qq/QQ音乐 163/网易云音乐 xiami/虾米音乐，默认为qq
     * @param style   启用新版样式，目前仅 QQ音乐 支持
     * @return 本消息对象
     */
    @Override
    public CoolQCode music(long musicId, String type, boolean style) {
        super.music(musicId, type, style);
        return this;
    }

    /**
     * 发送音乐自定义分享(music)
     *
     * @param url     分享链接,点击分享后进入的音乐页面（如歌曲介绍页）
     * @param audio   音频链接,音乐的音频链接（如mp3链接）
     * @param title   标题,音乐的标题，建议12字以内
     * @param content 内容,音乐的简介，建议30字以内
     * @param image   封面图片链接,音乐的封面图片链接，留空则为默认图片
     * @return 本消息对象
     */
    @Override
    public CoolQCode music(String url, String audio, String title, String content, String image) {
        super.music(url, audio, title, content, image);
        return this;
    }

    /**
     * 发送名片分享(contact)
     *
     * @param type 目前支持 qq/好友分享 group/群分享
     * @param id   类型为qq，则为QQID；类型为group，则为群号
     * @return 本消息对象
     */
    @Override
    public CoolQCode contact(String type, long id) {
        super.contact(type, id);
        return this;
    }

    /**
     * 发送链接分享(share)
     *
     * @param url     分享的链接
     * @param title   分享的标题，建议12字以内
     * @param content 分享的简介，建议30字以内
     * @param image   分享的图片链接，留空则为默认图片
     * @return 本消息对象
     */
    @Override
    public CoolQCode share(String url, String title, String content, String image) {
        super.share(url, title, content, image);
        return this;
    }

    /**
     * 发送位置分享(location)
     *
     * @param lat     纬度
     * @param lon     经度
     * @param zoom    放大倍数，默认为 15
     * @param title   地点名称，建议12字以内
     * @param content 地址，建议20字以内
     * @return 本消息对象
     */
    @Override
    public CoolQCode location(double lat, double lon, int zoom, String title, String content) {
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
    public CoolQCode image(String file) {
        super.image(file);
        return this;
    }

    /**
     * 发送图片(image)
     *
     * @param path 要发送的图片文件对象，位置随意
     * @return 本消息对象
     * @throws IOException IO异常
     */
    @Override
    public CoolQCode image(File path) throws IOException {
        super.image(path);
        return this;
    }

    /**
     * 发送图片(image)
     *
     * @param image 要发送的CQImage对象
     * @return 本消息对象
     * @throws IOException IO异常
     */
    @Override
    public CoolQCode image(CQImage image) throws IOException {
        super.image(image);
        return this;
    }

    /**
     * 发送图片(image),使用GET请求
     *
     * @param url 要发送的图片链接
     * @return 本消息对象
     * @throws IOException IO异常
     */
    @Override
    public CoolQCode imageUseGet(String url) throws IOException {
        super.imageUseGet(url);
        return this;
    }

    /**
     * 发送图片(image),使用GET请求
     *
     * @param url               要发送的图片链接
     * @param requestProperties 请求头信息
     * @return 本消息对象
     * @throws IOException IO异常
     */
    @Override
    public CoolQCode imageUseGet(String url, Map<String, List<String>> requestProperties) throws IOException {
        super.imageUseGet(url, requestProperties);
        return this;
    }

    /**
     * 发送图片(image),使用POST请求
     *
     * @param url               要发送的图片链接
     * @param requestProperties 请求头信息
     * @param bytes             请求的数据
     * @return 本消息对象
     * @throws IOException IO异常
     */
    @Override
    public CoolQCode imageUsePost(String url, Map<String, List<String>> requestProperties, byte[] bytes) throws IOException {
        super.imageUsePost(url, requestProperties, bytes);
        return this;
    }

    /**
     * 发送语音(record)
     *
     * @param file  将语音放在 data\record 下，并填写相对路径。如 data\record\1.amr 则填写 1.amr
     * @param magic 是否是魔法语音
     * @return 本消息对象
     */
    @Override
    public CoolQCode record(String file, boolean magic) {
        super.record(file, magic);
        return this;
    }

    /**
     * 发送语音(record)
     *
     * @param file 将语音放在 data\record 下，并填写相对路径。如 data\record\1.amr 则填写 1.amr
     * @return 本消息对象
     */
    @Override
    public CoolQCode record(String file) {
        super.record(file);
        return this;
    }
}
