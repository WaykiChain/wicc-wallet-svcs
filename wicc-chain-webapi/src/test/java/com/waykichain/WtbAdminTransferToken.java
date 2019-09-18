package com.waykichain;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.waykichain.chain.contract.wusd.TransferWithFeeByAdminDomain;
import com.waykichain.coin.wicc.WiccMethods;
import com.waykichain.coin.wicc.po.CreateContractTxPO;
import com.waykichain.coin.wicc.vo.WiccCreateContractTxJsonRpcResponse;
import org.junit.Test;

import java.util.Collections;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class WtbAdminTransferToken {

    JsonRpcClient client;
    WiccMethods wiccMethods;
    CreateContractTxPO createContractTxPO;
    long common_wusd_fee = WusdTestConstants.common_wusd_fee;

    String fromAddress  = "WhzAbAC9dWegHvd34EnzHRn7xPCekNKXuW"; //Richard's WTB account address
    String toAddress    = Strings.repeat("8", 34);

    /* actual usage */
    @Test
    public void B_testTransferWithFeeByTokenAdmin() throws Exception{
        long amount = 20000 * 100000000L;

        TransferWithFeeByAdminDomain domain = new TransferWithFeeByAdminDomain();
        domain.setFrom( fromAddress );
        domain.setTo( toAddress );
        domain.setAmount( amount );
        domain.setFee( common_wusd_fee );

        String contract = domain.serialize();
        System.out.println("from: " + fromAddress);
        System.out.println("to: " + toAddress);
        System.out.println("contract:\n" + contract);

        TransferWithFeeByAdminDomain domain_deser = new TransferWithFeeByAdminDomain();
        domain_deser.deserialize(contract);
        System.out.println("domain_deser:" + JSON.toJSONString(domain_deser));
        assertEquals(domain.getFrom(), domain_deser.getFrom());

//        createContractTxPO.setContract(contract);
//        WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
//        System.out.println(response);
//        assertNull(response.getError());
    }
}
