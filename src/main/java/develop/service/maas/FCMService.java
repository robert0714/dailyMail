package develop.service.maas;
  

import javax.annotation.PostConstruct; 
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory; 
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper; 

import develop.odata.fcm.reqest.FcmRequest;
import develop.odata.fcm.reqest.MaasMsgJob;
import develop.odata.fcm.reqest.Notification;
import develop.odata.fcm.response.FcmResponse;
import develop.service.maas.internal.SendMsgV02;
import develop.service.maas.internal.SendMsgV03; 

@Component
public class FCMService {
	/** The log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());
	protected RestTemplate restTemplate;

	protected String resourceUrl = "https://fcm.googleapis.com/fcm/send";

	@Value("${develop.odata.service.fcm.key:AAAAR87djjU:APA91bFWWwl3VYu_HbZUUSJgQKHbYFuBEryFmNm7V_-fsSq22YBshfFIKrhfNhWNVt6uaQwvrdsTCGmp7xR3MaSI5qw7NKizZ99BFqrMpTsNxpFXtiIwBWo_fWR5dri8ne6BRiFTQkkS}")
	protected String fcmKey;
	
	private ObjectMapper mapper = new ObjectMapper();

	@PostConstruct
	public void postConstruct() {
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier())
				.build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		//
		this.restTemplate = new RestTemplate(requestFactory);
	}

	public FcmResponse sendMsg(MaasMsgJob job) {
		FcmRequest sample = new FcmRequest();
		Notification notification = new Notification();

		notification.setTitle(job.getActivityName());
		notification.setBody(job.getContent());
		sample.setNotification(notification); 

		sample.setIds(job.getDeviceIds().values().toArray(new String[] {}));
		
		return sendMsg(sample);
	}

	public FcmResponse sendMsg(FcmRequest object) {		 
		return sendMsgV03(object);
	}

	/***
	 * 序列處理,blocking
	 * 
	 ***/
	public FcmResponse sendMsgV01(FcmRequest object) {
		log.info("fcm key: {} ", fcmKey);

		String key = "key=" + fcmKey;

		MultiValueMap<String, String> headerss = new LinkedMultiValueMap<String, String>();

		headerss.add(HttpHeaders.AUTHORIZATION, key);
		headerss.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<FcmRequest> request1 = new HttpEntity<FcmRequest>(object, headerss);

		FcmResponse answer = restTemplate.postForObject(resourceUrl, request1, FcmResponse.class);

		return answer;
	}
	/***
	 * 平行處理,non-blocking處理，有中斷等待設計
	 * 
	 ***/
	public FcmResponse sendMsgV02(FcmRequest object) {
		final SendMsgV02 implementation = new SendMsgV02(fcmKey, mapper);
		return implementation.sendMsg(object);
	}
	
	/***
	 * 平行處理,non-blocking處理，有非同步結果紀錄處理
	 * 
	 ***/
	public FcmResponse sendMsgV03(FcmRequest object) {
		final SendMsgV03 implementation = new SendMsgV03(fcmKey, mapper); 
		return implementation.sendMsg(object  );
	}
	public FcmResponse sendMsgV03(FcmRequest object,final EmptyCallback callback) {
		final SendMsgV03 implementation = new SendMsgV03(fcmKey, mapper); 
		return implementation.sendMsg(object,callback);
	}
 
}
