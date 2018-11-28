package com.waykichain.ico;

import com.alibaba.fastjson.JSON;
import com.waykichain.JsonRpcClient;
import com.waykichain.WusdTestConstants;
import com.waykichain.chain.contract.ico.IcoTransferDamain;
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

public class IcoTest {
    JsonRpcClient client;
    WiccMethods wiccMethods;
    CreateContractTxPO createContractTxPO;

    @Before
    public void init() throws Exception {
        client = new JsonRpcClient(WusdTestConstants.JSON_RPC_IP, WusdTestConstants.JSON_RPC_PORT,
                "waykichain", "admin@1234", false);
        wiccMethods = new WiccMethods(client);

        createContractTxPO = new CreateContractTxPO();
        createContractTxPO.setUserregid(WusdTestConstants.normalAddr);
        createContractTxPO.setAppId(WusdTestConstants.appid);
        createContractTxPO.setAmount(0L);
        createContractTxPO.setFee(1000000L);
    }

    @Test
    public void testTransferTokenByUser() throws Exception{
        IcoTransferDamain domain = new IcoTransferDamain();
        domain.setTo("wVCfTim2m7F6u5qt9SUixUngbqkpGMoSL6");
        domain.setAmount(10000);
        String contract = domain.serialize();

        System.out.println("contract:" + contract);

        IcoTransferDamain domain_deser = new IcoTransferDamain();
        domain_deser.deserialize(contract);
        assertEquals(domain.getAmount(),domain_deser.getAmount());
        assertEquals(domain.getTo(),domain_deser.getTo());

/*        createContractTxPO.setContract(contract);
        createContractTxPO.setFee(1000000L);
        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
        System.out.println(response);

        assertNull(response.getError());*/
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
