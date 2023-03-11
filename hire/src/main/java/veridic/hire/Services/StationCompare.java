package veridic.hire.Services;

import java.util.Comparator;

import veridic.hire.Models.ChargingStation;


public class StationCompare implements Comparator<ChargingStation>{
	private static final int STATION_ID = 1;
	private static final int STATION_NAME = 2;
	private static final int STATION_PRICING = 3;
	private static final int STATION_ADDRESS = 4;
	private static boolean ASC = true;
	
	private int column;
	
	public StationCompare(String column, String order) {
		if(column.equalsIgnoreCase("Station_id")) {
			this.column = 1;
		}else if(column.equalsIgnoreCase("Station_name")) {
			this.column = 2;
		}else if(column.equalsIgnoreCase("Station_pricing")) {
			this.column = 3;
		}else if(column.equalsIgnoreCase("Station_address")) {
			this.column = 4;
		}
		
		if(order.equalsIgnoreCase("ASC")) {
			this.ASC = true;
		}else {
			this.ASC = false;
		}
	}

	@Override
	public int compare(ChargingStation o1, ChargingStation o2) {
		switch(column) {
			case STATION_ID:
				if(ASC) return o1.getStation_id()-o2.getStation_id();
				return o2.getStation_id()-o1.getStation_id();
			case STATION_NAME:
				if(ASC) return o1.getStation_name().compareTo(o2.getStation_name());
				return o2.getStation_name().compareTo(o1.getStation_name());
			case STATION_ADDRESS:
				if(ASC) return o1.getStation_address().compareTo(o2.getStation_address());
				return o2.getStation_address().compareTo(o1.getStation_address());
			case STATION_PRICING:
				if(ASC) return o1.getStation_pricing()-o2.getStation_pricing();
				return o2.getStation_pricing()-o1.getStation_pricing();
			default:
				return 0;
		}
	}

}
