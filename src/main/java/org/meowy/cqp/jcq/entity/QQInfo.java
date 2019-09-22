package org.meowy.cqp.jcq.entity;

import org.meowy.cqp.jcq.entity.enumerate.Gender;

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
    Gender gender;
    /**
     * 年龄
     */
    int age;
    /**
     * 昵称
     */
    String nick;

    /**
     * QQInfo 无参构造函数
     */
    public QQInfo() {
    }

    /**
     * QQInfo 有参构造函数
     *
     * @param qqId QQ号
     */
    public QQInfo(long qqId) {
        this.qqId = qqId;
    }

    /**
     * QQInfo 有参构造函数
     *
     * @param qqId   QQ号
     * @param gender 性别
     * @param age    年龄
     * @param nick   昵称
     */
    public QQInfo(long qqId, Gender gender, int age, String nick) {
        this.qqId = qqId;
        this.gender = gender;
        this.age = age;
        this.nick = nick;
    }

    /**
     * 设置QQ号
     *
     * @param qqId QQ号
     */
    public void setQQId(long qqId) {
        this.qqId = qqId;
    }

    /**
     * 获取QQ号
     *
     * @return QQ号
     */
    public long getQQId() {
        return qqId;
    }

    /**
     * 获取性别 0/男性 1/女性
     *
     * @return 性别
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * 获取年龄
     *
     * @return 年龄
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取昵称
     *
     * @return 昵称
     */
    public String getNick() {
        return nick;
    }

    /**
     * 设置昵称
     *
     * @param nick 昵称
     */
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
        qqInfo.gender = Gender.valueOf(pack.getInt());
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
