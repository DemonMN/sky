package portal.api;

import util.Message;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;

@Api(name = "photo")
public class PhotoAPI {
	private final GcsService gcsService = GcsServiceFactory
			.createGcsService(new RetryParams.Builder()
			.initialRetryDelayMillis(10).retryMaxAttempts(10)
			.totalRetryPeriodMillis(15000).build());
	@ApiMethod(name = "user.get")
	public Message getUser(@Named("token") String token) {
		
		return new Message().addField("message", "success");
	}

	@ApiMethod(name = "user.logout")
	public Message logout(@Named("token") String token) {
		return new Message().addField("message", "success");
	}

	@ApiMethod(name = "user.login")
	public Message login(@Named("token") String token) {
		return new Message().addField("message", "success");
	}
}
