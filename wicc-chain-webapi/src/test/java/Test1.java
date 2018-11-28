import com.waykichain.chain.contract.util.ContractUtil;
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

}
