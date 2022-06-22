package com.example.demo.linkUtils.geo;

import com.google.common.base.Splitter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * <p>Description: GeoHash算法</p>
 *
 * @author jack
 * @since 2021/3/30
 **/
public class GeoHash {

    private static final int DEFAULT_KEY_LENGTH = 10;

    private static final int BASE_32_ENCODE_BIT = 5;

    private static final int SUPPORTED_MAX_KEY_LENGTH = 12;

    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private int lngBit;

    private int latBit;

    private int keyLength;

    private double minLng;

    private double minLat;

    /**
     * 构造一个指定geoHash长度为默认长度的GeoHash对象
     *
     * @see #DEFAULT_KEY_LENGTH
     */
    public GeoHash() {
        this(DEFAULT_KEY_LENGTH);
    }

    /**
     * 构造一个指定key长度的GeoHash对象
     *
     * @param keyLength GeoHash值长度
     */
    public GeoHash(int keyLength) {
        if (keyLength <= 0 || keyLength > SUPPORTED_MAX_KEY_LENGTH) {
            throw new IllegalArgumentException("the keyLength not supported");
        }
        init(keyLength);
    }

    public String encode(double lat, double lng) {
        BitSet latBits = getBits(lat, GeoConsts.MIN_LAT, GeoConsts.MAX_LAT);
        BitSet lngBits = getBits(lng, GeoConsts.MIN_LNG, GeoConsts.MAX_LNG);

        String buffer = mergeBits(latBits, lngBits);

        return base32(buffer);
    }

    private String mergeBits(BitSet latBits, BitSet lngBits) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < latBit; i++) {
            buffer.append((lngBits.get(i)) ? '1' : '0');
            buffer.append((latBits.get(i)) ? '1' : '0');
        }
        if (lngBit > latBit) {
            buffer.append(lngBits.get(latBit) ? '1' : '0');
        }
        return buffer.toString();
    }

    public List<String> getAroundGeoHash(double lat, double lng) {

        List<String> list = new ArrayList<>();

        double upLat = lat + minLat;
        double downLat = lat - minLat;

        double leftLng = lng - minLng;
        double rightLng = lng + minLng;

        list.add(encode(upLat, leftLng));

        list.add(encode(lat, leftLng));

        list.add(encode(downLat, leftLng));

        list.add(encode(upLat, lng));

        list.add(encode(lat, lng));

        list.add(encode(downLat, lng));

        list.add(encode(upLat, rightLng));

        list.add(encode(lat, rightLng));

        list.add(encode(downLat, rightLng));

        return list;
    }

    private String base32(String biString) {
        Iterable<String> keys = Splitter.fixedLength(5).split(biString);

        StringBuilder keyBuilder = new StringBuilder(keyLength);

        keys.forEach(key -> keyBuilder.append(DIGITS[Integer.parseInt(key, 2)]));
        return keyBuilder.toString();
    }

    private BitSet getBits(double val, double min, double max) {
        BitSet buffer = new BitSet(lngBit);
        for (int i = 0; i < lngBit; i++) {
            double mid = (min + max) / 2;
            if (val >= mid) {
                buffer.set(i);
                min = mid;
            } else {
                max = mid;
            }
        }
        return buffer;
    }

    private void init(int keyLength) {
        this.keyLength = keyLength;
        int totalBit = keyLength * BASE_32_ENCODE_BIT;
        this.lngBit = (totalBit + 1) >> 1;
        this.latBit = totalBit >> 1;
        setMinLatLng();
    }

    private void setMinLatLng() {
        minLat = GeoConsts.MAX_LAT - GeoConsts.MIN_LAT;
        for (int i = 0; i < latBit; i++) {
            minLat /= 2;
        }
        minLng = GeoConsts.MAX_LNG - GeoConsts.MIN_LNG;
        for (int i = 0; i < lngBit; i++) {
            minLng /= 2;
        }
    }

}
