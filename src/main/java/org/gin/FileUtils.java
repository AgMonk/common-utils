package org.gin;

import java.io.*;

/**
 * 文件工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 11:37
 **/
@SuppressWarnings("unused")
public class FileUtils {
    /**
     * 将一个字符串写入到文件
     * @param string 字符串
     * @param file 文件
     * @throws IOException 一查给你
     */
    public static void writeToFile(String string, File file) throws IOException {
        createParentFile(file);
        assertNotExists(file);

        final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(string);
        writer.close();
    }

    /**
     * 从文件读取字符串
     * @param file 文件
     * @throws IOException 异常
     * @return 字符串
     */
    public static String readFromFile(File file) throws IOException {
        assertExists(file);

        final BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine())!=null){
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }



    /**
     * 断言文件不存在
     * @param file 文件
     * @throws IOException 异常
     */
    public static void assertNotExists(File file) throws IOException {
        assertNotNull(file);
        if (file.exists()) {
            throw new IOException("该文件已存在: " + file.getPath());
        }
    }

    /**
     * 断言文件存在
     * @param file 文件
     * @throws IOException 异常
     */
    public static void assertExists(File file) throws IOException {
        assertNotNull(file);
        if (!file.exists()) {
            throw new IOException("该文件不存在: " + file.getPath());
        }
    }

    /**
     * 为一个文件创建父路径
     * @param file 文件
     * @throws IOException 异常
     */
    public static void createParentFile(File file) throws IOException {
        assertNotNull(file);
        final File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("父路径创建失败: " + parentFile.getPath());
        }
    }

    /**
     * 断言file不等于null
     * @param file 文件
     * @throws IOException 异常
     */
    public static void assertNotNull(File file) throws IOException {
        if (file == null) {
            throw new IOException("文件不能为 null");
        }
    }


}
