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
	 * 运营方、平台方调用该接口为所属同一方的同一级别账户或者下级账户充值；
	 *
	 * @param to 充值账户的地址
	 * @param amount 充值金额
	 * @return 返回交易哈希
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

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		return chargeLogic.recharge(to, amount).send().getTransactionHash();
	}

	/**
	 * 查询指定账户的余额。
	 *
	 * @param accAddr 查询的账户地址
	 * @return 返回账户所对应的业务费余额
	 * @throws Exception
	 */
	public BigInteger balanceOf(String accAddr) throws Exception {
		if (Strings.isEmpty(accAddr)) {
			throw new DDCException(ErrorMessage.ACC_ADDR_IS_EMPTY);
		}

		if (!AddressUtils.isValidAddress(accAddr)) {
			throw new DDCException(ErrorMessage.ACC_ADDR_IS_NOT_ADDRESS_FORMAT);
		}

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		return chargeLogic.balanceOf(accAddr).send();
	}

	/**
	 * 查询指定的DDC业务主逻辑合约的方法所对应的调用业务费用。
	 *
	 * @param ddcAddr DDC业务主逻辑合约地址
	 * @param sig Hex格式的合约方法ID
	 * @return 返回DDC合约业务费
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

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		return chargeLogic.queryFee(ddcAddr, sig.getBytes(sig)).send();
	}

	/**
	 * 运营方调用为自己的账户增加业务费。
	 *
	 * @param amount 对运营方账户进行充值的业务费
	 * @return 返回交易哈希
	 * @throws Exception
	 */
	public String selfRecharge(BigInteger amount) throws Exception {
		if (amount == null || amount.compareTo(BigInteger.valueOf(0L)) <= 0) {
			throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
		}

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		return chargeLogic.selfRecharge(amount).send().getTransactionHash();
	}

	/**
	 * 运营方调用接口设置指定的DDC主合约的方法调用费用。
	 *
	 * @param ddcAddr DDC业务主逻辑合约地址
	 * @param sig Hex格式的合约方法ID
	 * @param amount 业务费用
	 * @return 返回交易哈希
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

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		return chargeLogic.setFee(ddcAddr, sig.getBytes(sig), amount).send().getTransactionHash();
	}

	/**
	 * 运营方调用接口删除指定的DDC主合约的方法调用费用。
	 *
	 * @param ddcAddr DDC业务主逻辑合约地址
	 * @param sig Hex格式的合约方法ID
	 * @return 返回交易哈希
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

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		return chargeLogic.deleteFee(ddcAddr, sig.getBytes(sig)).send().getTransactionHash();
	}

	/**
	 * 运营方调用该接口删除指定的DDC业务主逻辑合约授权。
	 *
	 * @param ddcAddr DDC业务主逻辑合约地址
	 * @return 返回交易哈希
	 * @throws Exception
	 */
	public String delDDC(String ddcAddr) throws Exception {
		if (Strings.isEmpty(ddcAddr)) {
			throw new DDCException(ErrorMessage.DDC_ADDR_IS_EMPTY);
		}

		if (!AddressUtils.isValidAddress(ddcAddr)) {
			throw new DDCException(ErrorMessage.DDC_ADDR_IS_NOT_ADDRESS_FORMAT);
		}

		Web3jUtils web3jUtils = new Web3jUtils();
		ChargeLogic chargeLogic = web3jUtils.getCharge();

		return chargeLogic.deleteDDC(ddcAddr).send().getTransactionHash();
	}

}
