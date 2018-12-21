package develop.odata.fcm.reqest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

	@JsonProperty("title")
	private String title;

	@JsonProperty("message")
	private String message;

	@JsonProperty("planId")
	private String planId;
	
	@JsonProperty("isSystemMessage")
	private boolean isSystemMessage ;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public boolean isSystemMessage() {
		return isSystemMessage;
	}

	public void setSystemMessage(boolean isSystemMessage) {
		this.isSystemMessage = isSystemMessage;
	}

}
