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
    private long qqId;
    /**
     * 性别 0/男性 1/女性
     */
    private int gender;
    /**
     * 年龄
     */
    private int age;
    /**
     * 昵称
     */
    private String nick;

    public QQInfo() {
    }

    public QQInfo(long qqId, int gender, int age, String nick) {
        this.qqId = qqId;
        this.gender = gender;
        this.age = age;
        this.nick = nick;
    }

    public long getQqId() {
        return qqId;
    }

    public void setQqId(long qqId) {
        this.qqId = qqId;
    }

    /**
     * 性别 0/男性 1/女性
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

    public static QQInfo toQQInfo(byte[] bytes) {
        Pack pack = new Pack(bytes);
        QQInfo qqInfo = new QQInfo();
        qqInfo.setQqId(pack.getLong());
        qqInfo.setNick(pack.getLenStr());
        qqInfo.setGender(pack.getInt());
        qqInfo.setAge(pack.getInt());
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
