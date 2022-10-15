package org.gin;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Map工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/9/23 17:52
 **/
@SuppressWarnings("unused")
public class MapUtils {
    /**
     * 转换一个集合到一个HashMap
     * @param collection 集合
     * @param getKey 获取唯一key的方法
     * @param getValue 获取value的方法
     * @return HashMap
     * @param <K> key类型
     * @param <V> value类型
     * @param <T> 源类型
     */
    public static <K, V, T> HashMap<K,V> collectionToMap(Collection<T> collection, Function<T,K> getKey, Function<T,V> getValue){
        final HashMap<K, V> map = new HashMap<>();
        if (collection==null || collection.size()==0) {
            return map;
        }
        collection.forEach(item->{
            final K key = getKey.apply(item);
            final V value = getValue.apply(item);
            map.put(key,value);
        });
        return map;
    }

    /**
     * 生成一个单个字段的HashMap
     * @param key key
     * @param value value
     * @return HashMap
     * @param <K> key类型
     * @param <V> value类型
     */
    public static <K,V> HashMap<K,V> singleTon(K key,V value){
        final HashMap<K, V> map = new HashMap<>(1);
        map.put(key,value);
        return map;
    }
}
