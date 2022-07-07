package org.vtop.CourseRegistration.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.TimeTablePatternDetailModel;
import org.vtop.CourseRegistration.model.TimeTablePatternDetailPK;


@Repository
public interface TimeTablePatternDetailRepository extends 
			JpaRepository<TimeTablePatternDetailModel,TimeTablePatternDetailPK> {

	List<TimeTablePatternDetailModel> findAll();  
	List<TimeTablePatternDetailModel> findByStatus(int status);
	 
	@Modifying
	@Query("update TimeTablePatternDetailModel a set a.status=2, a.logUserId=?3, a.logTimestamp=?4, "+
			"a.logIpaddress=?5 where a.ttpdPkId.patternId=?1 and a.ttpdPkId.session=?2 ")
	void updateTTPatternDetailByStatusLock(Integer patternId, String session, String userId, 
			Date timeStamp, String ipAddress);
	
	@Modifying
	@Query("update TimeTablePatternDetailModel a set a.status=0, a.logUserId=?3, a.logTimestamp=?4, "+
			"a.logIpaddress=?5 where a.ttpdPkId.patternId=?1 and a.ttpdPkId.session=?2")
	void updateTTPatternDetailByStatusOpen(Integer patternId, String session, String userId, 
			Date timeStamp, String ipAddress);

	/* TT Pattern Time Master */
	@Query("select a from TimeTablePatternDetailModel a where a.ttpdPkId.patternId=?1")
	List<TimeTablePatternDetailModel> findByPatternId1(Integer patternId);
		
	@Query(value="select greatest(a.NUM_TH_SLOTS,a.NUM_LAB_SLOTS) as num, a.TT_SESSION "+
					"from academics.time_table_pattern_detail a where a.PATTERN_ID=?1", 
					nativeQuery= true)
	List<Object[]> getMaxSlots(Integer patternId);

	/*@Query(value="select SLOT_ORDER, SLOT_SESSION, SLOT_SL_NO from ("+
					"(select 1 as SLOT_ORDER, 'FN' as SLOT_SESSION, level as slot_sl_no from "+
					"(select greatest(NUM_TH_SLOTS,NUM_LAB_SLOTS) as max_slot from "+
					"academics.time_table_pattern_detail where PATTERN_ID=?1 and TT_SESSION='FN') "+
					"CONNECT BY level<=max_slot) union all "+
					"(select 2 as SLOT_ORDER, 'LN' as SLOT_SESSION, 1 as slot_sl_no from dual) "+
					"union all "+
					"(select 3 as SLOT_ORDER, 'AN' as SLOT_SESSION, level as slot_sl_no from "+
					"(select greatest(NUM_TH_SLOTS,NUM_LAB_SLOTS) as max_slot from "+
					"academics.time_table_pattern_detail where PATTERN_ID=?1 and TT_SESSION='AN') "+ 
					"CONNECT BY level<=max_slot) union all "+
					"(select 4 as SLOT_ORDER, 'EN' as SLOT_SESSION, level as slot_sl_no from "+
					"(select greatest(NUM_TH_SLOTS,NUM_LAB_SLOTS) as max_slot from "+
					"academics.time_table_pattern_detail where PATTERN_ID=?1 and TT_SESSION='EN') "+
					"CONNECT BY level<=max_slot)) "+
					"order by SLOT_ORDER, SLOT_SL_NO", nativeQuery= true)*/
	@Query(value="select decode(TT_SESSION,'FN',1,'LN',2,'AN',3,'EN',4,5) as tt_session_order, TT_SESSION, "+
					"NUM_TH_SLOTS, NUM_LAB_SLOTS, MAX_SLOT from "+
					"((select TT_SESSION, NUM_TH_SLOTS, NUM_LAB_SLOTS, greatest(NUM_TH_SLOTS, NUM_LAB_SLOTS) "+
					"as max_slot from academics.time_table_pattern_detail where PATTERN_ID=?1) "+
					"union all "+
					"(select 'LN' as TT_SESSION, 0 as NUM_TH_SLOTS, 0 as NUM_LAB_SLOTS, 0 as max_slot from dual)) "+
					"order by tt_session_order", nativeQuery= true)
	List<Object[]> findSessionSlotByPatternId(Integer patternId);
}
