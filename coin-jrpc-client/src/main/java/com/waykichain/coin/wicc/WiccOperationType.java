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

    public final static String METHOD_NEW_ADDRESS = "getnewaddr";

    //获取账户信息
    public final static String METHOD_GET_ACCOUNT_INFO = "getaccountinfo";
    //列举地址信息
    public final static String METHOD_LIST_ADDR = "listaddr";
    //检查普通地址或者合约地址是否有效
    public final static String METHOD_VALIDATE_ADDR = "validateaddr";
    //获取地址对应的私钥
    public final static String METHOD_DUMP_PRIVKEY = "dumpprivkey";

    //将私钥（由dumpprivkey导出）导入钱包
    public final static String METHOD_GEN_IMPORT_PRIVKEY = "importprivkey";

    //列举所有钱包的交易
    public final static String METHOD_LIST_TX = "listtx";
    //列举所有钱包的交易
    public final static String METHOD_GET_BLOCK = "getblock";

    public final static String METHO_GET_BLOCK_HASH = "getblockhash";

    //获取交易信息
    public final static String METHOD_GET_TX_DETAIL = "gettxdetail";

    //注册地址
    public final static String METHOD_REGISTER_ACOUNT_TX = "submitaccountregistertx";

    public final static String METHOD_SUBMIT_SEND_TX = "submitsendtx";

    public final static String METHOD_SUBMIT_TX_RAW = "submittxraw";

    //根据签名字段解析原始交易单
    public final static String METHOD_DECODE_TX_RAW = "decodetxraw";

    public final static String METHOD_GET_INFO = "getinfo";

    public final static String METHOD_CREATE_CONTRACT_TX = "submitcontractcalltx";

    public final static String METHOD_REGISTER_APP_TX = "submitcontractdeploytx";

    public final static String METHOD_GET_SCRIPT_ID = "getcontractregid";

    public final static String METHOD_GET_CONTRACT_DATA = "getcontractdata";

    public final static String METHOD_GET_CONTRACT_ACCOUNT_INFO = "getcontractaccountinfo";
    //获取智能合约信息
    public final static String METHOD_GET_CONTRACT_INFO = "getcontractinfo";

    public final static String METHOD_GET_TOTAL_COIN = "gettotalcoins";


    /**
     * dex
     */

    public final static String METHOD_SUBMIT_DEX_BUY_LIMIT_ORDER_TX = "submitdexbuylimitordertx" ;

    public final static String METHOD_SUBMIT_DEX_SELL_LIMIT_ORDER_TX = "submitdexselllimitordertx" ;

    public final static String METHOD_SUBMIT_DEX_BUY_MARKET_ORDER_TX = "submitdexbuymarketordertx" ;

    public final static String METHOD_SUBMIT_DEX_SELL_MARKET_ORDER_TX = "submitdexsellmarketordertx" ;

    public final static String METHOD_SUBMIT_DEX_CANCEL_ORDER_TX = "submitdexcancelordertx" ;

    public final static String METHOD_SUBMIT_DEX_SETTLE_TX = "submitdexsettletx" ;

    public final static String METHOD_GET_SCOIN_INFO = "getscoininfo";

    public final static String METHOD_SUBMIT_PRICE_FEED_TX = "submitpricefeedtx" ;

    public final static String METHOD_GET_MEDIAN_PRICE = "getmedianprice" ;

    public final static String METHOD_GET_DEX_SYS_ORDERS = "getdexsysorders" ;

    /**
     * cdp
     */
    public final static String METHOD_CDP_USER_CDP = "getusercdp";

    public final static String METHOD_CDP_GET_INFO = "getcdp";

    public final static String METHOD_CDP_SUBMIT_STAKE_TX = "submitcdpstaketx";

    public final static String METHOD_CDP_SUBMIT_REDEEM_TX = "submitcdpredeemtx";

    public final static String METHOD_CDP_SUBMIT_LIQUIDATE_TX = "submitcdpliquidatetx";
}

//addmulsigaddr num_signatures ["address",...]
//addnode "node:port" "add|remove|onetry"
//backupwallet "dest_dir"
//createmulsig num_signatures ["address",...]
//decodemulsigscript "hex"
//decodetxraw "hexstring"
//disconnectblock "numbers"
//dropminerkeys
//dropprivkey "address"
//dumpprivkey "address"
//dumpwallet "filename"
//encryptwallet "passphrase"
//genmulsigtx "multisigscript" "recvaddress" "amount" "fee" "height"
//getaccountinfo "addr"
//getaddednodeinfo "dns" ["node"]
//getassets
//getblock "hash or height" ["verbose"]
//getblockcount
//getblockfailures "block height"
//getcdp "cdp_id"
//getchaininfo "num"
//getcoinunitinfo
//getconnectioncount
//getcontractaccountinfo "contract regid" "account address or regid"
//getcontractassets "contract_regid"
//getcontractdata "contract regid" "key" [hexadecimal]
//getcontractinfo "contract regid"
//getcontractregid
//getdexorder "order_id"
//getdexorders ["begin_height"] ["end_height"] ["max_count"] ["last_pos_info"]
//getdexsysorders ["height"]
//gethash  "str"
//getinfo
//getminedblocks
//getmininginfo
//getnettotals
//getnetworkinfo
//getnewaddr  ["IsMiner"]
//getpeerinfo
//getrawmempool ( verbose )
//getscoininfo
//getsignature
//gettotalcoins
//gettxdetail "txid"
//getusercdp "addr"
//getwalletinfo
//help ( "command" )
//importprivkey "privkey"
//importwallet "filename"
//invalidateblock "hash"
//listaddr
//listcontractassets regid
//listcontracts "show detail"
//listdelegates
//listtx
//listtxcache
//ping
//reconsiderblock "hash"
//reloadtxcache
//saveblocktofile "blockhash" "filepath"
//setgenerate generate ( genblocklimit )
//signmessage "address" "message"
//signtxraw "str" "addr"
//startcommontpstest "period" "batch_size"
//startcontracttpstest "regid" "period" "batch_size"
//stop
//submitaccountregistertx "addr" ["fee"]
//submitassetissuetx "addr" "asset_symbol" "asset_owner_addr" "asset_name" total_supply mintable [symbol:fee:unit]
//submitassetupdatetx "addr" "asset_symbol" "update_type" "update_value" [symbol:fee:unit]
//submitblock "hexdata" ( "jsonparametersobject" )
//submitcdpliquidatetx "addr" "cdp_id" liquidate_amount [symbol:fee:unit]
//submitcdpredeemtx "addr" "cdp_id" repay_amount redeem_amount ["symbol:fee:unit"]
//submitcdpstaketx "addr" stake_combo_money mint_combo_money ["cdp_id"] [symbol:fee:unit]
//submitcoinstaketx "addr" "coin_symbol" "coin_amount" ["symbol:fee:unit"]
//submitcontractcalltx "sender_addr" "contract_regid" "arguments" "amount" "fee" ["height"]
//submitcontractdeploytx "addr" "filepath" "fee" ["height"] ["contract_memo"]
//submitdelegatevotetx "sendaddr" "votes" "fee" ["height"]
//submitdexbuylimitordertx "addr" "coin_symbol" "asset_symbol" asset_amount price [symbol:fee:unit]
//submitdexbuymarketordertx "addr" "coin_symbol" coin_amount "asset_symbol" [symbol:fee:unit]
//submitdexcancelordertx "addr" "txid" [symbol:fee:unit]
//submitdexselllimitordertx "addr" "coin_symbol" "asset_symbol" asset_amount price [symbol:fee:unit]
//submitdexsellmarketordertx "addr" "coin_symbol" "asset_symbol" asset_amount [symbol:fee:unit]
//submitdexsettletx "addr" "deal_items" [symbol:fee:unit]
//submitpricefeedtx {price_feeds_json} ["symbol:fee:unit"]
//submitsendtx "from" "to" "symbol:coin:unit" "symbol:fee:unit" ["memo"]
//submittxraw "rawtx"
//submituniversalcontractcalltx "sender_addr" "contract_regid" "arguments" "amount" "fee" ["height"]
//submituniversalcontractdeploytx "addr" "filepath" "fee" ["height"] ["contract_memo"]
//validateaddr "address"
//verifychain ( checklevel numofblocks )
//verifymessage "address" "signature" "message"
//vmexecutescript "addr" "script_path" ["arguments"] [amount] [symbol:fee:unit]
//walletlock
//walletpassphrase "passphrase" "timeout"
//walletpassphrasechange "oldpassphrase" "newpassphrase"
