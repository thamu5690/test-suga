package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.AdditionalLearningDetailsModel;

@Repository
public interface AdditionalLearningDetailsRepository extends JpaRepository<AdditionalLearningDetailsModel, String>
{	
	@Query("select a from AdditionalLearningDetailsModel a where a.learningType=?1 and "+
			"a.learningType not in ('HON') and a.status=0 order by a.description")
	List<AdditionalLearningDetailsModel> findByLearningType(String learnType);
	
	@Query("select a from AdditionalLearningDetailsModel a where a.learningType='HON' and "+
			"a.description like ?1 and a.status=0 order by a.code")
	List<AdditionalLearningDetailsModel> findAdditionalLearningHonours(String progCode);
}
