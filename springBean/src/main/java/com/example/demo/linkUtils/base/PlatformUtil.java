package com.example.demo.linkUtils.base;

import org.apache.commons.lang3.SystemUtils;

import java.io.File;

/**
 * 关于系统设定，平台信息的变量(via Common Lang SystemUtils)
 * @see SystemUtils
 */
public class PlatformUtil {

    private PlatformUtil() {
    }

    /**
     * 文件路径分隔符
     * @see File#separator
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final String FILE_PATH_SEPARATOR = File.separator;

    /**
     * 文件路径分隔符
     * @see File#separatorChar
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final char FILE_PATH_SEPARATOR_CHAR = File.separatorChar;
    /**
     * windows文件路径分隔符
     */
    public static final char WINDOWS_FILE_SEPARATOR_CHAR = '\\';
    /**
     * UNIX文件路径分隔符
     */
    public static final char UNIX_FILE_SEPARATOR_CHAR = '/';

    /**
     * windows文件路径分隔符
     * @see #WINDOWS_FILE_SEPARATOR_CHAR
     * @deprecated
     */
    @Deprecated
    public static final char WINDOWS_FILE_PATH_SEPARATOR_CHAR = WINDOWS_FILE_SEPARATOR_CHAR;
    /**
     * UNIX文件路径分隔符
     * @see #UNIX_FILE_SEPARATOR_CHAR
     * @deprecated
     */
    @Deprecated
    public static final char UNIX_FILE_PATH_SEPARATOR_CHAR = UNIX_FILE_SEPARATOR_CHAR;


    /**
     * ClassPath分隔符
     * @see File#pathSeparator
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final String CLASS_PATH_SEPARATOR = File.pathSeparator;
    /**
     * ClassPath分隔符
     * @see File#pathSeparatorChar
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final char CLASS_PATH_SEPARATOR_CHAR = File.pathSeparatorChar;

    /**
     * 换行符
     * @see System#lineSeparator()
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final String LINE_SEPARATOR = System.lineSeparator();


    /**
     * 临时目录
     * @see SystemUtils#JAVA_IO_TMPDIR
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final String TMP_DIR = SystemUtils.JAVA_IO_TMPDIR;
    /**
     * 应用的工作目录
     * @see SystemUtils#USER_DIR
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final String WORKING_DIR = SystemUtils.USER_DIR;
    /**
     * 用户 HOME目录
     * @see SystemUtils#USER_HOME
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final String USER_HOME = SystemUtils.USER_HOME;
    /**
     * Java HOME目录
     * @see SystemUtils#JAVA_HOME
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final String JAVA_HOME = SystemUtils.JAVA_HOME;

    /**
     * Java版本
     * e.g. 1.8
     * @see SystemUtils#JAVA_SPECIFICATION_VERSION
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final String JAVA_SPECIFICATION_VERSION = SystemUtils.JAVA_SPECIFICATION_VERSION;
    /**
     * Java版本
     * e.g. 1.8.0_102
     * @see SystemUtils#JAVA_VERSION
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final String JAVA_VERSION = SystemUtils.JAVA_VERSION;
    /**
     * @see SystemUtils#IS_JAVA_1_8
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final boolean IS_JAVA8 = SystemUtils.IS_JAVA_1_8;
    /**
     * @see SystemUtils#IS_JAVA_9
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final boolean IS_JAVA9 = SystemUtils.IS_JAVA_9;
    /**
     * @see SystemUtils#IS_JAVA_10
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final boolean IS_JAVA10 = SystemUtils.IS_JAVA_10;
    /**
     * @see SystemUtils#IS_JAVA_11
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final boolean IS_JAVA11 = SystemUtils.IS_JAVA_11;
    /**
     * @see SystemUtils
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final boolean IS_ATLEASET_JAVA8 = IS_JAVA8 || IS_JAVA9 || IS_JAVA10 || IS_JAVA11;
    /**
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final boolean IS_ATLEASET_JAVA9 = IS_JAVA9 || IS_JAVA10 || IS_JAVA11;
    /**
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final boolean IS_ATLEASET_JAVA10 = IS_JAVA10 || IS_JAVA11;
    /**
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final boolean IS_ATLEASET_JAVA11 = IS_JAVA11;

    /**
     * 操作系统类型及版本
     */
    /**
     *
     * @see cn.hutool.system.SystemUtil#OS_NAME
     * @see SystemUtils#OS_NAME
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final String OS_NAME = SystemUtils.OS_NAME;
    /**
     *
     * @see cn.hutool.system.SystemUtil#OS_VERSION
     * @see SystemUtils#OS_VERSION
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final String OS_VERSION = SystemUtils.OS_VERSION;
    /**
     *
     * e.g. x86_64
     * @see cn.hutool.system.SystemUtil#OS_ARCH
     * @see SystemUtils#OS_ARCH
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final String OS_ARCH = SystemUtils.OS_ARCH;

    /**
     * @see SystemUtils#IS_OS_UNIX
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final boolean IS_UNIX = SystemUtils.IS_OS_UNIX;

    /**
     * @see SystemUtils#IS_OS_WINDOWS
     * @deprecated will remove in 2.0.0
     */
    @Deprecated
    public static final boolean IS_WINDOWS = SystemUtils.IS_OS_WINDOWS;
}
