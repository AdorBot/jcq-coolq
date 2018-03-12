package com.sobte.cqp.jcq.entity;

import java.nio.ByteBuffer;

/**
 * Created by Sobte on 2018/3/11.
 * Time: 23:08
 * Email: i@sobte.me
 * 数据包处理
 */
public class Pack {

    private ByteBuffer date;

    public Pack(byte[] date) {
        this.date = ByteBuffer.wrap(date);
    }

    public Pack() {
        this.date = ByteBuffer.allocate(0);
    }

    public void setDate(byte[] date) {
        this.date = ByteBuffer.wrap(date);
    }

    /**
     * 清空
     */
    public void empty() {
        date = ByteBuffer.allocate(0);
    }

    /**
     * 取剩下所有数据
     *
     * @return 剩下数据
     */
    public byte[] getRemainingAll() {
        byte[] bytes = new byte[date.remaining()];
        date.get(bytes);
        return bytes;
    }

    /**
     * 取全部数据
     *
     * @return 全部数据
     */
    public byte[] getAll() {
        return date.array();
    }

    /**
     * 取指定长度字节
     *
     * @param len 长度
     * @return 数据
     */
    public byte[] getBin(int len) {
        byte[] bytes = new byte[len];
        date.get(bytes);
        return bytes;
    }

    /**
     * 添加数据
     *
     * @param bytes 数据
     */
    public void setBin(byte[] bytes) {
        date.put(bytes);
    }

    /**
     * 取字节
     *
     * @return 字节
     */
    public byte getByte() {
        return date.get();
    }

    /**
     * 添加单字节
     *
     * @param b 字节
     */
    public void setByte(byte b) {
        date.put(b);
    }

    /**
     * 取整数
     *
     * @return 整数
     */
    public int getInt() {
        return date.getInt();
    }

    /**
     * 添加整数
     *
     * @param i 整数
     */
    public void setInt(int i) {
        date.putInt(i);
    }

    /**
     * 取长整数
     *
     * @return 长整数
     */
    public long getLong() {
        return date.getLong();
    }

    /**
     * 添加长整数
     *
     * @param l 长整数
     */
    public void setLong(long l) {
        date.putLong(l);
    }

    /**
     * 取短整数
     *
     * @return 短整数
     */
    public short getShort() {
        return date.getShort();
    }

    /**
     * 添加短整数
     *
     * @param s 短整数
     */
    public void setShort(short s) {
        date.putShort(s);
    }

    /**
     * 添加字符串
     *
     * @param s 字符串
     */
    public void setStr(String s) {
        date.put(s.getBytes());
    }

    /**
     * 取Ascii
     *
     * @return 字符串
     */
    public String getLenStr() {
        short len = date.getShort();
        byte[] bytes = new byte[len];
        date.get(bytes);
        return new String(bytes);
    }

    /**
     * 添加Ascii
     *
     * @param s 字符串
     */
    public void setLenStr(String s) {
        byte[] bytes = s.getBytes();
        short len = (short) bytes.length;
        date.putShort(len);
        date.put(bytes);
    }

    /**
     * 取剩下长度
     *
     * @return 剩下长度
     */
    public int getRemainingLen() {
        return date.remaining();
    }

    /**
     * 取长度
     *
     * @return 长度
     */
    public int getLen() {
        return date.limit();
    }

    /**
     * 取令牌
     *
     * @return 令牌数据
     */
    public byte[] getToken() {
        short len = date.getShort();
        byte[] bytes = new byte[len];
        date.get(bytes);
        return bytes;
    }

    /**
     * 设置令牌
     *
     * @param bytes 令牌数据
     */
    public void setToken(byte[] bytes) {
        short len = (short) bytes.length;
        date.putShort(len);
        date.put(bytes);
    }

}
