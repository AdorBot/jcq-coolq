package com.sobte.cqp.jcq.entity;

import com.sobte.cqp.jcq.util.StringHelper;

/**
 * Created by Sobte on 2018/3/27.<br>
 * Time: 2018/3/27 18:45<br>
 * Email: i@sobte.me<br>
 * CQ码，用于辅助开发加快开发效率<br>
 * CQ码基本格式 [CQ:action,key=value,key=value...]<br>
 * Tip:如果不知道怎么抒写相关CQ码，可以对机器人发相关信息，然后查看日志信息中收到的CQ码
 *
 * @author Sobte
 */
public class CQCode {

    /**
     * 特殊字符，转义，避免冲突
     *
     * @param code    要转义的字符串
     * @param isComma 是否转义逗号
     * @return 转义后的字符串
     */
    public static String encode(String code, boolean isComma) {
        code = code.replaceAll("&", "&amp;");
        code = code.replaceAll("\\[", "&#91;");
        code = code.replaceAll("]", "&#93;");
        if (isComma)
            code = code.replaceAll(",", "&#44;");
        return code;
    }

    /**
     * 特殊字符，反转义
     *
     * @param code 要反转义的字符串
     * @return 反转义后的字符串
     */
    public static String decode(String code) {
        code = code.replaceAll("&#91;", "[");
        code = code.replaceAll("&#93;", "]");
        code = code.replaceAll("&#44;", ",");
        code = code.replaceAll("&amp;", "&");
        return code;
    }

    /**
     * 表情QQ表情(face)
     *
     * @param faceId 表情ID
     * @return CQ码
     */
    public String face(int faceId) {
        return StringHelper.stringConcat("[CQ:face,id=", faceId, "]");
    }

    /**
     * emoji表情(emoji)
     *
     * @param emojiId emoji的unicode编号
     * @return CQ码
     */
    public String emoji(int emojiId) {
        return StringHelper.stringConcat("[CQ:emoji,id=", emojiId, "]");
    }

    /**
     * 提醒某人，@某人(at)
     *
     * @param qqId      qq号，-1 为全体
     * @param isNoSpace At后添加空格，可使At更规范美观。如果不需要添加空格，请置本参数为true。
     * @return CQ码
     * @see #at(long) @某人(末尾加空格)
     */
    public String at(long qqId, boolean isNoSpace) {
        return StringHelper.stringConcat("[CQ:at,qq=", qqId, "]", isNoSpace ? "" : " ");
    }

    /**
     * 提醒某人，@某人(at)，末尾有空格<br>
     * At后添加空格，可使At更规范美观。如果不需要添加空格，请参考{@link #at(long, boolean) @某人}
     *
     * @param qqId qq号，-1 为全体
     * @return CQ码
     * @see #at(long, boolean) @某人
     */
    public String at(long qqId) {
        return StringHelper.stringConcat("[CQ:at,qq=", qqId, "] ");
    }

    /**
     * 窗口抖动(shake) - 仅支持好友
     *
     * @return CQ码
     */
    public String shake() {
        return "[CQ:shake]";
    }

    /**
     * 匿名发消息(anonymous) - 仅支持群
     *
     * @param ignore 是否不强制，如果希望匿名失败时，将消息转为普通消息发送(而不是取消发送)，请置本参数为true。
     * @return CQ码
     */
    public String anonymous(boolean ignore) {
        return StringHelper.stringConcat("[CQ:anonymous", ignore ? ",ignore=true" : "", "] ");
    }

    /**
     * 发送音乐(music)
     *
     * @param musicId 音乐的歌曲数字ID
     * @param type    目前支持 qq/QQ音乐 163/网易云音乐 xiami/虾米音乐，默认为qq
     * @param style   启用新版样式，目前仅 QQ音乐 支持
     * @return CQ码
     */
    public String music(long musicId, String type, boolean style) {
        return StringHelper.stringConcat("[CQ:music,id=", musicId, ",type=", StringHelper.isTrimEmpty(type) ? "qq" : encode(type, true), style ? ",style=1" : "", "]");
    }

    /**
     * 发送音乐自定义分享(music)
     *
     * @param url     分享链接,点击分享后进入的音乐页面（如歌曲介绍页）
     * @param audio   音频链接,音乐的音频链接（如mp3链接）
     * @param title   标题,音乐的标题，建议12字以内
     * @param content 内容,音乐的简介，建议30字以内
     * @param image   封面图片链接,音乐的封面图片链接，留空则为默认图片
     * @return CQ码
     */
    public String music(String url, String audio, String title, String content, String image) {
        StringBuilder sb = new StringBuilder();
        sb.append("[CQ:music,type=custom");
        sb.append(",url=").append(encode(url, true));
        sb.append(",audio=").append(encode(audio, true));
        if (!StringHelper.isTrimEmpty(title))
            sb.append(",title=").append(encode(title, true));
        if (!StringHelper.isTrimEmpty(content))
            sb.append(",content=").append(encode(content, true));
        if (!StringHelper.isTrimEmpty(image))
            sb.append(",image=").append(encode(image, true));
        sb.append("]");
        return sb.toString();
    }

    /**
     * 发送名片分享(contact)
     *
     * @param type 目前支持 qq/好友分享 group/群分享
     * @param id   类型为qq，则为QQID；类型为group，则为群号
     * @return CQ码
     */
    public String contact(String type, long id) {
        return StringHelper.stringConcat("[CQ:contact,type=", encode(type, true), ",id=", id, "]");
    }

    /**
     * 发送链接分享(share)
     *
     * @param url     分享的链接
     * @param title   分享的标题，建议12字以内
     * @param content 分享的简介，建议30字以内
     * @param image   分享的图片链接，留空则为默认图片
     * @return CQ码
     */
    public String share(String url, String title, String content, String image) {
        StringBuilder sb = new StringBuilder();
        sb.append("[CQ:share");
        sb.append(",url=").append(encode(url, true));
        if (!StringHelper.isTrimEmpty(title))
            sb.append(",title=").append(encode(title, true));
        if (!StringHelper.isTrimEmpty(content))
            sb.append(",content=").append(encode(content, true));
        if (!StringHelper.isTrimEmpty(image))
            sb.append(",image=").append(encode(image, true));
        sb.append("]");
        return sb.toString();
    }

    /**
     * 发送位置分享(location)
     *
     * @param lat     纬度
     * @param lon     经度
     * @param zoom    放大倍数，默认为 15
     * @param title   地点名称，建议12字以内
     * @param content 地址，建议20字以内
     * @return CQ码
     */
    public String location(double lat, double lon, int zoom, String title, String content) {
        StringBuilder sb = new StringBuilder();
        sb.append("[CQ:location");
        sb.append(",lat=").append(lat).append(",lon=").append(lon);
        if (zoom > 0)
            sb.append(",zoom=").append(zoom);
        sb.append(",title=").append(encode(title, true));
        sb.append(",content=").append(encode(content, true));
        sb.append("]");
        return sb.toString();
    }

    /**
     * 发送图片(image)
     *
     * @param file 将图片放在 data\image 下，并填写相对路径。如 data\image\1.jpg 则填写 1.jpg
     * @return CQ码
     */
    public String image(String file) {
        return StringHelper.stringConcat("[CQ:image,file=", encode(file, true), "]");
    }

    /**
     * 发送语音(record)
     *
     * @param file 将语音放在 data\record 下，并填写相对路径。如 data\record\1.amr 则填写 1.amr
     * @return CQ码
     */
    public String record(String file) {
        return StringHelper.stringConcat("[CQ:record,file=", encode(file, true), "]");
    }

}
