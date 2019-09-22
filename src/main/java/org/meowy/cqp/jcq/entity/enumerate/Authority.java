package org.meowy.cqp.jcq.entity.enumerate;

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
     * 权限：成员
     */
    MEMBER(1),
    /**
     * 权限：管理员
     */
    ADMIN(2),
    /**
     * 权限：群主
     */
    OWNER(3);
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
            case 2:
                return ADMIN;
            case 3:
                return OWNER;
            case 1:
            default:
                return MEMBER;
        }
    }

}
