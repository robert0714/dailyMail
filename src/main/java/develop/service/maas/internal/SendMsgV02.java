package develop.service.maas.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import develop.odata.fcm.reqest.FcmRequest;
import develop.odata.fcm.reqest.Notification;
import develop.odata.fcm.response.FcmResponse;
import develop.service.maas.EmptyCallback;
import develop.service.maas.Message;
import develop.service.maas.Sender; 

public class SendMsgV02 {
	protected String fcmKey;

	private ObjectMapper mapper;

	/** The log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public SendMsgV02(final String fcmKey, final ObjectMapper mapper) {
		super();
		this.fcmKey = fcmKey;
		this.mapper = mapper;
	}

	public FcmResponse sendMsg(FcmRequest object) {
		log.info("fcm server  key: {} ", fcmKey);

		Sender fcm = new Sender(fcmKey);

		// build multiple tokens ids
		List<String> registrationIds = new ArrayList<>();
		for (String id : object.getIds()) {
			registrationIds.add(id);
		}

		final EmptyCallback callback = new EmptyCallback(mapper);
		final Notification notification = object.getNotification();

		int idNumbers = object.getIds().length;

		for (int i = 0; i < idNumbers; ++i) {

			String fcmToken = object.getIds()[i];
			Message msg = buildMessage(notification.getTitle(), notification.getBody(), fcmToken);

			log.info("mobile device fcm Token : {} ", fcmToken);

			// send the message
			fcm.send(msg, callback);

			try {
				Thread.sleep(RandomUtils.nextInt(0,10) * 100l);
			} catch (InterruptedException e) {
				log.error(e.getMessage(), e);
				;
			}
		}
		final FcmResponse answer = callback.getObject();
		return answer;
	}
	protected Message buildMessage(final String title,final String content,final String...  fcmToken) {
		develop.service.maas.Notification note = new develop.service.maas.Notification(title, content);
		note.setIcon("icon");
		note.setSound("sound"); 
//		Message msg = new Message.MessageBuilder().addRegistrationToken(fcmToken) // add array
//				.notification(note).build();
		
		//勤威科技的工程師(Brack Chang) 表示他需要將payload格式的notification換成data格式,才能將背景的app喚醒
		Message msg = new Message.MessageBuilder()
    			.addRegistrationToken(Arrays.asList(fcmToken)) // add array 
        	    .notification(note) 
//        	    .addData("title", title)
//        	    .addData("message", content)
//        	    .addData("icon", "icon")
//        	    .addData("sound", "sound")
        	    .build();
		//2018.12.11發現iOS系統要用Notification才能收到fcm訊息，據說勤威科技要用data tag目的是因為超連結功能,所以建議是如果content有超連結才轉換成使用data tag
		return msg ;
	}
}
