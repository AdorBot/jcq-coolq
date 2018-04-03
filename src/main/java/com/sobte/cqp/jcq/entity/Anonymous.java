package com.sobte.cqp.jcq.entity;

import java.util.Arrays;

/**
 * Created by Sobte on 2018/3/12.
 * Time: 14:24
 * Email: i@sobte.me
 * 匿名用户
 */
public class Anonymous {

    /**
     * 匿名用户的标识
     */
    private long aid;
    /**
     * 代号 如“大力鬼王”
     */
    private String name;
    /**
     * 令牌
     */
    private byte[] token;

    public Anonymous() {
    }

    public Anonymous(long aid, String name, byte[] token) {
        this.aid = aid;
        this.name = name;
        this.token = token;
    }

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    /**
     * 代号 如“大力鬼王”
     *
     * @return 匿名名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    /**
     * 数据转匿名
     *
     * @param bytes 数据
     * @return 匿名
     */
    public static Anonymous toAnonymous(byte[] bytes) {
        if (bytes == null || bytes.length < 12)
            return null;
        Pack pack = new Pack(bytes);
        Anonymous anonymous = new Anonymous();
        anonymous.setAid(pack.getLong());
        anonymous.setName(pack.getLenStr());
        anonymous.setToken(pack.getToken());
        return anonymous;
    }

    @Override
    public String toString() {
        return "Anonymous{" +
                "aid=" + aid +
                ", name='" + name + '\'' +
                ", token=" + Arrays.toString(token) +
                '}';
    }
}
