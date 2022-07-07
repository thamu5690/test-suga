package org.vtop.CourseRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.ProgramMigrationCourseModel;
import org.vtop.CourseRegistration.model.ProgramMigrationCoursePK;


@Repository
public interface ProgramMigrationCourseRepository extends JpaRepository<ProgramMigrationCourseModel,ProgramMigrationCoursePK> {


}
