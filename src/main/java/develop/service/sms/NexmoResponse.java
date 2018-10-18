package develop.service.sms;

import java.util.List;

import com.google.gson.annotations.SerializedName;

//	{
//	  "message-count":"1",
//	  "messages":[
//	    {
//	    "status":"returnCode",
//	    "message-id":"messageId",
//	    "to":"to",
//	    "client-ref":"client-ref",
//	    "remaining-balance":"remaining-balance",
//	    "message-price":"message-price",
//	    "network":"network",
//	    "error-text":"error-message"
//	    }
//	  ]
//	}
public class NexmoResponse {
	@SerializedName("message-count")
	private String messageCount;
	
	private List<NexmoMessage> messages;

	public String getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(String messageCount) {
		this.messageCount = messageCount;
	}

	public List<NexmoMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<NexmoMessage> messages) {
		this.messages = messages;
	}
	
	
	
}
