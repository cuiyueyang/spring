package com.example.demo.linkUtils.io;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

/**
 * IO Stream/Reader相关工具集. 固定encoding为UTF8.
 *
 * 建议使用Apache Commons IO和Guava关于IO的工具类(com.google.common.io.*), 在未引入Commons IO时可以用本类做最基本的事情.
 *
 * <p>
 * 1. 安静关闭Closeable对象
 * <p>
 * 2. 读出InputStream/Reader全部内容到String 或 {@code List<String>}
 * <p>
 * 3. 读出InputStream一行内容到String
 * <p>
 * 4. 将String写到OutputStream/Writer
 * <p>
 * 5. InputStream/Reader与OutputStream/Writer之间复制的copy
 *
 */
public class IOUtil {
    private static final String CLOSE_ERROR_MESSAGE = "IOException thrown while closing Closeable.";

    private static final Logger logger = LoggerFactory.getLogger(IOUtil.class);

    private IOUtil() {}

    /**
     * 在final中安静的关闭, 不再往外抛出异常避免影响原有异常，最常用函数. 同时兼容Closeable为空未实际创建的情况.
     *
     * @see Closeables#close
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            logger.warn(CLOSE_ERROR_MESSAGE, e);
        }
    }

    /**
     * 简单读取InputStream到String.
     */
    public static String toString(InputStream input) throws IOException {
        InputStreamReader reader = new InputStreamReader(input, Charsets.UTF_8);
        return toString(reader);
    }

    /**
     * 简单读取Reader到String
     *
     * @see CharStreams#toString
     */
    public static String toString(Reader input) throws IOException {
        return CharStreams.toString(input);
    }

    /**
     * 简单读取Reader的每行内容到List&lt;String&gt;
     */
    public static List<String> toLines(final InputStream input) throws IOException {
        return CharStreams.readLines(new BufferedReader(new InputStreamReader(input, Charsets.UTF_8)));
    }

    /**
     * 简单读取Reader的每行内容到List&lt;String&gt;
     *
     * @see CharStreams#readLines
     */
    public static List<String> toLines(final Reader input) throws IOException {
        return CharStreams.readLines(toBufferedReader(input));
    }

    /**
     * 读取一行数据，比如System.in的用户输入
     */
    public static String readLine(final InputStream input) throws IOException {
        return new BufferedReader(new InputStreamReader(input, Charsets.UTF_8)).readLine();
    }

    /**
     * 读取一行数据
     */
    public static String readLine(final Reader reader) throws IOException {
        return toBufferedReader(reader).readLine();
    }

    /**
     * 简单写入String到OutputStream.
     */
    public static void write(final String data, final OutputStream output) throws IOException {
        if (data != null) {
            output.write(data.getBytes(Charsets.UTF_8));
        }
    }

    /**
     * 简单写入String到Writer.
     */
    public static void write(final String data, final Writer output) throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    /**
     * 在Reader与Writer间复制内容
     *
     * @see CharStreams#copy
     */
    public static long copy(final Reader input, final Writer output) throws IOException {
        return CharStreams.copy(input, output);
    }

    /**
     * 在InputStream与OutputStream间复制内容
     *
     * @see ByteStreams#copy
     */
    public static long copy(final InputStream input, final OutputStream output) throws IOException {
        return ByteStreams.copy(input, output);
    }

    public static BufferedReader toBufferedReader(final Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }
}
