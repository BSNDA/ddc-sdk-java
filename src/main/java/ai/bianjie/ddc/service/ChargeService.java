package ai.bianjie.ddc.service;

import ai.bianjie.ddc.constant.ChargeFunctions;
import ai.bianjie.ddc.constant.ErrorMessage;
import ai.bianjie.ddc.contract.ChargeLogic;
import ai.bianjie.ddc.contract.DDC721;
import ai.bianjie.ddc.exception.DDCException;
import ai.bianjie.ddc.listener.SignEventListener;
import ai.bianjie.ddc.listener.sign;
import ai.bianjie.ddc.util.AddressUtils;
import ai.bianjie.ddc.util.HexUtils;
import ai.bianjie.ddc.util.Web3jUtils;
import org.web3j.abi.datatypes.Function;
import org.web3j.utils.Numeric;
import org.web3j.utils.Strings;

import java.math.BigInteger;

public class ChargeService extends BaseService {
    private ChargeLogic chargeLogic;
    private String encodedFunction;

    public ChargeService(SignEventListener signEventListener) {
        super.signEventListener = signEventListener;
        this.chargeLogic = Web3jUtils.getCharge();
    }


    /**
     * ��Ӫ����ƽ̨�����øýӿ�Ϊ����ͬһ����ͬһ�����˻������¼��˻���ֵ��
     *
     * @param to     ��ֵ�˻��ĵ�ַ
     * @param amount ��ֵ���
     * @return ���ؽ��׹�ϣ
     * @throws Exception
     */
    public String recharge(String sender,String to, BigInteger amount) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(to)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(to)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (amount == null || amount.intValue() <= 0) {
            throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
        }
        encodedFunction = chargeLogic.recharge(to, amount).encodeFunctionCall();

        return signAndSend(chargeLogic, ChargeFunctions.Recharge, encodedFunction, signEventListener,sender).getTransactionHash();
    }

    /**
     * ��ѯָ���˻�����
     *
     * @param accAddr ��ѯ���˻���ַ
     * @return �����˻�����Ӧ��ҵ������
     * @throws Exception
     */
    public BigInteger balanceOf(String sender,String accAddr) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(accAddr)) {
            throw new DDCException(ErrorMessage.ACC_ADDR_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(accAddr)) {
            throw new DDCException(ErrorMessage.ACC_ADDR_IS_NOT_ADDRESS_FORMAT);
        }

        return Web3jUtils.getCharge().balanceOf(accAddr).send();
    }

    /**
     * ��ѯָ����DDCҵ�����߼���Լ�ķ�������Ӧ�ĵ���ҵ����á�
     *
     * @param ddcAddr DDCҵ�����߼���Լ��ַ
     * @param sig     Hex��ʽ�ĺ�Լ����ID
     * @return ����DDC��Լҵ���
     * @throws Exception
     */
    public BigInteger queryFee(String sender,String ddcAddr, String sig) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(sig)) {
            throw new DDCException(ErrorMessage.SIG_IS_EMPTY);
        }

        if (!HexUtils.isValid4ByteHash(sig)) {
            throw new DDCException(ErrorMessage.SIG_IS_NOT_4BYTE_HASH);
        }
        byte[] sigInByte = Numeric.hexStringToByteArray(sig);

        return Web3jUtils.getCharge().queryFee(ddcAddr, sigInByte).send();
    }

    /**
     * ��Ӫ������Ϊ�Լ����˻�����ҵ��ѡ�
     *
     * @param amount ����Ӫ���˻����г�ֵ��ҵ���
     * @return ���ؽ��׹�ϣ
     * @throws Exception
     */
    public String selfRecharge(String sender,BigInteger amount) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (amount == null || amount.intValue() <= 0) {
            throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
        }
        encodedFunction = chargeLogic.selfRecharge(amount).encodeFunctionCall();

        return signAndSend(chargeLogic, ChargeFunctions.Recharge, encodedFunction, signEventListener,sender).getTransactionHash();
    }

    /**
     * ��Ӫ�����ýӿ�����ָ����DDC����Լ�ķ������÷��á�
     *
     * @param ddcAddr DDCҵ�����߼���Լ��ַ
     * @param sig     Hex��ʽ�ĺ�Լ����ID
     * @param amount  ҵ�����
     * @return ���ؽ��׹�ϣ
     * @throws Exception
     */
    public String setFee(String sender,String ddcAddr, String sig, BigInteger amount) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(sig)) {
            throw new DDCException(ErrorMessage.SIG_IS_EMPTY);
        }

        if (!HexUtils.isValid4ByteHash(sig)) {
            throw new DDCException(ErrorMessage.SIG_IS_NOT_4BYTE_HASH);
        }

        if (amount == null || amount.intValue() <= 0) {
            throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
        }
        byte[] sigInByte = Numeric.hexStringToByteArray(sig);
        encodedFunction = chargeLogic.setFee(ddcAddr, sigInByte, amount).encodeFunctionCall();

        return signAndSend(chargeLogic, ChargeFunctions.Recharge, encodedFunction, signEventListener,sender).getTransactionHash();
    }

    /**
     * ��Ӫ�����ýӿ�ɾ��ָ����DDC����Լ�ķ������÷��á�
     *
     * @param ddcAddr DDCҵ�����߼���Լ��ַ
     * @param sig     Hex��ʽ�ĺ�Լ����ID
     * @return ���ؽ��׹�ϣ
     * @throws Exception
     */
    public String delFee(String sender,String ddcAddr, String sig) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(sig)) {
            throw new DDCException(ErrorMessage.SIG_IS_EMPTY);
        }

        if (!HexUtils.isValid4ByteHash(sig)) {
            throw new DDCException(ErrorMessage.SIG_IS_NOT_4BYTE_HASH);
        }

        byte[] sigInByte = Numeric.hexStringToByteArray(sig);
        encodedFunction = chargeLogic.delFee(ddcAddr, sigInByte).encodeFunctionCall();

        return signAndSend(chargeLogic, ChargeFunctions.Recharge, encodedFunction, signEventListener,sender).getTransactionHash();
    }

    /**
     * ��Ӫ�����øýӿ�ɾ��ָ����DDCҵ�����߼���Լ��Ȩ��
     *
     * @param ddcAddr DDCҵ�����߼���Լ��ַ
     * @return ���ؽ��׹�ϣ
     * @throws Exception
     */
    public String delDDC(String sender,String ddcAddr) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_NOT_ADDRESS_FORMAT);
        }

        encodedFunction = chargeLogic.delDDC(ddcAddr).encodeFunctionCall();

        return signAndSend(chargeLogic, ChargeFunctions.Recharge, encodedFunction, signEventListener,sender).getTransactionHash();
    }

}
