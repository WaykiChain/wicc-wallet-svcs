package com.waykichain.chain.contract.util;

import com.waykichain.chain.contract.wusd.TransferWithFeeByAdminDomain;
import com.waykichain.chain.contract.wusd.domain.ExchangeTokenDomain;
import com.waykichain.chain.contract.wusd.domain.SetAdminDomain;
import com.waykichain.chain.contract.wusd.domain.SetExchangeRateDomain;

public class Main {

    public static void main(String[] args){
        System.out.println("Test1");
        ExchangeTokenDomain ex2 = new ExchangeTokenDomain();
        ex2.deserialize("f001000006000000000000000000000000000000");
        System.out.println(ex2);

        System.out.println("hex admin: " + ContractUtil.toHexString("admin"));
        System.out.println("hex exchangeRate: " + ContractUtil.toHexString("exchangeRate"));

    //    String hexStr = "f0320000313233343536373839303132333435363738393031323334353637383930313233340200774c574257316a5676644b343466693943706b336436624c73597a33624873474366a08601000000000077544a3675466b4643443155696476687137554177554e644b504b447a5237586974400d030000000000";
        String hexStr = ContractUtil.toHexString("WRuR8oX59rVfHLQFVVVW9d36FUkczTNUpn");
        System.out.println("hexStr:" + hexStr);
        printByte(hexStr);

        String xxx = "1:1";
        String xx4 = "1:1";
        String xx2 = new String("1:1");
        String xx3 = new String("1:1");
        System.out.println(xxx.equals(xx4));

    //    System.out.println("s:" + ContractUtil.to2HexString(2,(byte)2));

   //     System.out.println("1" + Integer.toHexString("1"));

/*        printAdmin();
        printExchagneRate();
        printExchangeToken();
        printTransfer();*/
    }

    public static void printAdmin(){

        String adminAddr = "wTwrWser78mEa22f8mHfiHGrdKysTv8eBU";
        SetAdminDomain setAdminDomain = new SetAdminDomain();
        setAdminDomain.setAdminAddr(adminAddr);

        String adminHex = setAdminDomain.serialize();
        System.out.println("adminHex:" + adminHex);

        printByte(adminHex);
    }

    public static void printExchagneRate(){
        double exchangeRate = 1.51;
        long exchangeRateParam = (long) (exchangeRate * 10000);

        SetExchangeRateDomain domain = new SetExchangeRateDomain();
        domain.setType("13");
        domain.setExchangeRate(exchangeRateParam);

        String exchangeRateHex = domain.serialize();
        System.out.println("exchangeRateHex:" + exchangeRateHex);
        printByte(exchangeRateHex);
    }

    public static void printExchangeToken(){
        ExchangeTokenDomain domain = new ExchangeTokenDomain();
        double exchangeRate = 1.51;
        long exchangeRateParam = (long) (exchangeRate * 10000);
        domain.setExchangeRate(exchangeRateParam);
        domain.setExchangeTokenAmount(151000000);

        String ser = domain.serialize();
        System.out.println("printExchangeToken:" + ser);
        printByte(ser);

    }

    public static void printTransfer(){
        TransferWithFeeByAdminDomain domain = new TransferWithFeeByAdminDomain();
        domain.setFrom("wTwrWser78mEa22f8mHfiHGrdKysTv8eBU");
        domain.setTo("wX6LbTqTJGxkLwREWnYPVBu9XsLzpb5zpK");
        domain.setAmount(10000000);
        domain.setFee(0);
        String ser = domain.serialize();
        System.out.println("printTransfer:" + ser);
        printByte(ser);
    }



    public static void printByte(String str) {

        for(int i=0; i<str.length(); i=i+2) {
            System.out.print("0x" + str.substring(i, i+2) + ",");
        }
    }

}
