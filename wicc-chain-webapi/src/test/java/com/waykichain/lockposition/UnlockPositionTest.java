package com.waykichain.lockposition;

import com.alibaba.fastjson.JSON;
import com.waykichain.chain.contract.lockposition.UnlockPositionByAdminDomain;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnlockPositionTest {

    @Test
    public void unlockPositionByAdminTest(){
        String contract_src = "f20457526d4a5643714b7841794b34656e766a7041366e4b394848367139526d6e72586f80f0fa02000000007cc6b10034653662633137643237336338623461363735373466633164326233373166343865616332366261646264646534313466366634313234613263643134393064";

        UnlockPositionByAdminDomain domain_deser = new UnlockPositionByAdminDomain();
        domain_deser.deserialize(contract_src);
        System.out.println("domain_deser:" + JSON.toJSONString(domain_deser));

        UnlockPositionByAdminDomain domain_ser = new UnlockPositionByAdminDomain();
        domain_ser.setAddress(domain_deser.getAddress());
        domain_ser.setLockTxHash(domain_deser.getLockTxHash());
        domain_ser.setUnlockCount(domain_deser.getUnlockCount());
        domain_ser.setUnlockHeight(domain_deser.getUnlockHeight());
        String contract_ser = domain_ser.serialize();
        System.out.println("contract_ser:"+contract_ser);
        assertEquals(contract_ser,contract_src);
    }

}
