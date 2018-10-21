package com.sobte.cqp.jcq.annotation;

/**
 * Created by Sobte on 2018/7/13.<br>
 * Time: 2018/7/13 14:15<br>
 * Email: i@sobte.me<br>
 * 性别
 * @author Sobte
 */
public enum Gender {

    /**
     * 性别：男
     */
    Male(0),
    /**
     * 性别：女
     */
    Female(1);
    /**
     * 性别常量值 0/男性 1/女性
     */
    private int value;

    Gender(int value) {
        this.value = value;
    }

    /**
     * 获取性别常量值 0/男性 1/女性
     *
     * @return 值
     */
    public int value() {
        return value;
    }

    /**
     * 可以将值转换为枚举
     *
     * @param value 值
     * @return 枚举
     */
    public static Gender valueOf(int value) {
        switch (value) {
            case 0:
                return Male;
            case 1:
                return Female;
            default:
                return null;
        }
    }

}
