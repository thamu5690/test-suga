package org.vtop.CourseRegistration.repository.estates;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.BuildingMaster;

@Repository
public interface BuildingMasterRepository extends JpaRepository<BuildingMaster,Long> {

	List<BuildingMaster> findAllByOrderByBuildingIdAsc();
	
	/*@Query("SELECT b.buildingId,b.description FROM BuildingMaster b WHERE b.buildingType.code=?1")
	List<BuildingMaster> findByBuildingType(String buildingType);*/

	@Query("SELECT b.buildingId,b.description ||' - '|| b.buildingBlock AS BuildingName FROM BuildingMaster b WHERE b.campusCode=?1 AND b.buildingType.code=?2")
	List<BuildingMaster> findByCampusCodeAndBuildingType(String campusCode,String buildingType);

	@Query("SELECT b.floorCount FROM BuildingMaster b WHERE b.buildingId=?1")
	Integer findFloorCount(Long buildingId);
	
	@Query("SELECT b FROM BuildingMaster b WHERE b.buildingBlock=?1")
	List<BuildingMaster> findByBuildingBlock(String buildingName);
	
	@Query("SELECT b FROM BuildingMaster b WHERE b.campusCode=?1")
	List<BuildingMaster> findByCampusCode(String campusCode);
	
	/*@Query("SELECT b FROM BuildingMaster b WHERE b.buildingId=?1")
	BuildingMaster findByBuildingId(Long buildingId);*/
	
	@Query("SELECT b FROM BuildingMaster b WHERE b.code=?1")
	BuildingMaster findByBuildingCode(String code);
	
	@Query("SELECT b FROM BuildingMaster b WHERE b.campusCode=?1 AND b.buildingType.code=?2 AND b.code=?3")
	BuildingMaster findByCampusCodeAndBuildingTypeAndCode(String Campus,String buildingType,String code);
	
	
}
