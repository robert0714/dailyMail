package develop.odata.fcm.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FcmResponse {

	@JsonProperty("multicast_id")
	private Long  multicastId;
	
	@JsonProperty("canonical_ids")
	private Long  canonicalIds;
	
	@JsonProperty("success")
	private Integer  success;
	
	@JsonProperty("failure")
	private Integer  failure;
	
	@JsonProperty("results")
	private Result[] results;

	public Long getMulticastId() {
		return multicastId;
	}

	public void setMulticastId(Long multicastId) {
		this.multicastId = multicastId;
	}

	public Long getCanonicalIds() {
		return canonicalIds;
	}

	public void setCanonicalIds(Long canonicalIds) {
		this.canonicalIds = canonicalIds;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public Integer getFailure() {
		return failure;
	}

	public void setFailure(Integer failure) {
		this.failure = failure;
	}

	public Result[] getResults() {
		return results;
	}

	public void setResults(Result[] results) {
		this.results = results;
	}
	
}
