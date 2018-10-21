package com.sobte.cqp.jcq.entity;

/**
 * Created by Sobte on 2018/3/12.
 * Time: 15:21
 * Email: i@sobte.me
 * QQ信息
 */
public class QQInfo {

    /**
     * QQ
     */
    long qqId;
    /**
     * 性别 0/男性 1/女性
     */
    int gender;
    /**
     * 年龄
     */
    int age;
    /**
     * 昵称
     */
    String nick;

    public QQInfo() {
    }

    public QQInfo(long qqId) {
        this.qqId = qqId;
    }

    public QQInfo(long qqId, int gender, int age, String nick) {
        this.qqId = qqId;
        this.gender = gender;
        this.age = age;
        this.nick = nick;
    }

    @Deprecated
    public long getQqId() {
        return qqId;
    }

    @Deprecated
    public void setQqId(long qqId) {
        this.qqId = qqId;
    }

    public void setQQId(long qqId) {
        this.qqId = qqId;
    }

    public long getQQId() {
        return qqId;
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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * 数据转QQ信息
     *
     * @param bytes 数据
     * @return QQ信息
     */
    public static QQInfo toQQInfo(byte[] bytes) {
        return toQQInfo(bytes, new QQInfo());
    }

    /**
     * 数据转QQ信息
     *
     * @param bytes  数据
     * @param qqInfo QQ信息
     * @return QQ信息
     */
    public static QQInfo toQQInfo(byte[] bytes, QQInfo qqInfo) {
        if (bytes == null || qqInfo == null)
            return null;
        Pack pack = new Pack(bytes);
        qqInfo.qqId = pack.getLong();
        qqInfo.nick = pack.getLenStr();
        qqInfo.gender = pack.getInt();
        qqInfo.age = pack.getInt();
        return qqInfo;
    }

    @Override
    public String toString() {
        return "QQInfo{" +
                "qqId=" + qqId +
                ", gender=" + gender +
                ", age=" + age +
                ", nick=" + nick +
                '}';
    }
}
