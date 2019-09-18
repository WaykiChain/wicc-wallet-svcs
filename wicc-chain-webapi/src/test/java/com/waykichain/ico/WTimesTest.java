package com.waykichain.ico;

import com.alibaba.fastjson.JSON;
import com.waykichain.chain.contract.ico.WTimesTransferDamain;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WTimesTest {

    @Test
    public void WTimesTransferTest(){
        String contract_src = "f007575561464d537970586463774657343154735852486b59574c646a4d5a374b72323700fa76450d000000";
        WTimesTransferDamain  domain_deser = new WTimesTransferDamain();
        domain_deser.deserialize(contract_src);
        System.out.println("domain_deser:" + JSON.toJSONString(domain_deser));

        WTimesTransferDamain domain_ser = new WTimesTransferDamain();
        domain_ser.setAmount(domain_deser.getAmount());
        domain_ser.setTo(domain_deser.getTo());
        System.out.println("domain_ser:" + JSON.toJSONString(domain_ser));
        String contract_ser =  domain_ser.serialize();
        System.out.println("contract_ser：" + contract_ser);
        assertEquals(contract_src,contract_ser);
    }

}
