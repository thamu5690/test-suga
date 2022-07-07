package org.vtop.CourseRegistration.repository.estates;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.RoomMaster;
import org.vtop.CourseRegistration.model.RoomMasterPK;

@Repository
public interface RoomMasterRepository extends JpaRepository<RoomMaster, RoomMasterPK> {
	
	public List<RoomMaster> findAll();
	
	@Query("SELECT DISTINCT(r.roomCapacity),r.roomCapacity ||' Bed' FROM RoomMaster r WHERE r.id.buildingMasterBuildingId=?1")
	List<Object[]> findAllRoomCapacity(Long buildingId);
	
	/*@Query("SELECT b FROM RoomMaster b WHERE b.buildingMaster.buildingId=?1")
	public List<RoomMaster> findByBuildingMaster(Long buildingId);*/
	
	@Query("SELECT b FROM RoomMaster b WHERE b.id.buildingMasterBuildingId=?1 AND b.id.roomNumber=?2")
	public RoomMaster findRoomCapacity(Long buildingId,String roomNo);
	
	@Query("SELECT b FROM RoomMaster b WHERE b.buildingMaster.buildingId=?1")	
	public List<RoomMaster> findRoomsByBlockId(String buildingId);
		
}
