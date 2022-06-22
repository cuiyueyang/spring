package com.example.demo.linkUtils.geo;

/**
 * <p>Description: 地理信息相关工具类</p>
 *
 * @author jack
 * @since 2020/9/14
 **/
public class GeoUtil {

    private GeoUtil() {}

    /**
     * WGS84转GCj02
     * @param lng WGS84坐标经度
     * @param lat WGS84坐标纬度
     * @return 火星坐标
     */
    public static double[] wgs84ToGcj02(double lng, double lat) {
        return CoordinateTransform.wgs84ToGcj02(lng, lat);
    }

    /**
     * GCJ02 转换为 WGS84
     * @param lng 火星坐标系经度
     * @param lat 火星坐标系纬度
     * @return WGS84坐标
     */
    public static double[] gcj02ToWgs84(double lng, double lat) {
        return CoordinateTransform.gcj02ToWgs84(lng, lat);
    }

    /**
     * 判断是否在国内，不在国内不做偏移
     *
     * @param lng 经度
     * @param lat 纬度
     * @return boolean
     */
    public static boolean outOfChina(double lng, double lat) {
        if (lng < 72.004 || lng > 137.8347) {
            return true;
        }
        return lat < 0.8293 || lat > 55.8271;
    }

}
