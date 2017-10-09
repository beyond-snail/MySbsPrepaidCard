package com.tool.utils.utils;

////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//              佛祖保佑       永无BUG     永不修改                  //
//                                                                //
//          佛曰:                                                  //
//                  写字楼里写字间，写字间里程序员；                   //
//                  程序人员写程序，又拿程序换酒钱。                   //
//                  酒醒只在网上坐，酒醉还来网下眠；                   //
//                  酒醉酒醒日复日，网上网下年复年。                   //
//                  但愿老死电脑间，不愿鞠躬老板前；                   //
//                  奔驰宝马贵者趣，公交自行程序员。                   //
//                  别人笑我忒疯癫，我笑自己命太贱；                   //
//                  不见满街漂亮妹，哪个归得程序员？                   //
////////////////////////////////////////////////////////////////////

import java.math.BigDecimal;

/**********************************************************
 * *
 * Created by wucongpeng on 2016/12/13.        *
 **********************************************************/


public class Arith {

    //默认除法运算精度

    private static final int DEFAULT_DIV_SCALE = 10;

    /**
     * 提供精确加法计算的add方法
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数数学加和，以字符串格式返回
     */

    public static String add(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).toString();
    }

    /**
     * 提供精确减法运算的sub方法
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.subtract(b2).doubleValue();
    }


    /**
     * 提供精确的减法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数数学差，以字符串格式返回
     */
    public static String subtract(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).toString();
    }

    /**
     * 提供精确乘法运算的mul方法
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数的数学积，以字符串格式返回
     */

    public static String multiply(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).toString();
    }


    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * <p>
     * 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
     *
     * @param v1
     * @param v2
     * @return 两个参数的商
     */
    public static double divide(double v1, double v2)
    {
        return divide(v1, v2, DEFAULT_DIV_SCALE);
    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * <p>
     * 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
     *
     * @param v1
     * @param v2
     * @param scale 表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */

    public static double divide(double v1, double v2, int scale)
    {
        return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * <p>
     * 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
     *
     * @param v1
     * @param v2
     * @param scale      表示需要精确到小数点以后几位
     * @param round_mode 表示用户指定的舍入模式
     * @return 两个参数的商
     */

    public static double divide(double v1, double v2, int scale, int round_mode) {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, round_mode).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * <p>
     * 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
     * @param v1
     * @param v2
     * @return 两个参数的商，以字符串格式返回
     */
    public static String divide(String v1, String v2)
    {
        return divide(v1, v2, DEFAULT_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * <p>
     * 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
     * @param v1
     * @param v2
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商，以字符串格式返回
     */

    public static String divide(String v1, String v2, int scale)
    {
        return divide(v1, v2, DEFAULT_DIV_SCALE, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * <p>
     * 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
     * @param v1
     * @param v2
     * @param scale      表示需要精确到小数点以后几位
     * @param round_mode 表示用户指定的舍入模式
     * @return 两个参数的商，以字符串格式返回
     */

    public static String divide(String v1, String v2, int scale, int round_mode)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, round_mode).toString();
    }

    /**
     * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */

    public static double round(double v, int scale)
    {
        return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 提供精确的小数位四舍五入处理
     * @param v          需要四舍五入的数字
     * @param scale      小数点后保留几位
     * @param round_mode 指定的舍入模式
     * @return 四舍五入后的结果
     */

    public static double round(double v, int scale, int round_mode)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale, round_mode).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果，以字符串格式返回
     */

    public static String round(String v, int scale)
    {
        return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 提供精确的小数位四舍五入处理
     * @param v          需要四舍五入的数字
     * @param scale      小数点后保留几位
     * @param round_mode 指定的舍入模式
     * @return 四舍五入后的结果，以字符串格式返回
     */

    public static String round(String v, int scale, int round_mode)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, round_mode).toString();
    }

//    /**
//     * 提供精确的除法运算方法div
//     * @param value1 被除数
//     * @param value2 除数
//     * @param scale 精确范围
//     * @return 两个参数的商
//     * @throws IllegalAccessException
//     */
//    public static double div2Double(double value1,double value2,int scale) throws IllegalAccessException{
//        //如果精确范围小于0，抛出异常信息
//        if(scale<0){
//            throw new IllegalAccessException("精确度不能小于0");
//        }
//        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
//        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
//        return b1.divide(b2, scale).doubleValue();
//    }
//
//
//    public static int div2Int(double value1,double value2,int scale) throws IllegalAccessException{
//        //如果精确范围小于0，抛出异常信息
//        if(scale<0){
//            throw new IllegalAccessException("精确度不能小于0");
//        }
//        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
//        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
//        return b1.divide(b2, scale).intValue();
//    }

}
