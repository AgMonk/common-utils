package org.gin.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.File;
import java.io.IOException;

/**
 * @author bx002
 */
@SuppressWarnings("unused")
public class JsonUtils {
    /**
     * 使用json格式输出一个对象
     * @param obj 对象
     */
    public static void printJson(Object obj) {
        System.err.println(obj2JsonString(obj));
    }

    /**
     * 使用json格式输出一个json字符串
     * @param jsonString json字符串
     */
    public static void printJson(String jsonString) {printJson(JSONObject.parse(jsonString));}

    /**
     * 将一个对象转换成json字符串(添加缩进)
     * @param obj 对象
     * @return json字符串
     */
    public static String obj2JsonString(Object obj) {
        return JSONObject.toJSONString(obj, SerializerFeature.PrettyFormat);
    }

    /**
     * 将一个对象以json格式写入一个文件
     * @param obj 对象
     * @param file 文件
     */
    public static void writeToFile(Object obj,File file) throws IOException {
        FileUtils.writeToFile(obj2JsonString(obj),file);
    }

    /**
     * 从文件中读取一个对象
     * @param file 文件
     * @param clazz 类型
     * @return 对象
     * @param <T> 对象类型
     * @throws IOException 异常
     */
    public static <T> T readFromFile(File file,Class<T> clazz) throws IOException {
        return JSONObject.parseObject(FileUtils.readFromFile(file),clazz);
    }

    /**
     * 从文件中读取一个对象
     * @param file 文件
     * @param clazz 类型
     * @return 对象
     * @param <T> 对象类型
     * @throws IOException 异常
     */
    public static <T> T readFromFile(File file, TypeReference<T> clazz) throws IOException {
        return JSONObject.parseObject(FileUtils.readFromFile(file),clazz);
    }
}
