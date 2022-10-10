package org.gin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 列表工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/8/3 17:31
 **/
public class CollectionUtils {
    /**
     * 转换一个集合到一个列表
     * @param collection 集合
     * @param func       转换方法
     * @param <D>        目标类型
     * @param <R>        原类型
     * @return 列表
     */
    public static <D, R> List<D> convert(Collection<R> collection, Function<R, D> func) {
        return collection.stream().map(func).collect(Collectors.toList());
    }

    /**
     * 判断一个集合是否为空
     * @param o 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> o) {
        return o == null || o.size() == 0;
    }

    /**
     * 判断一个集合是否为非空
     * @param o 集合
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Collection<?> o) {
        return !isEmpty(o);
    }

    /**
     * 将一个集合整理成树形结构
     * @param <T>         类
     * @param source      数据来源
     * @param getId       元素id获取方法
     * @param getParentId 父元素id获取方法
     * @param getChildren 获取子项目列表
     * @return 属性结构
     */
    public static <T> List<T> toTreeList(Collection<T> source,
                                         Function<T, Serializable> getId,
                                         Function<T, Serializable> getParentId,
                                         Function<T, List<T>> getChildren) {
        final ArrayList<T> res = new ArrayList<>();
        if (isEmpty(source)) {
            return res;
        }
        //创建字典
        HashMap<Serializable, T> map = new HashMap<>(source.size());
        source.forEach(item -> map.put(getId.apply(item), item));
        source.forEach(item -> {
            final Serializable parentId = getParentId.apply(item);
            if (parentId == null || "".equals(parentId)) {
                //无父元素id ，添加为根元素
                res.add(item);
            } else if (map.containsKey(parentId)) {
                //添加到父项目的子元素列表
                getChildren.apply(map.get(parentId)).add(item);
            }
        });
        return res;
    }
}
