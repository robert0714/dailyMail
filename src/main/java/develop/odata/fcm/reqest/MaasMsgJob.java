package develop.odata.fcm.reqest;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
 

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaasMsgJob implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8109781732434027192L;

	private String msgProfileId;

	private String content;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm", locale = "zh", timezone = "GMT+8")
	private Date notifyDate;

	private String type;

	private String activityName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
	private Date startActivityDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
	private Date endActivityDate;
	
	private Map<Integer, String> deviceIds;

	private Integer groupId;
	
	public String getMsgProfileId() {
		return msgProfileId;
	}

	public void setMsgProfileId(String msgProfileId) {
		this.msgProfileId = msgProfileId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}
 

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getStartActivityDate() {
		return startActivityDate;
	}

	public void setStartActivityDate(Date startActivityDate) {
		this.startActivityDate = startActivityDate;
	}

	public Date getEndActivityDate() {
		return endActivityDate;
	}

	public void setEndActivityDate(Date endActivityDate) {
		this.endActivityDate = endActivityDate;
	}

	public Map<Integer, String> getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(Map<Integer, String> deviceIds) {
		this.deviceIds = deviceIds;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	
	
}

