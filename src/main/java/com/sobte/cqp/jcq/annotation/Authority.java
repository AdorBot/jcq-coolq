package com.sobte.cqp.jcq.annotation;

/**
 * Created by Sobte on 2018/7/15.<br>
 * Time: 2018/7/15 15:33<br>
 * Email: i@sobte.me<br>
 * 管理权限
 *
 * @author Sobte
 */
public enum Authority {

    /**
     * 成员
     */
    Member(1),
    /**
     * 管理员
     */
    Admin(2),
    /**
     * 群主
     */
    Owner(3);
    /**
     * 管理权限常量值 1/成员 2/管理员 3/群主
     */
    private int value;

    Authority(int value) {
        this.value = value;
    }

    /**
     * 获取权限常量值 1/成员 2/管理员 3/群主
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
    public static Authority valueOf(int value) {
        switch (value) {
            case 1:
                return Member;
            case 2:
                return Admin;
            case 3:
                return Owner;
            default:
                return null;
        }
    }

}
