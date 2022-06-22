package com.example.demo.linkUtils.io;

import com.example.demo.linkUtils.text.WildcardMatcher;
import com.google.common.graph.Traverser;
import com.google.common.io.Files;

import java.io.File;
import java.util.List;
import java.util.Spliterator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FileTreeWalker {

    private FileTreeWalker() {
    }

    /**
     * 前序递归列出所有文件, 包含文件与目录，及根目录本身.
     * <p>
     * 前序即先列出父目录，在列出子目录. 如要后序遍历, 直接使用Files.fileTreeTraverser()
     */
    public static List<File> listAll(File rootDir) {
        Spliterator<File> fileIter = Files.fileTraverser().depthFirstPreOrder(rootDir).spliterator();
        return StreamSupport
                .stream(fileIter, true)
                .collect(Collectors.toList());
    }

    /**
     * 前序递归列出所有文件, 只包含文件.
     */
    public static List<File> listFile(File rootDir) {
        Spliterator<File> fileIter = Files.fileTraverser().depthFirstPreOrder(rootDir).spliterator();
        return StreamSupport
                .stream(fileIter, true)
                .filter(File::isFile)
                .collect(Collectors.toList());
    }

    /**
     * 前序递归列出所有文件, 列出后缀名匹配的文件. （后缀名不包含.）
     */
    public static List<File> listFileWithExtension(final File rootDir, final String extension) {
        Spliterator<File> fileIter = Files.fileTraverser().depthFirstPreOrder(rootDir).spliterator();
        return StreamSupport
                .stream(fileIter, true)
                .filter(file -> fileExtensionFilter(file, extension))
                .collect(Collectors.toList());
    }

    /**
     * 前序递归列出所有文件, 列出文件名匹配通配符的文件
     * <p>
     * 如 ("/a/b/hello.txt", "he*") 将被返回
     */
    public static List<File> listFileWithWildcardFileName(final File rootDir, final String fileNamePattern) {
        Spliterator<File> fileIter = Files.fileTraverser().depthFirstPreOrder(rootDir).spliterator();
        return StreamSupport
                .stream(fileIter, true)
                .filter(file -> wildcardFileNameFilter(file, fileNamePattern))
                .collect(Collectors.toList());
    }

    /**
     * 前序递归列出所有文件, 列出文件名匹配正则表达式的文件
     * <p>
     * 如 ("/a/b/hello.txt", "he.*\.txt") 将被返回
     */
    public static List<File> listFileWithRegexFileName(final File rootDir, final String regexFileNamePattern) {
        Spliterator<File> fileIter = Files.fileTraverser().depthFirstPreOrder(rootDir).spliterator();
        return StreamSupport
                .stream(fileIter, true)
                .filter(file -> regexFileNameFilter(file, regexFileNamePattern))
                .collect(Collectors.toList());
    }

    /**
     * 前序递归列出所有文件, 列出符合ant path风格表达式的文件
     * <p>
     * 如 ("/a/b/hello.txt", "he.*\.txt") 将被返回
     */
    public static List<File> listFileWithAntPath(final File rootDir, final String antPathPattern) {
        Spliterator<File> fileIter = Files.fileTraverser().depthFirstPreOrder(rootDir).spliterator();
        final String pattern = FilePathUtil.concat(rootDir.getAbsolutePath(), antPathPattern);
        return StreamSupport
                .stream(fileIter, true)
                .filter(file -> antPathFilter(file, pattern))
                .collect(Collectors.toList());
    }

    /**
     * 直接使用Guava的Traverser，获得更大的灵活度, 比如前序/后序的选择
     *
     * <pre>
     * FileUtil.fileTreeTraverser().preOrderTraversal(root).iterator();
     * </pre>
     */
    public static Traverser<File> fileTreeTraverser() {
        return Files.fileTraverser();
    }

    /**
     * 以文件名正则表达式为filter
     */
    public static boolean regexFileNameFilter(File input, String pattern) {

        return input.isFile() && Pattern.compile(pattern).matcher(input.getName()).matches();
    }

    /**
     * 以文件名通配符为filter
     */
    public static boolean wildcardFileNameFilter(File input, String pattern) {

        return input.isFile() && WildcardMatcher.match(input.getName(), pattern);
    }

    /**
     * 以文件名后缀做filter
     */
    public static boolean fileExtensionFilter(File input, String extension) {

        return input.isFile() && extension.equals(FileUtil.getFileExtension(input));

    }

    /**
     * 以ant风格的path为filter
     */
    public static boolean antPathFilter(File input, String pattern) {

        return input.isFile() && WildcardMatcher.matchPath(input.getAbsolutePath(), pattern);
    }
}
