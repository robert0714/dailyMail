package develop.service.maas.internal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper; 

import develop.odata.fcm.reqest.Data;
import develop.odata.fcm.reqest.FcmRequest;
import develop.odata.fcm.reqest.Notification;
import develop.odata.fcm.response.FcmResponse;
import develop.service.maas.EmptyCallback;
import develop.service.maas.Message;
import develop.service.maas.Sender; 

public class SendMsgV03 {
	protected String fcmKey;

	private ObjectMapper mapper;

	/** The log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public SendMsgV03(final String fcmKey, final ObjectMapper mapper) {
		super();
		this.fcmKey = fcmKey;
		this.mapper = mapper;
	}

	public FcmResponse sendMsg(FcmRequest object) {
		final EmptyCallback callback = new EmptyCallback(mapper);		 
		return sendMsg(object, callback);
	}
	
	public FcmResponse sendMsg(FcmRequest object,EmptyCallback callback) {
		log.info("fcm server  key: {} ", fcmKey);
		Sender fcm = new Sender(fcmKey);
		Message msg = buildMessage(object);
		// send the message
		fcm.send(msg, callback);
		final FcmResponse answer = callback.getObject();
		return answer;
	}
	
	protected Message buildMessage(FcmRequest object) {
		final Message msg = new Message.MessageBuilder().addRegistrationToken(Arrays.asList(object.getIds())).build();

		final Notification notification = object.getNotification();
		if (notification != null) {
			develop.service.maas.Notification note = new develop.service.maas.Notification(notification.getTitle(),
					notification.getBody());
			note.setIcon("icon");
			note.setSound("sound");
			msg.setNotification(note);
		}
		final Data requestData = object.getData();
		if (requestData != null) {
			msg.setData(new HashMap<String, Object>());
			if (StringUtils.isNotBlank(requestData.getTitle())) {
				msg.getData().put("title", requestData.getTitle());
			}
			if (StringUtils.isNotBlank(requestData.getMessage())) {
				msg.getData().put("message", requestData.getMessage());
			}
			if (StringUtils.isNotBlank(requestData.getMessage())) {
				msg.getData().put("isSystemMessage", requestData.isSystemMessage());
			}
			if (StringUtils.isNotBlank(requestData.getPlanId())) {
				msg.getData().put("planId", requestData.getPlanId());
			}
		}
		return msg;
	}
}
