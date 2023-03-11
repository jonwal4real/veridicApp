package veridic.hire.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import veridic.hire.Models.ChargingStation;
import veridic.hire.Services.StationService;

@RestController
@CrossOrigin("http://localhost:3000")
public class MainController {
	
	@Autowired
	StationService stationService;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ResponseEntity<String> getAllStations(@RequestParam Map<String, String> customQuery) {
		if(customQuery.containsKey("limit")) {
			int limit = Integer.parseInt(customQuery.get("limit"));
			return stationService.getAllStations(limit);
		}else if(customQuery.containsKey("sort")) {
			return stationService.getAllStations(customQuery.get("param"), customQuery.get("sort"));
		}
		
		return stationService.getAllStations();
	}

	@RequestMapping(path = "/show/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> getStationById(@PathVariable int id ) {
		ResponseEntity<String> responseData = stationService.getStationById(id);
		return responseData;
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<String> addStation(@RequestBody ChargingStation station) {
		return stationService.addUpdateStation(station);
	}

	@RequestMapping(path = "/{id}/edit", method = RequestMethod.PUT)
	public ResponseEntity<String> editStation(@PathVariable int id, @RequestBody ChargingStation station) {
		station.setStation_id(id);
		return stationService.addUpdateStation(station);
	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteStation(@PathVariable int id) {
		return stationService.deleteStationById(id);
	}
	
	
	
	
	
	
}
