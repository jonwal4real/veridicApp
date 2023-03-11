package veridic.hire.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import veridic.hire.Models.ChargingStation;
import veridic.hire.Repository.StationRepository;


@Service
public class StationService {
	
	@Autowired
	StationRepository stationRepo;
	
	@Autowired
	UtilityService utilService;
	
	public ResponseEntity<String> getAllStations() {

		Map<String,Object> responseData = new HashMap<String,Object>();
		List<ChargingStation> stationList = new ArrayList<ChargingStation>();
		stationList = stationRepo.findAll();
		responseData . put("Data",stationList);

		responseData.put("Status", "Successful");
		responseData.put("Status msg", "All records fetched");
		return utilService.getMessage(new Gson().toJson(responseData));
	}
	
	
	
	public ResponseEntity<String> getStationById(int id ){
		Map<String,Object> responseData = new HashMap<String,Object>();
		try {
			ChargingStation station = stationRepo.getOne(id);
			responseData . put("Data",utilService.unproxy(station));

			responseData.put("Status", "Successful");
			responseData.put("Status msg", "Record found with station_id: "+id);
		}catch(EntityNotFoundException e) {
			responseData.put("Status", "Unsuccessful");
			responseData.put("Status msg", "No record found with station_id: "+id);
		}
		return utilService.getMessage(new Gson().toJson(responseData));
	}
	
	
	public ResponseEntity<String> addUpdateStation(ChargingStation station){
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			station = stationRepo.save(station);
			if(station.getStation_id()>0) {
				response.put("Status", "Success");
				response.put("Status msg", "Station list updated successfully");
				response.put("Data", station);
			}else {
				response.put("Status", "Unsuccessful");
				response.put("Status msg", "Unable to update stationList");
			}
		}catch(IllegalArgumentException e) {
			response.put("Status", "Unsuccessful");
			response.put("Status msg", "Unable to update stationList");
		}
		String responseData = new Gson().toJson(response);
		return utilService.getMessage(responseData);
	}
	
	public ResponseEntity<String> deleteStationById(int id){
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			stationRepo.deleteById(id);
			response.put("Status", "Success");
			response.put("Status msg", "Station with station_id "+id+" deleted succesfully");
		}catch(IllegalArgumentException e) {
			response.put("Status", "Unsuccessfull");
			response.put("Status msg", "Unable to delete the station with station_id: "+id);
		}catch(EmptyResultDataAccessException e) {
			response.put("Status", "Unsuccessfull");
			response.put("Status msg", "No station exists with station_id: "+id);
		}
		String responseData = new Gson().toJson(response);
		return utilService.getMessage(responseData);
	}



	public ResponseEntity<String> getAllStations(int limit) {
		Map<String,Object> responseData = new HashMap<>();
		List<ChargingStation> stationList = new ArrayList<ChargingStation>();
		stationList = stationRepo.findAll();
		if(limit<stationList.size())
			stationList = stationList.subList(0, limit);
		responseData.put("Status", "Success");
		responseData.put("Status msg", "Records fetched successfully");
		responseData.put("Data", stationList);
		String response = new Gson().toJson(responseData);
		return utilService.getMessage(response);
	}
	
	public ResponseEntity<String> getAllStations(String columnName, String Order) {
		List<ChargingStation> stationList = new ArrayList<ChargingStation>();
		stationList = stationRepo.findAll();
		Collections.sort(stationList,new StationCompare(columnName,Order));
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("Status", "Success");
		responseData.put("Status msg", "Records fetched successfully");
		responseData.put("Data", stationList);
		String response = new Gson().toJson(responseData);
		return utilService.getMessage(response);
	}
	
	
}
