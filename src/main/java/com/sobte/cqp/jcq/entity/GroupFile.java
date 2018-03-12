package com.sobte.cqp.jcq.entity;

/**
 * Created by Sobte on 2018/3/12.
 * Time: 14:27
 * Email: i@sobte.me
 * 群文件
 */
public class GroupFile {

    /**
     * 文件名
     */
    private String name;
    /**
     * 文件Id
     */
    private String id;
    /**
     * busid
     */
    private int busid;
    /**
     * 大小
     */
    private long size;

    public GroupFile() {
    }

    public GroupFile(String name, String id, int busid, long size) {
        this.name = name;
        this.id = id;
        this.busid = busid;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBusid() {
        return busid;
    }

    public void setBusid(int busid) {
        this.busid = busid;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "GroupFile{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", busid=" + busid +
                ", size=" + size +
                '}';
    }

}
