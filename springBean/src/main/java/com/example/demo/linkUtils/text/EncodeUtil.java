package com.example.demo.linkUtils.text;

import com.google.common.io.BaseEncoding;

import java.util.Base64;

/**
 * string/url -&gt; hex/base64 编解码工具集(via guava BaseEncoding)
 * @see Base64
 * @see cn.hutool.core.util.HexUtil
 * @deprecated will removed in 1.4
 */
@Deprecated
public class EncodeUtil {

    private EncodeUtil() {}

    /**
     * Hex编码, 将byte[]编码为String，默认为ABCDEF为大写字母.
     */
    public static String encodeHex(byte[] input) {
        return BaseEncoding.base16().encode(input);
    }

    /**
     * Hex解码, 将String解码为byte[].
     * <p>
     * 字符串有异常时抛出IllegalArgumentException.
     */
    public static byte[] decodeHex(CharSequence input) {
        return BaseEncoding.base16().decode(input);
    }

    /**
     * Base64编码.
     */
    public static String encodeBase64(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    /**
     * Base64解码.
     * <p>
     * 如果字符不合法，抛出IllegalArgumentException
     */
    public static byte[] decodeBase64(CharSequence input) {
        return Base64.getDecoder().decode(input.toString());
    }

    /**
     * Base64编码, URL安全.(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
     */
    public static String encodeBase64UrlSafe(byte[] input) {
        return Base64.getUrlEncoder().encodeToString(input);
    }

    /**
     * Base64解码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
     * <p>
     * 如果字符不合法，抛出IllegalArgumentException
     * @see cn.hutool.core.codec.Base64
     */
    public static byte[] decodeBase64UrlSafe(CharSequence input) {
        return Base64.getUrlDecoder().decode(input.toString());
    }
}
