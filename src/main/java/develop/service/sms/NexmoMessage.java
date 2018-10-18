package develop.service.sms;

import com.google.gson.annotations.SerializedName;

//"messages":[
//{
//"status":"returnCode",
//"message-id":"messageId",
//"to":"to",
//"client-ref":"client-ref",
//"remaining-balance":"remaining-balance",
//"message-price":"message-price",
//"network":"network",
//"error-text":"error-message"
//}
//]
public class NexmoMessage {
	private String status;
	@SerializedName("message-id")
	private String messageId;
	private String to;
	@SerializedName("client-ref")
	private String clientRef;
	@SerializedName("remaining-balance")
	private String remainingBalance;
	@SerializedName("message-price")
	private String messagePrice;
	@SerializedName("error-text")
	private String errorText;
	private String network;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getClientRef() {
		return clientRef;
	}
	public void setClientRef(String clientRef) {
		this.clientRef = clientRef;
	}
	public String getRemainingBalance() {
		return remainingBalance;
	}
	public void setRemainingBalance(String remainingBalance) {
		this.remainingBalance = remainingBalance;
	}
	public String getMessagePrice() {
		return messagePrice;
	}
	public void setMessagePrice(String messagePrice) {
		this.messagePrice = messagePrice;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	
	
	
}
