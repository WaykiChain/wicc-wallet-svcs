package com.waykichain;

import com.waykichain.chain.contract.wusd.domain.SetAdminDomain;
import com.waykichain.coin.wicc.WiccMethods;
import com.waykichain.coin.wicc.po.CreateContractTxPO;
import com.waykichain.coin.wicc.vo.WiccCreateContractTxJsonRpcResponse;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class WusdSuperViserOperationTest{

    JsonRpcClient client;
    WiccMethods wiccMethods;
    CreateContractTxPO createContractTxPO;


    @Before
    public void init() throws Exception {
        client = new JsonRpcClient(WusdTestConstants.JSON_RPC_IP,WusdTestConstants.JSON_RPC_PORT,
                "waykichain", "admin@1234", false);
        wiccMethods = new WiccMethods(client);

        createContractTxPO = new CreateContractTxPO();
        createContractTxPO.setUserregid(WusdTestConstants.superviser);
        createContractTxPO.setAppId(WusdTestConstants.appid);
        createContractTxPO.setAmount(0L);
        createContractTxPO.setFee(1000000L);
    }

    @Test
    public void testSetAdminByWiriteSupserviser() throws Exception{
        WiccCreateContractTxJsonRpcResponse response = testSetAdmin();
        assertNull(response.getError());
    }

    @Test
    public void testErrorSuperViser() throws Exception{
        createContractTxPO.setUserregid(WusdTestConstants.adminAddr);
        WiccCreateContractTxJsonRpcResponse response = testSetAdmin();
        System.out.println(response.getError().getMessage());
        assertEquals(Integer.valueOf(-4),response.getError().getCode());
    }

    private WiccCreateContractTxJsonRpcResponse testSetAdmin() throws Exception{
        String adminAddr = WusdTestConstants.adminAddr;
        String symbol = WusdTestConstants.contract_symbol;
        SetAdminDomain setAdminDomain = new SetAdminDomain();
        setAdminDomain.setAdminAddr(adminAddr);
        setAdminDomain.setSymbol(symbol);
        String adminHex = setAdminDomain.serialize();
        System.out.println("adminHex:" + adminHex);

        SetAdminDomain deser_setAdminDomain = new SetAdminDomain();
        deser_setAdminDomain.deserialize(adminHex);
        assertEquals(setAdminDomain.getAdminAddr(), deser_setAdminDomain.getAdminAddr());
        assertEquals(setAdminDomain.getSymbol(),    deser_setAdminDomain.getSymbol());

        createContractTxPO.setContract(adminHex);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        return response;
    }

}
