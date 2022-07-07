package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.PatternTimeMasterModel;
import org.vtop.CourseRegistration.model.PatternTimeMasterPK;


@Repository
public interface PatternTimeMasterRepository extends JpaRepository<PatternTimeMasterModel,PatternTimeMasterPK> {	
	
	List<PatternTimeMasterModel> findAll();	
	
	@Modifying
	@Query("delete from PatternTimeMasterModel a where a.ptmPkId.timeTablePatternModel=?1")
	void deleteByPatternIdSlot(Integer patternId);
	
	@Query("select a from PatternTimeMasterModel a order by a.ptmPkId.timeTablePatternModel")
	Page<PatternTimeMasterModel> findAll(Pageable pageable);
	
	
	@Query("select a from PatternTimeMasterModel a where a.ptmPkId.timeTablePatternModel=?1")
	Page<PatternTimeMasterModel> findByPatternTimeMasterPagination(Integer searchval, Pageable pageable);
	
	@Query("select a from PatternTimeMasterModel a where a.ptmPkId.timeTablePatternModel=?1 ORDER BY a.StartingTime ASC")
	List<PatternTimeMasterModel> findAllOrderByDateAsc(Integer patternId);
	
	
	@Query("select a from PatternTimeMasterModel a where a.ptmPkId.timeTablePatternModel=?1 and a.ptmPkId.slotType ='THEORY'  ORDER BY a.StartingTime ASC")
	List<PatternTimeMasterModel> findTheoryOnly(Integer patternId);
	
	@Query("select a from PatternTimeMasterModel a where a.ptmPkId.timeTablePatternModel=?1 and a.ptmPkId.slotType ='LAB'  ORDER BY a.StartingTime ASC")
	List<PatternTimeMasterModel> findLabOnly(Integer patternId);
	
	@Query("select a.StartingTime, a.EndingTime, a.ptmPkId.slotName from PatternTimeMasterModel a "+
			"where a.ptmPkId.timeTablePatternModel=?1")
	List<Object[]> findByPatternId2(Integer patternId);
	
	@Query("select substring(a.ptmPkId.slotName,1, 2) from PatternTimeMasterModel a "
			+ "	where  a.ptmPkId.timeTablePatternModel=?1 group by substring(a.ptmPkId.slotName,1, 2) ")
	List<String> getSlotSessions(Integer patternId);
	
	/*@Query("select substring(a.ptmPkId.slotName,1, 2), to_char(StartingTime,'HH24:MI'), timeTablePatternModel  from PatternTimeMasterModel a "
			+ "	where  a.ptmPkId.timeTablePatternModel=?1 and slot_type = 'THEORY' order by starting_time,substring(a.ptmPkId.slotName,1, 2) ")
	List<String> getSlotSessions(Integer patternId);*/
	
	/*@Query(value="select SLOT_TYPE, SLOT_NAME, SLOT_SESSION, row_number() OVER (order by slot_order, "+
					"STARTING_TIME) as session_order, start_time, end_time, slot_order, STARTING_TIME from "+
					"(select SLOT_TYPE, SLOT_NAME, substr(SLOT_NAME,1,2) as SLOT_SESSION, "+
					"to_char(STARTING_TIME,'HH24:MI') as start_time, to_char(ENDING_TIME,'HH24:MI') as "+
					"end_time, decode(SLOT_TYPE,'THEORY',1,'LAB',2) as slot_order, STARTING_TIME from "+
					"ACADEMICS.TT_PATTERN_TIME_MASTER where TMTBLPATTERN_MASTER_PATTERN_ID=?1) "+
					"order by slot_order, STARTING_TIME", nativeQuery= true)*/
	@Query(value="select SLOT_TYPE, SLOT_NAME, SLOT_SESSION, SLOT_SL_NO, start_time, end_time, slot_order, "+
					"STARTING_TIME from (select SLOT_TYPE, SLOT_NAME, substr(SLOT_NAME,1,2) as SLOT_SESSION, "+
					"substr(SLOT_NAME,6,1) as SLOT_SL_NO, to_char(STARTING_TIME,'HH24:MI') as start_time, "+
					"to_char(ENDING_TIME,'HH24:MI') as end_time, decode(SLOT_TYPE,'THEORY',1,'LAB',2) as "+
					"slot_order, STARTING_TIME from ACADEMICS.TT_PATTERN_TIME_MASTER where "+
					"TMTBLPATTERN_MASTER_PATTERN_ID=?1) order by slot_order, STARTING_TIME", nativeQuery= true)
	List<Object[]> findSlotDetailByPatternId(Integer patternId);

}