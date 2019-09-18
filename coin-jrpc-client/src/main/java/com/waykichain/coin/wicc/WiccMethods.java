package com.waykichain.coin.wicc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.waykichain.JsonRpcClient;
import com.waykichain.JsonRpcRequest;
import com.waykichain.coin.wicc.po.*;
import com.waykichain.coin.wicc.vo.*;
import com.waykichain.serialize.AnnotationsConfigurationsLoader;
import com.waykichain.serialize.ConfigurationsLoader;
import com.waykichain.serialize.DataABIEncoder;
import com.waykichain.utils.RandomUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.formula.functions.T;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Joss
 *
 * @since 0.1.0
 */
@Slf4j
public class WiccMethods {
    @Setter
    private JsonRpcClient rpcClient;
    @Setter
    private DataABIEncoder dataAbiEncoder;

    private WiccMethods() {
        super();
    }

    public WiccMethods(JsonRpcClient rpcClient) throws Exception {
        super();
        this.rpcClient = rpcClient;
        ConfigurationsLoader condigurationsLoader;
        condigurationsLoader = new AnnotationsConfigurationsLoader();
        condigurationsLoader.setPackageToScan("com.waykichain.serialize");
//        condigurationsLoader.loadConfigurations();
        this.dataAbiEncoder = new DataABIEncoder(condigurationsLoader);
    }

    public <T, U> T call(String methodId, U data, Class<T> clazz) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_CALL);

        request.getParams().add(this.dataAbiEncoder.encode(methodId, data));
        request.getParams().add("latest");

        return this.execute(request, clazz);
    }


    public WiccAddressJsonRpcResponse getNewAddress() throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_NEW_ADDRESS);
        WiccAddressJsonRpcResponse reponse =  this.execute(request, WiccAddressJsonRpcResponse.class);
        return reponse;
    }

//    public WiccBalanceJsonRpcResponse getBalance(String address) throws Exception {
//        JsonRpcRequest request = new JsonRpcRequest();
//        request.setId(RandomUtils.generateRandomId());
//        request.setMethod(WiccOperationType.METHOD_GET_BALANCE);
//        request.getParams().add(address);
//        WiccBalanceJsonRpcResponse reponse =  this.execute(request, WiccBalanceJsonRpcResponse.class);
//        return reponse;
//    }

    public WiccAccountInfoJsonRpcResponse getAccountInfo(String address) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_ACCOUNT_INFO);
        request.getParams().add(address);
        WiccAccountInfoJsonRpcResponse reponse =  this.execute(request, WiccAccountInfoJsonRpcResponse.class);
        return reponse;
    }

    public WiccSubmitTxJsonRpcResponse activeAddress(String address) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_REGISTER_ACOUNT_TX);
        request.getParams().add(address);
        request.getParams().add(100000);
        WiccSubmitTxJsonRpcResponse reponse =  this.execute(request, WiccSubmitTxJsonRpcResponse.class);
        return reponse;
    }

    public WiccValidateAddrJsonRpcResponse validateAddrress(String address) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_VALIDATE_ADDR);
        request.getParams().add(address);
        WiccValidateAddrJsonRpcResponse reponse =  this.execute(request, WiccValidateAddrJsonRpcResponse.class);
        return reponse;
    }

    public WiccDumpPrivkeyJsonRpcResponse dumpPrivkey(String address) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_DUMP_PRIVKEY);
        request.getParams().add(address);
        WiccDumpPrivkeyJsonRpcResponse reponse =  this.execute(request, WiccDumpPrivkeyJsonRpcResponse.class);
        return reponse;
    }

//    public WiccGenRegisterAccountrawJsonRpcResponse genRegisterAccountraw(WiccGenRegisterAccountrawPO wiccGenRegisterAccountrawPO) throws Exception {
//        JsonRpcRequest request = new JsonRpcRequest();
//        request.setId(RandomUtils.generateRandomId());
//        request.setMethod(WiccOperationType.METHOD_GEN_REGISTER_ACCOUNTRAW);
//        request.getParams().add(wiccGenRegisterAccountrawPO.getFee());
//        request.getParams().add(wiccGenRegisterAccountrawPO.getHeight());
//        request.getParams().add(wiccGenRegisterAccountrawPO.getPublickey());
//        WiccGenRegisterAccountrawJsonRpcResponse reponse =  this.execute(request, WiccGenRegisterAccountrawJsonRpcResponse.class);
//        return reponse;
//    }

    public WiccImportPrivkeyJsonRpcResponse importPrivkey(ImportPrivkeyPO importPrivkeyPO) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GEN_IMPORT_PRIVKEY);
        request.getParams().add(importPrivkeyPO.getPrivkey());
//        request.getParams().add(importPrivkeyPO.getLabel());
//        request.getParams().add(importPrivkeyPO.isRescan());
        WiccImportPrivkeyJsonRpcResponse reponse =  this.execute(request, WiccImportPrivkeyJsonRpcResponse.class);
        return reponse;
    }


    public WiccInfoJsonRpcResponse getInfo() throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_INFO);
        WiccInfoJsonRpcResponse reponse =  this.execute(request, WiccInfoJsonRpcResponse.class);
        return reponse;
    }

    public WiccListAddressJsonRpcResponse listAddress() throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_LIST_ADDR);
        WiccListAddressJsonRpcResponse reponse =  this.execute(request, WiccListAddressJsonRpcResponse.class);
        return reponse;
    }

    public String getTxDetailJson(String txHash) throws Exception{

        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_TX_DETAIL);
        request.getParams().add(txHash);
        return this.execute(request);
    }

//    public WiccGetTxDetailJsonRpcResponse getTxDetail(String tx) throws Exception {
//        JsonRpcRequest request = new JsonRpcRequest();
//        request.setId(RandomUtils.generateRandomId());
//        request.setMethod(WiccOperationType.METHOD_GET_TX_DETAIL);
//        request.getParams().add(tx);
//        WiccGetTxDetailJsonRpcResponse reponse =  this.execute(request, WiccGetTxDetailJsonRpcResponse.class);
//        return reponse;
//    }

    public WiccBlockJsonRpcResponse getBlock(Integer blockId) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_BLOCK);
        request.getParams().add(blockId);
        request.getParams().add(true);
        WiccBlockJsonRpcResponse reponse =  this.execute(request, WiccBlockJsonRpcResponse.class);
        return reponse;
    }

    public WiccBlockJsonRpcResponse getBlock(String hash) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_BLOCK);
        request.getParams().add(hash);
        WiccBlockJsonRpcResponse reponse =  this.execute(request, WiccBlockJsonRpcResponse.class);
        return reponse;
    }


    public WiccGetBlockHashJsonRpcResponse getBlockHash(Integer blockId) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHO_GET_BLOCK_HASH);
        request.getParams().add(blockId);

        return this.execute(request,WiccGetBlockHashJsonRpcResponse.class);
    }

    public WiccSubmitTxJsonRpcResponse sendToAddress(SendTxPO sendTxPO) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SUBMIT_SEND_TX);
        request.getParams().add(sendTxPO.sendAddress);
        request.getParams().add(sendTxPO.receiveAddress);
        request.getParams().add(sendTxPO.coinSymbol+":"+sendTxPO.amount.toString()+":"+sendTxPO.unit);
        request.getParams().add(sendTxPO.feeSymbol+":"+sendTxPO.fee.toString()+":"+sendTxPO.unit);
        WiccSubmitTxJsonRpcResponse reponse =  this.execute(request, WiccSubmitTxJsonRpcResponse.class);
        return reponse;
    }

//    public WiccRawTxJsonRpcResponse sendToAddressRaw(SendToAddressRawPO sendToAddressRawPO)throws Exception{
//        JsonRpcRequest request = new JsonRpcRequest();
//        request.setId(RandomUtils.generateRandomId());
//        request.setMethod(WiccOperationType.METHOD_SEND_TO_ADDRESS_RAW);
//        request.getParams().add(sendToAddressRawPO.getFee());
//        request.getParams().add(sendToAddressRawPO.getAmount());
//        request.getParams().add(sendToAddressRawPO.getSrcaddress());
//        request.getParams().add(sendToAddressRawPO.getRecvaddress());
//        request.getParams().add(sendToAddressRawPO.getHeight());
//        WiccRawTxJsonRpcResponse reponse =  this.execute(request, WiccRawTxJsonRpcResponse.class);
//        return reponse;
//    }

    public WiccSendToAddressWithFeeJsonRpcResponse sendToAddressWithFee(SendTxPO sendTxPO)throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SUBMIT_SEND_TX);
        request.getParams().add(sendTxPO.sendAddress);
        request.getParams().add(sendTxPO.receiveAddress);
        request.getParams().add(sendTxPO.coinSymbol+":"+sendTxPO.amount.toString()+":"+sendTxPO.unit);
        request.getParams().add(sendTxPO.feeSymbol+":"+sendTxPO.fee.toString()+":"+sendTxPO.unit);
        WiccSendToAddressWithFeeJsonRpcResponse reponse =  this.execute(request, WiccSendToAddressWithFeeJsonRpcResponse.class);
        return reponse;
    }

//    public WiccGenSendtoAddressTxrawJsonRpcResponse genSendtoAddresstxraw(GenSendToAddressTxRawPO genSendToAddressTxRawPO)throws Exception{
//        JsonRpcRequest request = new JsonRpcRequest();
//        request.setId(RandomUtils.generateRandomId());
//        request.setMethod(WiccOperationType.METHOD_SEND_TO_ADDRESS_RAW);
//        request.getParams().add(genSendToAddressTxRawPO.getSender());
//        request.getParams().add(genSendToAddressTxRawPO.getRecviver());
//        request.getParams().add(genSendToAddressTxRawPO.getAmount());
//        request.getParams().add(genSendToAddressTxRawPO.getFee());
//        request.getParams().add(genSendToAddressTxRawPO.getHeight());
//        WiccGenSendtoAddressTxrawJsonRpcResponse reponse =  this.execute(request, WiccGenSendtoAddressTxrawJsonRpcResponse.class);
//        return reponse;
//    }

    public WiccDecodeRawTxJsonRpcResponse decodeRawtx(DecodeRawTxPO decodeRawTxPO)throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_DECODE_TX_RAW);
        request.getParams().add(decodeRawTxPO.getHexstring());
        WiccDecodeRawTxJsonRpcResponse reponse =  this.execute(request, WiccDecodeRawTxJsonRpcResponse.class);
        return reponse;
    }

    public WiccListTxInfoJsonRpcResponse listTx()throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_LIST_TX);
        WiccListTxInfoJsonRpcResponse reponse =  this.execute(request, WiccListTxInfoJsonRpcResponse.class);
        return reponse;
    }

    public WiccSubmitTxJsonRpcResponse submitTx(String tx) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SUBMIT_TX_RAW);
        request.getParams().add(tx);
        WiccSubmitTxJsonRpcResponse reponse =  this.execute(request, WiccSubmitTxJsonRpcResponse.class);
        return reponse;
    }

    public WiccTxHashJsonRpcResponse registerAppTx(RegisterAppTxPO registerAppTxPO) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_REGISTER_APP_TX);
        request.getParams().add(registerAppTxPO.getAddr());
        request.getParams().add(registerAppTxPO.getFilepath());
        request.getParams().add(registerAppTxPO.getFee());
        WiccTxHashJsonRpcResponse response = this.execute(request, WiccTxHashJsonRpcResponse.class);
        return response;
    }

    public WiccGetContractRegidJsonRpcResponse getContractRegid(GetContractRegidPO getScriptIdPO) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_SCRIPT_ID);
        request.getParams().add(getScriptIdPO.getHash());
        WiccGetContractRegidJsonRpcResponse response = this.execute(request, WiccGetContractRegidJsonRpcResponse.class);
        return response;
    }

    public WiccCreateContractTxJsonRpcResponse WiccCreateContractTx(CreateContractTxPO createContractTxPO) throws Exception {
    	JsonRpcRequest request = new JsonRpcRequest();
    	request.setId(RandomUtils.generateRandomId());
    	request.setMethod(WiccOperationType.METHOD_CREATE_CONTRACT_TX);
    	request.getParams().add(createContractTxPO.getUserregid());
    	request.getParams().add(createContractTxPO.getAppId());
    	request.getParams().add(createContractTxPO.getAmount());
    	if(createContractTxPO.getContract().length()>=2000){
    	    throw new Exception("The contract command length should less than 2000");
        }
    	request.getParams().add(createContractTxPO.getContract());
    	request.getParams().add(createContractTxPO.getFee());
    	if(createContractTxPO.getHeight()!=null) {
    		request.getParams().add(createContractTxPO.getHeight());
    	}
    	WiccCreateContractTxJsonRpcResponse response = this.execute(request, WiccCreateContractTxJsonRpcResponse.class);
    	return response;
    }

//    public WiccRawTxJsonRpcResponse createContractTxRaw(CreateContractTxRawPO createContractTxRawPO) throws Exception{
//        JsonRpcRequest request = new JsonRpcRequest();
//        request.setId(RandomUtils.generateRandomId());
//        request.setMethod(WiccOperationType.METHOD_CREATE_CONTRACT_TX_RAW);
//        request.getParams().add(createContractTxRawPO.getFee());
//        request.getParams().add(createContractTxRawPO.getAmount());
//        request.getParams().add(createContractTxRawPO.getAddr());
//        request.getParams().add(createContractTxRawPO.getAppid());
//        request.getParams().add(createContractTxRawPO.getContract());
//        request.getParams().add(createContractTxRawPO.getHeight());
//        WiccRawTxJsonRpcResponse response =  this.execute(request, WiccRawTxJsonRpcResponse.class);
//        return response;
//    }

    public WiccGetScriptDataJsonRpcResponse getScriptData(GetContractDataPO getContractDataPO) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_CONTRACT_DATA);
        request.getParams().add(getContractDataPO.getContractRegid());
        request.getParams().add(getContractDataPO.getKey());
        WiccGetScriptDataJsonRpcResponse response =this.execute(request, WiccGetScriptDataJsonRpcResponse.class);
        return response;
    }

//    public WiccGetScriptDataJsonRpcResponse getContractDataRaw(GetContractDataPO getContractDataPO) throws Exception{
//        JsonRpcRequest request = new JsonRpcRequest();
//        request.setId(RandomUtils.generateRandomId());
//        request.setMethod(WiccOperationType.METHOD_GET_CONTRACT_DATA_RAW);
//        request.getParams().add(getContractDataPO.getContractRegid());
//        request.getParams().add(getContractDataPO.getKey());
//        WiccGetScriptDataJsonRpcResponse response =this.execute(request, WiccGetScriptDataJsonRpcResponse.class);
//        return response;
//    }

    public WiccGetContractInfoJsonRpcResponse getContractInfo(GetContractInfoPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_CONTRACT_INFO);
        request.getParams().add(po.getRegid());
        WiccGetContractInfoJsonRpcResponse response =this.execute(request, WiccGetContractInfoJsonRpcResponse.class);
        return response;
    }

    public WiccGetAppAccInfoJsonRpcResponse getappaccinfo(GetAppAccInfoPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_CONTRACT_ACCOUNT_INFO);
        request.getParams().add(po.getScriptid());
        request.getParams().add(po.getAddress());
        WiccGetAppAccInfoJsonRpcResponse response =this.execute(request, WiccGetAppAccInfoJsonRpcResponse.class);
        return response;
    }

/*    public WiccGetAppAccInfoJsonRpcResponse active(String address) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_APP_ACC_INFO);
        request.getParams().add(address);
        WiccGetAppAccInfoJsonRpcResponse response =this.execute(request, WiccGetAppAccInfoJsonRpcResponse.class);
        return response;
    }*/

    public WiccTxHashJsonRpcResponse createContract(CreateContractPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setMethod(WiccOperationType.METHOD_CREATE_CONTRACT_TX);
        request.getParams().add(po.getUserregid());
        request.getParams().add(po.getAppid());
        request.getParams().add(po.getAmount());
        request.getParams().add(po.getContract());
        request.getParams().add(po.getFee());

        return this.execute(request, WiccTxHashJsonRpcResponse.class);
    }

    public WiccBlockCountJsonRpcResponse getBlockCount() throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setMethod(WiccOperationType.METHOD_GET_BLOCK_COUNT);

        return this.execute(request, WiccBlockCountJsonRpcResponse.class);
    }

    public WiccTotalCoinJsonRpcResponse getTotalCoin() throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setMethod(WiccOperationType.METHOD_GET_TOTAL_COIN);

        return this.execute(request, WiccTotalCoinJsonRpcResponse.class);
    }

//    public List<GetTxReceiptResult> getTxReceipt(String txid ) throws  Exception{
//
//        JsonRpcRequest request = new JsonRpcRequest();
//        request.setId(RandomUtils.generateRandomId());
//        request.setMethod(WiccOperationType.METHOD_GET_TX_RECEIPT);
//        request.getParams().add(txid) ;
//
//        List<GetTxReceiptResult> receiptList  = new ArrayList<>() ;
//        String re = this.execute(request) ;
//
//        JSONObject jsonObject = JSONObject.parseObject(re) ;
//        if( jsonObject == null ){
//            return receiptList ;
//        }
//
//        JSONArray receiptArray = jsonObject.getJSONArray("result") ;
//        if( receiptArray == null ){
//            return receiptList ;
//        }
//
//        List<GetTxReceiptResult> results = receiptArray.toJavaList(GetTxReceiptResult.class) ;
//
//        if (results != null) {
//            return results;
//        } else {
//            return receiptList ;
//        }
//
//
//    }

    /**
     * dex
     */
    public DefaultSubmitTxResponse submitDexBuyLimitOrderTx( SubmitDexBuyLimitOrderPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SUBMIT_DEX_BUY_LIMIT_ORDER_TX);
        request.getParams().add(po.getAddress()) ;
        request.getParams().add(po.getCoinType()) ;
        request.getParams().add(po.getAssetType()) ;
        request.getParams().add(po.getAssetAmount()) ;
        request.getParams().add(po.getPrice()) ;
        request.getParams().add(po.getFee()) ;
        return this.execute(request, DefaultSubmitTxResponse.class) ;

    }


    public DefaultSubmitTxResponse submitDexSellLimitOrderTx( SubmitDexSellLimitOrderPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SUBMIT_DEX_SELL_LIMIT_ORDER_TX);
        request.getParams().add(po.getAddress()) ;
        request.getParams().add(po.getCoinType()) ;
        request.getParams().add(po.getAssetType()) ;
        request.getParams().add(po.getAssetAmount()) ;
        request.getParams().add(po.getPrice()) ;
        request.getParams().add(po.getFee()) ;
        return this.execute(request, DefaultSubmitTxResponse.class) ;

    }

    public DefaultSubmitTxResponse submitDexBuyMarketOrderTx( SubmitDexBuyMarketOrderPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SUBMIT_DEX_BUY_MARKET_ORDER_TX);
        request.getParams().add(po.getAddress()) ;
        request.getParams().add(po.getCoinType()) ;
        request.getParams().add(po.getAssetType()) ;
        request.getParams().add(po.getCoinAmount()) ;

        request.getParams().add(po.getFee()) ;
        return this.execute(request, DefaultSubmitTxResponse.class) ;

    }

    public DefaultSubmitTxResponse submitDexSellMarketOrderTx( SubmitDexSellMarketOrderPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SUBMIT_DEX_SELL_MARKET_ORDER_TX);
        request.getParams().add(po.getAddress()) ;
        request.getParams().add(po.getCoinType()) ;
        request.getParams().add(po.getAssetType()) ;
        request.getParams().add(po.getAssetAmount()) ;
        request.getParams().add(po.getFee()) ;
        return this.execute(request, DefaultSubmitTxResponse.class) ;

    }


    public DefaultSubmitTxResponse submitDexCancelOrderTx(SubmitDexCancelOrderTxPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SUBMIT_DEX_CANCEL_ORDER_TX);

        request.getParams().add(po.getAddress()) ;
        request.getParams().add(po.getTxid()) ;
        request.getParams().add(po.getFee()) ;

        return this.execute(request,DefaultSubmitTxResponse.class) ;
    }

    public GetDexSysOrdersJsonRpcResponse getDexsysorders(Long height) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_DEX_SYS_ORDERS);
        request.getParams().add(height) ;
        return this.execute(request,GetDexSysOrdersJsonRpcResponse.class) ;
    }

    public DefaultSubmitTxResponse submitDexSettleTx(SubmitDexSettleTxPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SUBMIT_DEX_SETTLE_TX);
        request.getParams().add(po.getSettlerAddress()) ;
        request.getParams().add(po.getDealItems()) ;
        request.getParams().add(po.getFee()) ;
        return this.execute(request, DefaultSubmitTxResponse.class) ;
    }

    public WiccGetScoinInfoJsonRpcResponse getSCoinInfo() throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setMethod(WiccOperationType.METHOD_GET_SCOIN_INFO);
        return this.execute(request, WiccGetScoinInfoJsonRpcResponse.class);
    }

    /**
     * cdp
     */
    public CdpInfoJsonRpcResponse getCdpInfo(String cdpid) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setMethod(WiccOperationType.METHOD_CDP_GET_INFO);
        request.getParams().add(cdpid);
        return this.execute(request, CdpInfoJsonRpcResponse.class);
    }

    public UserCdpJsonRpcResponse getUserCdp(String address) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setMethod(WiccOperationType.METHOD_CDP_USER_CDP);
        request.getParams().add(address);
        return this.execute(request, UserCdpJsonRpcResponse.class);
    }

    public CdpStakeJsonRpcResponse submitStakeCdpTx(CdpTxPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setMethod(WiccOperationType.METHOD_CDP_SUBMIT_STAKE_TX);
        request.getParams().add(po.getAddress());
        request.getParams().add(po.getStakecombomoney());
        request.getParams().add(po.getMintcombomoney());
        if (StringUtils.isNotBlank(po.getCdpid())) { request.getParams().add(po.getCdpid()); }
        if (StringUtils.isNotBlank(po.getFee())) { request.getParams().add(po.getFee()); }
        return this.execute(request, CdpStakeJsonRpcResponse.class);
    }

    public CdpRedeemJsonRpcResponse submitRedeemCdpTx(CdpTxPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setMethod(WiccOperationType.METHOD_CDP_SUBMIT_REDEEM_TX);
        request.getParams().add(po.getAddress());
        request.getParams().add(po.getCdpid());
        request.getParams().add(po.getRepayamount());
        request.getParams().add(po.getRedeemamount());
        if (StringUtils.isNotBlank(po.getFee())) { request.getParams().add(po.getFee()); }
        return this.execute(request, CdpRedeemJsonRpcResponse.class);
    }

    public CdpLiquidateJsonRpcResponse submitLiquidateCdpTx(CdpTxPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setMethod(WiccOperationType.METHOD_CDP_SUBMIT_LIQUIDATE_TX);
        request.getParams().add(po.getAddress());
        request.getParams().add(po.getCdpid());
        request.getParams().add(po.getLiquidateamount());
        if (StringUtils.isNotBlank(po.getFee())) { request.getParams().add(po.getFee()); }
        return this.execute(request, CdpLiquidateJsonRpcResponse.class);
    }

    public GetMedianPriceJsonRpcResponse getMedianPrice(GetMedianPricePO po) throws  Exception{

        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_MEDIAN_PRICE);
        request.getParams().add(po.getCoinType()) ;
        request.getParams().add(po.getPriceType());
        if(po.getHeight() != null &&po.getHeight() !=0){
            request.getParams().add(po.getHeight()) ;
        }
        return this.execute(request, GetMedianPriceJsonRpcResponse.class) ;
    }


    public DefaultSubmitTxResponse submitPriceFeedTx(PriceFeedPO po) throws  Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SUBMIT_PRICE_FEED_TX);
        request.getParams().add(po.getFeederAddress()) ;
        request.getParams().add(po.getPriceFeeds()) ;
        request.getParams().add(po.getFee()) ;
        return this.execute(request, DefaultSubmitTxResponse.class) ;
    }

    private <T> T execute(final JsonRpcRequest request, final Class<T> classTypeResponse) throws Exception {
        T response = null;
        try {

            response = this.rpcClient.execute(request, classTypeResponse);

        } catch (IOException ioExc) {
            log.error("Error calling method {}", request.getMethod(), ioExc);
            ioExc.printStackTrace();
            throw new Exception();
        }
        return response;
    }

    private String execute(final JsonRpcRequest request) throws Exception {

        try {

            return this.rpcClient.execute(request);

        } catch (IOException ioExc) {
            log.error("Error calling method {}", request.getMethod(), ioExc);
            ioExc.printStackTrace();
            throw new Exception();
        }
    }
}
