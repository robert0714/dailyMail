package develop.service.maas;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import develop.odata.fcm.response.FcmResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmptyCallback implements Callback<JsonObject> {
	/** The log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private ObjectMapper mapper;
	 
	private String[] ids;

	private FcmResponse object = null;

	public FcmResponse getObject() {
		return object;
	}

	public EmptyCallback(ObjectMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	@Override
	public void onFailure(Call<JsonObject> call, Throwable t) {
		t.printStackTrace();
	}

	@Override
	public void onResponse(final Call<JsonObject> call, final Response<JsonObject> response) { 
		if (ids != null  ) {
			log.info("fcm device_ids :{} ",ToStringBuilder.reflectionToString(ids, ToStringStyle.SIMPLE_STYLE));
		}
		if (response != null && response.body() != null) {
			log.info(response.body().toString() );
		}

		if (response == null) {
			return;
		}
		if (response.body() == null) {
			return;
		}
		final String msg = response.body().toString();

		try {
			object = mapper.readValue(msg, FcmResponse.class);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
