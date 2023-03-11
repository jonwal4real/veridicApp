package veridic.hire.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "ChargingStation")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class ChargingStation implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int station_id;
	private String station_name;
	private String station_image;
	private int station_pricing;
	private String station_address;
	
	public int getStation_id() {
		return station_id;
	}
	public void setStation_id(int station_id) {
		this.station_id = station_id;
	}
	public String getStation_name() {
		return station_name;
	}
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
	public String getStation_image() {
		return station_image;
	}
	public void setStation_image(String station_image) {
		this.station_image = station_image;
	}
	public int getStation_pricing() {
		return station_pricing;
	}
	public void setStation_pricing(int station_pricing) {
		this.station_pricing = station_pricing;
	}
	public String getStation_address() {
		return station_address;
	}
	public void setStation_address(String station_address) {
		this.station_address = station_address;
	}
	@Override
	public String toString() {
		return "ChargingStations [station_id=" + station_id + ", station_name=" + station_name + ", station_image="
				+ station_image + ", station_pricing=" + station_pricing + ", station_address=" + station_address + "]";
	}
	
	
}
