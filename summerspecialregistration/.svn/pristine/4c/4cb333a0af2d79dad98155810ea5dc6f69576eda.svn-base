package org.vtop.CourseRegistration.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.SlotTimeMasterModel;
import org.vtop.CourseRegistration.model.SlotTimeMasterPK;

@Repository
public interface SlotTimeMasterRepository extends JpaRepository<SlotTimeMasterModel,SlotTimeMasterPK> {
	
	List<SlotTimeMasterModel> findByStmPkIdSlotAndStmPkIdWeekdaysAndStmPkIdSlotStartingTimeAndStmPkIdSlotEndingTimeAndStmPkIdPatternId(
								String slot, String weekDays,Date startingTime,Date endingTime, Integer patternId);
	
	List<SlotTimeMasterModel> findAll();
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.slot=?2")
	List<SlotTimeMasterModel> findByPatternIdSlot(Integer patternId, String slot);
	
	@Query("select a.stmPkId.weekdays, a.stmPkId.slotStartingTime, a.stmPkId.slotEndingTime from SlotTimeMasterModel a "+
			"where a.stmPkId.patternId=?1 and a.stmPkId.slot=?2")
	List<Object[]> findByPatternIdSlot2(Integer patternId, String slot);
	
	@Modifying
	@Query("delete from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.slot=?2")
	void deleteByPatternIdSlot(Integer patternId, String slot);
	
	@Query("select a from SlotTimeMasterModel a order by a.stmPkId.patternId")
	Page<SlotTimeMasterModel> findAll(Pageable pageable);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 ")
	Page<SlotTimeMasterModel> findBySlotTimeMasterPagination(Integer searchval, Pageable pageable);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findAllOrderByDateAsc(Integer patternId);
	
	
	/* Time Table Details*/	
	@Query(value="select slot from "+
					"(select distinct slot as slot, (regexp_replace(slot,'[^[:alpha:]]')"+ 
					"|| LPAD(TO_NUMBER(regexp_replace(slot,'[^[:digit:]]')),4,'0')) as slotOrder from "+
					"SLOT_TIME_MASTER where TMTBLPATTERN_MASTER_PATTERN_ID=?1 and SLOT_TYPE=?2 and "+
					"TT_SESSION=?3 and NUMBER_OF_UNITS='1') order by slotOrder", nativeQuery=true)
	List<String> findByPatternIdSlotType(Integer patternId, String slotType, String session);

	@Query("select nvl(count(a.stmPkId.slot),0) as slotCount from SlotTimeMasterModel a where "+
			"a.stmPkId.patternId=?1 and a.stmPkId.slot=?2")
	Integer findPatternIdSlotCount(Integer patternId, String slotName);
	
	@Query("select distinct a.stmPkId.slot from SlotTimeMasterModel a where a.stmPkId.patternId=?1 "+
			"and a.stmPkId.weekdays=?2 and to_char(a.stmPkId.slotStartingTime,'HH24:MI:SS')=?3 and "+
			"to_char(a.stmPkId.slotEndingTime,'HH24:MI:SS')=?4")
	List<String> findByClash(Integer patternId, String weekdays, String slotStartingTime, String slotEndingTime);
	
	@Query("select distinct a.stmPkId.slot from SlotTimeMasterModel a where a.stmPkId.patternId=?1 "+
			"and a.stmPkId.weekdays=?2 and numOfUnits='1' and ((to_number(to_char(a.stmPkId.slotStartingTime,'HH24MISS')) "+
			"between ?3 and ?4) or (to_number(to_char(a.stmPkId.slotEndingTime,'HH24MISS')) between ?3 and ?4))")
	List<String> findByClashValue(Integer patternId, String weekdays, Long slotStartingTime, Long slotEndingTime);
	
	/* Course Page */	
	@Query("select distinct a.stmPkId.weekdays from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.slot=?2")
	List<String> findByWeekdays(Integer patternId, String slotName);
	
	/* Time Table Preview */
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='MON' and "+
			"(a.SlotType='THEORY' or a.SlotType ='EX_TH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findMonTheoryOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='MON' and "+
			"(a.SlotType='LAB' or a.SlotType ='EX_LH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findMonLabOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='TUE' and "+
			"(a.SlotType='THEORY' or a.SlotType ='EX_TH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findTueTheoryOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='TUE' and "+
			"(a.SlotType='LAB' or a.SlotType='EX_LH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findTueLabOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='WED' and "+
			"(a.SlotType='THEORY' or a.SlotType='EX_TH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findWedTheoryOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='WED' and "+
			"(a.SlotType='LAB' or a.SlotType='EX_LH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findWedLabOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='THU' and "+
			"(a.SlotType='THEORY' or a.SlotType='EX_TH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findThuTheoryOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='THU' and "+
			"(a.SlotType='LAB' or a.SlotType='EX_LH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findThuLabOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='FRI' and "+
			"(a.SlotType='THEORY' or a.SlotType='EX_TH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findFriTheoryOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='FRI' and "+
			"(a.SlotType='LAB' or a.SlotType='EX_LH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findFriLabOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='SAT' and "+
			"(a.SlotType='THEORY' or a.SlotType='EX_TH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findSatTheoryOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='SAT' and "+
			"(a.SlotType='LAB' or a.SlotType='EX_LH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findSatLabOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='SUN' and "+
			"(a.SlotType='THEORY' or a.SlotType='EX_TH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findSunTheoryOnly(Integer patternId);
	
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and a.stmPkId.weekdays='SUN' and "+
			"(a.SlotType='LAB' or a.SlotType='EX_LH') and lockStatus='0' ORDER BY a.stmPkId.slotStartingTime ASC")
	List<SlotTimeMasterModel> findSunLabOnly(Integer patternId);
	
	//View Course Allocation
	@Query("select distinct a.stmPkId.slot from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and "+
			"a.stmPkId.slot<>'-' order by a.stmPkId.slot")
	List<String>findDistinctSlot(Integer patternId);  
	  
	@Query("select distinct a.stmPkId.slot from SlotTimeMasterModel a where a.stmPkId.patternId=?1 and "+
			"a.SlotType=?2 and a.stmPkId.slot<>'-' order by a.stmPkId.slot")
	List<String>findSlotNames(Integer patternId, String slotType);
	
	@Query(value="select decode(WEEK_DAY,'MON',1,'TUE',2,'WED',3,'THU',4,'FRI',5,'SAT',6,'SUN',7) as "+
					"week_day_order, WEEK_DAY from (select distinct WEEK_DAY as WEEK_DAY from "+
					"ACADEMICS.SLOT_TIME_MASTER where TMTBLPATTERN_MASTER_PATTERN_ID=?1 and LOCK_STATUS=0) "+
					"order by week_day_order", nativeQuery=true)
	List<Object[]> findWeekDayList(Integer patternId);
	
	@Query(value="select a.SLOT_TYPE, a.WEEK_DAY, a.TT_SESSION, nvl(b.MAX_SLOT,0) as max_slot, "+
					"a.SLOT_COUNT, decode(a.SLOT_TYPE,'THEORY',1,'LUNCH',2,'LAB',3,4) as slot_order, "+
					"decode(a.WEEK_DAY,'MON',1,'TUE',2,'WED',3,'THU',4,'FRI',5,'SAT',6,'SUN',7) "+
					"as week_day_order, decode(a.TT_SESSION,'FN',1,'LN',2,'AN',3,'EN',4,5) as "+
					"session_order from ("+
					"(select SLOT_TYPE, WEEK_DAY, TT_SESSION, count(SLOT) as slot_count from "+
					"(select decode(SLOT_TYPE,'EX_TH','THEORY',SLOT_TYPE) as SLOT_TYPE, WEEK_DAY, "+
					"TT_SESSION, SLOT from ACADEMICS.SLOT_TIME_MASTER where TMTBLPATTERN_MASTER_PATTERN_ID=?1 "+
					"and LOCK_STATUS=0) group by SLOT_TYPE, WEEK_DAY, TT_SESSION) "+
					"union all "+
					"(select distinct 'THEORY' as SLOT_TYPE, WEEK_DAY, 'LN' as TT_SESSION, 0 as "+
					"slot_count from ACADEMICS.SLOT_TIME_MASTER where TMTBLPATTERN_MASTER_PATTERN_ID=?1 "+
					"and LOCK_STATUS=0) "+
					"union all "+
					"(select distinct 'LAB' as SLOT_TYPE, WEEK_DAY, 'LN' as TT_SESSION, 0 as "+
					"slot_count from ACADEMICS.SLOT_TIME_MASTER where TMTBLPATTERN_MASTER_PATTERN_ID=?1 and "+
					"LOCK_STATUS=0)) a left outer join (select TT_SESSION, greatest(NUM_TH_SLOTS, NUM_LAB_SLOTS) "+
					"as MAX_SLOT from academics.time_table_pattern_detail where PATTERN_ID=?1) b "+
					"on (b.TT_SESSION=a.TT_SESSION) order by week_day_order, slot_order, session_order", 
					nativeQuery=true)
	List<Object[]> findWeekDaySessionList(Integer patternId);
	  
	/*@Query(value="select SLOT_TYPE, WEEK_DAY, TT_SESSION, SLOT, decode(SLOT_TYPE,'THEORY',1,'LAB',2,3) "+
					"as slot_order, decode(WEEK_DAY,'MON',1,'TUE',2,'WED',3,'THU',4,'FRI',5,'SAT',6,'SUN',7,8) "+
					"as week_day_order, SLOT_STARTING_TIME from ACADEMICS.SLOT_TIME_MASTER where "+
					"TMTBLPATTERN_MASTER_PATTERN_ID=?1 and LOCK_STATUS=0 and SLOT_TYPE not in ('EX_TH') "+
					"order by slot_order, week_day_order, SLOT_STARTING_TIME", nativeQuery=true)*/
	@Query(value="select SLOT_TYPE, WEEK_DAY, TT_SESSION, SLOT, WEEK_DAY_ORDER, SLOT_STARTING_TIME, "+
					"decode(SLOT_TYPE,'THEORY',1,'LAB',2,3) as slot_order from "+
					"(select decode(SLOT_TYPE,'EX_TH','THEORY',SLOT_TYPE) as SLOT_TYPE, WEEK_DAY, TT_SESSION, "+
					"SLOT, decode(WEEK_DAY,'MON',1,'TUE',2,'WED',3,'THU',4,'FRI',5,'SAT',6,'SUN',7) as "+
					"week_day_order, SLOT_STARTING_TIME from ACADEMICS.SLOT_TIME_MASTER where "+
					"TMTBLPATTERN_MASTER_PATTERN_ID=?1 and LOCK_STATUS=0) "+
					"order by slot_order, WEEK_DAY_ORDER, SLOT_STARTING_TIME", nativeQuery=true)
	List<Object[]> findSlotTimeDetailByPatternId(Integer patternId);
}
