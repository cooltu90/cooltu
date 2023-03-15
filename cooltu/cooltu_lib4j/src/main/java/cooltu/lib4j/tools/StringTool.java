package cooltu.lib4j.tools;

import java.text.DecimalFormat;

public class StringTool {

    /***************************************
     *
     * 判断字符串是否为空。包括null和"","   "等
     *
     ***************************************/
    public static boolean isBlank(String text) {
        return text == null || text.trim().length() <= 0;
    }

    /***************************************
     *
     * 判断字符串是否有值，不包括"","  "等
     *
     ***************************************/
    public static boolean isNotBlank(String text) {
        return !isBlank(text);
    }

    /***************************************
     *
     * 格式化数字。比如12，格式化成4位的0012
     *
     * @param number 需要格式化的数字，整数型
     * @param digits 位数
     * @return 格式化的数字
     *
     ***************************************/
    public static String formatNumber(Object number, int digits) {
        StringBuilder sb = new StringBuilder();
        sb.append(number);
        int rest = digits - sb.length();
        if (rest > 0) {
            for (int i = 0; i < rest; i++) {
                sb.insert(0, "0");
            }
        }
        return sb.toString();
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public static String parseNumber(double num, int bit, boolean trim) {
        String type = null;
        if (trim) {
            type = "#." + repeatString(bit, "#");
        } else {
            type = "0." + repeatString(bit, "0");
        }
        return new DecimalFormat(type).format(num);
    }


    /***************************************
     *
     * 重复拼接字符串
     *
     * @param times 重复几次
     * @param str 重复的字符串
     * @return 拼接的字符串
     *
     ***************************************/
    public static String repeatString(int times, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /***************************************
     *
     * object转换成string
     *
     ***************************************/
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        } else if (obj instanceof String) {
            return (String) obj;
        } else {
            return String.valueOf(obj);
        }
    }


    /**************************************************
     *
     * 判断是否为url
     *
     **************************************************/
    public static boolean isUrl(String str) {
        if (isBlank(str))
            return false;
        str = str.trim();
        return str.matches("^(http|https)://.+");
    }

    /**************************************************
     *
     * 判断给定的字符串，是否在给定的字符串数组中
     *
     **************************************************/
    public static boolean inRange(String str, String... strs) {
        for (int i = 0; i < CountTool.count(strs); i++) {
            if (strs[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    /************************************************
     *
     * 获取子字符串
     *
     ************************************************/
    public static String getSub(String oriStr, String startStr, String left, String right) {
        int fromIndex = isBlank(startStr) ? 0 : (oriStr.indexOf(startStr) + startStr.length());
        int startIndex = oriStr.indexOf(left, fromIndex) + left.length();
        return oriStr.substring(startIndex, oriStr.indexOf(right, startIndex));
    }


    /**************************************************
     *
     * 获取子字符串
     * startIndex 为起点位置
     * bit 为截取几位
     *
     **************************************************/
    public static String getSubFromStart(String str, int startIndex, int bit) {
        int endIndex = startIndex + bit;
        return getSub(str, startIndex, endIndex);
    }


    public static String getSubFromStart(String str, int bit) {
        return getSubFromStart(str, 0, bit);
    }


    /**************************************************
     *
     * 获取子字符串
     * endIndex 为终点位置
     * bit 为截取几位
     *
     **************************************************/
    public static String getSubFromEnd(String str, int endIndex, int bit) {
        int startIndex = endIndex - bit;
        return getSub(str, startIndex, endIndex);
    }

    public static String getSubFromEnd(String str, int bit) {
        return getSubFromEnd(str, str.length(), bit);
    }


    //获取子字符串，但是会判断首尾位置
    private static String getSub(String str, int startIndex, int endIndex) {
        int length = str.length();
        if (startIndex > endIndex) {
            int temp = startIndex;
            startIndex = endIndex;
            endIndex = temp;
        }
        startIndex = getIndexInStr(length, startIndex);
        endIndex = getIndexInStr(length, endIndex);
        return str.substring(startIndex, endIndex);
    }

    //修改字符串的角标，让角标在字符串的范围内
    private static int getIndexInStr(int length, int num) {
        if (num < 0) {
            return 0;
        }
        if (num >= length) {
            return length;
        }
        return num;
    }


    public static String getColorfulStr(String str, String color) {
        return "<font color='" + color + "'>" + str + "</font>";
    }

    public static String getColorfulStr(char str, String color) {
        return "<font color='" + color + "'>" + str + "</font>";
    }

    public static boolean contains(String str, char c) {
        if (StringTool.isBlank(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (c == str.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public static String getColorfulStr1(String company, String keyword, String defaultColor, String lightColor) {
        if (isBlank(company)) {
            return null;
        }

        if (isBlank(keyword)) {
            return getColorfulStr(company, defaultColor);
        }

        for (int i = 0; i < keyword.length(); i++) {
            char key = keyword.charAt(i);
            if (!contains(company, key)) {
                return null;
            }
        }
        return getColorfulStr(company, keyword, defaultColor, lightColor);
    }

    public static String getColorfulStr(String company, String keyword, String defaultColor, String lightColor) {
        StringBuilder sb = new StringBuilder();

        int length = company.length();
        char c = company.charAt(0);
        boolean isContains = false;
        if (contains(keyword, c)) {
            isContains = true;
        }
        if (length == 1) {
            return isContains ? getColorfulStr(company, lightColor) : getColorfulStr(company, defaultColor);
        }
        StringBuilder sub = new StringBuilder();
        sub.append(c);
        for (int i = 1; i < length; i++) {
            c = company.charAt(i);
            if (contains(keyword, c)) {
                if (!isContains) {
                    //上一个不包含
                    String colorStr = getColorfulStr(sub.toString(), defaultColor);
                    sb.append(colorStr);
                    sub = new StringBuilder();
                }
                sub.append(c);
                isContains = true;
            } else {
                if (isContains) {
                    String colorStr = getColorfulStr(sub.toString(), lightColor);
                    sb.append(colorStr);
                    sub = new StringBuilder();
                }
                sub.append(c);
                isContains = false;
            }
        }
        if (isContains) {
            String colorStr = StringTool.getColorfulStr(sub.toString(), lightColor);
            sb.append(colorStr);
        } else {
            String colorStr = StringTool.getColorfulStr(sub.toString(), defaultColor);
            sb.append(colorStr);
        }

        return sb.toString();
    }


}
