package com.tanhua.common.utils;

/**
 * @创建人：Young
 * @时 间： 2019/3/13
 * @描 述: 高德地图对应经纬度计算距离
 */
public class LocationUtils {
    // 地球赤道半径
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * @描述 经纬度获取距离，单位为米
     * @参数 [lat1, lng1, lat2, lng2]
     * @返回值 double
     * @创建人 Young
     * @创建时间 2019/3/13 20:33
     **/
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 100d) / 100d;

        return s;
    }
 
    /*public static void main(String[] args) {
        double distance = getDistance(31.12055, 131.717594,
            21.090867, 111.817629);
        System.out.println("距离" + distance + "千米");
    }*/

}
