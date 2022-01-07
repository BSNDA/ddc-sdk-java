package ai.bianjie.ddc.service;

import ai.bianjie.ddc.constant.ErrorMessage;
import ai.bianjie.ddc.contract.ChargeLogic;
import ai.bianjie.ddc.exception.DDCException;
import ai.bianjie.ddc.listener.SignEventListener;
import ai.bianjie.ddc.util.AddressUtils;
import ai.bianjie.ddc.util.HexUtils;
import ai.bianjie.ddc.util.Web3jUtils;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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

		if (amount == null || amount.compareTo(BigInteger.valueOf(0L)) <= 0) {
			throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
		}

		if(signEventListener == null) {
			throw new DDCException(ErrorMessage.NO_SIGN_EVENT_LISTNER);
		}

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		String hash = chargeLogic.recharge(to, amount).send().getTransactionHash();

		return hash;
	}

	/**
	 * ��ѯָ���˻�����
	 *
	 * @param accAddr ��ѯ���˻���ַ
	 * @return �����˻�����Ӧ��ҵ������
	 * @throws Exception
	 */
	public String balanceOf(String accAddr) throws Exception {
		if (Strings.isEmpty(accAddr)) {
			throw new DDCException(ErrorMessage.ACC_ADDR_IS_EMPTY);
		}

		if (!AddressUtils.isValidAddress(accAddr)) {
			throw new DDCException(ErrorMessage.ACC_ADDR_IS_NOT_ADDRESS_FORMAT);
		}

//		if(signEventListener == null) {
//			throw new DDCException(ErrorMessage.NO_SIGN_EVENT_LISTNER);
//		}

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		return chargeLogic.balanceOf(accAddr).send().toString();
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

		if(signEventListener == null) {
			throw new DDCException(ErrorMessage.NO_SIGN_EVENT_LISTNER);
		}

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		return chargeLogic.queryFee(ddcAddr, sig.getBytes(sig)).send();
	}

	/**
	 * ��Ӫ������Ϊ�Լ����˻�����ҵ��ѡ�
	 *
	 * @param amount ����Ӫ���˻����г�ֵ��ҵ���
	 * @return ���ؽ��׹�ϣ
	 * @throws Exception
	 */
	public String selfRecharge(BigInteger amount) throws Exception {
		if (amount == null || amount.compareTo(BigInteger.valueOf(0L)) <= 0) {
			throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
		}

		if(signEventListener == null) {
			throw new DDCException(ErrorMessage.NO_SIGN_EVENT_LISTNER);
		}

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		TransactionReceipt txReceipt = chargeLogic.selfRecharge(amount).send();
		resultCheck(txReceipt);
		return txReceipt.getTransactionHash();
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

		if (amount == null) {
			throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
		}

		if (amount == null || amount.compareTo(BigInteger.valueOf(0L)) < 0) {
			throw new DDCException(ErrorMessage.AMOUNT_LT_ZERO);
		}

		if(signEventListener == null) {
			throw new DDCException(ErrorMessage.NO_SIGN_EVENT_LISTNER);
		}

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		TransactionReceipt txReceipt = chargeLogic.setFee(ddcAddr, sig.getBytes(sig), amount).send();
		resultCheck(txReceipt);
		return txReceipt.getTransactionHash();
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

		if(signEventListener == null) {
			throw new DDCException(ErrorMessage.NO_SIGN_EVENT_LISTNER);
		}

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		TransactionReceipt txReceipt = chargeLogic.deleteFee(ddcAddr, sig.getBytes(sig)).send();
		resultCheck(txReceipt);
		return txReceipt.getTransactionHash();
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

		if(signEventListener == null) {
			throw new DDCException(ErrorMessage.NO_SIGN_EVENT_LISTNER);
		}

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		TransactionReceipt txReceipt = chargeLogic.deleteDDC(ddcAddr).send();
		resultCheck(txReceipt);
		return txReceipt.getTransactionHash();
	}

}
