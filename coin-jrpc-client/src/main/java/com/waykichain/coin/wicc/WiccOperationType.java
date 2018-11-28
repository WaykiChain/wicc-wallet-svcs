package com.waykichain.coin.wicc;

/**
 *
 * Created at 2018-08-15, 00:38.
 * @author Joss
 * @since 1.0
 */
public class WiccOperationType {

    public static String METHOD_CALL = "call";
    public final static String METHOD_GET_BALANCE = "getbalance";

    public final static String METHOD_NEW_ADDRESS = "getnewaddress";
    //获取区块信息
    public final static String METHOD_GET_BLOCK_CHAIN_INFO = "getblockchaininfo";
    //获取账户信息
    public final static String METHOD_GET_ACCOUNT_INFO = "getaccountinfo";
    //列举地址信息
    public final static String METHOD_LIST_ADDR = "listaddr";
    //获取所有(最近)交易信息
    public final static String METHOD_GET_ALL_TX_INFO = "getalltxinfo";
    //列举所有钱包的交易
    public final static String METHOD_LIST_TX = "listtx";
    //列举所有钱包的交易
    public final static String METHOD_GET_BLOCK = "getblock";
    //获取交易信息
    public final static String METHOD_GET_TX_DETAIL = "gettxdetail";
    //注册地址
    public final static String METHOD_REGIST_ACOUNT_TX = "registaccounttx";
    //发送币
    public final static String METHOD_SEND_TO_ADDRESS = "sendtoaddress";

    public final static String METHOD_SEND_TO_ADDRESS_RAW = "sendtoaddressraw";

    public final static String METHOD_GET_INFO = "getinfo";

    public final static String METHOD_CREATE_CONTRACT_TX = "createcontracttx";

    public final static String METHOD_CREATE_CONTRACT_TX_RAW = "createcontracttxraw";

    public final static String METHOD_SUBMIT_TX = "submittx";

    public final static String METHOD_REGISTER_APP_TX = "registerapptx";

    public final static String METHOD_GET_SCRIPT_ID = "getscriptid";

    public final static String METHOD_GET_SCRIPT_DATA = "getscriptdata";

    public final static String METHOD_GET_APP_ACC_INFO = "getappaccinfo";

    //raw
}
