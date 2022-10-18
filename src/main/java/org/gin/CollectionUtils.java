package org.gin;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 列表工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/8/3 17:31
 **/
@SuppressWarnings("unused")
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

    /**
     * 求并集
     * @param colA 集合A
     * @param colB 集合B
     * @param <T>  集合成员类型
     * @return 并集
     */
    public static <T> List<T> union(Collection<T> colA, Collection<T> colB) {
        final HashSet<T> set = new HashSet<>();
        set.addAll(colA);
        set.addAll(colB);
        return new ArrayList<>(set);
    }

    /**
     * 求交集
     * @param colA 集合A
     * @param colB 集合B
     * @param <T>  集合成员类型
     * @return 交集
     */
    public static <T> List<T> intersection(Collection<T> colA, Collection<T> colB) {
        assert colA != null;
        assert colB != null;
        return colA.stream().filter(colB::contains).collect(Collectors.toList());
    }

    /**
     * 交集的补集（析取）
     * @param colA 集合A
     * @param colB 集合B
     * @param <T>  集合成员类型
     * @return 交集
     */
    public static <T> List<T> disjunction(Collection<T> colA, Collection<T> colB) {
        final List<T> union = union(colA, colB);
        final List<T> intersection = intersection(colA, colB);

        return subtract(union, intersection);
    }

    /**
     * 差集（扣除）
     * @param colA 集合A
     * @param colB 集合B
     * @param <T>  集合成员类型
     * @return 差集（扣除）
     */
    public static <T> List<T> subtract(Collection<T> colA, Collection<T> colB) {
        return colA.stream().filter(o -> !colB.contains(o)).collect(Collectors.toList());
    }

}
