package develop.service.sms;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Component;
 

@Component(value = "hinet")
public class SMSServiceHiNetImpl implements SMSService {
	private static Logger logger = LoggerFactory.getLogger(SMSServiceHiNetImpl.class);
	
	@Value("${service.sms.hinet.account}")
	private String ACCOUNT;

	@Value("${service.sms.hinet.password}")
	private String PASSWORD;

	@Value("${service.sms.hinet.server}")
	private String SERVER;

	@Value("${service.sms.hinet.port}")
	private Integer SERVER_PORT;

	public String getSmsCode() {
		return RandomStringUtils.randomNumeric(6);
	}

	public void send(String content, String tel)  {
		try {
			String message = new String(content.getBytes(), "utf-8"); // 簡訊內容

			// ----建立連線 and 檢查帳號密碼是否錯誤
			HiNetSms mysms = new HiNetSms();
			int k = mysms.create_conn(SERVER, SERVER_PORT, ACCOUNT, PASSWORD);
			if (k == 0) {

				System.out.println("帳號密碼check ok!");
			} else {
				// 結束連線
				mysms.close_conn();
				throw new Exception(String.format("hinet sms account & password fail msg:%s", mysms.get_message()));
			}

			k = mysms.send_text_message(tel, message);
			if (k != 0) {
				// 結束連線
				mysms.close_conn();

				throw new Exception(String.format("hinet sms send fail code:%s,msg:%s", k, mysms.get_message()));
			}

			// 結束連線
			mysms.close_conn();

		} catch (Exception e) {
			logger.error(e.getMessage() ,e);
			
			throw new  RuntimeException (String.format("hinet sms send IO Error,msg:%s", e.getMessage()));
		}

	}

	/**
	 * 失敗回傳 null , 發送失敗是因為伺服器沒儲值
	 * 
	 * @param phone
	 * @param code
	 * @return
	 * @throws DevelopException 
	 */
	public void sendAuthSMS(String phone, String code)   {
		String msg = String.format("您好，歡迎下載UMAJI APP，您的手機註冊認證碼：%s，請於5分鐘內於APP畫面輸入此認證碼以完成會員註冊，謝謝。", code);
		this.send(msg, phone);
	}

	/**
	 * 店舖審核通過＆開通
	 * 
	 * @param phone
	 * @param storeName
	 * @param storeStatus
	 * @return
	 */
	public String notificationStoreStatus(String phone, String storeName, String storeStatus) {
		// System.out.println(String.format("%s,%s,%s", ACCESS_KEY_ID,
		// SECRET_ACCESS_KEY, ENDPOINT));
		//
		// SmsClientConfiguration config = new SmsClientConfiguration();
		// config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID,
		// SECRET_ACCESS_KEY));
		// config.setEndpoint(ENDPOINT);
		// SmsClient client = new SmsClient(config);
		//
		// SendMessageRequest request = new SendMessageRequest();
		// request.setTemplateId("smsTpl:adf578d6-8e65-4f5f-8d57-bfcc66d4cb62");
		// request.setContentVar(
		// String.format("{\"store-name\" : \"%s\" , \"store-status\":\"%s\"}",
		// storeName, storeStatus));
		// request.setReceiver(Arrays.asList(new String[] { phone }));// 设置接收人列表
		//
		// SendMessageResponse sendResponse = client.sendMessage(request);//
		// 请求Server
		//
		// return sendResponse.getMessageId();
		return null;
	}

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
	public String notificationUserWalletPaymnetOfKiosk(String phone, String money, String balance) {
		// System.out.println(String.format("%s,%s,%s", ACCESS_KEY_ID,
		// SECRET_ACCESS_KEY, ENDPOINT));
		//
		// SmsClientConfiguration config = new SmsClientConfiguration();
		// config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID,
		// SECRET_ACCESS_KEY));
		// config.setEndpoint(ENDPOINT);
		// SmsClient client = new SmsClient(config);
		//
		// SendMessageRequest request = new SendMessageRequest();
		// request.setTemplateId("smsTpl:596b5d2d-261e-47da-a839-49be55e713ac");
		// request.setContentVar(
		// String.format("{\"PHONE\" : \"%s\" , \"MONEY\":\"%s\",
		// \"BALANCE\":\"%s\"}", phone, money, balance));
		// request.setReceiver(Arrays.asList(new String[] { phone }));// 设置接收人列表
		//
		// SendMessageResponse sendResponse = client.sendMessage(request);//
		// 请求Server
		//
		// return sendResponse.getMessageId();
		return null;
	}

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
	public String notificationSacnPaymentOfKiosk(String phone, String money) {
		// System.out.println(String.format("%s,%s,%s", ACCESS_KEY_ID,
		// SECRET_ACCESS_KEY, ENDPOINT));
		//
		// SmsClientConfiguration config = new SmsClientConfiguration();
		// config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID,
		// SECRET_ACCESS_KEY));
		// config.setEndpoint(ENDPOINT);
		// SmsClient client = new SmsClient(config);
		//
		// SendMessageRequest request = new SendMessageRequest();
		// request.setTemplateId("smsTpl:730d1f51-8ff1-47b1-9d2c-e818341d1518");
		// request.setContentVar(String.format("{\"PHONE\" : \"%s\" ,
		// \"MONEY\":\"%s\"}", phone, money));
		// request.setReceiver(Arrays.asList(new String[] { phone }));// 设置接收人列表
		//
		// SendMessageResponse sendResponse = client.sendMessage(request);//
		// 请求Server
		//
		// return sendResponse.getMessageId();
		return null;
	}

}
