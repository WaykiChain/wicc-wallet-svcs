package com.waykichain;

import com.waykichain.chain.contract.wusd.SetAliveDomain;
import com.waykichain.chain.contract.wusd.domain.SetAdminDomain;
import com.waykichain.chain.contract.wusd.domain.WusdConstants;
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
                WusdTestConstants.JSON_RPC_ADMIN, WusdTestConstants.JSON_RPC_PASSWORD, false);
        wiccMethods = new WiccMethods(client);

        createContractTxPO = new CreateContractTxPO();
        createContractTxPO.setUserregid(WusdTestConstants.superviser);
        createContractTxPO.setAppId(WusdTestConstants.appid);
        createContractTxPO.setAmount(0L);
        createContractTxPO.setFee(1000000L);
    }

   @Test
    public void testSetAlive() throws Exception{
        SetAliveDomain setAliveDomain = new SetAliveDomain();
        setAliveDomain.setAlive(WusdConstants.WUSD_CONTRACT_LIFE_ALIVE);
        String contract = setAliveDomain.serialize();

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        assertNull(response.getError());
    }


    @Test
    public void testSetAdminByWriteSupserviser() throws Exception{

        String adminAddr = WusdTestConstants.adminAddr;
        String adminType = WusdConstants.WUSD_CONTRACT_ADMIN_TYPE_TOKEN_ADMIN;
        WiccCreateContractTxJsonRpcResponse response = testSetAdmin(adminAddr,adminType);
        assertNull(response.getError());

        adminAddr = WusdTestConstants.adminAddr;
        adminType = WusdConstants.WUSD_CONTRACT_ADMIN_TYPE_GAME_ADMIN;
        response = testSetAdmin(adminAddr,adminType);
        assertNull(response.getError());

        adminAddr = WusdTestConstants.adminAddr;
        adminType = WusdConstants.WUSD_CONTRACT_ADMIN_TYPE_LOTT_ADMIN;
        response = testSetAdmin(adminAddr,adminType);
        assertNull(response.getError());

         adminAddr = WusdTestConstants.superviser;
         adminType = WusdConstants.WUSD_CONTRACT_ADMIN_TYPE_SUPERVISOR;
         response = testSetAdmin(adminAddr,adminType);
         assertNull(response.getError());
    }

    @Test
    public void testErrorSuperViser() throws Exception{
        createContractTxPO.setUserregid(WusdTestConstants.adminAddr);
        String adminAddr = WusdTestConstants.adminAddr;
        String adminType = WusdConstants.WUSD_CONTRACT_ADMIN_TYPE_LOTT_ADMIN;
        WiccCreateContractTxJsonRpcResponse response = testSetAdmin(adminAddr,adminType);
        System.out.println(response.getError().getMessage());
        assertEquals(Integer.valueOf(-4),response.getError().getCode());
    }

    private WiccCreateContractTxJsonRpcResponse testSetAdmin(String adminAddr ,  String adminType ) throws Exception{
        SetAdminDomain setAdminDomain = new SetAdminDomain();
        setAdminDomain.setAdminAddr(adminAddr);
        setAdminDomain.setAdminType(adminType);
        String adminHex = setAdminDomain.serialize();
        System.out.println("adminHex:" + adminHex);

        SetAdminDomain deser_setAdminDomain = new SetAdminDomain();
        deser_setAdminDomain.deserialize(adminHex);
        assertEquals(setAdminDomain.getAdminAddr(), deser_setAdminDomain.getAdminAddr());
        assertEquals(setAdminDomain.getAdminType(),    deser_setAdminDomain.getAdminType());

        createContractTxPO.setContract(adminHex);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        return response;
    }

}
