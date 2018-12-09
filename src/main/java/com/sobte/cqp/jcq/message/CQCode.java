package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.entity.CQImage;
import com.sobte.cqp.jcq.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
public final class CQCode {

    /**
     * 默认的CQCode
     */
    private static final CQCode code = new CQCode();

    /**
     * 获取默认的CQCode
     *
     * @return 默认的CQCode
     */
    public static CQCode getInstance() {
        return code;
    }

    /**
     * 特殊字符，转义，避免冲突
     *
     * @param code    要转义的字符串
     * @param isComma 是否转义逗号
     * @return 转义后的字符串
     */
    public static String encode(String code, boolean isComma) {
        code = StringUtils.stringReplace(code, "&", "&amp;");
        code = StringUtils.stringReplace(code, "[", "&#91;");
        code = StringUtils.stringReplace(code, "]", "&#93;");
        if (isComma)
            code = StringUtils.stringReplace(code, ",", "&#44;");
        return code;
    }

    /**
     * 特殊字符，转义，避免冲突
     *
     * @param c       要转义的字符
     * @param isComma 是否转义逗号
     * @return 转义后的字符串
     */
    public static String encode(char c, boolean isComma) {
        switch (c) {
            case '&':
                return "&amp;";
            case '[':
                return "&#91;";
            case ']':
                return "&#93;";
            case ',':
                if (isComma)
                    return "&#44;";
            default:
                return String.valueOf(c);
        }
    }

    /**
     * 特殊字符，反转义
     *
     * @param code 要反转义的字符串
     * @return 反转义后的字符串
     */
    public static String decode(String code) {
        code = StringUtils.stringReplace(code, "&#91;", "[");
        code = StringUtils.stringReplace(code, "&#93;", "]");
        code = StringUtils.stringReplace(code, "&#44;", ",");
        code = StringUtils.stringReplace(code, "&amp;", "&");
        return code;
    }

    /**
     * 解析CQ码
     *
     * @param code 要解析CQ码
     * @return 解析完成的CQ码
     */
    public CoolQCode analysis(String code) {
        return new CoolQCode(code);
    }

    /**
     * 表情QQ表情(face)
     *
     * @param faceId 表情ID
     * @return CQ码
     */
    public String face(int... faceId) {
        if (faceId == null || faceId.length == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (long id : faceId) {
            sb.append("[CQ:face,id=");
            sb.append(id);
            sb.append("]");
        }
        return sb.toString();
    }

    /**
     * 从CQ码中获取QQ表情ID，错误返回 -1
     *
     * @param code CQ码
     * @return 表情ID
     */
    public int getFaceId(String code) {
        return new CoolQCode(code).getFaceId();
    }

    /**
     * 从CQ码中获取所有QQ表情ID
     *
     * @param code CQ码
     * @return 所有表情ID
     */
    public List<Integer> getFaceIds(String code) {
        return new CoolQCode(code).getFaceIds();
    }

    /**
     * emoji表情(emoji)
     *
     * @param emojiId emoji的unicode编号
     * @return CQ码
     */
    public String emoji(int... emojiId) {
        if (emojiId == null || emojiId.length == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (long id : emojiId) {
            sb.append("[CQ:emoji,id=");
            sb.append(id);
            sb.append("]");
        }
        return sb.toString();
    }

    /**
     * 从CQ码中获取emoji表情ID，错误返回 -1
     *
     * @param code CQ码
     * @return emoji的unicode编号
     */
    public int getEmoji(String code) {
        return new CoolQCode(code).getEmoji();
    }

    /**
     * 从CQ码中获取所有emoji表情ID
     *
     * @param code CQ码
     * @return 所有emoji表情ID
     */
    public List<Integer> getEmojis(String code) {
        return new CoolQCode(code).getEmojis();
    }

    /**
     * 提醒某人，@某人(at)
     *
     * @param qqId      qq号，-1 为全体
     * @param isNoSpace At后添加空格，可使At更规范美观。如果不需要添加空格，请置本参数为true。
     * @return CQ码
     * @see #at(long...) @某人(末尾加空格)
     */
    public String at(long qqId, boolean isNoSpace) {
        return StringUtils.stringConcat("[CQ:at,qq=", qqId == -1 ? "all" : qqId, "]", isNoSpace ? "" : " ");
    }

    /**
     * 提醒某人，@某人(at)，末尾有空格<br>
     * At后添加空格，可使At更规范美观。如果不需要添加空格，请参考{@link #at(long, boolean) @某人}
     *
     * @param qqId qq号，-1 为全体
     * @return CQ码
     * @see #at(long, boolean) @某人
     */
    public String at(long... qqId) {
        if (qqId == null || qqId.length == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (long id : qqId) {
            sb.append("[CQ:at,qq=");
            sb.append(id == -1 ? "all" : id);
            sb.append("] ");
        }
        return sb.toString();
    }

    /**
     * 从CQ码中获取at的QQ号，-1 为全体，错误为 -1000
     *
     * @param code CQ码
     * @return qq号，-1 为全体，错误为 -1000
     */
    public long getAt(String code) {
        return new CoolQCode(code).getAt();
    }

    /**
     * 从CQ码中获取所有at的QQ号，-1 为全体
     *
     * @param code CQ码
     * @return qq号集合，-1 为全体
     */
    public List<Long> getAts(String code) {
        return new CoolQCode(code).getAts();
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
     * 判断CQ码中是否包含 窗口抖动(shake)
     *
     * @param code CQ码
     * @return 是否包含
     */
    public boolean isShake(String code) {
        return new CoolQCode(code).isShake();
    }

    /**
     * 匿名发消息(anonymous) - 仅支持群
     *
     * @param ignore 是否不强制，如果希望匿名失败时，将消息转为普通消息发送(而不是取消发送)，请置本参数为true。
     * @return CQ码
     */
    public String anonymous(boolean ignore) {
        return StringUtils.stringConcat("[CQ:anonymous", ignore ? ",ignore=true" : "", "] ");
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
        return StringUtils.stringConcat("[CQ:music,id=", musicId, ",type=", StringUtils.isEmpty(type) ? "qq" : encode(type, true), style ? ",style=1" : "", "]");
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
        if (!StringUtils.isEmpty(title))
            sb.append(",title=").append(encode(title, true));
        if (!StringUtils.isEmpty(content))
            sb.append(",content=").append(encode(content, true));
        if (!StringUtils.isEmpty(image))
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
        return StringUtils.stringConcat("[CQ:contact,type=", encode(type, true), ",id=", id, "]");
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
        if (!StringUtils.isEmpty(title))
            sb.append(",title=").append(encode(title, true));
        if (!StringUtils.isEmpty(content))
            sb.append(",content=").append(encode(content, true));
        if (!StringUtils.isEmpty(image))
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
        return StringUtils.stringConcat("[CQ:image,file=", encode(file, true), "]");
    }

    /**
     * 发送图片(image)
     *
     * @param path 要发送的图片文件对象，位置随意
     * @return CQ码
     * @throws IOException IO异常
     */
    public String image(File path) throws IOException {
        CQImage image = new CQImage(path, false);
        path = image.download("data/image/", image.getName());
        path.deleteOnExit();
        return image(path.getName());
    }

    /**
     * 发送图片(image)
     *
     * @param image 要发送的CQImage对象
     * @return CQ码
     * @throws IOException IO异常
     */
    public String image(CQImage image) throws IOException {
        File path = new File("data/image/", image.getName());
        if (!image.getName().endsWith(".cqimg") || !path.isFile())
            image.download(path);
        path.deleteOnExit();
        return image(path.getName());
    }

    /**
     * 发送图片(image),使用GET请求
     *
     * @param url 要发送的图片链接
     * @return CQ码
     * @throws IOException IO异常
     */
    public String imageUseGet(String url) throws IOException {
        return imageUseGet(url, null);
    }

    /**
     * 发送图片(image),使用GET请求
     *
     * @param url               要发送的图片链接
     * @param requestProperties 请求头信息
     * @return CQ码
     * @throws IOException IO异常
     */
    public String imageUseGet(String url, Map<String, List<String>> requestProperties) throws IOException {
        CQImage image = new CQImage(url);
        File path = image.downloadUseGet(new File("data/image/", image.getName()), requestProperties);
        path.deleteOnExit();
        return image(path.getName());
    }

    /**
     * 发送图片(image),使用POST请求
     *
     * @param url               要发送的图片链接
     * @param requestProperties 请求头信息
     * @param bytes             请求的数据
     * @return CQ码
     * @throws IOException IO异常
     */
    public String imageUsePost(String url, Map<String, List<String>> requestProperties, byte[] bytes) throws IOException {
        CQImage image = new CQImage(url);
        File path = image.downloadUsePost(new File("data/image/", image.getName()), requestProperties, bytes);
        path.deleteOnExit();
        return image(path.getName());
    }

    /**
     * 从CQ码中获取图片的路径，如 [CQ:image,file=1.jpg] 则返回 1.jpg
     *
     * @param code CQ码
     * @return 图片路径，如 [CQ:image,file=1.jpg] 则返回 1.jpg，错误返回 {@code null}
     */
    public String getImage(String code) {
        return new CoolQCode(code).getImage();
    }

    /**
     * 从CQ码中获取图片的 CQImage 对象
     *
     * @param code CQ码
     * @return CQImage 对象，错误返回 {@code null}
     */
    public CQImage getCQImage(String code) {
        return new CoolQCode(code).getCQImage();
    }

    /**
     * 从CQ码中获取 所有 CQImage 对象
     *
     * @param code CQ码
     * @return CQImage 对象集合
     */
    public List<CQImage> getCQImages(String code) {
        return new CoolQCode(code).getCQImages();
    }

    /**
     * 发送语音(record)
     *
     * @param file  将语音放在 data\record 下，并填写相对路径。如 data\record\1.amr 则填写 1.amr
     * @param magic 是否是魔法语音
     * @return CQ码
     */
    public String record(String file, boolean magic) {
        return StringUtils.stringConcat("[CQ:record,file=", encode(file, true), magic ? ",magic=true" : "", "]");
    }

    /**
     * 发送语音(record)
     *
     * @param file 将语音放在 data\record 下，并填写相对路径。如 data\record\1.amr 则填写 1.amr
     * @return CQ码
     */
    public String record(String file) {
        return StringUtils.stringConcat("[CQ:record,file=", encode(file, true), "]");
    }

    /**
     * 从CQ码中获取语音的路径，如 [CQ:record,file=1.amr] 则返回 1.amr
     *
     * @param code CQ码
     * @return 语音路径，如 [CQ:record,file=1.amr] 则返回 1.amr，错误返回 {@code null}
     */
    public String getRecord(String code) {
        return new CoolQCode(code).getRecord();
    }

}
