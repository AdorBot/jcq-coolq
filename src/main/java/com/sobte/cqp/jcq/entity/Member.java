package com.sobte.cqp.jcq.entity;

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
    private int authority;
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

    public Member() {
    }

    public Member(long groupId, long qqId) {
        super(qqId);
        this.groupId = groupId;
    }

    public Member(long groupId, long qqId, String nick, String card, int gender, int age, String area, Date addTime, Date lastTime, String levelName, int authority, String title, Date titleExpire, boolean bad, boolean modifyCard) {
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

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * 管理权限 1/成员 2/管理员 3/群主
     *
     * @return 权限id
     */
    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 专属头衔过期时间 null 代表不过期
     *
     * @return 过期时间
     */
    public Date getTitleExpire() {
        return titleExpire;
    }

    public void setTitleExpire(Date titleExpire) {
        this.titleExpire = titleExpire;
    }

    public boolean isBad() {
        return bad;
    }

    public void setBad(boolean bad) {
        this.bad = bad;
    }

    public boolean isModifyCard() {
        return modifyCard;
    }

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
        member.gender = pack.getInt();
        member.age = pack.getInt();
        member.area = pack.getLenStr();
        member.addTime = new Date(pack.getInt() * 1000L);
        member.lastTime = new Date(pack.getInt() * 1000L);
        member.levelName = pack.getLenStr();
        member.authority = pack.getInt();
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
