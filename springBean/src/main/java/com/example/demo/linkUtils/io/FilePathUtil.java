package com.example.demo.linkUtils.io;

import com.example.demo.linkUtils.base.PlatformUtil;
import com.example.demo.linkUtils.text.MoreStringUtil;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;

/**
 * 关于文件路径的工具集. 这个类只适合处理纯字符串的路径，如果是File对象或者Path对象的路径处理，建议直接使用Path类的方法。
 *
 * @see java.nio.file.Path
 */
public class FilePathUtil {

    private FilePathUtil() {}

    /**
     * 路径分割符，转换为操作平台的文件分割符
     */
    public static String normalizePath(String path) {
        if(SystemUtils.IS_OS_UNIX && StringUtils.indexOf(path, PlatformUtil.WINDOWS_FILE_SEPARATOR_CHAR) != -1) {
            return StringUtils.replaceChars(path, PlatformUtil.WINDOWS_FILE_SEPARATOR_CHAR,
                    File.separatorChar);
        }

        if (SystemUtils.IS_OS_WINDOWS && StringUtils.indexOf(path, PlatformUtil.UNIX_FILE_SEPARATOR_CHAR) != -1) {
            return StringUtils.replaceChars(path, PlatformUtil.UNIX_FILE_SEPARATOR_CHAR,
                    File.separatorChar);
        }
        return path;

    }

    /**
     * 将路径整理，如 "a/../b"，整理成 "b"
     */
    public static String simplifyPath(String path) {
        return Files.simplifyPath(path);
    }

    /**
     * 以拼接路径名
     */
    public static String concat(String baseName, String... appendName) {
        if (appendName.length == 0) {
            return baseName;
        }
        baseName = normalizePath(baseName);
        StringBuilder concatName = new StringBuilder();
        if (MoreStringUtil.endWith(baseName, PlatformUtil.FILE_PATH_SEPARATOR_CHAR)) {
            concatName.append(baseName).append(appendName[0]);
        } else {
            concatName.append(baseName).append(PlatformUtil.FILE_PATH_SEPARATOR_CHAR).append(appendName[0]);
        }

        if (appendName.length > 1) {
            for (int i = 1; i < appendName.length; i++) {
                concatName.append(PlatformUtil.FILE_PATH_SEPARATOR_CHAR).append(appendName[i]);
            }
        }

        return concatName.toString();
    }

    /**
     * 获得上层目录的路径
     */
    public static String getParentPath(String path) {
        String parentPath = path;

        if (PlatformUtil.FILE_PATH_SEPARATOR.equals(parentPath)) {
            return parentPath;
        }

        parentPath = MoreStringUtil.removeEnd(parentPath, PlatformUtil.FILE_PATH_SEPARATOR_CHAR);

        int idx = parentPath.lastIndexOf(PlatformUtil.FILE_PATH_SEPARATOR_CHAR);
        if (idx >= 0) {
            parentPath = parentPath.substring(0, idx + 1);
        } else {
            parentPath = PlatformUtil.FILE_PATH_SEPARATOR;
        }

        return parentPath;
    }

    /**
     * 获得参数clazz所在的Jar文件的绝对路径
     */
    public static String getJarPath(Class<?> clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
    }
}
