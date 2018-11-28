package com.waykichain;

import com.alibaba.fastjson.JSON;
import com.waykichain.chain.contract.wusd.TransferTokenByUserDomain;
import com.waykichain.chain.contract.wusd.domain.ExchangeTokenDomain;
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain;
import com.waykichain.coin.wicc.WiccMethods;
import com.waykichain.coin.wicc.po.CreateContractTxPO;
import com.waykichain.coin.wicc.vo.WiccCreateContractTxJsonRpcResponse;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class WusdUserOperationTest {

    JsonRpcClient client;
    WiccMethods wiccMethods;
    CreateContractTxPO createContractTxPO;

    @Before
    public void init() throws Exception {
        client = new JsonRpcClient(WusdTestConstants.JSON_RPC_IP,WusdTestConstants.JSON_RPC_PORT,
                "waykichain", "admin@1234", false);
        wiccMethods = new WiccMethods(client);

        createContractTxPO = new CreateContractTxPO();
        createContractTxPO.setUserregid(WusdTestConstants.normalAddr);
        createContractTxPO.setAppId(WusdTestConstants.appid);
        createContractTxPO.setAmount(0L);
        createContractTxPO.setFee(1000000L);
    }

    @Test
    public void testExchangeToken() throws Exception{
        long exchagneWiccAmount = 1000 * 100000000L;

        ExchangeTokenDomain domain = new ExchangeTokenDomain();
        double exchangeRate = WusdTestConstants.common_exchangeRate;
        long exchangeRateParam = (long) (exchangeRate * 10000);
        domain.setExchangeRate(exchangeRateParam);
        System.out.println("exchangeRate:" + exchangeRate);
        System.out.println("exchagneWiccAmount:" + exchagneWiccAmount);
        long exchangeTokenCount = (long)(exchangeRate * exchagneWiccAmount);
        System.out.println("exchangeTokenCount:" + exchangeTokenCount);
        domain.setExchangeTokenAmount( exchangeTokenCount);
        String contract = domain.serialize();
        System.out.println("printExchangeToken,contract:" + contract);

        ExchangeTokenDomain domain_deser = new ExchangeTokenDomain();
        domain_deser.deserialize(contract);
        System.out.println("testExchangeToken, domain_deser:" + JSON.toJSONString(domain_deser));
        assertWusdBaseDomain(domain,domain_deser);
        assertEquals(domain.getExchangeRate(),domain_deser.getExchangeRate());
        assertEquals(domain.getExchangeTokenAmount(),domain_deser.getExchangeTokenAmount());

        createContractTxPO.setAmount(exchagneWiccAmount);
        createContractTxPO.setContract(contract);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);

        assertNull(response.getError());
    }

    @Test
    public void testTransferTokenByUser() throws Exception{
        TransferTokenByUserDomain domain = new TransferTokenByUserDomain();
        domain.setTo(WusdTestConstants.normalAddr2);
        domain.setAmount(1000);
        String contract = domain.serialize();

        TransferTokenByUserDomain domain_deser = new TransferTokenByUserDomain();
        domain_deser.deserialize(contract);
        assertWusdBaseDomain(domain,domain_deser);
        assertEquals(domain.getAmount(),domain_deser.getAmount());
        assertEquals(domain.getTo(),domain_deser.getTo());

        createContractTxPO.setContract(contract);
        createContractTxPO.setFee(1000000L);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);

        assertNull(response.getError());
    }

    private void checkResponse(WiccCreateContractTxJsonRpcResponse response){
        assertNull(response.getError());
    }

    public void assertWusdBaseDomain(WusdBaseDomain target, WusdBaseDomain src){
        assertEquals(target.getSystype(), src.getSystype());
        assertEquals(target.getType(), src.getType());
        assertEquals(target.getVersion(),src.getVersion());
    }

}
