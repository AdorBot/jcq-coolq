package com.sobte.cqp.jcq.entity;

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
        return group;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
