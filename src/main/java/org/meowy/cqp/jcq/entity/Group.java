package org.meowy.cqp.jcq.entity;

import java.nio.BufferUnderflowException;

/**
 * Created by Sobte on 2018/3/12.
 * Time: 14:03
 * Email: i@sobte.me
 * 群
 */
public class Group {

    /**
     * 群号
     */
    private long id;
    /**
     * 群名称
     */
    private String name;
    /**
     * 当前人数 (仅“取群信息”支持获取)
     */
    private int count;
    /**
     * 人数上限 (仅“取群信息”支持获取)
     */
    private int countMax;

    public Group() {
    }

    public Group(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 群号
     *
     * @return 群号
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * 群名称
     *
     * @return 名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取当前群员人数
     *
     * @return 当前人数
     */
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 获取当前群员人数上限
     *
     * @return 人数上限
     */
    public int getCountMax() {
        return countMax;
    }

    public void setCountMax(int countMax) {
        this.countMax = countMax;
    }

    /**
     * 数据转单群
     *
     * @param bytes 数据
     * @return 群
     */
    public static Group toGroup(byte[] bytes) {
        return toGroup(bytes, new Group());
    }

    /**
     * 数据转单群
     *
     * @param bytes 数据
     * @param group 群
     * @return 群
     */
    public static Group toGroup(byte[] bytes, Group group) {
        if (bytes == null || bytes.length < 10)
            return null;
        Pack pack = new Pack(bytes);
        group.setId(pack.getLong());
        group.setName(pack.getLenStr());
        try {
            group.setCount(pack.getInt());
            group.setCountMax(pack.getInt());
        } catch (BufferUnderflowException ignored) {
            // 忽略这个异常，读取数量错误的话，都是获取群列表转的信息
        }
        return group;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", countMax=" + countMax +
                '}';
    }
}
