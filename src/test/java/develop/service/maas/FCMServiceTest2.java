package develop.service.maas;
 

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths; 
 
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before; 
import org.junit.Test;
import org.junit.runner.RunWith; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner; 
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
 
import develop.odata.fcm.reqest.FcmRequest; 
 
@RunWith(SpringRunner.class)
@SpringBootTest 
public class FCMServiceTest2 {
	private String resourceUrl = "https://fcm.googleapis.com/fcm/send";
	private ObjectMapper mapper;
	private RestTemplate restTemplate;

	private CloseableHttpClient httpClient;
	
	@Value("${develop.odata.service.fcm.key:AAAAR87djjU:APA91bFWWwl3VYu_HbZUUSJgQKHbYFuBEryFmNm7V_-fsSq22YBshfFIKrhfNhWNVt6uaQwvrdsTCGmp7xR3MaSI5qw7NKizZ99BFqrMpTsNxpFXtiIwBWo_fWR5dri8ne6BRiFTQkkS}")
	private String fcmKey;

	@Before
	public void setUp() throws Exception {
		this.mapper = new ObjectMapper();
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier())
				.build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		//
		this.restTemplate = new RestTemplate(requestFactory); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendMsg() throws Exception {		 
		if(fcmKey==null) {
			fcmKey="AAAAR87djjU:APA91bFWWwl3VYu_HbZUUSJgQKHbYFuBEryFmNm7V_-fsSq22YBshfFIKrhfNhWNVt6uaQwvrdsTCGmp7xR3MaSI5qw7NKizZ99BFqrMpTsNxpFXtiIwBWo_fWR5dri8ne6BRiFTQkkS";
		}
		String key="key="+fcmKey;  		 
		String resource = "/fcm-request.json";
		String jsonInput = new String(Files.readAllBytes(Paths.get(getClass().getResource(resource).toURI())),StandardCharsets.UTF_8);
		FcmRequest object = this.mapper.readValue(jsonInput, FcmRequest.class);		
		
		MultiValueMap<String, String> headerss = new LinkedMultiValueMap<String, String>();
		headerss.add(HttpHeaders.AUTHORIZATION,key);
		headerss.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<FcmRequest> request1 = new HttpEntity<FcmRequest>(object, headerss);

		String answer = restTemplate.postForObject(resourceUrl, request1, String.class);
		
		System.out.println(answer);;
	}

}
