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
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class WusdAdminDebug {

    JsonRpcClient client;
    WiccMethods wiccMethods;
    CreateContractTxPO createContractTxPO;

    private String freezeId = "freezeId00123456789012345678901234";

    @Before
    public void init() throws Exception {
        client = new JsonRpcClient(WusdTestConstants.JSON_RPC_IP,WusdTestConstants.JSON_RPC_PORT,
                "waykichain", "admin@1234", false);
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
     //   domain.setSwitchValue(WusdConstants.WUSD_CONTRACT_SWITCH_VALUE_OPEN);
        domain.setSwitchValue(WusdConstants.WUSD_CONTRACT_SWITCH_VALUE_CLOSE);
        String contract = domain.serialize();
        System.out.println("contract:" + contract);

        SetSwitchDomain domain_dser = new SetSwitchDomain();
        domain_dser.deserialize(contract);
        assertWusdBaseDomain(domain,domain_dser);
        assertEquals(domain.getSwitchValue(),domain_dser.getSwitchValue());

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
    //    checkResponse(response);
    }

    @Test
    public void testSetExchangeRate() throws Exception{
       double exchangeRate = WusdTestConstants.common_exchangeRate;
        long exchangeRateParam = (long) (exchangeRate * 10000);
        SetExchangeRateDomain domain = new SetExchangeRateDomain();
        domain.setExchangeRate(exchangeRateParam);
        String exchangeRateHex = domain.serialize();
        System.out.println("exchangeRateHex:" + exchangeRateHex);

        SetExchangeRateDomain domain_dser = new SetExchangeRateDomain();
        domain_dser.deserialize(exchangeRateHex);
        assertWusdBaseDomain(domain,domain_dser);
        assertEquals(domain_dser.getExchangeRate(), domain.getExchangeRate());

        createContractTxPO.setContract(exchangeRateHex);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testSendPrizeByGame() throws Exception{
        SendPrizeAccordingGameDomain domain = new SendPrizeAccordingGameDomain();
        domain.setGameId(WusdTestConstants.commonGameId);
        domain.setPrizeContent("12345678901234567890");
        int sendPrizeCount = 2;
        domain.setSendPrizeCount(sendPrizeCount);
        List<TransferModel>  sendPrizeList = new ArrayList();
        TransferModel transferModel1 = new TransferModel();
        transferModel1.setToAddress("wLWBW1jVvdK44fi9Cpk3d6bLsYz3bHsGCf");
        transferModel1.setAmount(10001);
        sendPrizeList.add(transferModel1);
        TransferModel transferModel2 = new TransferModel();
        transferModel2.setToAddress("wTJ6uFkFCD1Uidvhq7UAwUNdKPKDzR7Xit");
        transferModel2.setAmount(20002);
        sendPrizeList.add(transferModel2);
        domain.setSendPrizeList(sendPrizeList);
        String contract = domain.serialize();
        System.out.println("testSendPrizeByGame:" + contract);

        SendPrizeAccordingGameDomain domain_deser = new SendPrizeAccordingGameDomain();
        domain_deser.deserialize(contract);
        assertWusdBaseDomain(domain,domain_deser);
        assertEquals(domain.getGameId(),domain_deser.getGameId());
        assertEquals(domain.getPrizeContent(),domain_deser.getPrizeContent());
        assertEquals(domain.getSendPrizeCount(),domain_deser.getSendPrizeCount());
        assertEquals(domain.getSendPrizeList().get(0).getFromAddress(), domain_deser.getSendPrizeList().get(0).getFromAddress());
        assertEquals(domain.getSendPrizeList().get(0).getToAddress(), domain_deser.getSendPrizeList().get(0).getToAddress());
        assertEquals(domain.getSendPrizeList().get(0).getAmount(), domain_deser.getSendPrizeList().get(0).getAmount());
        assertEquals(domain.getSendPrizeList().get(0).getFee(), domain_deser.getSendPrizeList().get(0).getFee());
        assertEquals(domain.getSendPrizeList().get(1).getFromAddress(), domain_deser.getSendPrizeList().get(1).getFromAddress());
        assertEquals(domain.getSendPrizeList().get(1).getToAddress(), domain_deser.getSendPrizeList().get(1).getToAddress());
        assertEquals(domain.getSendPrizeList().get(1).getAmount(), domain_deser.getSendPrizeList().get(1).getAmount());
        assertEquals(domain.getSendPrizeList().get(1).getFee(), domain_deser.getSendPrizeList().get(1).getFee());

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testBatchSendPrize() throws Exception{
        BatchSendPrizeDomain domain = new BatchSendPrizeDomain();
        domain.setPrizeContent("12345678901234567890");
        int sendPrizeCount = 2;
        domain.setSendPrizeCount(sendPrizeCount);

        List<TransferModel>  sendPrizeList = new ArrayList();
        TransferModel transferModel1 = new TransferModel();
        transferModel1.setFromAddress(WusdTestConstants.commonGameId);
        transferModel1.setToAddress(WusdTestConstants.normalAddr);
        transferModel1.setAmount(1003);
        sendPrizeList.add(transferModel1);
        TransferModel transferModel2 = new TransferModel();
        transferModel2.setFromAddress(WusdTestConstants.commonGameId);
        transferModel2.setToAddress(WusdTestConstants.normalAddr2);
        transferModel2.setAmount(1009);
        sendPrizeList.add(transferModel2);
        domain.setSendPrizeList(sendPrizeList);

        String contract = domain.serialize();
        System.out.println("testBatchSendPrize:" + contract);

        BatchSendPrizeDomain domain_deser = new BatchSendPrizeDomain();
        domain_deser.deserialize(contract);
        System.out.println("domain_deser:" + JSON.toJSONString(domain_deser));

        assertWusdBaseDomain(domain,domain_deser);
        assertEquals(domain.getPrizeContent(),domain_deser.getPrizeContent());
        assertEquals(domain.getSendPrizeCount(),domain_deser.getSendPrizeCount());
        assertEquals(domain.getSendPrizeList().get(0).getFromAddress(), domain_deser.getSendPrizeList().get(0).getFromAddress());
        assertEquals(domain.getSendPrizeList().get(0).getToAddress(), domain_deser.getSendPrizeList().get(0).getToAddress());
        assertEquals(domain.getSendPrizeList().get(0).getAmount(), domain_deser.getSendPrizeList().get(0).getAmount());
        assertEquals(domain.getSendPrizeList().get(0).getFee(), domain_deser.getSendPrizeList().get(0).getFee());
        assertEquals(domain.getSendPrizeList().get(1).getFromAddress(), domain_deser.getSendPrizeList().get(1).getFromAddress());
        assertEquals(domain.getSendPrizeList().get(1).getToAddress(), domain_deser.getSendPrizeList().get(1).getToAddress());
        assertEquals(domain.getSendPrizeList().get(1).getAmount(), domain_deser.getSendPrizeList().get(1).getAmount());
        assertEquals(domain.getSendPrizeList().get(1).getFee(), domain_deser.getSendPrizeList().get(1).getFee());

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testFreezeToken() throws Exception{
        FreezeTokenDomain domain = new FreezeTokenDomain();
        domain.setFrom(WusdTestConstants.normalAddr);
        domain.setTo(freezeId);
        domain.setAmount(1042);
        String contract = domain.serialize();
        System.out.println("contract:" + contract);

        FreezeTokenDomain domain_deser = new FreezeTokenDomain();
        domain_deser.deserialize(contract);
        assertWusdBaseDomain(domain,domain_deser);
        assertTransferWithoutFeeDomain(domain, domain_deser);

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testUnfreezeToken() throws Exception{
        UnfreezeTokenDomain domain = new UnfreezeTokenDomain();
        domain.setFrom(freezeId);
        domain.setTo(WusdTestConstants.normalAddr);
        domain.setAmount(1042);
        String contract = domain.serialize();
        System.out.println("contract:" + contract);

        UnfreezeTokenDomain domain_deser = new UnfreezeTokenDomain();
        domain_deser.deserialize(contract);
        assertWusdBaseDomain(domain,domain_deser);
        assertTransferWithoutFeeDomain(domain, domain_deser);

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    private void checkResponse(WiccCreateContractTxJsonRpcResponse response){
        assertNull(response.getError());
/*        String checkAccountFalse_Msg = "Error:run-script-error:luaL_loadbuffer fail:[string \"line\"]:289: Check Admin Account false\n";
        assertEquals(checkAccountFalse_Msg,response.getError().getMessage());*/
    }

    public void assertWusdBaseDomain(WusdBaseDomain target, WusdBaseDomain src){
        assertEquals(target.getSystype(), src.getSystype());
        assertEquals(target.getType(), src.getType());
        assertEquals(target.getVersion(),src.getVersion());
    }

    public void assertTransferWithoutFeeDomain(TransferWithoutFeeDomain target, TransferWithoutFeeDomain src){
        assertWusdBaseDomain(target,src);
        assertEquals(target.getFrom(),src.getFrom());
        assertEquals(target.getTo(),src.getTo());
        assertEquals(target.getAmount(),src.getAmount());
    }

}
