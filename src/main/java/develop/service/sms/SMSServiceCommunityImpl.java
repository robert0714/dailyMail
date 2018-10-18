package develop.service.sms;

import java.util.Arrays;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.SendMessageRequest;
import com.baidubce.services.sms.model.SendMessageResponse;
 

@Component(value = "community")
public class SMSServiceCommunityImpl implements SMSService {

	@Value("${service.sms.access-key-id}")
	private String ACCESS_KEY_ID;

	@Value("${service.sms.secret-access-key}")
	private String SECRET_ACCESS_KEY;

	@Value("${service.sms.endpoint}")
	private String ENDPOINT;

	@Value("${service.sms.template-id}")
	private String templateId;

	public String getSmsCode() {
		return RandomStringUtils.randomNumeric(6);
	}

	public void send(String content, String tel)  {
	};

	/**
	 * 失敗回傳 null , 發送失敗是因為伺服器沒儲值
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	public void sendAuthSMS(String phone, String code) {

		// 初始化一个SmsClient
		SmsClientConfiguration config = new SmsClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
		config.setEndpoint(ENDPOINT);
		SmsClient client = new SmsClient(config);

		// ListTemplateResponse response = client.listTemplate(new
		// SmsRequest());
		//
		// for(GetTemplateDetailResponse template : response.getTemplateList()){
		// System.out.println(template.getTemplateId());// 打印模板ID
		// }
		// smsTpl:10703271-401a-443f-920c-098be7eb2512
		// smsTpl:e7476122a1c24e37b3b0de19d04ae900

		SendMessageRequest request = new SendMessageRequest();
		request.setTemplateId("smsTpl:e7476122a1c24e37b3b0de19d04ae900");
		request.setContentVar(String.format("{\"code\" : \"%s\"}", code));
		request.setReceiver(Arrays.asList(new String[] { phone }));// 设置接收人列表

		SendMessageResponse sendResponse = client.sendMessage(request);// 请求Server

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
		System.out.println(String.format("%s,%s,%s", ACCESS_KEY_ID, SECRET_ACCESS_KEY, ENDPOINT));

		SmsClientConfiguration config = new SmsClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
		config.setEndpoint(ENDPOINT);
		SmsClient client = new SmsClient(config);

		SendMessageRequest request = new SendMessageRequest();
		request.setTemplateId("smsTpl:adf578d6-8e65-4f5f-8d57-bfcc66d4cb62");
		request.setContentVar(
				String.format("{\"store-name\" : \"%s\" , \"store-status\":\"%s\"}", storeName, storeStatus));
		request.setReceiver(Arrays.asList(new String[] { phone }));// 设置接收人列表

		SendMessageResponse sendResponse = client.sendMessage(request);// 请求Server

		return sendResponse.getMessageId();
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
		System.out.println(String.format("%s,%s,%s", ACCESS_KEY_ID, SECRET_ACCESS_KEY, ENDPOINT));

		SmsClientConfiguration config = new SmsClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
		config.setEndpoint(ENDPOINT);
		SmsClient client = new SmsClient(config);

		SendMessageRequest request = new SendMessageRequest();
		request.setTemplateId("smsTpl:596b5d2d-261e-47da-a839-49be55e713ac");
		request.setContentVar(
				String.format("{\"PHONE\" : \"%s\" , \"MONEY\":\"%s\", \"BALANCE\":\"%s\"}", phone, money, balance));
		request.setReceiver(Arrays.asList(new String[] { phone }));// 设置接收人列表

		SendMessageResponse sendResponse = client.sendMessage(request);// 请求Server

		return sendResponse.getMessageId();
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
		System.out.println(String.format("%s,%s,%s", ACCESS_KEY_ID, SECRET_ACCESS_KEY, ENDPOINT));

		SmsClientConfiguration config = new SmsClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
		config.setEndpoint(ENDPOINT);
		SmsClient client = new SmsClient(config);

		SendMessageRequest request = new SendMessageRequest();
		request.setTemplateId("smsTpl:730d1f51-8ff1-47b1-9d2c-e818341d1518");
		request.setContentVar(String.format("{\"PHONE\" : \"%s\" , \"MONEY\":\"%s\"}", phone, money));
		request.setReceiver(Arrays.asList(new String[] { phone }));// 设置接收人列表

		SendMessageResponse sendResponse = client.sendMessage(request);// 请求Server

		return sendResponse.getMessageId();
	}

}
