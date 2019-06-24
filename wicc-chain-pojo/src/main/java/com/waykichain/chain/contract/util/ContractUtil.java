package com.waykichain.chain.contract.util;

import java.io.UnsupportedEncodingException;

public class ContractUtil {

    public static final Long SYSTEM_SEND_COIN=85171907L;



    /**
     * 字符类型 Hex码转字符,  Convert Contract Content Hex to Contract Content String
     * @param data
     * @return
     */
    public static String ConvertContractData(String data)  {

        byte[] utf8Data = new byte[data.length()/2];
        for(int i=0,j=0; i<data.length(); i+=2,j++) {
            String temp = data.substring(i, i+2);
            utf8Data[j] = (byte)Integer.parseInt(temp,16);
        }
        try {
            return  new String(utf8Data,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //    logger.error("转化lua脚本失败直接返回hex码");
        }
        return data;
    }


    //转换十六进制编码为字符串
    public static String hexToString(String s) {
        if ("0x".equals(s.substring(0, 2))) {
            s = s.substring(2);
        }
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");//UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return s;
    }

    //转换十六进制编码为UTF8
    public static String hexToUTF8(String s) {
        if (s == null || s.equals("")) {
            return null;
        }

        try {
            int total = s.length() / 2;
            int pos = 0;

            byte[] buffer = new byte[total];
            for (int i = 0; i < total; i++) {
                int start = i * 2;

                buffer[i] = (byte) Integer.parseInt(s.substring(start, start + 2), 16);
                pos++;
            }

            return new String(buffer, 0, pos, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 转换为16进制，8位，不足补0
     * @param val
     * @return
     */
    public static String to2HexString(Integer val) {
        /*
        String s = Integer.toHexString(val);
        if (s.length() < 2) {
            s = "0" + s;
        }
        return fillZero(s, 8);
        */

        String s = Integer.toHexString(val);
        String first = "";
        if (s.length() % 2 == 1) {
            first = "0" + s.substring(0, 1);
            s = s.substring(1, s.length());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i > 1; i = i - 2) {
            sb.append(s.substring(i - 2, i));
        }
        sb.append(first);

        return fillZero(sb.toString(), 8);
    }

    public static String to2HexString(Integer val, byte digit) {
        String s = Integer.toHexString(val);
        String first = "";
        if (s.length() % 2 == 1) {
            first = "0" + s.substring(0, 1);
            s = s.substring(1, s.length());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i > 1; i = i - 2) {
            sb.append(s.substring(i - 2, i));
        }
        sb.append(first);

        return fillZero(sb.toString(), digit*2);
    }

    public static String to2HexString(byte val) {
        String s = Integer.toHexString(val);
        if (s.length() < 2) {
            s = "0" + s;
        }
        return fillZero(s, 2);
    }

    public static String upsidedownHex(String s){
        StringBuffer sb = new StringBuffer();
        for (int i = s.length(); i > 1; i = i - 2) {
            sb.append(s.substring(i - 2, i));
        }
        return sb.toString();
    }

    public static String to2HexString(Long val) {
        String s = Long.toHexString(val);
        String first = "";
        if (s.length() % 2 == 1) {
            first = "0" + s.substring(0, 1);
            s = s.substring(1, s.length());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i > 1; i = i - 2) {
            sb.append(s.substring(i - 2, i));
        }
        sb.append(first);

        return fillZero(sb.toString(), 16);
    }

    public static String to2HexString4byte(Long val) {
        String s = Long.toHexString(val);
        String first = "";
        if (s.length() % 2 == 1) {
            first = "0" + s.substring(0, 1);
            s = s.substring(1, s.length());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i > 1; i = i - 2) {
            sb.append(s.substring(i - 2, i));
        }
        sb.append(first);

        return fillZero(sb.toString(), 8);
    }

    public static String toHexString(String s) {
        StringBuilder sb = new StringBuilder();
        String s4 = null;
        int ch = 0;
        for (int i = 0; i < s.length(); i++) {
            ch = (int) s.charAt(i);
            s4 = Integer.toHexString(ch);
            sb.append(s4);
        }
        return sb.toString();//0x表示十六进制
    }

    public static String fillZero(String str, int maxlength) {
        int filllenth = maxlength - str.length();
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < filllenth; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    public static String utf8ToHexString(String s, int maxlength) {
        if (s == null || s.equals("")) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try {
            char c;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                byte[] b;

                b = Character.toString(c).getBytes("utf-8");

                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append(Integer.toHexString(k));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int gap = (maxlength * 2 - sb.length());
        if(gap > 0) {
            for(int j = 0; j < gap; j++) {
                sb.append("0");
            }
        }
        return sb.toString();
    }

}
