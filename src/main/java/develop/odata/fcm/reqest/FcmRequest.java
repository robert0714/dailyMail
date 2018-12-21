package develop.odata.fcm.reqest;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class FcmRequest.<br/>
 * 根據勤崴國際信件資訊<br/>
 * From: 康宇宣 <yskang@kingwaytek.com> <br/>
 * Sent: Friday, December 7, 2018 1:54 PM <br/>
 * Hi 羿稚,<br/>
 * 有關FCM推播的格式，因為中華要求收到推播後要做客製化的動作，<br/>
 * 目前雙平台的格式會長的不太一樣，請依照 Android / iOS 裝置分開推送，範例如下:<br/>
 * <h1>訊息中心(系統訊息/個人訊息)</h1>
 * <h2>[Android]</h2>
 * 
 * <pre>
 * {
 *    "registration_ids": ["fYc68xfTHOU:APA91bHe5YvOy3RWN5qrPMdCJ1ymI0q08vGGb-p4Km3XLabkLk24DeI6Jc8PJ5TBj1HCZnBeMwtoZ6TxxP3U8UPvtUv5TNSxgfFNpyDpweQqvgeNwTq1nGkJMErbMTyY49EW6ieSvl_L"],
 *    "data": {
 *       "title": "推播訊息的 Title",
 *       "message":"推播訊息的 Body",
 *       "isSystemMessage": true
 *     }
 * }
 * </pre>
 * 
 * <h2>[iOS]</h2>
 * 
 * <pre>
 * {
 *    "registration_ids": ["fYc68xfTHOU:APA91bHe5YvOy3RWN5qrPMdCJ1ymI0q08vGGb-p4Km3XLabkLk24DeI6Jc8PJ5TBj1HCZnBeMwtoZ6TxxP3U8UPvtUv5TNSxgfFNpyDpweQqvgeNwTq1nGkJMErbMTyY49EW6ieSvl_L"],
 *    "data": {
 *       "isSystemMessage": true
 *    }
 *    "notification": {
 *         "title": "推播訊息的 Title",
 *         "body": "推播訊息的 Body"
 *    }
 * }
 * </pre>
 * 
 * <h1>事件訊息</h1>
 * <h2>[Android]</h2>
 * 
 * <pre>
 * {
 *    "registration_ids": ["fYc68xfTHOU:APA91bHe5YvOy3RWN5qrPMdCJ1ymI0q08vGGb-p4Km3XLabkLk24DeI6Jc8PJ5TBj1HCZnBeMwtoZ6TxxP3U8UPvtUv5TNSxgfFNpyDpweQqvgeNwTq1nGkJMErbMTyY49EW6ieSvl_L"],
 *    "data": {
 *       "title": "推播訊息的 Title",
 *       "message":"推播訊息的 Body \n\n請至「隨行秘書」確認行程是否受到影響",
 *       "planId": "123456789"
 *     }
 * }
 * </pre>
 * 
 * <h2>[iOS]</h2>
 * 
 * <pre>
 * {
 *    "registration_ids": ["fYc68xfTHOU:APA91bHe5YvOy3RWN5qrPMdCJ1ymI0q08vGGb-p4Km3XLabkLk24DeI6Jc8PJ5TBj1HCZnBeMwtoZ6TxxP3U8UPvtUv5TNSxgfFNpyDpweQqvgeNwTq1nGkJMErbMTyY49EW6ieSvl_L"],
 *    "data": {
 *       "planId": "1104036413898149545461539325624651"
 *    }
 *    "notification": {
 *         "title": "推播訊息的 Title",
 *         "body": "推播訊息的 Body \n\n請至「隨行秘書」確認行程是否受到影響"
 *    }
 * }
 * </pre>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FcmRequest implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5204258618230126147L;

	/** The account id. */
	@JsonProperty("account_id")
	private String accountId;

	/** The ids. */
	@JsonProperty("registration_ids")
	private String[] ids;

	/** The notification. */
	@JsonProperty("notification")
	private Notification notification;

	/** The data. */
	@JsonProperty("data")
	private Data data;

	/** The send date. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@JsonProperty("send_date")
	private Date sendDate;

	/**
	 * Gets the ids.
	 *
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}

	/**
	 * Sets the ids.
	 *
	 * @param ids the new ids
	 */
	public void setIds(String... ids) {
		this.ids = ids;
	}

	/**
	 * Gets the notification.
	 *
	 * @return the notification
	 */
	public Notification getNotification() {
		return notification;
	}

	/**
	 * Sets the notification.
	 *
	 * @param notification the new notification
	 */
	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	/**
	 * Gets the send date.
	 *
	 * @return the send date
	 */
	public Date getSendDate() {
		return sendDate;
	}

	/**
	 * Sets the send date.
	 *
	 * @param sendDate the new send date
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * Gets the account id.
	 *
	 * @return the account id
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * Sets the account id.
	 *
	 * @param accountId the new account id
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Data getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Data data) {
		this.data = data;
	}

}
