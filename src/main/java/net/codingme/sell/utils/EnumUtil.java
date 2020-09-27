package net.codingme.sell.utils;

import net.codingme.sell.enums.CodeEnum;

/**
 * <p>
 * 枚举工具类
 *
 * @Author niujinpeng
 * @Date 2019/8/14 13:58
 */
public class EnumUtil {

    /**
     * 根据枚举码code获取枚举对象
     * 
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
