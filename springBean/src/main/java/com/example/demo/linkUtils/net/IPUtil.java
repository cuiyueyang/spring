package com.example.demo.linkUtils.net;

import cn.hutool.core.util.NumberUtil;
import com.example.demo.linkUtils.text.MoreStringUtil;
import com.google.common.collect.Lists;
import com.google.common.net.InetAddresses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * InetAddress工具类，基于Guava的InetAddresses.
 * <p>
 * 主要包含int, String/IPV4String, InetAdress/Inet4Address之间的互相转换
 * <p>
 * 先将字符串传换为byte[]再用InetAddress.getByAddress(byte[])，避免了InetAddress.getByName(ip)可能引起的DNS访问.
 * <p>
 * InetAddress与String的转换其实消耗不小，如果是有限的地址，建议进行缓存.
 */
@Slf4j
public class IPUtil {

    public static final String UNKNOWN_IP = "unknown";
    public static final String IPV4_LOCALHOST = "127.0.0.1";
    public static final String IPV6_LOCALHOST = "0:0:0:0:0:0:0:1";
    public static final String DELIMITER_COMMA = ",";

    public static final List<String> IP_PROXY_HEADER_STR = Lists.newArrayList("x-forwarded-for"
            ,"Proxy-Client-IP","WL-Proxy-Client-IP","HTTP_CLIENT_IP","HTTP_X_FORWARDED_FOR","X-Real-IP");

    private IPUtil() {}

    /**
     * 从InetAddress转化到int, 传输和存储时, 用int代表InetAddress是最小的开销.
     * <p>
     * InetAddress可以是IPV4或IPV6，都会转成IPV4.
     *
     * @see com.google.common.net.InetAddresses#coerceToInteger(InetAddress)
     */
    public static int toInt(InetAddress address) {
        return InetAddresses.coerceToInteger(address);
    }

    /**
     * InetAddress转换为String.
     * <p>
     * InetAddress可以是IPV4或IPV6. 其中IPV4直接调用getHostAddress()
     *
     * @see com.google.common.net.InetAddresses#toAddrString(InetAddress)
     */
    public static String toIpString(InetAddress address) {
        return InetAddresses.toAddrString(address);
    }

    /**
     * 从int转换为Inet4Address(仅支持IPV4)
     */
    public static Inet4Address fromInt(int address) {
        return InetAddresses.fromInteger(address);
    }

    /**
     * 从String转换为InetAddress.
     * <p>
     * IpString可以是ipv4 或 ipv6 string, 但不可以是域名.
     * <p>
     * 先字符串传换为byte[]再调getByAddress(byte[])，避免了调用getByName(ip)可能引起的DNS访问.
     */
    public static InetAddress fromIpString(String address) {
        return InetAddresses.forString(address);
    }

    /**
     * 从IPv4String转换为InetAddress.
     * <p>
     * IpString如果确定ipv4, 使用本方法减少字符分析消耗 .
     * <p>
     * 先字符串传换为byte[]再调getByAddress(byte[])，避免了调用getByName(ip)可能引起的DNS访问.
     */
    public static Inet4Address fromIpv4String(String address) {
        byte[] bytes = ip4StringToBytes(address);
        if (bytes == null) {
            return null;
        } else {
            try {
                return (Inet4Address) InetAddress.getByAddress(bytes);
            } catch (UnknownHostException e) {
                throw new AssertionError(e);
            }
        }
    }

    /**
     * int转换到IPV4 String, from Netty NetUtil
     */
    public static String intToIpv4String(int i) {
        return new StringBuilder(15).append((i >> 24) & 0xff).append('.').append(i >> 16 & 0xff).append('.')
                .append((i >> 8) & 0xff).append('.').append(i & 0xff).toString();
    }

    /**
     * Ipv4 String 转换到int
     */
    public static int ipv4StringToInt(String ipv4Str) {
        byte[] byteAddress = ip4StringToBytes(ipv4Str);
        if (byteAddress == null) {
            return 0;
        } else {
            return NumberUtil.toInt(byteAddress);
        }
    }

    /**
     * Ipv4 String 转换到byte[]
     */
    private static byte[] ip4StringToBytes(String ipv4Str) {
        if (ipv4Str == null) {
            return null;
        }

        List<String> it = MoreStringUtil.split(ipv4Str, '.', 4);
        if (it.size() != 4) {
            return null;
        }

        byte[] byteAddress = new byte[4];
        for (int i = 0; i < 4; i++) {
            int tempInt = Integer.parseInt(it.get(i));
            if (tempInt > 255) {
                return null;
            }
            byteAddress[i] = (byte) tempInt;
        }
        return byteAddress;
    }

    public static String getRealIp(HttpServletRequest request) {
        String ip = IPV4_LOCALHOST;
        if (request == null) {
            return ip;
        }

        ip = parseProxyIp(request);

        if (null == ip) {
            ip = request.getRemoteAddr();
            if (ip.equals(IPV4_LOCALHOST) || ip.equals(IPV6_LOCALHOST)) {
                //根据网卡取本机配置的IP
                ip = NetUtil.getLocalAddress().getHostAddress();
            }
        }

        if (ip != null && ip.indexOf(DELIMITER_COMMA) > 0) {
            ip = ip.substring(0, ip.indexOf(DELIMITER_COMMA));
        }
        return ip;
    }

    private static String parseProxyIp(HttpServletRequest request) {
        return IP_PROXY_HEADER_STR.stream().filter(s -> isIpValid(request.getHeader(s)))
                .findFirst().map(request::getHeader).orElse(null);
    }

    private static boolean isIpValid(String ip) {
        return StringUtils.isNotBlank(ip) && !UNKNOWN_IP.equalsIgnoreCase(ip);
    }
}
