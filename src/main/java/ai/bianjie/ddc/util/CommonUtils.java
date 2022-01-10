package ai.bianjie.ddc.util;

import org.bouncycastle.crypto.digests.KeccakDigest;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class CommonUtils {
    /**
     * String转换为BigInteger
     */
    public static BigInteger string2BigInteger(String val) {
        boolean numeric00 = CommonUtils.isNumeric00(val);
        if(numeric00){
            return BigInteger.valueOf(Long.valueOf(val));
        }else{
            throw new NumberFormatException();
        }
    }

    /**
     * 判断字符串是否由数字组成
     * @param str
     * @return
     */
    public static boolean isNumeric00(String str) {
        try{
            Long.parseLong(str);
            return true;
        }catch(NumberFormatException e)
        {
//            System.out.println("异常：\"" + str + "\"不是数字/整数...");
            return false;
        }
    }


}
