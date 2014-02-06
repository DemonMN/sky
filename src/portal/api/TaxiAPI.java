package portal.api;

import java.util.List;

import portal.db.DBAccess;
import portal.facebook.FacebookUser;
import portal.users.FacadeUser;
import taxi.model.Trip;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;


@Api(name = "trip")
public class TaxiAPI {

	private static FacadeUser facadeUser = new FacadeUser();
	
	@ApiMethod(name = "all")
	public List<Trip> allTrip(@Named("token")String token) {
		facadeUser.getUser(token);
		List<Trip> trips = DBAccess.findFilter(Trip.class, "key desc", "");
		for (Trip trip : trips) {
			FacebookUser user = facadeUser.getUserByKey(trip.getUser().getId());
			trip.setFbUser(user);
		}
		return trips;
	}
	
	@ApiMethod(name = "save")
	public Trip save(@Named("token")String token, Trip trip) {
		FacebookUser user = facadeUser.getUser(token);
		trip.setUser(user.getKey());
		return DBAccess.save(trip);
	}
	
	@ApiMethod(name = "update")
	public Trip updateQuestion(@Named("token")String token, Trip trip) {
		FacebookUser user = facadeUser.getUser(token);
		trip.setUser(user.getKey());
		trip = DBAccess.update(trip);
		return trip;
	}
	
	@ApiMethod(name = "my")
	public List<Trip> myTrips(@Named("token")String token) {
		FacebookUser me = facadeUser.getUser(token);
		List<Trip> trips = DBAccess.findFilter(Trip.class, "key desc", "user = " + me.getKey().getId());
		for (Trip trip : trips) {
			FacebookUser user = facadeUser.getUserById(trip.getUser().getId() + "");
			trip.setFbUser(user);
		}
		return trips;
	}
}

