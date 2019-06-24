import com.waykichain.chain.contract.util.ContractUtil;
import com.waykichain.chain.contract.wusd.domain.ExchangeTokenDomain;
import org.junit.Test;

public class Test1 {

    @Test
    public void test12(){

        System.out.println("5555");

        System.out.println("i:" + Integer.toHexString(5 & 0xFF));
        System.out.println("i:" + Integer.toHexString(5));

        String str = "中国：  美 国";
        String strHex = ContractUtil.utf8ToHexString(str,0);
        System.out.println("strHex:" + strHex);
        String str2 = ContractUtil.hexToUTF8(strHex);
        System.out.println("str2:" + str2);

/*        byte[] b = str.getBytes();
        for(int i=0; i<b.length; i++){
            System.out.println("i:" + b[i]);
            System.out.println("i:" + Integer.toHexString(b[i] & 0xFF));

        }*/

    }

    public static void printByte(String str) {

        for(int i=0; i<str.length(); i=i+2) {
            System.out.print("0x" + str.substring(i, i+2) + ",");
        }
    }

    @Test
    public void printExchangeToken(){
        ExchangeTokenDomain domain = new ExchangeTokenDomain();
        double exchangeRate = 0.74;
        long exchangeRateParam = (long) (exchangeRate * 10000);
        domain.setExchangeRate(exchangeRateParam);
        domain.setExchangeTokenAmount(22110*100000000L);

        String ser = domain.serialize();
        System.out.println("printExchangeToken:" + ser);
        printByte(ser);

    }

}
