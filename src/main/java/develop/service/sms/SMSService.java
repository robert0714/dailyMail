package develop.service.sms;

import org.springframework.stereotype.Component;
 

@Component
public interface SMSService {
	public void send(String content, String tel) ;

	public String getSmsCode();

	/**
	 * 失敗回傳 null , 發送失敗是因為伺服器沒儲值
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	public void sendAuthSMS(String phone, String code) ;

	/**
	 * 店舖審核通過＆開通
	 * 
	 * @param phone
	 * @param storeName
	 * @param storeStatus
	 * @return
	 */
	public String notificationStoreStatus(String phone, String storeName, String storeStatus);

	/**
	 * 余额支付通知 内容：尊敬的${PHONE}用户，您订购的商品已经成功付款${MONEY}元，当前余额${BALANCE}元。我们将尽快为您发货，
	 * 到货后将会有社区服务人员联系您，请保持电话畅通，谢谢。
	 * ${PHONE}是手机号码，${MONEY}是本次付款的金额，${BALANCE}是当前账户的余额。
	 * 模板ID：smsTpl:596b5d2d-261e-47da-a839-49be55e713ac
	 * 
	 * @param phone
	 * @param storeName
	 * @param storeStatus
	 * @return
	 */
	public String notificationUserWalletPaymnetOfKiosk(String phone, String money, String balance);

	/**
	 * 支付宝/微信支付通知:
	 * 内容：尊敬的${PHONE}用户，您订购的商品已经成功付款${MONEY}元。我们将尽快为您发货，到货后将会有社区服务人员联系您，请保持电话畅通，
	 * 谢谢。 ${PHONE}是手机号码，${MONEY}是本次付款的金额
	 * 模板ID：smsTpl:730d1f51-8ff1-47b1-9d2c-e818341d1518
	 * 
	 * @param phone
	 * @param storeName
	 * @param storeStatus
	 * @return
	 */
	public String notificationSacnPaymentOfKiosk(String phone, String money);

}
