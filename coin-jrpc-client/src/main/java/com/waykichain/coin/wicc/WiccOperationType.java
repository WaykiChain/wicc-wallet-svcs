package com.waykichain.coin.wicc;

/**
 *
 * Created at 2018-08-15, 00:38.
 * @author Joss
 * @since 1.0
 */
public class WiccOperationType {

    public static final String METHOD_GET_BLOCK_COUNT = "getblockcount";
    public static String METHOD_CALL = "call";
    public final static String METHOD_GET_BALANCE = "getbalance";

    public final static String METHOD_NEW_ADDRESS = "getnewaddr";
    //获取区块信息
    public final static String METHOD_GET_BLOCK_CHAIN_INFO = "getblockchaininfo";
    //获取账户信息
    public final static String METHOD_GET_ACCOUNT_INFO = "getaccountinfo";
    //列举地址信息
    public final static String METHOD_LIST_ADDR = "listaddr";
    //检查普通地址或者合约地址是否有效
    public final static String METHOD_VALIDATE_ADDR = "validateaddr";
    //获取地址对应的私钥
    public final static String METHOD_DUMP_PRIVKEY = "dumpprivkey";
    //用于创建离线激活账户的交易内容
    public final static String METHOD_GEN_REGISTER_ACCOUNTRAW = "genregisteraccountraw";
    //将私钥（由dumpprivkey导出）导入钱包
    public final static String METHOD_GEN_IMPORT_PRIVKEY = "importprivkey";

    //获取所有(最近)交易信息
    public final static String METHOD_GET_ALL_TX_INFO = "getalltxinfo";
    //列举所有钱包的交易
    public final static String METHOD_LIST_TX = "listtx";
    //列举所有钱包的交易
    public final static String METHOD_GET_BLOCK = "getblock";

    public final static String METHO_GET_BLOCK_HASH = "getblockhash";

    //获取交易信息
    public final static String METHOD_GET_TX_DETAIL = "gettxdetail";
    //注册地址
    public final static String METHOD_REGISTER_ACOUNT_TX = "registeraccounttx";
    //发送币
    public final static String METHOD_SEND_TO_ADDRESS = "sendtoaddress";
    //从源地址账户转账到目的地址账户,手动设置手续费
    public final static String METHOD_SEND_TO_ADDRESS_WITH_FEE = "sendtoaddresswithfee";
    //根据签名字段解析原始交易单
    public final static String METHOD_DECODE_RAW_TX = "decoderawtx";

    //创建交易签名字段,手动设置手续费,可用 submittx 方法进行提交交易
    public final static String METHOD_SEND_TO_ADDRESS_RAW = "gensendtoaddressraw";

    public final static String METHOD_GET_INFO = "getinfo";

    public final static String METHOD_CREATE_CONTRACT_TX = "callcontracttx";

    public final static String METHOD_CREATE_CONTRACT_TX_RAW = "callcontracttxraw";

    public final static String METHOD_SUBMIT_TX = "submittx";

    public final static String METHOD_REGISTER_APP_TX = "registercontracttx";

    public final static String METHOD_GET_SCRIPT_ID = "getcontractregid";

    public final static String METHOD_GET_CONTRACT_DATA = "getcontractdata";

    public final static String METHOD_GET_CONTRACT_DATA_RAW = "getcontractdataraw";

    public final static String METHOD_GET_CONTRACT_ACCOUNT_INFO = "getcontractaccountinfo";
    //获取智能合约信息
    public final static String METHOD_GET_CONTRACT_INFO = "getcontractinfo";

    public final static String METHOD_GET_TOTAL_COIN = "gettotalcoins";

    //raw
}
