package portal.api;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Named;

import util.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

@Api(name = "auction")
public class AuctionAPI {
	private static final ChannelService channelService = ChannelServiceFactory.getChannelService();
	private static String channelKey;
	private AtomicLong price = new AtomicLong(0L);
	static{
		channelKey = channelService.createChannel("auction");
	}
	@ApiMethod(name = "create", httpMethod = "POST")
	public Message createArticle(@Named("token") String token) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			price.addAndGet(100L);
			json = mapper.writeValueAsString(price);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		channelService.sendMessage(new ChannelMessage(channelKey, new Date() + " - " + json));
		return new Message().addField("message", "success").addField("time", new Date());
	}
	
	@ApiMethod(name = "channel")
	public Message getChannelKey() {
		return new Message().addField("token", channelKey);
	}
}
