package top.lucas9.crowdfunding.utils;

import java.util.Collection;
import java.util.Map;

public class CheckEffective {
    /**
     * 判断明文字符串是否有效
     * @param string 待判断的字符串
     * @return true表示有效， false表示无效
     */
    public static boolean stringEffective(String string) {
        return null != string && string.length() > 0;
    }

    /**
     * 判断明文字符串是否有效
     * @param collection 待判断的集合
     * @return true表示有效， false表示无效
     */
    public static <E> boolean collectionEffective(Collection<E> collection) {
        return null != collection && collection.size() > 0;
    }

    /**
     * 判断明文字符串是否有效
     * @param map 待判断的 map
     * @return true表示有效， false表示无效
     */
    public static <K, V> boolean mapEffective(Map<K, V> map) {
        return null != map && map.size() > 0;
    }
}
