package com.waykichain;

import com.alibaba.fastjson.JSON;
import com.waykichain.chain.contract.lottery.LotteryBetDomain;
import com.waykichain.chain.contract.lottery.LotteryOpenPrizeDomain;
import com.waykichain.chain.contract.lottery.RecordLotteryBetDomain;
import com.waykichain.chain.contract.lottery.RecordLotteryOpenPrizeDomain;
import com.waykichain.chain.contract.wusd.TransferWithoutFeeDomain;
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain;
import com.waykichain.coin.wicc.WiccMethods;
import com.waykichain.coin.wicc.po.CreateContractTxPO;
import com.waykichain.coin.wicc.vo.WiccCreateContractTxJsonRpcResponse;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WusdLotteryByAdmin {

    JsonRpcClient client;
    WiccMethods wiccMethods;
    CreateContractTxPO createContractTxPO;

    private String lotteryBankerId = "lotteryBankerId0000000000000000000";

    @Before
    public void init() throws Exception {
        client = new JsonRpcClient(WusdTestConstants.JSON_RPC_IP,WusdTestConstants.JSON_RPC_PORT,
                WusdTestConstants.JSON_RPC_ADMIN, WusdTestConstants.JSON_RPC_PASSWORD, false);
        wiccMethods = new WiccMethods(client);

        createContractTxPO = new CreateContractTxPO();
        createContractTxPO.setUserregid(WusdTestConstants.adminAddr);
    //    createContractTxPO.setUserregid(WusdTestConstants.normalAddr);
        createContractTxPO.setAppId(WusdTestConstants.appid);
        createContractTxPO.setAmount(0L);
        createContractTxPO.setFee(1000000L);
    }


    @Test
    public void A_testRecordLotteryBetDomain() throws Exception {
        RecordLotteryBetDomain recordLotteryBetDomain = new RecordLotteryBetDomain();
        recordLotteryBetDomain.setUserId("1234567890");
        recordLotteryBetDomain.setLotteryInfo("Hello<br/>Lotter<br/>彩票信息<br/>投注1");
        String contract = recordLotteryBetDomain.serialize();
        System.out.println("contract:" + contract);

        RecordLotteryBetDomain recordLotteryBetDomain_Deser = new RecordLotteryBetDomain();
        recordLotteryBetDomain_Deser.deserialize(contract);
        System.out.println("recordLotteryBetDomain_Deser:" + JSON.toJSONString(recordLotteryBetDomain_Deser));
        assertWusdBaseDomain(recordLotteryBetDomain,recordLotteryBetDomain_Deser);
        assertEquals(recordLotteryBetDomain.getUserId(),recordLotteryBetDomain_Deser.getUserId());
        assertEquals(recordLotteryBetDomain.getLotteryInfo(),recordLotteryBetDomain_Deser.getLotteryInfo());

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void B_testRecordLotteryOpenPrizeDomain() throws Exception {
        RecordLotteryOpenPrizeDomain domain = new RecordLotteryOpenPrizeDomain();
        domain.setUserId("1234567890");
        domain.setLotteryInfo("肖远航");
        String contract = domain.serialize();

        System.out.println(contract);

        RecordLotteryOpenPrizeDomain domain_Deser = new RecordLotteryOpenPrizeDomain();
        domain_Deser.deserialize(contract);
        System.out.println("RecordLotteryOpenPrizeDomain domain_Deser:" + JSON.toJSONString(domain_Deser));
        assertWusdBaseDomain(domain,domain_Deser);
        assertEquals(domain.getUserId(),domain_Deser.getUserId());
        assertEquals(domain.getLotteryInfo(),domain_Deser.getLotteryInfo());

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void C_testLotteryBet() throws Exception{
        LotteryBetDomain lotteryBetDomain = new LotteryBetDomain();
        lotteryBetDomain.setFrom(WusdTestConstants.normalAddr);
        lotteryBetDomain.setTo(lotteryBankerId);
        lotteryBetDomain.setAmount(71999990000L);
        String contract = lotteryBetDomain.serialize();
        System.out.println("contract:" + contract);

        LotteryBetDomain lotteryBetDomain_Deser = new LotteryBetDomain();
        lotteryBetDomain_Deser.deserialize(contract);
        System.out.println("lotteryBetDomain_Deser:" + JSON.toJSONString(lotteryBetDomain_Deser));
        assertTransferWithoutFeeDomain(lotteryBetDomain,lotteryBetDomain_Deser);

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void D_testLotteryOpenPrize() throws Exception{
        LotteryOpenPrizeDomain domain = new LotteryOpenPrizeDomain();
        domain.setFrom(lotteryBankerId);
        domain.setTo(WusdTestConstants.normalAddr);
        domain.setAmount(70000L);
        String contract = domain.serialize();
        System.out.println("contract:" + contract);

        LotteryBetDomain domain_deser = new LotteryBetDomain();
        domain_deser.deserialize(contract);
        System.out.println("lotteryBetDomain_Deser:" + JSON.toJSONString(domain_deser));
        assertTransferWithoutFeeDomain(domain,domain_deser);

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
