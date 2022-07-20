package org.epoch.core.util;

import java.io.File;
import java.io.IOException;

/**
 * 文件常用工具类
 *
 * @author Marshal
 * @date 2020/1/20
 */
public class FileOperations {

    private FileOperations() {
    }

    /**
     * 文件是否存在
     *
     * @param path
     * @return
     */
    public static boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 判断文件目录是否存在不存在则创建
     *
     * @param file
     * @throws IOException
     */
    public static void createFileDir(File file) throws IOException {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * 获取文件夹下所有文件列表
     *
     * @param directory
     */
    public static File[] getFileList(String directory) {
        File dir = new File(directory);
        File[] files = dir.listFiles();
        return files;
    }
}
