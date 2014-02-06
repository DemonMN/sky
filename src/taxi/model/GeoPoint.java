package taxi.model;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class GeoPoint {
	private double e;
	private double d;
	public double getE() {
		return e;
	}
	public void setE(double e) {
		this.e = e;
	}
	public double getD() {
		return d;
	}
	public void setD(double d) {
		this.d = d;
	}
}
