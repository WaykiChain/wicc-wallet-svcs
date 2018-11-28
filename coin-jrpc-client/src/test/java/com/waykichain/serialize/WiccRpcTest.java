package com.waykichain.serialize;

import com.waykichain.JsonRpcClient;
import com.waykichain.coin.wicc.WiccMethods;
import com.waykichain.coin.wicc.po.*;
import com.waykichain.coin.wicc.vo.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * <p><b>Created:</b> 18/08/16, 02:23 PM</p>
 * @author <a href="mailto:samuel.quintana@globant.com">samuel</a>
 * @since 0.1.0
 */
public class WiccRpcTest {


    JsonRpcClient client;
    WiccMethods wiccMethods;
    @Before
    public void init() throws Exception {
       client = new JsonRpcClient("10.0.0.4",6967,
                "wikichain", "admin", false);

        wiccMethods = new WiccMethods(client);
    }


    @Test
    public void Brush_testGetAddress()throws Exception {
        for(int i=0; i<300; i++){
            WiccAddressJsonRpcResponse response = wiccMethods.getNewAddress();
            System.out.print(response);
        }
    }

    @Test
    public void Brush_testSendToAddress() throws Exception {
        SendToAddressPO sendToAddressPO =  new SendToAddressPO();
        sendToAddressPO.sendAddress = "Wms55pCGmNpwAztbLKaFeF2RM842Jr8KiQ";
        sendToAddressPO.amount = new BigDecimal(30 ).multiply(new BigDecimal(101000000));

        WiccListAddressJsonRpcResponse response = wiccMethods.listAddress();
        System.out.println(response.getResult().size());

        for(int i=0; i<response.getResult().size(); i++){
            WiccListAddressResult ad = response.getResult().get(i);
            if(ad.getBalance().doubleValue() < 30){
                sendToAddressPO.recvAddress = ad.addr;
                WiccSubmitTxJsonRpcResponse response2 = wiccMethods.sendToAddress(sendToAddressPO);
                System.out.print(response2);
                Thread.sleep(100);
            }
        }
    }

    @Test
    public void Brush_activeAddress() throws Exception {
        WiccListAddressJsonRpcResponse response = wiccMethods.listAddress();
        System.out.println(response.getResult().size());

        for(int i=0; i<response.getResult().size(); i++){
            WiccListAddressResult ad = response.getResult().get(i);
            if(ad.getBalance().doubleValue() > 30 ){
                String address = ad.addr;
                WiccSubmitTxJsonRpcResponse response2 = wiccMethods.activeAddress(address);
                System.out.print(response2);
                Thread.sleep(100);
            }
        }

    }

    @Test
    public void Brush_testSendToAddress2() throws Exception {
        SendToAddressPO sendToAddressPO =  new SendToAddressPO();
        sendToAddressPO.recvAddress = "WQjQqSTKYUBTQpSdLcbQTuYYH9EidCnVBQ";
        sendToAddressPO.amount = new BigDecimal(30 ).multiply(new BigDecimal(100000000));

        WiccListAddressJsonRpcResponse response = wiccMethods.listAddress();
        System.out.println(response.getResult().size());

        for(int i=0; i<response.getResult().size(); i++){
            WiccListAddressResult ad = response.getResult().get(i);
            if(ad.getBalance().doubleValue() > 30){
                sendToAddressPO.sendAddress  = ad.addr;
                WiccSubmitTxJsonRpcResponse response2 = wiccMethods.sendToAddress(sendToAddressPO);
                System.out.print(response2);
                Thread.sleep(5000);
            }
        }
    }

    ///<地址: wXzvgD3YwMccp5AVZDavLXkLFKMcmBChAx
    @Test
    public void testGetAddress() throws Exception {
        WiccAddressJsonRpcResponse response = wiccMethods.getNewAddress();
        System.out.print(response);
    }

    @Test
    public void testSendToAddress() throws Exception {
        SendToAddressPO sendToAddressPO =  new SendToAddressPO();
        sendToAddressPO.sendAddress = "wTwrWser78mEa22f8mHfiHGrdKysTv8eBU";
        sendToAddressPO.recvAddress = "wXscdWVUN9irrLGvJJDZiaBoKXvat6Pqa9";
        sendToAddressPO.amount = new BigDecimal(10 * 100_000_000);

        WiccSubmitTxJsonRpcResponse response = wiccMethods.sendToAddress(sendToAddressPO);
        System.out.print(response);
    }

    @Test
    public void testSendToAddressRaw() throws Exception {
        SendToAddressRawPO po = new SendToAddressRawPO();
        po.setFee(new Long(10000));
        po.setAmount(new Long(100000L));
        po.setSrcaddress("wTwrWser78mEa22f8mHfiHGrdKysTv8eBU");
        po.setRecvaddress("wXscdWVUN9irrLGvJJDZiaBoKXvat6Pqa9");
        po.setHeight(243038);

        WiccRawTxJsonRpcResponse response = wiccMethods.sendToAddressRaw(po);
        System.out.println(response);
    }

    @Test
    public void activeAddress() throws Exception {
        String address = "wXzvgD3YwMccp5AVZDavLXkLFKMcmBChAx";

        WiccSubmitTxJsonRpcResponse response = wiccMethods.activeAddress(address);
        System.out.print(response);
    }
    @Test
    public void testGetBalance() throws Exception {
        WiccBalanceJsonRpcResponse response = wiccMethods.getBalance("wXzvgD3YwMccp5AVZDavLXkLFKMcmBChAx");
        System.out.print(response);
    }

    @Test
    public void testGetAccountInfo() throws Exception {
        WiccAccountInfoJsonRpcResponse response = wiccMethods.getAccountInfo("wTMrkcgbfFWEWQxBs1XHvGgyTkd5JenQxP");
        System.out.print(response);
    }

    @Test
    public void testListAddress() throws Exception {
        WiccListAddressJsonRpcResponse response = wiccMethods.listAddress();
        System.out.print(response);
    }

    @Test
    public void testGetInfo() throws Exception {
        WiccInfoJsonRpcResponse response = wiccMethods.getInfo();
        System.out.print(response);
    }

    @Test
    public void testGetBlock() throws Exception {
        WiccBlockJsonRpcResponse response = wiccMethods.getBlock(832968);
        System.out.print(response);
    }

    @Test
    public void testRegisterAppTx() throws Exception {
        RegisterAppTxPO registerAppTxPO = new RegisterAppTxPO();
        registerAppTxPO.setAddr("wTwrWser78mEa22f8mHfiHGrdKysTv8eBU");
        registerAppTxPO.setFilepath("dapp2.5_3.lua");
        registerAppTxPO.setFee(1000000000L);
        WiccTxHashJsonRpcResponse response = wiccMethods.registerAppTx(registerAppTxPO);
        System.out.println(response);
    }

    @Test
    public void testGetScriptId() throws Exception {
        GetScriptIdPO getScriptIdPO = new GetScriptIdPO();
        getScriptIdPO.setHash("3dcb5be9ccb623837baa77d0fad273fb3fda11f13a16f29937884e7c74ba8fcf");
        WiccGetScriptIdJsonRpcResponse response = wiccMethods.getScriptId(getScriptIdPO);
        System.out.println(response);
    }
    
    @Test
    public void testCreateContractTx() throws Exception {
    	CreateContractTxPO createContractTxPO = new CreateContractTxPO();
    	createContractTxPO.setUserregid("wTwrWser78mEa22f8mHfiHGrdKysTv8eBU");
    	createContractTxPO.setAppId("26394-1");
    	createContractTxPO.setAmount(0L);
    	createContractTxPO.setContract("f0110001775477725773657237386d4561323266386d486669484772644b7973547638654255");
    	createContractTxPO.setFee(1000000L);
    	WiccCreateContractTxJsonRpcResponse response = wiccMethods.WiccCreateContractTx(createContractTxPO);
    	System.out.println(response);
    }

    @Test
    public void testCreateContractTxRaw() throws Exception {
        CreateContractTxRawPO createContractTxRawPO = new CreateContractTxRawPO();
        createContractTxRawPO.setFee(1000000L);
        createContractTxRawPO.setAmount(0L);
        createContractTxRawPO.setAddr("wTwrWser78mEa22f8mHfiHGrdKysTv8eBU");
        createContractTxRawPO.setAppid("26394-1");
        createContractTxRawPO.setContract("f0110001775477725773657237386d4561323266386d486669484772644b7973547638654255");
        createContractTxRawPO.setHeight(130426);
        WiccRawTxJsonRpcResponse response = wiccMethods.createContractTxRaw(createContractTxRawPO);
        System.out.println(response);
    }

    @Test
    public void testGetScriptData() throws Exception {
        GetScriptDataPO po = new GetScriptDataPO();
        po.setScriptid("77092-1");
        po.setKey("65786368616e676552617465");
        WiccGetScriptDataJsonRpcResponse response = wiccMethods.getScriptData(po);
        System.out.println(response);
    }

    @Test
    public void testgetappaccinfo() throws Exception{
        GetAppAccInfoPO po = new GetAppAccInfoPO();
        po.setScriptid("68615-1");
        po.setAddress("wWGf6ZJKSCbgyTyU582tLeYuozKzVXKsq1");
        WiccGetAppAccInfoJsonRpcResponse response = wiccMethods.getappaccinfo(po);
        System.out.println(response);
    }

    @Test
    public void testgettxdetails() throws Exception{

        WiccGetTxDetailJsonRpcResponse response = wiccMethods.getTxDetail("65fb030c33085806cb09a02cf6e91051cda26e2b03ad8f90ce41398cc618227e");
        System.out.println(response.toString());
    }

    @Test
    public void submitTx() throws Exception{

        WiccSubmitTxJsonRpcResponse response = wiccMethods.submitTx("03018aaf280480c855010486db260101858c20004730450221008c0a70b49a5cb30a9dcb4e5523ec6b20fe20f41467f1015020dfb21c66553b85022016d647e203b6951a7f01cbed6b264eeba554df4323b1e663a0152fcc511ce71f");
        System.out.println(response.toString());
    }
}
