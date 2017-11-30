package com.can.mvp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by can on 2017/11/17.
 */

public class StringUtils {
    private static final Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private static final Pattern IMG_URL = Pattern.compile(".*?(gif|jpeg|png|jpg|bmp)");
    private static final Pattern URL = Pattern.compile("^(https|http)://.*?$(net|com|.com.cn|org|me|)");

    public StringUtils() {
    }

    public static boolean isEmpty(String input) {
        return input == null || "".equals(input);
    }

    public static boolean isEmail(String email) {
        return email != null && email.trim().length() != 0?emailer.matcher(email).matches():false;
    }

    public static boolean isImgUrl(String url) {
        return url != null && url.trim().length() != 0?IMG_URL.matcher(url).matches():false;
    }

    public static boolean isPhoneValid(String phone) {
        return isEmpty(phone)?false:phone.length() == 11 && phone.subSequence(0, 1).equals("1");
    }

    public static boolean isUrl(String str) {
        return str != null && str.trim().length() != 0?URL.matcher(str).matches():false;
    }

    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        return bit == 78?false:cardId.charAt(cardId.length() - 1) == bit;
    }

    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if(nonCheckCodeCardId != null && nonCheckCodeCardId.trim().length() != 0 && nonCheckCodeCardId.matches("\\d+")) {
            char[] chs = nonCheckCodeCardId.trim().toCharArray();
            int luhmSum = 0;
            int i = chs.length - 1;

            for(int j = 0; i >= 0; ++j) {
                int k = chs[i] - 48;
                if(j % 2 == 0) {
                    k *= 2;
                    k = k / 10 + k % 10;
                }

                luhmSum += k;
                --i;
            }

            return luhmSum % 10 == 0?'0':(char)(10 - luhmSum % 10 + 48);
        } else {
            return 'N';
        }
    }

    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception var3) {
            return defValue;
        }
    }

    public static int toInt(Object obj) {
        return obj == null?0:toInt(obj.toString(), 0);
    }

    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception var2) {
            return 0L;
        }
    }

    public static float toFloat(String b) {
        try {
            return Float.parseFloat(b);
        } catch (Exception var2) {
            return 0.0F;
        }
    }

    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception var2) {
            return false;
        }
    }

    public static String getString(String s) {
        return s == null?"":s;
    }

    public static String toConvertString(InputStream is) {
        StringBuffer res = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);

        try {
            for(String e = read.readLine(); e != null; e = read.readLine()) {
                res.append(e + "<br>");
            }
        } catch (IOException var13) {
            var13.printStackTrace();
        } finally {
            try {
                if(null != isr) {
                    isr.close();
                    isr.close();
                }

                if(null != read) {
                    read.close();
                    read = null;
                }

                if(null != is) {
                    is.close();
                    is = null;
                }
            } catch (IOException var12) {
                ;
            }

        }

        return res.toString();
    }

    public static String getSubString(int start, int num, String str) {
        if(str == null) {
            return "";
        } else {
            int leng = str.length();
            if(start < 0) {
                start = 0;
            }

            if(start > leng) {
                start = leng;
            }

            if(num < 0) {
                num = 1;
            }

            int end = start + num;
            if(end > leng) {
                end = leng;
            }

            return str.substring(start, end);
        }
    }

    public static String getSubString(String start, String end, String decry) {
        String jieguo = decry.substring(decry.indexOf(start), decry.indexOf(end));
        return jieguo;
    }

    public static String getSubString(String start, int end, String decry) {
        String jieguo = decry.substring(decry.indexOf(start), end);
        return jieguo;
    }

    public static boolean isEmptyList(List list) {
        boolean isEmpty = false;
        if(list == null || list.size() == 0) {
            isEmpty = true;
        }

        return isEmpty;
    }

    public static String byteToString(byte[] responseBody) {
        String recString = "";

        try {
            recString = new String(responseBody, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        return recString;
    }

    public static String phone4Xing(String strPhone) {
        String phoneStar = getSubString(0, 3, strPhone);
        String phoneEnd = getSubString(8, 10, strPhone);
        String newPhone = phoneStar + "*****" + phoneEnd;
        return newPhone;
    }
}
