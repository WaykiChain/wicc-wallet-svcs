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
 * <p><b>Created:</b> 18/08/16, 02:23 PM</p>F
 * @author <a href="mailto:samuel.quintana@globant.com">samuel</a>
 * @since 0.1.0
 */
public class WiccRpcTest {


    JsonRpcClient client;
    WiccMethods wiccMethods;
    @Before
    public void init() throws Exception {
       client = new JsonRpcClient("10.0.0.4",6967,
                "wiccuser", "wicc1000", false);
/*        client = new JsonRpcClient("47.107.82.41",6967,
                "waykichain", "admin@123", false);*/

        wiccMethods = new WiccMethods(client);
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
        sendToAddressPO.sendAddress = "wVC3y9Sm3JHe1N3heK93BGozPLKHgLGAhx";
        sendToAddressPO.recvAddress = "0-2";
        sendToAddressPO.amount = new BigDecimal(10 * 100_000_000);

        WiccSubmitTxJsonRpcResponse response = wiccMethods.sendToAddress(sendToAddressPO);
        System.out.print(response);
    }

    @Test
    public void testSendToAddressRaw() throws Exception {
        SendToAddressRawPO po = new SendToAddressRawPO();
        po.setFee(new Long(10000));
        po.setAmount(new Long(100000L));
        po.setSrcaddress("wVC3y9Sm3JHe1N3heK93BGozPLKHgLGAhx");
        po.setRecvaddress("wXscdWVUN9irrLGvJJDZiaBoKXvat6Pqa9");
        po.setHeight(2338);

        WiccRawTxJsonRpcResponse response = wiccMethods.sendToAddressRaw(po);
        System.out.println(response);
    }

    @Test
    public void activeAddress() throws Exception {
        String address = "wNuoAkHbBY5ncRrEiYwjonizpnbUf72wox";

        WiccSubmitTxJsonRpcResponse response = wiccMethods.activeAddress(address);
        System.out.print(response);
    }
    @Test
    public void testGetBalance() throws Exception {
        WiccBalanceJsonRpcResponse response = wiccMethods.getBalance("0-2");
        System.out.print(response);
    }

    @Test
    public void testGetAccountInfo() throws Exception {
        WiccAccountInfoJsonRpcResponse response = wiccMethods.getAccountInfo("0-2");
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
        WiccBlockJsonRpcResponse response = wiccMethods.getBlock(20);
        System.out.print(response);
    }

    @Test
    public void testGetBlockHash() throws Exception {
        WiccGetBlockHashJsonRpcResponse response = wiccMethods.getBlockHash(100);
        System.out.println(response);
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
        GetContractRegidPO getRegidPO = new GetContractRegidPO();
        getRegidPO.setHash("3dcb5be9ccb623837baa77d0fad273fb3fda11f13a16f29937884e7c74ba8fcf");
        WiccGetContractRegidJsonRpcResponse response = wiccMethods.getContractRegid(getRegidPO);
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
        GetContractDataPO po = new GetContractDataPO();
        po.setContractRegid("77092-1");
        po.setKey("65786368616e676552617465");
        WiccGetScriptDataJsonRpcResponse response = wiccMethods.getScriptData(po);
        System.out.println(response);
    }

    @Test
    public void testGetContractDataRaw() throws Exception {
        GetContractDataPO po = new GetContractDataPO();
        po.setContractRegid("77092-1");
        po.setKey("65786368616e676552617465");
        WiccGetScriptDataJsonRpcResponse response = wiccMethods.getContractDataRaw(po);
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

        WiccGetTxDetailJsonRpcResponse response = wiccMethods.getTxDetail("38d0a6dc434a08008c58c4c2e18991eb4e24ce840e065a7b5f9b0c238641fcc7");
        System.out.println(response.toString());
    }

    @Test
    public void submitTx() throws Exception{

        WiccSubmitTxJsonRpcResponse response = wiccMethods.submitTx("0301912203906c0114865044b3ac4f7e1facb0f881666ef557d441e0ffcd10858c200046304402202c7ed6242e231c78f43761c04e267dc925d10c031092a5f58c4b51c64c5c97d4022028c69e16e041ed8b10b3069df0688a6ca0b57681ba47e2396d9f6e9d4dc05ebc");
        System.out.println(response.toString());
    }
}
