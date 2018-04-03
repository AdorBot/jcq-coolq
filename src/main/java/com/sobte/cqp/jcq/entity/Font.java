package com.sobte.cqp.jcq.entity;

/**
 * Created by Sobte on 2018/3/12.
 * Time: 13:57
 * Email: i@sobte.me
 * 字体
 */
public class Font {

    /**
     * 名称
     */
    private String name;
    /**
     * 字号
     */
    private int size;
    /**
     * 颜色
     */
    private int color;
    /**
     * 样式 粗体：1 斜体：2 下划线：4
     */
    private int style;
    /**
     * 气泡
     */
    private int bubble;

    public Font() {
    }

    public Font(String name, int size, int color, int style, int bubble) {
        this.name = name;
        this.size = size;
        this.color = color;
        this.style = style;
        this.bubble = bubble;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 粗体：1 斜体：2 下划线：4
     *
     * @return 大小
     */
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public int getBubble() {
        return bubble;
    }

    public void setBubble(int bubble) {
        this.bubble = bubble;
    }

    @Override
    public String toString() {
        return "Font{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", color=" + color +
                ", style=" + style +
                ", bubble=" + bubble +
                '}';
    }

}
