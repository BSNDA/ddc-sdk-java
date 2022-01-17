package ai.bianjie.ddc.listener;

import org.web3j.crypto.RawTransaction;

public interface SignEventListener {
	
	/**
	 * 交易签名事件，SDK将调用此事件的具体实现来签名所有发送到链上的交易
	 * 
	 * 
	 * @param rawTransaction 签名事件参数
	 * @return 返回签名交易串
	 */
	public String signEvent(String sender,RawTransaction rawTransaction);

}
