package com.waykichain;

import com.alibaba.fastjson.JSON;
import com.waykichain.chain.contract.wusd.*;
import com.waykichain.chain.contract.wusd.domain.SetExchangeRateDomain;
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain;
import com.waykichain.coin.wicc.WiccMethods;
import com.waykichain.coin.wicc.po.CreateContractTxPO;
import com.waykichain.coin.wicc.vo.WiccCreateContractTxJsonRpcResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

public class WusdOperateUserByAdmin {

    JsonRpcClient client;
    WiccMethods wiccMethods;
    CreateContractTxPO createContractTxPO;

    private String commonGameId = WusdTestConstants.commonGameId;
    private long common_wusd_fee = 10;

    @Before
    public void init() throws Exception {
        client = new JsonRpcClient(WusdTestConstants.JSON_RPC_IP,WusdTestConstants.JSON_RPC_PORT,
                "waykichain", "admin@123", false);
        wiccMethods = new WiccMethods(client);

        createContractTxPO = new CreateContractTxPO();
        createContractTxPO.setUserregid(WusdTestConstants.adminAddr);
      //  createContractTxPO.setUserregid(WusdTestConstants.normalAddr);
        createContractTxPO.setAppId(WusdTestConstants.appid);
        createContractTxPO.setAmount(0L);
        createContractTxPO.setFee(1000000L);
    }

    @Test
    public void testExchangeWicc() throws Exception{
        ExchangeWiccByAdminDomain domain = new ExchangeWiccByAdminDomain();
        domain.setExchangeAddress(WusdTestConstants.normalAddr);
        double exchangeRate = WusdTestConstants.common_exchangeRate;
        long exchangeRateParam = (long) (exchangeRate * 10000);
        domain.setExchangeRate(exchangeRateParam);
        domain.setExchangeTokenAmount(100);
        domain.setExchangeWiccAmount(40);
        domain.setFee(common_wusd_fee);
        String contract = domain.serialize();

        ExchangeWiccByAdminDomain deser_domain = new ExchangeWiccByAdminDomain();
        deser_domain.deserialize(contract);
        assertWusdBaseDomain(domain,deser_domain);
        assertEquals(domain.getExchangeAddress(),deser_domain.getExchangeAddress());
        assertEquals(domain.getExchangeRate(),deser_domain.getExchangeRate());
        assertEquals(domain.getExchangeTokenAmount(),deser_domain.getExchangeTokenAmount());
        assertEquals(domain.getExchangeWiccAmount(),deser_domain.getExchangeWiccAmount());
        assertEquals(domain.getFee(),deser_domain.getFee());

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testTransferWithFee() throws Exception{
        TransferWithFeeByAdminDomain domain = new TransferWithFeeByAdminDomain();
        domain.setFrom(WusdTestConstants.normalAddr);
        domain.setTo(WusdTestConstants.gameCreater);
        domain.setAmount(-10);
        domain.setFee(0);
        String contract = domain.serialize();
        System.out.println("testTransferWithFee:" + contract);

/*        TransferWithFeeByAdminDomain domain_deser = new TransferWithFeeByAdminDomain();
        System.out.println("domain_deser:" + JSON.toJSONString(domain_deser));
        domain_deser.deserialize(contract);
        assertTransferWithFeeDomain(domain,domain_deser);*/

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testCreateGame() throws Exception{
        CreateGameDomain domain = new CreateGameDomain();
        domain.setFrom(WusdTestConstants.gameCreater);
        String gameId = commonGameId;
        domain.setTo(gameId);
        domain.setAmount(20000);
        domain.setFee(common_wusd_fee);
        String contract = domain.serialize();
        System.out.println("testCreateGame:" + contract);

        CreateGameDomain domain_deser = new CreateGameDomain();
        domain_deser.deserialize(contract);
        assertTransferWithFeeDomain(domain,domain_deser);

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testCreateGameAndBetGame() throws Exception{
        CreateGameAndBetDomain domain = new CreateGameAndBetDomain();
        domain.setFrom(WusdTestConstants.gameCreater);
        domain.setTo(commonGameId);
        domain.setAmount(20001);
        domain.setFee(common_wusd_fee);
        domain.setBetFrom(WusdTestConstants.normalAddr);
        domain.setBetTo(commonGameId);
        domain.setBetAmount(20003);
        domain.setBetFee(common_wusd_fee);
        String contract = domain.serialize();
        System.out.println("testCreateGame:" + contract);

        CreateGameAndBetDomain domain_deser = new CreateGameAndBetDomain();
        domain_deser.deserialize(contract);
        assertTransferWithFeeDomain(domain,domain_deser);
        assertEquals(domain.getBetFrom(), domain_deser.getBetFrom());
        assertEquals(domain.getBetTo(), domain_deser.getBetTo());
        assertEquals(domain.getBetAmount(), domain_deser.getBetAmount());
        assertEquals(domain.getBetFee(), domain_deser.getBetFee());

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testUpdateGame() throws Exception{
        UpdateGameDomain domain = new UpdateGameDomain();
        domain.setUpdateGameAddress(WusdTestConstants.gameCreater);
        domain.setUpdateGameFee(common_wusd_fee);
        domain.setGameId(WusdTestConstants.commonGameId);
        String contract = domain.serialize();
        System.out.println("testCreateGame:" + contract);

        UpdateGameDomain domain_deser = new UpdateGameDomain();
        domain_deser.deserialize(contract);
        assertWusdBaseDomain(domain,domain_deser);
        assertEquals(domain.getGameId(), domain_deser.getGameId());
        assertEquals(domain.getUpdateGameAddress(), domain_deser.getUpdateGameAddress());
        assertEquals(domain.getUpdateGameFee(), domain_deser.getUpdateGameFee());

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testUpdateGameAndBet() throws Exception{
        UpdateGameAndBetDomain domain = new UpdateGameAndBetDomain();
        domain.setFrom("wTwrWser78mEa22f8mHfiHGrdKysTv8eBU");
        domain.setTo(WusdTestConstants.commonGameId);
        domain.setAmount(10006);
        domain.setFee(common_wusd_fee);
        domain.setUpdateGameAddress(WusdTestConstants.gameCreater);
        domain.setUpdateGameFee(common_wusd_fee);
        domain.setGameId(WusdTestConstants.commonGameId);
        String contract = domain.serialize();
        System.out.println("testCreateGame:" + contract);

        UpdateGameAndBetDomain domain_deser = new UpdateGameAndBetDomain();
        domain_deser.deserialize(contract);
        assertTransferWithFeeDomain(domain,domain_deser);
        assertEquals(domain.getGameId(), domain_deser.getGameId());
        assertEquals(domain.getUpdateGameAddress(), domain_deser.getUpdateGameAddress());
        assertEquals(domain.getUpdateGameFee(), domain_deser.getUpdateGameFee());

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testBetByAdmin() throws Exception{
        BetByAdminDomain domain = new BetByAdminDomain();
        domain.setFrom(WusdTestConstants.normalAddr);
        String gameId = commonGameId;
        domain.setTo(gameId);
        domain.setAmount(200000000);
        domain.setFee(common_wusd_fee);
        String contract = domain.serialize();
        System.out.println("testBetByAdmin:" + contract);

        BetByAdminDomain domain_deser = new BetByAdminDomain();
        domain_deser.deserialize(contract);
        assertTransferWithFeeDomain(domain,domain_deser);

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testBatchBetByAdmin() throws Exception{
        BatchBetByAdminDomain domain = new BatchBetByAdminDomain();
        domain.setBetCount(2);
        List<BetInfoModel> betList = new ArrayList();
        BetInfoModel transferModel1 = new BetInfoModel();
        transferModel1.setFromAddress(WusdTestConstants.normalAddr);
        transferModel1.setToAddress(commonGameId);
        transferModel1.setAmount(10);
        transferModel1.setFee(common_wusd_fee);
        betList.add(transferModel1);
        BetInfoModel transferModel2 = new BetInfoModel();
        transferModel2.setFromAddress(WusdTestConstants.normalAddr2);
        transferModel2.setToAddress(commonGameId);
        transferModel2.setAmount(20);
        transferModel2.setFee(4);
        betList.add(transferModel2);
        domain.setBetList(betList);
        String contract = domain.serialize();
        System.out.println("testBetByAdmin:" + contract);

        BatchBetByAdminDomain domain_deser = new BatchBetByAdminDomain();
        domain_deser.deserialize(contract);
        assertWusdBaseDomain(domain,domain_deser);
        assertEquals(domain.getBetCount(),domain_deser.getBetCount());
        assertEquals(domain_deser.getBetList().get(0).getFromAddress(),domain.getBetList().get(0).getFromAddress());
        assertEquals(domain_deser.getBetList().get(0).getToAddress(),domain.getBetList().get(0).getToAddress());
        assertEquals(domain_deser.getBetList().get(0).getAmount(),domain.getBetList().get(0).getAmount());
        assertEquals(domain_deser.getBetList().get(0).getFee(),domain.getBetList().get(0).getFee());
        assertEquals(domain_deser.getBetList().get(0).getBetComment(),domain.getBetList().get(0).getBetComment());
        assertEquals(domain_deser.getBetList().get(0).getBetOption(),domain.getBetList().get(0).getBetOption());
        assertEquals(domain_deser.getBetList().get(0).getBetRule(),domain.getBetList().get(0).getBetRule());
        System.out.println("domain_deser.getBetList().get(0).getBetOdds()："  +  domain_deser.getBetList().get(0).getBetOdds() + ";" +  domain_deser.getBetList().get(0).getBetOdds().length());
        System.out.println("domain.getBetList().get(0).getBetOdds()："  +  domain.getBetList().get(0).getBetOdds() + ";" + domain.getBetList().get(0).getBetOdds().length());
        assertEquals(domain_deser.getBetList().get(1).getFromAddress(),domain.getBetList().get(1).getFromAddress());
        assertEquals(domain_deser.getBetList().get(1).getToAddress(),domain.getBetList().get(1).getToAddress());
        assertEquals(domain_deser.getBetList().get(1).getAmount(),domain.getBetList().get(1).getAmount());
        assertEquals(domain_deser.getBetList().get(1).getFee(),domain.getBetList().get(1).getFee());
        assertEquals(domain_deser.getBetList().get(1).getBetComment(),domain.getBetList().get(1).getBetComment());
        assertEquals(domain_deser.getBetList().get(1).getBetOption(),domain.getBetList().get(1).getBetOption());
        assertEquals(domain_deser.getBetList().get(1).getBetRule(),domain.getBetList().get(1).getBetRule());

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void testCloseGameDomain() throws Exception{
        CloseGameDomain domain = new CloseGameDomain();
        domain.setFrom(WusdTestConstants.commonGameId);
        domain.setTo(WusdTestConstants.gameCreater);
        domain.setAmount(1004);
        domain.setFee(common_wusd_fee);
        String contract = domain.serialize();
        System.out.println("testBetByAdmin:" + contract);

        BetByAdminDomain domain_deser = new BetByAdminDomain();
        domain_deser.deserialize(contract);
        assertTransferWithFeeDomain(domain,domain_deser);

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

    public void assertTransferWithFeeDomain(TransferWithFeeByAdminDomain target, TransferWithFeeByAdminDomain src){
        assertWusdBaseDomain(target,src);
        assertEquals(target.getFrom(),src.getFrom());
        assertEquals(target.getTo(),src.getTo());
        assertEquals(target.getAmount(),src.getAmount());
        assertEquals(target.getFee(),src.getFee());
    }

}
