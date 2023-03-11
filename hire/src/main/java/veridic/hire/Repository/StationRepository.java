package veridic.hire.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import veridic.hire.Models.ChargingStation;


@Repository
public interface StationRepository extends JpaRepository<ChargingStation, Integer> {

}
