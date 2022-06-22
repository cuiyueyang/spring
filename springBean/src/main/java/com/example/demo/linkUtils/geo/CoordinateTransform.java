package com.example.demo.linkUtils.geo;

/**
 * GPS 坐标系转换工具
 */
public class CoordinateTransform {
    private static final double X_PI = GeoConsts.PI * 3000.0 / 180.0;
    private static final double A = 6378245.0;
    private static final double EE = 0.00669342162296594323;

    /**
     * 百度坐标系 (BD-09) 与 火星坐标系 (GCJ-02)的转换
     * 即 百度 转 谷歌、高德
     * @param bdLng 百度纬度
     * @param bdLat 百度经度
     * @return {*[]}
     */
    public double[] bd09ToGcj02(double bdLng, double bdLat) {
        double x = bdLng - 0.0065;
        double y = bdLat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * X_PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * X_PI);
        double ggLng = z * Math.cos(theta);
        double ggLat = z * Math.sin(theta);
        return new double[] {ggLng, ggLat};
    }

    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换
     * 即谷歌、高德 转 百度
     * @param lng 经度
     * @param lat 纬度
     * @return {*[]}
     */
    public static double[] gcj02ToBd09(double lng, double lat) {
        double z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * X_PI);
        double theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * X_PI);
        double bdLng = z * Math.cos(theta) + 0.0065;
        double bdLat = z * Math.sin(theta) + 0.006;
        return new double[] {bdLng, bdLat};
    }

    /**
     * WGS84转GCj02
     * @param lng WGS84坐标经度
     * @param lat WGS84坐标纬度
     * @return 火星坐标
     */
    public static double[] wgs84ToGcj02(double lng, double lat) {
        if(outOfChina(lng, lat)) {
            return new double[] {lng, lat};
        }
        return transformCoordinate(lng, lat);
    }

    /**
     * GCJ02 转换为 WGS84
     * @param lng 火星坐标系经度
     * @param lat 火星坐标系纬度
     * @return WGS84坐标
     */
    public static double[] gcj02ToWgs84(double lng, double lat) {
        if(outOfChina(lng, lat)) {
            return new double[] {lng, lat};
        }
        double[] coordinate = transformCoordinate(lng, lat);
        coordinate[0] = lng*2-coordinate[0];
        coordinate[1] = lat*2 - coordinate[1];
        return coordinate;
    }

    /**
     * GCJ02 与 WGS84 坐标转换算法
     * @param lng 经度
     * @param lat 纬度
     * @return 坐标
     */
    private static double[] transformCoordinate(double lng, double lat) {
        double dLat = transformlat(lng - 105.0, lat - 35.0);
        double dLng = transformlng(lng - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * GeoConsts.PI;
        double magic = Math.sin(radLat);
        magic = 1 - EE * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((A * (1 - EE)) / (magic * sqrtMagic) * GeoConsts.PI);
        dLng = (dLng * 180.0) / (A / sqrtMagic * Math.cos(radLat) * GeoConsts.PI);
        double mgLat = lat + dLat;
        double mgLng = lng + dLng;
        return new double[] { mgLng, mgLat };
    }

    /**
     * 纬度转换
     *
     * @param lng 经度
     * @param lat 纬度
     * @return 纬度
     */
    private static double transformlat(double lng, double lat) {
        double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * GeoConsts.PI) + 20.0 * Math.sin(2.0 * lng * GeoConsts.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * GeoConsts.PI) + 40.0 * Math.sin(lat / 3.0 * GeoConsts.PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lat / 12.0 * GeoConsts.PI) + 320 * Math.sin(lat * GeoConsts.PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * 经度转换
     *
     * @param lng 经度
     * @param lat 纬度
     * @return 经度
     */
    private static double transformlng(double lng, double lat) {
        double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * GeoConsts.PI) + 20.0 * Math.sin(2.0 * lng * GeoConsts.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lng * GeoConsts.PI) + 40.0 * Math.sin(lng / 3.0 * GeoConsts.PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lng / 12.0 * GeoConsts.PI) + 300.0 * Math.sin(lng / 30.0 * GeoConsts.PI)) * 2.0 / 3.0;
        return ret;
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
