package com.sobte.cqp.jcq.entity;

import java.util.Date;

/**
 * Created by Sobte on 2018/3/12.
 * Time: 14:06
 * Email: i@sobte.me
 * 群成员
 */
public class Member {

    /**
     * 群号
     */
    private long groupId;
    /**
     * qq
     */
    private long qqId;
    /**
     * 昵称
     */
    private String nick;
    /**
     * 名片
     */
    private String card;
    /**
     * 性别 0/男性 1/女性
     */
    private int gender;
    /**
     * 年龄
     */
    private int age;
    /**
     * 地区
     */
    private String area;
    /**
     * 加群时间
     */
    private Date clear;
    /**
     * 最后发言
     */
    private Date lastTime;
    /**
     * 等级名称
     */
    private String levelName;
    /**
     * 管理权限 1/成员 2/管理员 3/群主
     */
    private int Authority;
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

    public Member(long groupId, long qqId, String nick, String card, int gender, int age, String area, Date clear, Date lastTime, String levelName, int authority, String title, Date titleExpire, boolean bad, boolean modifyCard) {
        this.groupId = groupId;
        this.qqId = qqId;
        this.nick = nick;
        this.card = card;
        this.gender = gender;
        this.age = age;
        this.area = area;
        this.clear = clear;
        this.lastTime = lastTime;
        this.levelName = levelName;
        Authority = authority;
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

    public long getQqId() {
        return qqId;
    }

    public void setQqId(long qqId) {
        this.qqId = qqId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    /**
     * 性别 0/男性 1/女性
     *
     * @return 性别
     */
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getAddTime() {
        return clear;
    }

    public void setAddTime(Date clear) {
        this.clear = clear;
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
        return Authority;
    }

    public void setAuthority(int authority) {
        Authority = authority;
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
        if (bytes == null || bytes.length < 40)
            return null;
        Pack pack = new Pack(bytes);
        Member member = new Member();
        member.setGroupId(pack.getLong());
        member.setQqId(pack.getLong());
        member.setNick(pack.getLenStr());
        member.setCard(pack.getLenStr());
        member.setGender(pack.getInt());
        member.setAge(pack.getInt());
        member.setArea(pack.getLenStr());
        member.setAddTime(new Date(pack.getInt() * 1000L));
        member.setLastTime(new Date(pack.getInt() * 1000L));
        member.setLevelName(pack.getLenStr());
        member.setAuthority(pack.getInt());
        member.setBad(pack.getInt() == 1);
        member.setTitle(pack.getLenStr());
        int expire = pack.getInt();
        member.setTitleExpire(expire == -1 ? null : new Date(expire * 1000));
        member.setModifyCard(pack.getInt() == 1);
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
                ", clear=" + clear +
                ", lastTime=" + lastTime +
                ", levelName='" + levelName + '\'' +
                ", Authority=" + Authority +
                ", title='" + title + '\'' +
                ", titleExpire=" + titleExpire +
                ", bad=" + bad +
                ", modifyCard=" + modifyCard +
                '}';
    }
}
