package org.meowy.cqp.jcq.entity;

import org.meowy.cqp.jcq.entity.enumerate.Authority;
import org.meowy.cqp.jcq.entity.enumerate.Gender;

import java.util.Date;

/**
 * Created by Sobte on 2018/3/12.
 * Time: 14:06
 * Email: i@sobte.me
 * 群成员
 */
public class Member extends QQInfo {

    /**
     * 群号
     */
    private long groupId;
    /**
     * 名片
     */
    private String card;
    /**
     * 地区
     */
    private String area;
    /**
     * 加群时间
     */
    private Date addTime;
    /**
     * 最后发言时间
     */
    private Date lastTime;
    /**
     * 等级名称
     */
    private String levelName;
    /**
     * 管理权限 1/成员 2/管理员 3/群主
     */
    private Authority authority;
    /**
     * 专属头衔
     */
    private String title;
    /**
     * 专属头衔过期时间 null 代表不过期
     */
    private Date titleExpire;
    /**
     * 不良记录成员
     */
    private boolean bad;
    /**
     * 允许修改名片
     */
    private boolean modifyCard;

    /**
     * Member无参构造函数
     */
    public Member() {
    }

    /**
     * Member有参构造函数
     *
     * @param groupId 群号
     * @param qqId    QQ号
     */
    public Member(long groupId, long qqId) {
        super(qqId);
        this.groupId = groupId;
    }

    /**
     * Member有参构造函数
     *
     * @param groupId     群号
     * @param qqId        QQ号
     * @param nick        昵称
     * @param card        群名片
     * @param gender      性别
     * @param age         年龄
     * @param area        地区
     * @param addTime     加群时间
     * @param lastTime    最后发言时间
     * @param levelName   等级名称
     * @param authority   管理权限 1/成员 2/管理员 3/群主
     * @param title       专属头衔
     * @param titleExpire 专属头衔过期时间 null 代表不过期
     * @param bad         不良记录成员
     * @param modifyCard  允许修改名片
     */
    public Member(long groupId, long qqId, String nick, String card, Gender gender, int age, String area, Date addTime, Date lastTime, String levelName, Authority authority, String title, Date titleExpire, boolean bad, boolean modifyCard) {
        this.groupId = groupId;
        this.qqId = qqId;
        this.nick = nick;
        this.card = card;
        this.gender = gender;
        this.age = age;
        this.area = area;
        this.addTime = addTime;
        this.lastTime = lastTime;
        this.levelName = levelName;
        this.authority = authority;
        this.title = title;
        this.titleExpire = titleExpire;
        this.bad = bad;
        this.modifyCard = modifyCard;
    }

    /**
     * 获取群号
     *
     * @return 群号
     */
    public long getGroupId() {
        return groupId;
    }

    /**
     * 设置群号
     *
     * @param groupId 群号
     */
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取群名片
     *
     * @return 群名片
     */
    public String getCard() {
        return card;
    }

    /**
     * 设置群名片
     *
     * @param card 群名片
     */
    public void setCard(String card) {
        this.card = card;
    }

    /**
     * 获取地区
     *
     * @return 地区
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置地区
     *
     * @param area 地区
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取进群时间
     *
     * @return 进群时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置进群时间
     *
     * @param addTime 进群时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取最后发言时间
     *
     * @return 最后发言时间
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * 设置最后发言时间
     *
     * @param lastTime 最后发言时间
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取等级名称
     *
     * @return 等级名称
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * 设置等级名称
     *
     * @param levelName 等级名称
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * 获取管理权限 1/成员 2/管理员 3/群主
     *
     * @return 权限
     */
    public Authority getAuthority() {
        return authority;
    }

    /**
     * 设置管理权限 1/成员 2/管理员 3/群主
     *
     * @param authority 管理权限
     */
    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    /**
     * 获取专属头衔
     *
     * @return 专属头衔
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置专属头衔
     *
     * @param title 专属头衔
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取专属头衔过期时间 null 代表不过期
     *
     * @return 过期时间
     */
    public Date getTitleExpire() {
        return titleExpire;
    }

    /**
     * 设置专属头衔过期时间 null 代表不过期
     *
     * @param titleExpire 过期时间
     */
    public void setTitleExpire(Date titleExpire) {
        this.titleExpire = titleExpire;
    }

    /**
     * 获取不良记录成员状况
     *
     * @return 不良记录成员状况
     */
    public boolean isBad() {
        return bad;
    }

    /**
     * 设置不良记录成员状况
     *
     * @param bad 不良记录成员状况
     */
    public void setBad(boolean bad) {
        this.bad = bad;
    }

    /**
     * 获取是否允许修改群名片
     *
     * @return 是否允许修改群名片
     */
    public boolean isModifyCard() {
        return modifyCard;
    }

    /**
     * 设置是否允许修改群名片
     *
     * @param modifyCard 是否允许修改群名片
     */
    public void setModifyCard(boolean modifyCard) {
        this.modifyCard = modifyCard;
    }

    public static Member toMember(byte[] bytes) {
        return toMember(bytes, new Member());
    }

    public static Member toMember(byte[] bytes, Member member) {
        if (bytes == null || bytes.length < 40 || member == null)
            return null;
        Pack pack = new Pack(bytes);
        member.groupId = pack.getLong();
        member.qqId = pack.getLong();
        member.nick = pack.getLenStr();
        member.card = pack.getLenStr();
        member.gender = Gender.valueOf(pack.getInt());
        member.age = pack.getInt();
        member.area = pack.getLenStr();
        member.addTime = new Date(pack.getInt() * 1000L);
        member.lastTime = new Date(pack.getInt() * 1000L);
        member.levelName = pack.getLenStr();
        member.authority = Authority.valueOf(pack.getInt());
        member.bad = pack.getInt() == 1;
        member.title = pack.getLenStr();
        int expire = pack.getInt();
        member.titleExpire = expire == -1 ? null : new Date(expire * 1000);
        member.modifyCard = pack.getInt() == 1;
        return member;
    }

    @Override
    public String toString() {
        return "Member{" +
                "groupId=" + groupId +
                ", qqId=" + qqId +
                ", nick='" + nick + '\'' +
                ", card='" + card + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", area='" + area + '\'' +
                ", addTime=" + addTime +
                ", lastTime=" + lastTime +
                ", levelName='" + levelName + '\'' +
                ", authority=" + authority +
                ", title='" + title + '\'' +
                ", titleExpire=" + titleExpire +
                ", bad=" + bad +
                ", modifyCard=" + modifyCard +
                '}';
    }
}
