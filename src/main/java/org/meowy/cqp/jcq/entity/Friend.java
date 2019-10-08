package org.meowy.cqp.jcq.entity;

import org.meowy.cqp.jcq.entity.enumerate.Gender;

/**
 * Created by Sobte on 2019/10/8.
 * Time: 22:23
 * Email: i@sobte.me
 * 好友
 */
public class Friend extends QQInfo {

    /**
     * 备注
     */
    private String remark;

    /**
     * QQInfo 无参构造函数
     */
    public Friend() {
    }

    /**
     * QQInfo 有参构造函数
     *
     * @param qqId   QQ号
     * @param gender 性别
     * @param age    年龄
     * @param nick   昵称
     */
    public Friend(long qqId, Gender gender, int age, String nick, String remark) {
        super(qqId, gender, age, nick);
        this.remark = remark;
    }

    /**
     * 获取备注
     *
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 数据转好友
     *
     * @param bytes 数据
     * @return 好友
     */
    public static Friend toFriend(byte[] bytes) {
        return toFriend(bytes, new Friend());
    }

    /**
     * 数据转好友
     *
     * @param bytes  数据
     * @param friend 好友
     * @return 好友
     */
    public static Friend toFriend(byte[] bytes, Friend friend) {
        if (bytes == null || bytes.length < 12)
            return null;
        Pack pack = new Pack(bytes);
        friend.setQQId(pack.getLong());
        friend.setNick(pack.getLenStr());
        friend.setRemark(pack.getLenStr());
        return friend;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "remark='" + remark + '\'' +
                ", qqId=" + qqId +
                ", gender=" + gender +
                ", age=" + age +
                ", nick='" + nick + '\'' +
                '}';
    }
}
