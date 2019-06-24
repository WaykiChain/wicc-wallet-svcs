package com.waykichain;

import com.waykichain.JsonRpcClient;
import com.waykichain.coin.wicc.WiccMethods;
import org.junit.Before;

public class WusdTestBase {

    JsonRpcClient client;
    WiccMethods wiccMethods;

    @Before
    public void init() throws Exception {
        client = new JsonRpcClient(WusdTestConstants.JSON_RPC_IP,WusdTestConstants.JSON_RPC_PORT,
                WusdTestConstants.JSON_RPC_ADMIN, WusdTestConstants.JSON_RPC_PASSWORD, false);
        wiccMethods = new WiccMethods(client);

    }


}
