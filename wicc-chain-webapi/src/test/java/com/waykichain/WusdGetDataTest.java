package com.waykichain;

import com.waykichain.chain.contract.util.ContractUtil;
import com.waykichain.coin.wicc.WiccMethods;
import com.waykichain.coin.wicc.po.GetAppAccInfoPO;
import com.waykichain.coin.wicc.po.GetContractDataPO;
import com.waykichain.coin.wicc.vo.WiccGetAppAccInfoJsonRpcResponse;
import com.waykichain.coin.wicc.vo.WiccGetScriptDataJsonRpcResponse;
import com.waykichain.coin.wicc.vo.WiccGetScriptDataResult;
import com.waykichain.coin.wicc.vo.WiccGetTxDetailJsonRpcResponse;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class WusdGetDataTest {

    JsonRpcClient client;
    WiccMethods wiccMethods;

    @Before
    public void init() throws Exception {
        client = new JsonRpcClient(WusdTestConstants.JSON_RPC_IP,WusdTestConstants.JSON_RPC_PORT,
                WusdTestConstants.JSON_RPC_ADMIN, WusdTestConstants.JSON_RPC_PASSWORD, false);
        wiccMethods = new WiccMethods(client);
    }

//    @Test
//    public void testGetAdminAddr() throws Exception{
//        //   gKeys = { "key_contractalive", "key_superadmin", "key_tokenadmin", "key_gameadmin", "key_lottadmin", "key_tokenswitch", "key_tokenrate" },
//        GetContractDataPO po = new GetContractDataPO();
//        po.setContractRegid(WusdTestConstants.appid);
//        //   String adminKey = ContractUtil.toHexString("admin");
//     //   String adminKey = ContractUtil.toHexString("key_tokenadmin");
//        String adminKey = ContractUtil.toHexString("key_gameadmin");
//     //   String adminKey = ContractUtil.toHexString("key_lottadmin");
//    //    String adminKey = ContractUtil.toHexString("key_superadmin");
//        po.setKey(adminKey);
//        WiccGetScriptDataJsonRpcResponse adminResponse = wiccMethods.getContractDataRaw(po);
//        String getAdminAddr = ContractUtil.hexToString(adminResponse.getResult().getValue());
//        System.out.println("admin：" +  getAdminAddr);
//        assertEquals(WusdTestConstants.adminAddr, getAdminAddr);
//    }

//    @Test
//    public void testGetSymbol() throws Exception{
//        GetContractDataPO po = new GetContractDataPO();
//        po.setContractRegid(WusdTestConstants.appid);
//        String symbolKey = ContractUtil.toHexString("symbol");
//        po.setKey(symbolKey);
//        WiccGetScriptDataJsonRpcResponse symbolResponse = wiccMethods.getContractDataRaw(po);  //wiccMethods.getScriptData(po);
//        String getSymbol = ContractUtil.hexToString(symbolResponse.getResult().getValue());
//        System.out.println("getSymbol：" +  getSymbol);
//        assertEquals(WusdTestConstants.contract_symbol, getSymbol);
//    }

    @Test
    public void testGetSwitch() throws Exception{
        GetContractDataPO po = new GetContractDataPO();
        po.setContractRegid(WusdTestConstants.appid);
        String switchKey = ContractUtil.toHexString("switch");
        po.setKey(switchKey);
        WiccGetScriptDataJsonRpcResponse symbolResponse = wiccMethods.getScriptData(po);
        String getSwitch = symbolResponse.getResult().getValue();
        System.out.println("getSwitch：" +  getSwitch);

    }

    @Test
    public void testGetExchangeRate() throws Exception{
        GetContractDataPO po = new GetContractDataPO();
        po.setContractRegid(WusdTestConstants.appid);
        String key = ContractUtil.toHexString("key_tokenrate");
        po.setKey(key);
        WiccGetScriptDataJsonRpcResponse response = wiccMethods.getScriptData(po);
        WiccGetScriptDataResult result = response.getResult();
        double exchangeRate = Long.parseLong(ContractUtil.upsidedownHex(result.getValue()), 16)/10000.0;
        System.out.println("exchangeRate：" + exchangeRate);
    }

    @Test
    public void testgetappaccinfo() throws Exception{
        GetAppAccInfoPO po = new GetAppAccInfoPO();
        po.setScriptid(WusdTestConstants.appid);
        po.setAddress(WusdTestConstants.normalAddr);
        WiccGetAppAccInfoJsonRpcResponse response = wiccMethods.getappaccinfo(po);
        System.out.println(response);
    }

//    @Test
//    public void testgettxdetails() throws Exception{
//        WiccGetTxDetailJsonRpcResponse response = wiccMethods.getTxDetail("ad4ba40427dcf4b7ab48ed18abbcf962a374487128aa33de9c12e3e1fc99cb98");
//        System.out.println(response.toString());
//    }
}
