package com.waykichain;

import com.alibaba.fastjson.JSON;
import com.waykichain.chain.contract.wusd.*;
import com.waykichain.chain.contract.wusd.domain.SetExchangeRateDomain;
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain;
import com.waykichain.chain.contract.wusd.domain.WusdConstants;
import com.waykichain.coin.wicc.WiccMethods;
import com.waykichain.coin.wicc.po.CreateContractTxPO;
import com.waykichain.coin.wicc.vo.WiccCreateContractTxJsonRpcResponse;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WusdAdminTokenTest {

    JsonRpcClient client;
    WiccMethods wiccMethods;
    CreateContractTxPO createContractTxPO;

    private String freezeId = "freezeId00123456789012345678901234";

    @Before
    public void init() throws Exception {
        client = new JsonRpcClient(WusdTestConstants.JSON_RPC_IP,WusdTestConstants.JSON_RPC_PORT,
                WusdTestConstants.JSON_RPC_ADMIN, WusdTestConstants.JSON_RPC_PASSWORD, false);
        wiccMethods = new WiccMethods(client);

        createContractTxPO = new CreateContractTxPO();
        createContractTxPO.setUserregid(WusdTestConstants.adminAddr);
        //  createContractTxPO.setUserregid(WusdTestConstants.normalAddr);
        createContractTxPO.setAppId(WusdTestConstants.appid);
        createContractTxPO.setAmount(0L);
        createContractTxPO.setFee(1000000L);
    }

    @Test
    public void testSetSwitchDomain() throws Exception{
        SetSwitchDomain domain = new SetSwitchDomain();
        domain.setSwitchValue(WusdConstants.WUSD_CONTRACT_SWITCH_VALUE_OPEN);
        //  domain.setSwitchValue(WusdConstants.WUSD_CONTRACT_SWITCH_VALUE_CLOSE);
        String contract = domain.serialize();
        System.out.println("contract:" + contract);

        SetSwitchDomain domain_dser = new SetSwitchDomain();
        domain_dser.deserialize(contract);
        assertWusdBaseDomain(domain,domain_dser);
        assertEquals(domain.getSwitchValue(),domain_dser.getSwitchValue());

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testSetExchangeRate() throws Exception{
        double exchangeRate = WusdTestConstants.common_exchangeRate;
        long exchangeRateParam = (long) (exchangeRate * 10000);
        System.out.println("exchangeRateParam:" + exchangeRateParam);
        SetExchangeRateDomain domain = new SetExchangeRateDomain();
        domain.setExchangeRate(exchangeRateParam);
        String exchangeRateHex = domain.serialize();
        System.out.println("exchangeRateHex:" + exchangeRateHex);

        SetExchangeRateDomain domain_dser = new SetExchangeRateDomain();
        domain_dser.deserialize(exchangeRateHex);
        System.out.println("rate:" + JSON.toJSONString(domain_dser));
        assertWusdBaseDomain(domain,domain_dser);
        assertEquals(domain_dser.getExchangeRate(), domain.getExchangeRate());

        createContractTxPO.setContract(exchangeRateHex);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        WusdAdminTokenTest.checkResponse(response);
    }

    public static void checkResponse(WiccCreateContractTxJsonRpcResponse response){
        assertNull(response.getError());
/*        String checkAccountFalse_Msg = "Error:run-script-error:luaL_loadbuffer fail:[string \"line\"]:289: Check Admin Account false\n";
        assertEquals(checkAccountFalse_Msg,response.getError().getMessage());*/
    }

    public static void assertWusdBaseDomain(WusdBaseDomain target, WusdBaseDomain src){
        assertEquals(target.getSystype(), src.getSystype());
        assertEquals(target.getType(), src.getType());
        assertEquals(target.getVersion(),src.getVersion());
    }

    public static void assertTransferWithoutFeeDomain(TransferWithoutFeeDomain target, TransferWithoutFeeDomain src){
        assertWusdBaseDomain(target,src);
        assertEquals(target.getFrom(),src.getFrom());
        assertEquals(target.getTo(),src.getTo());
        assertEquals(target.getAmount(),src.getAmount());
    }

}
