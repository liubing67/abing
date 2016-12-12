package com.tools.util;

import android.text.TextUtils;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称：SecondPay
 * 类描述： 校验工具类
 * 创建人：Administrator
 * 创建时间：2016/8/9 9:31
 * 修改人：Administrator
 * 修改时间：2016/8/9 9:31
 * 修改备注：
 */
public class CheckUtils {


    //字母
    public static boolean isLetter(String msg) {
        Pattern p = Pattern.compile("^[a-zA-Z]+$");
        Matcher m = p.matcher(msg);
        return m.matches();
    }

    //中文
    public static boolean isChar(String msg) {
        Pattern p = Pattern.compile("^[\u4E00-\u9FA5]+$");
        Matcher m = p.matcher(msg);
        return m.matches();
    }

    //整数
    public static boolean isNum(String msg) {
        Pattern p = Pattern.compile("^\\d+$");
        Matcher m = p.matcher(msg);
        return m.matches();
    }

    //小数
    public static boolean isNumeric(String msg) {
        Pattern p = Pattern.compile("^\\d+(.\\d+)?$");
        Matcher m = p.matcher(msg);
        return m.matches();
    }

    //手机号
    public static boolean isMobile(String msg) {
        Pattern p = Pattern.compile("^(\\+86)?1[34578]\\d{9}$");
        Matcher m = p.matcher(msg);
        return m.matches();
    }

    //Email
    public static boolean isEmail(String msg) {
        Pattern p = Pattern.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
        Matcher m = p.matcher(msg);
        return m.matches();
    }

    //身份证
    public static boolean isIDCard(String msg) {
        Pattern p = Pattern.compile("^\\d{15}|\\d{18}|(\\d{17}(\\d|X|x))$");
        Matcher m = p.matcher(msg);
        return m.matches();
    }

    //正则截取
    public static String subRegexString(String msg, String regex) {
        String result = "";
        if (!TextUtils.isEmpty(msg)) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(msg);
            while (m.find()) {
                result = m.group();
                break;
            }
        }
        return result;
    }

    //正则截取验证码
    public static String regexSMSCode(String msg) {
        return subRegexString(msg, "(?<!\\d)\\d{6}(?!\\d)");
    }

    /**
     * 判断密码是否同时包含数字与字母
     */
    public static boolean passWordCheck(String mobile) {
        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+$");
        Matcher m = p.matcher(mobile);
        Log.i("info", "是否同时包含数字与字母" + m.matches());
        return m.matches();
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit =getBankCardCheckCode(cardId.substring(0,cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }
    /**
     * 从不含校验位的银行卡卡号采用 Luhm
     * 校验算法获得校验位
     *
     * @param nonCheckCodeCardId * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length()== 0||!nonCheckCodeCardId.matches("\\d+")) {//如果传的不是数据返回
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10
                - luhmSum
                % 10) + '0');
    }

    //将手机号设置为132****7940格式
    public static String setHidePhone(String phone)
    {
        String s = "";
        if (!TextUtils.isEmpty(phone))
        {
            if (isMobile(phone))//判断是否是正确手机号，
            {
                s=phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
            }else {
                s=phone;
            }
        }

        return  s;
    }
    //将身份证号设置为3708*****7940格式
    public static String setHideidCard(String idCard)
    {
        String s = "";
        if (!TextUtils.isEmpty(idCard))
        {
            if (isIDCard(idCard))///判断是否是正确身份证号码格式，
            {
                s=idCard.replaceAll("(\\d{6})\\d{9}(\\w{3})","$1*****$2");
            }else {
                s=idCard;
            }
        }
        return s;
    }
    //将银行卡号设置为6228*****7940格式
    public static String setHideBankCard(String bankCard)
    {
        String s="";
        if (!TextUtils.isEmpty(bankCard))
        {
            if (checkBankCard(bankCard))
            {
                s=bankCard.substring(0, 4) + "*****" + bankCard.substring(bankCard.length()-3,bankCard.length());
            }else {
                s=bankCard;
            }
        }
        return s;
    }
}
