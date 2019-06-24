package com.waykichain;

import com.alibaba.fastjson.JSON;
import com.waykichain.chain.contract.wusd.*;
import com.waykichain.chain.contract.wusd.domain.SetExchangeRateDomain;
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain;
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
import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WusdOperateUserByAdmin {

    JsonRpcClient client;
    WiccMethods wiccMethods;
    CreateContractTxPO createContractTxPO;

    private String commonGameId = WusdTestConstants.commonGameId;
    private long common_wusd_fee = WusdTestConstants.common_wusd_fee;

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
    public void A_testExchangeWicc() throws Exception{
        ExchangeWiccByAdminDomain domain = new ExchangeWiccByAdminDomain();
        domain.setExchangeAddress(WusdTestConstants.normalAddr);
        double exchangeRate = WusdTestConstants.common_exchangeRate;
        long exchangeRateParam = (long) (exchangeRate * 10000L);
        long tokenAmount = 4 * 100000000L;
        long wiccAmount = (long) (tokenAmount/WusdTestConstants.common_exchangeRate) ;

        domain.setExchangeRate(exchangeRateParam);
        domain.setExchangeTokenAmount(tokenAmount);
        domain.setExchangeWiccAmount(wiccAmount);
        domain.setFee(common_wusd_fee);
        String contract = domain.serialize();
        ExchangeWiccByAdminDomain deser_domain = new ExchangeWiccByAdminDomain();
        deser_domain.deserialize(contract);
        System.out.println("deser_domain" + JSON.toJSONString(deser_domain));

/*        assertWusdBaseDomain(domain,deser_domain);
        assertEquals(domain.getExchangeAddress(),deser_domain.getExchangeAddress());
        assertEquals(domain.getExchangeRate(),deser_domain.getExchangeRate());
        assertEquals(domain.getExchangeTokenAmount(),deser_domain.getExchangeTokenAmount());
        assertEquals(domain.getExchangeWiccAmount(),deser_domain.getExchangeWiccAmount());
        assertEquals(domain.getFee(),deser_domain.getFee());*/

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void B_testTransferWithFee() throws Exception{
        long amount = 40 * 100000000L;
    //    long amount = 619233305015L;

        TransferWithFeeByAdminDomain domain = new TransferWithFeeByAdminDomain();
        domain.setFrom(WusdTestConstants.normalAddr);
        domain.setTo(WusdTestConstants.gameCreater);
        domain.setAmount(amount);
        domain.setFee(common_wusd_fee);
        String contract = domain.serialize();
        System.out.println("testTransferWithFee:" + contract);

        TransferWithFeeByAdminDomain domain_deser = new TransferWithFeeByAdminDomain();
        System.out.println("domain_deser:" + JSON.toJSONString(domain_deser));
        domain_deser.deserialize(contract);
        assertTransferWithFeeDomain(domain,domain_deser);

        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);
        checkResponse(response);
    }

    @Test
    public void C_testCreateGame() throws Exception{
        long amount = 4 * 100000000L;

        CreateGameDomain domain = new CreateGameDomain();
        domain.setFrom(WusdTestConstants.gameCreater);
        domain.setTo(WusdTestConstants.commonGameId);
        domain.setAmount(amount);
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
    public void D_testCreateGameAndBetGame() throws Exception{
    //    long createGame_Amount = 635733311045L;
    //    long bet_Amount = 92000000000L;
        long createGame_Amount = 2 * 100000000L;
        long bet_Amount = 2 * 100000000L;

        CreateGameAndBetDomain domain = new CreateGameAndBetDomain();
        domain.setFrom(WusdTestConstants.gameCreater);
        domain.setTo(commonGameId);
        domain.setAmount(createGame_Amount);
        domain.setFee(common_wusd_fee);
        domain.setBetFrom(WusdTestConstants.normalAddr);
        domain.setBetTo(commonGameId);
        domain.setBetAmount(bet_Amount);
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
    public void E_testUpdateGame() throws Exception{
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
    public void F_testUpdateGameAndBet() throws Exception{
          long amount = 1 * 100000000L;
     //   long amount = 92000000000L;
     //   long amount = 92999980001L;

        UpdateGameAndBetDomain domain = new UpdateGameAndBetDomain();
        domain.setFrom(WusdTestConstants.gameCreater);
        domain.setTo(WusdTestConstants.commonGameId);
        domain.setAmount(amount);
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
    public void G_testBetByAdmin() throws Exception{
      //  String gameId = WusdTestConstants.normalAddr2; // commonGameId;
      //  long amount = 2 * 100000000L;
        long amount = 1000000000L;

        BetByAdminDomain domain = new BetByAdminDomain();
        domain.setFrom(WusdTestConstants.normalAddr);
        domain.setTo(commonGameId);
        domain.setAmount(amount);
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
    public void H_testBatchBetByAdmin() throws Exception{
/*        long betAmount1 = (long) (0.02 * 100000000L);
        long betAmount2 = (long) (0.01 * 100000000L);*/
        long betAmount1 = (long) (5000L);
        long betAmount2 = (long) (5L);

        BatchBetByAdminDomain domain = new BatchBetByAdminDomain();
        domain.setBetCount(2);
        List<BetInfoModel> betList = new ArrayList();
        BetInfoModel transferModel1 = new BetInfoModel();
        transferModel1.setFromAddress(WusdTestConstants.normalAddr);
        transferModel1.setToAddress(commonGameId);
        transferModel1.setAmount(betAmount1);
        transferModel1.setFee(common_wusd_fee);
        betList.add(transferModel1);
        BetInfoModel transferModel2 = new BetInfoModel();
        transferModel2.setFromAddress(WusdTestConstants.normalAddr);
        transferModel2.setToAddress(commonGameId);
        transferModel2.setAmount(betAmount2);
        transferModel2.setFee(common_wusd_fee);
        betList.add(transferModel2);
        domain.setBetList(betList);
        String contract = domain.serialize();
        System.out.println("testBetByAdmin:" + contract);

        BatchBetByAdminDomain domain_deser = new BatchBetByAdminDomain();
        domain_deser.deserialize(contract);
        System.out.println("domain_deser:" + JSON.toJSONString(domain_deser));

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
    public void I_testCloseGameDomain() throws Exception{
        long amount = 1004;

        CloseGameDomain domain = new CloseGameDomain();
        domain.setFrom(WusdTestConstants.commonGameId);
        domain.setTo(WusdTestConstants.gameCreater);
        domain.setAmount(amount);
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
