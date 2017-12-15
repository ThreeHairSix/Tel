package com.nptel.tel.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.text.TextPaint;
import android.text.TextUtils;
import android.widget.TextView;

import com.nptel.tel.activity.TelApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.Character.UnicodeBlock;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Tools {

    /**
     * @param tv
     * @return void 返回类型
     * @throws
     * @Description: textView加粗
     * @date 2016年8月8日上午8:54:04
     */
    public static void setTextViewBold(TextView tv) {
        TextPaint tp = tv.getPaint();
        tp.setFakeBoldText(true);
    }

    /**
     * 获取sd卡路径图片900*900
     **/
    public static Bitmap getBitmapFromSD900X900(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        int width = 900;
        int height = 900;
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高，注意此处的bitmap为null
        bitmap = BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false; // 设为 false
        options.inSampleSize = 1;
        bitmap = BitmapFactory.decodeFile(path, options);
        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    /**
     * 根据指定的图像路径和大小来获取缩略图 此方法有两点好处： 1.
     * 使用较小的内存空间，第一次获取的bitmap实际上为null，只是为了读取宽度和高度，
     * 第二次读取的bitmap是根据比例压缩过的图像，第三次读取的bitmap是所要的缩略图。 2.
     * 缩略图对于原图像来讲没有拉伸，这里使用了2.2版本的新工具ThumbnailUtils，使 用这个工具生成的图像不会被拉伸。
     *
     * @param imagePath 图像的路径
     * @param width     指定输出图像的宽度 100
     * @return 生成的缩略图
     */

    public static Bitmap getImageThumbnail(String imagePath, int width) {
        if (TextUtils.isEmpty(imagePath)) {
            return null;
        }
        File file = new File(imagePath);
        if (!file.exists()) {
            return null;
        }
        int height = width;
        Bitmap bitmap = null;
        try {
            InputStream is = new FileInputStream(file);
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            bitmap = BitmapFactory.decodeStream(is, null, opt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (bitmap == null) {
            return null;
        }
        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    /**
     * @param filePath
     * @return boolean 返回类型
     * @throws
     * @Description: 判断图片是否损坏
     * @date 2016年9月14日上午11:52:52
     */
    public static boolean isBitmapDamage(String filePath) {
        if (!filePath.endsWith(".jpg") && !filePath.endsWith(".png")) {
            return true;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options); // filePath代表图片路径
        if (options.mCancel || options.outWidth == -1
                || options.outHeight == -1) {
            return false;
        }
        return true;
    }



    /**
     * 获取当前时间 yyyy-MM-dd
     **/
    public static String getNowTimeToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dataTime = sdf.format(new Date());
        return dataTime;
    }

    /**
     * 获取当前时间 yyyy-MM-dd
     **/
    public static String getNowTime() {

        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new Date());

        return date;
    }

    /**
     * 获取当前时间 yyyy-MM-dd
     **/
    public static String getNowTime2() {

        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "MM-dd-hh-mm");
        String date = sDateFormat.format(new Date());

        return date;
    }

    public static String getTime3() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date d1 = new Date(time);
        String t1 = format.format(d1);
        return t1;
    }

    public static String getTime2() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date d1 = new Date(time);
        String t1 = format.format(d1);
        return t1;
    }

    /**
     * @return 获取星期
     */
    public static String StringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        // mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        // mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        // mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "日";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return "星期" + mWay;
        // return mYear + "年" + mMonth + "月" + mDay + "日" + "/星期" + mWay;
    }

    /**
     * 获取当前时间 yyyy-MM-dd
     **/
    public static String getNowTimeMonth2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String dataTime = sdf.format(new Date());
        return dataTime;
    }

    /**
     * 获取当前时间 yyyy-MM
     **/
    public static String getNowTimeMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        String dataTime = sdf.format(new Date());
        return dataTime;
    }

    /**
     * 去掉多余的反斜线
     **/
    public static String string2Json(String s) {
        String str = "";
        str = s.replaceAll("\\\\", "");
        return str;
    }

    /**
     * 删除目录
     **/
    public static void delete(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
            file.delete();
        }
    }

    public static Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
    }

    /**
     * SharedPreferences数据保存
     **/
    public static void setSharedPreferences(String str, String data) {
        if (TextUtils.isEmpty(data)) {
            return;
        }
        SharedPreferences sp = TelApplication.getInstance()
                .getSharedPreferences("tel", Activity.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(str, data);
        editor.commit();
    }

    /**
     * SharedPreferences数据读取
     **/
    public static String getSharedPreferences(String str) {
        SharedPreferences sp = TelApplication.getInstance()
                .getSharedPreferences("tel", Activity.MODE_PRIVATE);
        return sp.getString(str, "");
    }

    /**
     * 判断文件是否存在
     */
    public static boolean isFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        try {
            return new File(path).exists();
        } catch (Exception e) {
            return false;
        }
    }

//    public static String getTitleBg() {
//        if (UserData.getInstance().imgUrl == null) {
//            return null;
//        }
//        return UserData.getInstance().imgUrl[(int) (Math.random() * UserData
//                .getInstance().imgUrl.length)];
//    }

    public static String decodeUnicode(String theString) {
        if (TextUtils.isEmpty(theString)){
            return "";
        }
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u' || aChar == 'U') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        int index = x++;
                        if (index >= len) {
                            break;
                        }
                        aChar = theString.charAt(index);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                // return null;
                                break;
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    public static String utf8ToUnicode(String inStr) {
        inStr = inStr.replaceAll(" ", "");
        char[] myBuffer = inStr.toCharArray();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inStr.length(); i++) {
            UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
            if (ub == UnicodeBlock.BASIC_LATIN) {
                sb.append(myBuffer[i]);
            } else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                int j = (int) myBuffer[i] - 65248;
                sb.append((char) j);
            } else {
                short s = (short) myBuffer[i];
                String hexS = Integer.toHexString(s);
                if (hexS.length() < 4) {
                    hexS = '0' + hexS;
                }
                String unicode = "\\u" + hexS;
                if (unicode.contains("u0a0")) {
                    sb.append(" ");
                } else {
                    sb.append(unicode.toLowerCase());
                }
            }
        }
        return sb.toString();
    }

    // 版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    // 版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
