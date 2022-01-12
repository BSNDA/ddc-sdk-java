package ai.bianjie.ddc.service;

import ai.bianjie.ddc.constant.ErrorMessage;
import ai.bianjie.ddc.contract.ChargeLogic;
import ai.bianjie.ddc.exception.DDCException;
import ai.bianjie.ddc.listener.SignEventListener;
import ai.bianjie.ddc.util.AddressUtils;
import ai.bianjie.ddc.util.HexUtils;
import ai.bianjie.ddc.util.Web3jUtils;
import org.web3j.utils.Strings;

import java.math.BigInteger;

public class ChargeService extends BaseService {

	public ChargeService(SignEventListener signEventListener) {
		super.signEventListener = signEventListener;
	}

	/**
	 * ��Ӫ����ƽ̨�����øýӿ�Ϊ����ͬһ����ͬһ�����˻������¼��˻���ֵ��
	 *
	 * @param to ��ֵ�˻��ĵ�ַ
	 * @param amount ��ֵ���
	 * @return ���ؽ��׹�ϣ
	 * @throws Exception
	 */
	public String recharge(String to, BigInteger amount) throws Exception {
		if (Strings.isEmpty(to)) {
			throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_EMPTY);
		}

		if (!AddressUtils.isValidAddress(to)) {
			throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
		}


		if (amount == null || amount.intValue() <= 0) {
			throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
		}

		return Web3jUtils.getCharge().recharge(to, amount).send().getTransactionHash();
	}

	/**
	 * ��ѯָ���˻�����
	 *
	 * @param accAddr ��ѯ���˻���ַ
	 * @return �����˻�����Ӧ��ҵ������
	 * @throws Exception
	 */
	public BigInteger balanceOf(String accAddr) throws Exception {
		if (Strings.isEmpty(accAddr)) {
			throw new DDCException(ErrorMessage.ACC_ADDR_IS_EMPTY);
		}

		if (!AddressUtils.isValidAddress(accAddr)) {
			throw new DDCException(ErrorMessage.ACC_ADDR_IS_NOT_ADDRESS_FORMAT);
		}

		return Web3jUtils.getCharge().balanceOf(accAddr).send();
	}


	/**
	 * Hex�ַ���תbyte
	 * @param inHex ��ת����Hex�ַ���
	 * @return  ת�����byte
	 */
	private byte hexToByte(String inHex){
		return (byte)Integer.parseInt(inHex,16);
	}

	/**
	 * hex�ַ���תbyte����
	 * @param inHex ��ת����Hex�ַ���
	 * @return  ת�����byte������
	 */
	private byte[] hexToByteArray(String inHex){
		int hexlen = inHex.length();
		byte[] result;
		if (hexlen % 2 == 1){
			hexlen++;
			result = new byte[(hexlen/2)];
			inHex="0"+inHex;
		}else {
			result = new byte[(hexlen/2)];
		}
		int j=0;
		for (int i = 0; i < hexlen; i+=2){
			result[j]=hexToByte(inHex.substring(i,i+2));
			j++;
		}
		return result;
	}

	/**
	 * ��ѯָ����DDCҵ�����߼���Լ�ķ�������Ӧ�ĵ���ҵ����á�
	 *
	 * @param ddcAddr DDCҵ�����߼���Լ��ַ
	 * @param sig Hex��ʽ�ĺ�Լ����ID
	 * @return ����DDC��Լҵ���
	 * @throws Exception
	 */
	public BigInteger queryFee(String ddcAddr, String sig) throws Exception {
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

		return Web3jUtils.getCharge().queryFee(ddcAddr, hexToByteArray(sig)).send();
	}

	/**
	 * ��Ӫ������Ϊ�Լ����˻�����ҵ��ѡ�
	 *
	 * @param amount ����Ӫ���˻����г�ֵ��ҵ���
	 * @return ���ؽ��׹�ϣ
	 * @throws Exception
	 */
	public String selfRecharge(BigInteger amount) throws Exception {
		if (amount == null || amount.intValue() <= 0) {
			throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
		}

		return Web3jUtils.getCharge().selfRecharge(amount).send().getTransactionHash();
	}

	/**
	 * ��Ӫ�����ýӿ�����ָ����DDC����Լ�ķ������÷��á�
	 *
	 * @param ddcAddr DDCҵ�����߼���Լ��ַ
	 * @param sig Hex��ʽ�ĺ�Լ����ID
	 * @param amount ҵ�����
	 * @return ���ؽ��׹�ϣ
	 * @throws Exception
	 */
	public String setFee(String ddcAddr, String sig, BigInteger amount) throws Exception {
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

		return Web3jUtils.getCharge().setFee(ddcAddr, hexToByteArray(sig), amount).send().getTransactionHash();
	}

	/**
	 * ��Ӫ�����ýӿ�ɾ��ָ����DDC����Լ�ķ������÷��á�
	 *
	 * @param ddcAddr DDCҵ�����߼���Լ��ַ
	 * @param sig Hex��ʽ�ĺ�Լ����ID
	 * @return ���ؽ��׹�ϣ
	 * @throws Exception
	 */
	public String delFee(String ddcAddr, String sig) throws Exception {
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

		return Web3jUtils.getCharge().deleteFee(ddcAddr, hexToByteArray(sig)).send().getTransactionHash();
	}

	/**
	 * ��Ӫ�����øýӿ�ɾ��ָ����DDCҵ�����߼���Լ��Ȩ��
	 *
	 * @param ddcAddr DDCҵ�����߼���Լ��ַ
	 * @return ���ؽ��׹�ϣ
	 * @throws Exception
	 */
	public String delDDC(String ddcAddr) throws Exception {
		if (Strings.isEmpty(ddcAddr)) {
			throw new DDCException(ErrorMessage.DDC_ADDR_IS_EMPTY);
		}

		if (!AddressUtils.isValidAddress(ddcAddr)) {
			throw new DDCException(ErrorMessage.DDC_ADDR_IS_NOT_ADDRESS_FORMAT);
		}

		return Web3jUtils.getCharge().deleteDDC(ddcAddr).send().getTransactionHash();
	}

}
