package veridic.hire.Services;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import veridic.hire.Models.ChargingStation;


@Service
public class UtilityService {
	
	public ChargingStation unproxy(ChargingStation proxied){
		ChargingStation entity = proxied;
	    if (entity instanceof HibernateProxy) {
	        Hibernate.initialize(entity);
	        entity = (ChargingStation) ((HibernateProxy) entity)
	                  .getHibernateLazyInitializer()
	                  .getImplementation();
	    }
	    return entity;
	}
	
	public ResponseEntity<String> getMessage(String requestData) {
		org.springframework.http.HttpHeaders responseHeaders = new org.springframework.http.HttpHeaders();
		responseHeaders.add("Content-Type", "application/json;charset=utf-8");
		ResponseEntity<String> response =  new ResponseEntity<String>(requestData, responseHeaders, org.springframework.http.HttpStatus.OK);
		return response;
	}
}
