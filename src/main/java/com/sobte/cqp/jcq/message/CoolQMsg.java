package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.entity.CQImage;
import com.sobte.cqp.jcq.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Sobte on 2018/4/24.<br>
 * Time: 2018/4/24 19:07<br>
 * Email: i@sobte.me<br>
 * 酷Q消息解析类，普通消息和酷Q码的集合
 *
 * @author Sobte
 */
public final class CoolQMsg extends BaseMsg<ActionMsg> {

    private static final long serialVersionUID = -9145336990389381969L;

    /**
     * 无参初始化
     */
    public CoolQMsg() {
    }

    /**
     * 初始化
     *
     * @param code 要解析的消息
     */
    public CoolQMsg(String code) {
        analysis(code);
    }

    /**
     * 解析CQ码,含普通消息
     *
     * @param code 要解析的CQ码
     * @return 本消息对象
     */
    public CoolQMsg analysis(String code) {
        if (StringUtils.isTrimEmpty(code))
            return this;
        int length = code.length();
        int begin = 0;
        for (int i = 0; i < length; i++) {
            switch (code.charAt(i)) {
                case '[':
                    int idx = code.indexOf(']', i);
                    if (idx == -1) {
                        i = length;// 设置为超出，则退出循环
                        add(new ActionMsg(code.substring(begin, i)));
                        continue;
                    }
                    ActionCode ac = ActionCode.analysis(null, code.substring(i, idx + 1));
                    if (ac != null) {
                        add(ac);
                        begin = i = idx;
                        begin++;
                        break;
                    }
                    i = idx;
                default:
                    idx = code.indexOf('[', i);
                    if (idx == -1)
                        idx = length;
                    add(new ActionMsg(code.substring(begin, idx)));
                    begin = i = idx;
                    i--;
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
        return (ActionCode) get(ac);
    }

    /**
     * 根据索引，获取CQ码
     *
     * @param index 索引
     * @return CQ码
     */
    @Override
    protected ActionCode getCode(int index) {
        ActionMsg action = get(index);
        if (action instanceof ActionCode)
            return (ActionCode) action;
        return null;
    }

    /**
     * 整理合并相邻的普通消息
     *
     * @return 本消息对象
     */
    public CoolQMsg merge() {
        Iterator<ActionMsg> it = iterator();
        ActionMsg actionMsg = null;
        while (it.hasNext()) {
            ActionMsg msg = it.next();
            if (msg instanceof ActionCode)
                actionMsg = null;
            else {
                if (actionMsg != null) {
                    actionMsg.setMsg(actionMsg.getMsg().concat(msg.getMsg()));
                    it.remove();
                } else
                    actionMsg = msg;
            }
        }
        return this;
    }

    /**
     * 文本消息(text)
     *
     * @param str 文本，多个文本自动拼接成整段
     * @return 本消息对象
     */
    public CoolQMsg text(String... str) {
        add(new ActionMsg(StringUtils.stringConcat((Object[]) str)));
        return this;
    }

    /**
     * 从消息中提取普通消息<br>
     * 无CQ码返回整段消息，错误返回 null<br>
     *
     * @return 文本消息，错误返回 null
     */
    public String getText() {
        return getTextScreen(null, null, null);
    }

    /**
     * 从消息中提取普通消息，并且前缀相匹配<br>
     * 无CQ码则整段消息进行匹配，错误返回 null<br>
     *
     * @param prefix 前缀
     * @return 文本消息，错误返回 null
     */
    public String getTextStartsWith(String prefix) {
        return getTextScreen(prefix, null, null);
    }

    /**
     * 从消息中提取普通消息，并且包括匹配消息<br>
     * 无CQ码则整段消息进行匹配，错误返回 null<br>
     *
     * @param s 包括
     * @return 文本消息，错误返回 null
     */
    public String getTextContains(CharSequence s) {
        return getTextScreen(null, s, null);
    }

    /**
     * 从消息中提取普通消息，并且后缀相匹配<br>
     * 无CQ码则整段消息进行匹配，错误返回 null<br>
     *
     * @param suffix 后缀
     * @return 文本消息，错误返回 null
     */
    public String getTextEndsWith(String suffix) {
        return getTextScreen(null, null, suffix);
    }

    /**
     * 从消息中提取普通消息，并且筛选相匹配的第一个消息<br>
     * 无CQ码则整段消息进行筛选，错误返回 null<br>
     *
     * @param prefix   前缀，填null，则忽略
     * @param contains 包括，填null，则忽略
     * @param suffix   后缀，填null，则忽略
     * @return 文本消息，错误返回 null
     */
    public String getTextScreen(String prefix, CharSequence contains, String suffix) {
        for (ActionMsg msg : this) {
            if (!(msg instanceof ActionCode)) {
                String text = msg.getMsg();
                if ((prefix == null || text.startsWith(prefix)) && (contains == null || text.contains(contains)) && (suffix == null || text.endsWith(suffix)))
                    return text;
            }
        }
        return null;
    }

    /**
     * 从消息中提取普通消息，遇CQ码则分段<br>
     *
     * @return 文本消息集合
     */
    public List<String> getTexts() {
        return getTextsScreen(null, null, null);
    }

    /**
     * 从消息中提取普通消息，遇CQ码则分段，且每分段前缀相匹配<br>
     *
     * @param prefix 前缀
     * @return 文本消息集合
     */
    public List<String> getTextsStartsWith(String prefix) {
        return getTextsScreen(prefix, null, null);
    }

    /**
     * 从消息中提取普通消息，遇CQ码则分段，且每分段包含匹配文本<br>
     *
     * @param s 包含
     * @return 文本消息集合
     */
    public List<String> getTextsContains(CharSequence s) {
        return getTextsScreen(null, s, null);
    }

    /**
     * 从消息中提取普通消息，遇CQ码则分段，且每分段后缀相匹配<br>
     *
     * @param suffix 后缀
     * @return 文本消息集合
     */
    public List<String> getTextsEndsWith(String suffix) {
        return getTextsScreen(null, null, suffix);
    }

    /**
     * 从消息中提取普通消息，遇CQ码则分段，且每分段内容筛选相匹配<br>
     *
     * @param prefix   前缀，填null，则忽略
     * @param contains 包含，填null，则忽略
     * @param suffix   后缀，填null，则忽略
     * @return 文本消息集合
     */
    public List<String> getTextsScreen(String prefix, CharSequence contains, String suffix) {
        List<String> list = new ArrayList<String>();
        for (ActionMsg msg : this) {
            if (!(msg instanceof ActionCode)) {
                String text = msg.getMsg();
                if ((prefix == null || text.startsWith(prefix)) && (contains == null || text.contains(contains)) && (suffix == null || text.endsWith(suffix)))
                    list.add(text);
            }
        }
        return list;
    }

    /**
     * 从消息中提取普通消息，并且筛选相匹配的前后CQ码的第一个消息<br>
     * 无CQ码则整段消息进行筛选，错误返回 null<br>
     *
     * @param prefixCode 前一个CQ码，填null，则忽略
     * @return 文本消息，错误返回 null
     */
    public String getTextStartsWithCode(ActionCode prefixCode) {
        return getTextNearCodeScreen(prefixCode, null);
    }

    /**
     * 从消息中提取普通消息，并且筛选相匹配的前后CQ码的第一个消息<br>
     * 无CQ码则整段消息进行筛选，错误返回 null<br>
     *
     * @param prefixAction 前一个CQ码的功能，填null，则忽略
     * @return 文本消息，错误返回 null
     */
    public String getTextStartsWithCode(String prefixAction) {
        return getTextNearCodeScreen(prefixAction != null ? new ActionCode(prefixAction) : null, null);
    }

    /**
     * 从消息中提取普通消息，并且筛选相匹配的前后CQ码的第一个消息<br>
     * 无CQ码则整段消息进行筛选，错误返回 null<br>
     *
     * @param suffixCode 后一个CQ码，填null，则忽略
     * @return 文本消息，错误返回 null
     */
    public String getTextEndWithCode(ActionCode suffixCode) {
        return getTextNearCodeScreen(null, suffixCode);
    }

    /**
     * 从消息中提取普通消息，并且筛选相匹配的前后CQ码的第一个消息<br>
     * 无CQ码则整段消息进行筛选，错误返回 null<br>
     *
     * @param suffixAction 后一个CQ码，填null，则忽略
     * @return 文本消息，错误返回 null
     */
    public String getTextEndWithCode(String suffixAction) {
        return getTextNearCodeScreen(null, suffixAction != null ? new ActionCode(suffixAction) : null);
    }

    /**
     * 从消息中提取普通消息，并且筛选相匹配的前后CQ码的第一个消息<br>
     * 无CQ码则整段消息进行筛选，错误返回 null<br>
     *
     * @param prefixCode 前一个CQ码，填null，则忽略
     * @param suffixCode 后一个CQ码，填null，则忽略
     * @return 文本消息，错误返回 null
     */
    public String getTextNearCodeScreen(ActionCode prefixCode, ActionCode suffixCode) {
        merge();
        ListIterator<ActionMsg> msgList = listIterator();
        ActionMsg pCode = null;
        ActionMsg sCode = null;
        while (msgList.hasNext()) {
            if (msgList.hasPrevious()) {
                pCode = msgList.previous();
                if (msgList.hasNext())
                    sCode = msgList.next();
            }
            ActionMsg msg = msgList.next();
            if (msgList.hasNext()) {
                sCode = msgList.next();
                if (msgList.hasPrevious())
                    msgList.previous();
            }
            if (!(msg instanceof ActionCode)) {
                if ((prefixCode == null || prefixCode.equals(pCode)) && (suffixCode == null || suffixCode.equals(sCode))) {
                    return msg.getMsg();
                }
            }

        }
        return null;
    }

    /**
     * 从消息中提取普通消息，并且筛选相匹配的前后CQ码的所有消息<br>
     *
     * @param prefixCode 前一个CQ码，填null，则忽略
     * @return 文本消息集合
     */
    public List<String> getTextsStartsWithCode(ActionCode prefixCode) {
        return getTextsNearCodeScreen(prefixCode, null);
    }

    /**
     * 从消息中提取普通消息，并且筛选相匹配的前后CQ码的所有消息<br>
     *
     * @param prefixAction 前一个CQ码的功能，填null，则忽略
     * @return 文本消息集合
     */
    public List<String> getTextsStartsWithCode(String prefixAction) {
        return getTextsNearCodeScreen(prefixAction != null ? new ActionCode(prefixAction) : null, null);
    }

    /**
     * 从消息中提取普通消息，并且筛选相匹配的前后CQ码的所有消息<br>
     *
     * @param suffixCode 后一个CQ码，填null，则忽略
     * @return 文本消息集合
     */
    public List<String> getTextsEndWithCode(ActionCode suffixCode) {
        return getTextsNearCodeScreen(null, suffixCode);
    }

    /**
     * 从消息中提取普通消息，并且筛选相匹配的前后CQ码的所有消息<br>
     *
     * @param suffixAction 后一个CQ码，填null，则忽略
     * @return 文本消息集合
     */
    public List<String> getTextsEndWithCode(String suffixAction) {
        return getTextsNearCodeScreen(null, suffixAction != null ? new ActionCode(suffixAction) : null);
    }


    /**
     * 从消息中提取普通消息，并且筛选相匹配的前后CQ码的所有消息<br>
     *
     * @param prefixCode 前一个CQ码，填null，则忽略
     * @param suffixCode 后一个CQ码，填null，则忽略
     * @return 文本消息集合
     */
    public List<String> getTextsNearCodeScreen(ActionCode prefixCode, ActionCode suffixCode) {
        merge();
        List<String> list = new ArrayList<String>();
        ListIterator<ActionMsg> msgList = listIterator();
        ActionMsg pCode = null;
        ActionMsg sCode = null;
        while (msgList.hasNext()) {
            if (msgList.hasPrevious()) {
                pCode = msgList.previous();
                if (msgList.hasNext())
                    sCode = msgList.next();
            }
            ActionMsg msg = msgList.next();
            if (msgList.hasNext()) {
                sCode = msgList.next();
                if (msgList.hasPrevious())
                    msgList.previous();
            }
            if (!(msg instanceof ActionCode)) {
                if ((prefixCode == null || prefixCode.equals(pCode)) && (suffixCode == null || suffixCode.equals(sCode))) {
                    list.add(msg.getMsg());
                }
            }

        }
        return list;
    }

    /**
     * 表情QQ表情(face)
     *
     * @param faceId 表情ID
     * @return 本消息对象
     */
    @Override
    public CoolQMsg face(int... faceId) {
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
    public CoolQMsg emoji(int... emojiId) {
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
    public CoolQMsg at(long... qqId) {
        super.at(qqId);
        return this;
    }

    /**
     * 窗口抖动(shake) - 仅支持好友
     *
     * @return 本消息对象
     */
    @Override
    public CoolQMsg shake() {
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
    public CoolQMsg anonymous(boolean ignore) {
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
    public CoolQMsg music(long musicId, String type, boolean style) {
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
    public CoolQMsg music(String url, String audio, String title, String content, String image) {
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
    public CoolQMsg contact(String type, long id) {
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
    public CoolQMsg share(String url, String title, String content, String image) {
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
    public CoolQMsg location(double lat, double lon, int zoom, String title, String content) {
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
    public CoolQMsg image(String file) {
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
    public CoolQMsg image(File path) throws IOException {
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
    public CoolQMsg image(CQImage image) throws IOException {
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
    public CoolQMsg imageUseGet(String url) throws IOException {
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
    public CoolQMsg imageUseGet(String url, Map<String, List<String>> requestProperties) throws IOException {
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
    public CoolQMsg imageUsePost(String url, Map<String, List<String>> requestProperties, byte[] bytes) throws IOException {
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
    public CoolQMsg record(String file, boolean magic) {
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
    public CoolQMsg record(String file) {
        super.record(file);
        return this;
    }

}
