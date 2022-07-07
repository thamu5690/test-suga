package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.SemesterDetailsModel;

@Repository
public interface SemesterDetailsRepository extends JpaRepository<SemesterDetailsModel, String> {

  List<SemesterDetailsModel> findAll();  
  List<SemesterDetailsModel> findByStatus(Integer status);
  List<SemesterDetailsModel> findAllByOrderBySemesterSubIdAsc();
  List<SemesterDetailsModel> findAllByOrderBySemesterSubIdDesc();
  
  @Query(value="select SEMESTER_MASTER_SEMESTER_ID, DESCRIPTION, DESCRIPTION_SHORT, TMTBLPATTERN_MASTER_PATTERN_ID, "+
				"FEEDBACK_PATTERN_NUMBER, SEMESTER_YEAR, GRADUATE_YEAR from ACADEMICS.SEMESTER_DETAILS "+
				"where SEMESTER_SUB_ID=?1", nativeQuery=true)
  List<Object[]> findSemesterDetailBySemSubId(String semesterSubId);
}