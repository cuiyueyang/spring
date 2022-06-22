package com.example.demo.linkUtils.io;

import com.example.demo.linkUtils.base.PlatformUtil;
import com.google.common.base.Charsets;
import org.apache.commons.lang3.Validate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * 关于文件的工具集.
 * <p>
 * 主要是调用JDK自带的Files工具类，少量代码调用Guava Files。 固定encoding为UTF8.
 * <p>
 * 1.文件读写
 * <p>
 * 2.文件及目录操作
 */
public class FileUtil {

    private static final String MSG_NULL_FILE = "file is null";
    private static final String MSG_NULL_PATH = "path is null";
    private static final String MSG_DIR_NOT_EXISTS = "%s is not exist or not a dir";
    private static final String MSG_NOT_FILE = "%s is not exist or not a file";

    private FileUtil() {}

    /**
     * fileVisitor for file deletion on Files.walkFileTree
     */
    private static FileVisitor<Path> deleteFileVisitor = new SimpleFileVisitor<Path>() {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Files.delete(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            Files.delete(dir);
            return FileVisitResult.CONTINUE;
        }
    };

    //////// 文件读写//////

    /**
     * 读取文件到byte[].
     *
     * @see Files#readAllBytes
     */
    public static byte[] toByteArray(final File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    /**
     * 读取文件到String.
     */
    public static String toString(final File file) throws IOException {
        return com.google.common.io.Files.asCharSource(file, Charsets.UTF_8).read();
    }

    /**
     * 读取文件的每行内容到List&lt;String&gt;.
     *
     * @see Files#readAllLines
     */
    public static List<String> toLines(final File file) throws IOException {
        return Files.readAllLines(file.toPath(), Charsets.UTF_8);
    }

    /**
     * 简单写入String到File. 不自动创建文件
     *
     * @param data 待写入数据
     * @param file 指定文件
     * @throws IOException 读写文件错误
     */
    public static void write(final CharSequence data, final File file) throws IOException {
        write(data, file, false);
    }

    /**
     * 简单写入String到File.
     *
     * @param data 待写入数据
     * @param file 指定文件
     * @param createIfNotExists 如果不还在，是否创建
     * @throws IOException 读写文件错误
     */
    public static void write(final CharSequence data, final File file, boolean createIfNotExists) throws IOException {
        Validate.notNull(file);
        Validate.notNull(data);

        if(createIfNotExists) {
            makesureParentDirExists(file);
            if(!file.exists() && !file.createNewFile()) {
                //ignore when file exists
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), Charsets.UTF_8)) {
            writer.append(data);
        }
    }

    /**
     * 追加String到File.
     */
    public static void append(final CharSequence data, final File file) throws IOException {
        Validate.notNull(file);
        Validate.notNull(data);

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), Charsets.UTF_8,
                StandardOpenOption.APPEND)) {
            writer.append(data);
        }
    }

    /**
     * 打开文件为InputStream.
     *
     * @see Files#newInputStream
     */
    public static InputStream asInputStream(String fileName) throws IOException {
        return asInputStream(getPath(fileName));
    }

    /**
     * 打开文件为InputStream.
     *
     * @see Files#newInputStream
     */
    public static InputStream asInputStream(File file) throws IOException {
        Validate.notNull(file, MSG_NULL_FILE);
        return asInputStream(file.toPath());
    }

    /**
     * 打开文件为InputStream.
     *
     * @see Files#newInputStream
     */
    public static InputStream asInputStream(Path path) throws IOException {
        Validate.notNull(path, MSG_NULL_PATH);
        return Files.newInputStream(path);
    }

    /**
     * 打开文件为OutputStream.
     *
     * @see Files#newOutputStream
     */
    public static OutputStream asOututStream(String fileName) throws IOException {
        return asOututStream(getPath(fileName));
    }

    /**
     * 打开文件为OutputStream.
     *
     * @see Files#newOutputStream
     */
    public static OutputStream asOututStream(File file) throws IOException {
        Validate.notNull(file, MSG_NULL_FILE);
        return asOututStream(file.toPath());
    }

    /**
     * 打开文件为OutputStream.
     *
     * @see Files#newOutputStream
     */
    public static OutputStream asOututStream(Path path) throws IOException {
        Validate.notNull(path, MSG_NULL_PATH);
        return Files.newOutputStream(path);
    }

    /**
     * 获取File的BufferedReader.
     *
     * @see Files#newBufferedReader
     */
    public static BufferedReader asBufferedReader(String fileName) throws IOException {
        Validate.notBlank(fileName, "filename is blank");
        return asBufferedReader(getPath(fileName));
    }

    public static BufferedReader asBufferedReader(Path path) throws IOException {
        Validate.notNull(path, MSG_NULL_PATH);
        return Files.newBufferedReader(path, Charsets.UTF_8);
    }

    /**
     * 获取File的BufferedWriter.
     *
     * @see Files#newBufferedWriter
     */
    public static BufferedWriter asBufferedWriter(String fileName) throws IOException {
        Validate.notBlank(fileName, "filename is blank");
        return Files.newBufferedWriter(getPath(fileName), Charsets.UTF_8);
    }

    /**
     * 获取File的BufferedWriter.
     *
     * @see Files#newBufferedWriter
     */
    public static BufferedWriter asBufferedWriter(Path path) throws IOException {
        Validate.notNull(path, MSG_NULL_PATH);
        return Files.newBufferedWriter(path, Charsets.UTF_8);
    }

    ///// 文件操作 /////

    /**
     * 复制文件或目录, not following links.
     *
     * @param from 如果为null，或者是不存在的文件或目录，抛出异常.
     * @param to   如果为null，或者from是目录而to是已存在文件，或相反
     */
    public static void copy(@NonNull File from, @NonNull File to) throws IOException {
        Validate.notNull(from);
        Validate.notNull(to);

        copy(from.toPath(), to.toPath());
    }

    /**
     * 复制文件或目录, not following links.
     *
     * @param from 如果为null，或者是不存在的文件或目录，抛出异常.
     * @param to   如果为null，或者from是目录而to是已存在文件，或相反
     */
    public static void copy(@NonNull Path from, @NonNull Path to) throws IOException {
        Validate.notNull(from);
        Validate.notNull(to);

        if (from.toFile().isDirectory()) {
            copyDir(from, to);
        } else {
            copyFile(from, to);
        }
    }

    /**
     * 文件复制.
     *
     * @param from 如果为null，或文件不存在或者是目录，，抛出异常
     * @param to   如果to为null，或文件存在但是一个目录，抛出异常
     * @see Files#copy
     */
    public static void copyFile(@NonNull File from, @NonNull File to) throws IOException {
        Validate.notNull(from);
        Validate.notNull(to);
        copyFile(from.toPath(), to.toPath());
    }

    /**
     * 文件复制.
     *
     * @param from 如果为null，或文件不存在或者是目录，，抛出异常
     * @param to   如果to为null，或文件存在但是一个目录，抛出异常
     * @see Files
     */
    public static void copyFile(@NonNull Path from, @NonNull Path to) throws IOException {
        Validate.notNull(from, MSG_NULL_FILE);
        Validate.isTrue(from.toFile().exists(), MSG_NOT_FILE, from);
        Validate.notNull(to);
        Validate.isTrue(!FileUtil.isDirExists(to), "%s is exist but it is a dir", to);
        Files.copy(from, to);
    }

    /**
     * 复制目录
     */
    public static void copyDir(@NonNull File from, @NonNull File to) throws IOException {
        Validate.isTrue(isDirExists(from), MSG_DIR_NOT_EXISTS, from);
        Validate.notNull(to);

        copyDir(from.toPath(), to.toPath());
    }

    /**
     * 复制目录
     */
    public static void copyDir(@NonNull Path from, @NonNull Path to) throws IOException {
        Validate.isTrue(isDirExists(from), MSG_DIR_NOT_EXISTS, from);
        Validate.notNull(to);
        makesureDirExists(to);

        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(from)) {
            for (Path path : dirStream) {
                copy(path, to.resolve(path.getFileName()));
            }
        }
    }

    /**
     * 文件移动/重命名.
     *
     * @see Files#move
     */
    public static void moveFile(@NonNull File from, @NonNull File to) throws IOException {
        Validate.notNull(from);
        Validate.notNull(to);

        moveFile(from.toPath(), to.toPath());
    }

    /**
     * 文件移动/重命名.
     *
     * @see Files#move
     */
    public static void moveFile(@NonNull Path from, @NonNull Path to) throws IOException {
        Validate.isTrue(isFileExists(from), MSG_NOT_FILE, from);
        Validate.notNull(to);
        Validate.isTrue(!isDirExists(to), "%s is  exist but it is a dir", to);

        Files.move(from, to);
    }

    /**
     * 目录移动/重命名
     */
    public static void moveDir(@NonNull File from, @NonNull File to) throws IOException {
        Validate.isTrue(isDirExists(from), MSG_DIR_NOT_EXISTS, from);
        Validate.notNull(to);
        Validate.isTrue(!isFileExists(to), "%s is exist but it is a file", to);

        final boolean rename = from.renameTo(to);
        if (!rename) {
            if (to.getCanonicalPath().startsWith(from.getCanonicalPath() + File.separator)) {
                throw new IOException("Cannot move directory: " + from + " to a subdirectory of itself: " + to);
            }
            copyDir(from, to);
            deleteDir(from);
            if (from.exists()) {
                throw new IOException("Failed to delete original directory '" + from + "' after copy to '" + to + '\'');
            }
        }
    }

    /**
     * 创建文件或更新时间戳.
     *
     * @see com.google.common.io.Files#touch
     */
    public static void touch(String filePath) throws IOException {
        touch(new File(filePath));
    }

    /**
     * 创建文件或更新时间戳.
     *
     * @see com.google.common.io.Files#touch
     */
    public static void touch(File file) throws IOException {
        com.google.common.io.Files.touch(file);
    }

    /**
     * 删除文件.
     * <p>
     * 如果文件不存在或者是目录，则不做修改
     */
    public static void deleteFile(@Nullable File file) throws IOException {
        Validate.isTrue(isFileExists(file), MSG_NOT_FILE, file);
        deleteFile(file.toPath());
    }

    /**
     * 删除文件.
     * <p>
     * 如果文件不存在或者是目录，则不做修改
     */
    public static void deleteFile(@Nullable Path path) throws IOException {
        Validate.isTrue(isFileExists(path), MSG_NOT_FILE, path);

        Files.delete(path);
    }

    /**
     * 删除目录及所有子目录/文件
     *
     * @see Files#walkFileTree
     */
    public static void deleteDir(Path dir) throws IOException {
        Validate.isTrue(isDirExists(dir), MSG_DIR_NOT_EXISTS, dir);

        // 后序遍历，先删掉子目录中的文件/目录
        Files.walkFileTree(dir, deleteFileVisitor);
    }

    /**
     * 删除目录及所有子目录/文件
     */
    public static void deleteDir(File dir) throws IOException {
        Validate.isTrue(isDirExists(dir), MSG_DIR_NOT_EXISTS, dir);
        deleteDir(dir.toPath());
    }

    /**
     * 判断目录是否存在, from Jodd
     */
    public static boolean isDirExists(String dirPath) {
        if (dirPath == null) {
            return false;
        }
        return isDirExists(getPath(dirPath));
    }

    public static boolean isDirExists(Path dirPath) {
        return dirPath != null && dirPath.toFile().exists() && dirPath.toFile().isDirectory();
    }

    /**
     * 判断目录是否存在, from Jodd
     */
    public static boolean isDirExists(File dir) {
        if (dir == null) {
            return false;
        }
        return isDirExists(dir.toPath());
    }

    /**
     * 确保目录存在, 如不存在则创建
     */
    public static void makesureDirExists(String dirPath) throws IOException {
        makesureDirExists(getPath(dirPath));
    }

    /**
     * 确保目录存在, 如不存在则创建
     */
    public static void makesureDirExists(File file) throws IOException {
        Validate.notNull(file);
        makesureDirExists(file.toPath());
    }

    /**
     * 确保目录存在, 如不存在则创建.
     *
     * @see Files#createDirectories
     */
    public static void makesureDirExists(Path dirPath) throws IOException {
        Validate.notNull(dirPath);
        Files.createDirectories(dirPath);
    }

    /**
     * 确保父目录及其父目录直到根目录都已经创建.
     */
    public static void makesureParentDirExists(File file) throws IOException {
        Validate.notNull(file);
        makesureDirExists(file.getParentFile());
    }

    /**
     * 判断文件是否存在, from Jodd.
     */
    public static boolean isFileExists(String fileName) {
        if (fileName == null) {
            return false;
        }
        return isFileExists(getPath(fileName));
    }

    /**
     * 判断文件是否存在, from Jodd.
     */
    public static boolean isFileExists(File file) {
        if (file == null) {
            return false;
        }
        return isFileExists(file.toPath());
    }

    /**
     * 判断文件是否存在, from Jodd.
     *
     * @see File#isFile()
     * @see File#exists()
     */
    public static boolean isFileExists(Path path) {
        if (path == null) {
            return false;
        }
        return path.toFile().exists() && path.toFile().isFile();
    }

    /**
     * 在临时目录创建临时目录，命名为${毫秒级时间戳}-${同一毫秒内的随机数}.
     *
     * @see Files#createTempDirectory
     */
    public static Path createTempDir() throws IOException {
        return Files.createTempDirectory(System.currentTimeMillis() + "-");
    }

    /**
     * 在临时目录创建临时文件，命名为tmp-${random.nextLong()}.tmp
     *
     * @see Files#createTempFile
     */
    public static Path createTempFile() throws IOException {
        return Files.createTempFile("tmp-", ".tmp");
    }

    /**
     * 在临时目录创建临时文件，命名为${prefix}${random.nextLong()}${suffix}
     *
     * @see Files#createTempFile
     */
    public static Path createTempFile(String prefix, String suffix) throws IOException {
        return Files.createTempFile(prefix, suffix);
    }

    private static Path getPath(String filePath) {
        return Paths.get(filePath);
    }

    /**
     * 获取文件名(不包含路径)
     */
    public static String getFileName(@NonNull String fullName) {
        Validate.notEmpty(fullName);
        int last = fullName.lastIndexOf(PlatformUtil.FILE_PATH_SEPARATOR_CHAR);
        return fullName.substring(last + 1);
    }

    /**
     * 获取文件名的扩展名部分(不包含.)
     *
     * @see com.google.common.io.Files#getFileExtension
     */
    public static String getFileExtension(File file) {
        return com.google.common.io.Files.getFileExtension(file.getName());
    }

    /**
     * 获取文件名的扩展名部分(不包含.)
     *
     * @see com.google.common.io.Files#getFileExtension
     */
    public static String getFileExtension(String fullName) {
        return com.google.common.io.Files.getFileExtension(fullName);
    }
}
